package com.met.it355pz.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String getUsernameFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key).compact();
    }

    public Date getExpirationDate(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public boolean validateToken(String jwtToken) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return true;
        } catch (JwtException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
