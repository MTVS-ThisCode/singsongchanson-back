package com.singsongchanson.global.security.jwt.filter;

import com.singsongchanson.global.security.jwt.command.application.service.CustomTokenService;
import com.singsongchanson.global.security.oauth2.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailService customUserDetailService;
    private final CustomTokenService customTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);

        if (StringUtils.hasText(jwt) && customTokenService.validateToken(jwt)) {        // 유효성 검증 하고
            Long userNo = customTokenService.getUserNoFromToken(jwt);         // 토큰을 가지고 있는 유저의 아이디를 가져옴
            System.out.println("userNo = " + userNo);
            UserDetails userDetails = customUserDetailService.loadUserById(userNo);     // 유저 세부 정보에서 유저와 일치하는지 확인
            System.out.println("userDetails = " + userDetails);
            // security context holder에서 따로 관리하는 토큰을 새로 발급해서 유저 인증 정보와(userprincipal), 비밀번호(credentials), 권한(getAuthorities)을 가져옴
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 그 토큰에 요청 정보를 저장하고
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // security context holder에 유저 인증 정보와(userprincipal), 비밀번호(credentials), 권한(getAuthorities) = authentication 를 저장하고
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 다음 필터로 요청을 보냄
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");    // 헤더에 토큰 가져오고
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { // 다음 Bearer 가져오고
            return bearerToken.substring(7);
        } else {
            throw new NullPointerException("JWT 토큰이 존재하지 않습니다.");
        }
    }
}
