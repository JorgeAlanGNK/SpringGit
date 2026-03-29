package com.jorge_alan.spring_git_mvc.apis.Services;

import java.security.Key;
import java.security.PrivateKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final PrivateKey key;

    public JwtService(PrivateKey key) {
        this.key = key;
    }

    public String generarToken() {
        return Jwts.builder()
            .signWith(key)
            .compact();
    }
    
}
