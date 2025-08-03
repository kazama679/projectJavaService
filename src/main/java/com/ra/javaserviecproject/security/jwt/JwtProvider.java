package com.ra.javaserviecproject.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exprired}")
    private Integer expiration;

    @Value("${jwt.expriredRefesh}")
    private Integer expriredRefresh;

    public String generateToken(String username) {
        Date date = new Date(new Date().getTime() + expiration);
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Date date = new Date(new Date().getTime() + expriredRefresh);
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }

    public boolean validateToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token) != null;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
