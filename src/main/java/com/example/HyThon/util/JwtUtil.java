package com.example.HyThon.util;

import com.example.HyThon.auth.handler.JwtExceptionHandler;
import com.example.HyThon.service.MemberDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil implements InitializingBean {

    @Value("${spring.jwt.secret}")
    private String secret;
    private static SecretKey secretKey;
    private final MemberDetailService memberDetailService;

    @Override
    public void afterPropertiesSet() throws Exception {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new JwtExceptionHandler("잘못된 JWT 서명입니다.", e);
        } catch (ExpiredJwtException e) {
            throw new JwtExceptionHandler("만료된 JWT 토큰입니다.", e);
        } catch (UnsupportedJwtException e) {
            throw new JwtExceptionHandler("지원되지 않는 JWT 토큰입니다.", e);
        } catch (IllegalArgumentException e) {
            throw new JwtExceptionHandler("JWT 토큰이 잘못되었습니다.", e);
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = memberDetailService.loadUserByUsername(this.getName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public static String getName(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("memberName", String.class);
    }

    public String createAccessToken(Long id, String name) {
        return Jwts.builder()
                .header()
                .add("typ","JWT")
                .and()
                .claim("memberId", id)
                .claim("memberName", name)
                .signWith(secretKey)
                .compact();
    }
}
