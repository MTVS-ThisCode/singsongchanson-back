package com.singsongchanson.global.security.jwt.command.application.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class CustomTokenService {

    @Value("${app.Jwt.secretKey}")
    private String secretKey;

    @Value("${app.Jwt.tokenExpirationMsec}")
    private int tokenExpirationMsec;

    public String createToken(Long userNo, String userRole) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + tokenExpirationMsec);

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setSubject(Long.toString(userNo))
                .claim("role", userRole)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // token 암호화를 해독해서 userId를 가져오는 메소드
    public Long getUserNoFromToken(String token) {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
    // token 유효성 검사
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException | MalformedJwtException ex) {
            System.out.println("잘못된 JWT 서명");
            throw new SecurityException("잘못된 JWT 서명", ex.getCause());
        } catch (ExpiredJwtException ex) {
            throw new SecurityException("토큰 기한 만료 (유효 시간 : " + ex.getClaims().getExpiration() + ")", ex.getCause());
        } catch (UnsupportedJwtException ex) {
            System.out.println("지원되지 않는 JWT 토큰");
            throw new SecurityException("지원되지 않는 JWT 토큰", ex.getCause());
        } catch (IllegalArgumentException ex) {
            System.out.println("잘못된 JWT 토큰");
            throw new SecurityException("잘못된 JWT 토큰", ex.getCause());
        }
    }


}
