package com.joey.ars.accomodationreservationservice.jwt;

import com.joey.ars.accomodationreservationservice.entity.Member;
import com.joey.ars.accomodationreservationservice.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {
    private static final String NO_CHECK_URL = "/login";

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response); // "/login" 요청이 들어오면, 다음 필터 호출
            return; // return으로 이후 현재 필터 진행 막기 (안해주면 아래로 내려가서 계속 필터 진행시킴)
        }

        String refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService :: isTokenValid)
                .orElse(null);  // -> RefreshToken이 없거나 유효하지 않다면(DB에 저장된 RefreshToken과 다르다면) null을 반환

        if (refreshToken != null) {
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return;
        }

        if (refreshToken == null) {
            checkAccessTokenAndAuthentication(request, response, filterChain);
        }
    }

    /* 리프레시 토큰 검증 후 엑세스 토큰 재생성 및 헤더에 심어 전송 */
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        memberRepository.findByRefreshToken(refreshToken)
                .ifPresent(member -> {
                    String reIssuedRefreshToken = reIssueRefreshToken(member);
                    jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(member.getEmail()), reIssuedRefreshToken);
                });
    }

    /* RefreshToken 재발급, DB에 업데이트 */
    private String reIssueRefreshToken(Member member) {
        String reIssuedRefreshToken = jwtService.createRefreshToken();
        member.updateRefreshToken(reIssuedRefreshToken);
        memberRepository.saveAndFlush(member);
        return reIssuedRefreshToken;
    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("checkAccessTokenAndAuthentication() 호출");
        jwtService.extractAccessToken(request)
                .filter(jwtService :: isTokenValid)
                .ifPresent(accessToken -> jwtService.extractEmail(accessToken)
                        .ifPresent(email -> memberRepository.findByEmail(email)
                                .ifPresent(this :: saveAuthentication)));
        filterChain.doFilter(request, response);
    }

    /* 인증 허가 */
    public void saveAuthentication(Member myMember) {
        UserDetails userDetailsMember = org.springframework.security.core.userdetails.User.builder()
                .username(myMember.getEmail())
                .password(myMember.getPassword())
                .roles(myMember.getRank().name())
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsMember, null, authoritiesMapper.mapAuthorities(userDetailsMember.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
