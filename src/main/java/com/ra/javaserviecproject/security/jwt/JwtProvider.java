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
    private long expiration;

    @Value("${jwt.expriredRefesh}")
    private long expriredRefresh;

    public String generateToken(String username) {
        Date date = new Date();
        return Jwts
                .builder()
                .setIssuedAt(date)
                .setSubject(username)
                .setExpiration(new Date(date.getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Date date = new Date();
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + expriredRefresh))
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
