package com.ryan.helpdesk.config;


import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import java.util.Date;


@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration( new Date(System.currentTimeMillis() + expiration) )
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

}
