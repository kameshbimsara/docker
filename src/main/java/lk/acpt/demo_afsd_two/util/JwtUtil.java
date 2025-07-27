package lk.acpt.demo_afsd_two.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration; // in milliseconds

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        String jwtToken = authToken.substring("Bearer ".length());
        try {
            Jwts.parser().setSigningKey(getSigningKey()).build().parse(jwtToken);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT token: {}" + e.getMessage());
        }
        return false;
    }
}
