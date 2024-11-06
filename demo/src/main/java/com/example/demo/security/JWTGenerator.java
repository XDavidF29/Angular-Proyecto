package com.example.demo.security;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTGenerator {

     public static final Long EXPIRATION_TIME = 864000000L;
     private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); 
    
    public String generateToken(Authentication authentication) {   
        
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + EXPIRATION_TIME);

        String roles = authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));

        String token = Jwts.builder()
        .setSubject(username)
        .claim("role", roles) 
        .setIssuedAt(currentDate)
        .setExpiration(expireDate)
        .signWith(KEY, SignatureAlgorithm.HS512)
        .compact();

        return token;    
    }  

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
