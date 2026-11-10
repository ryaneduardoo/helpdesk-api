package com.ryan.helpdesk.controller;

import com.ryan.helpdesk.config.JwtService;
import com.ryan.helpdesk.dto.CredenciaisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody CredenciaisDTO creds){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha());
        Authentication auth = authenticationManager.authenticate(token);
        String tokenJwt = jwtService.generateToken(creds.getEmail());
        return ResponseEntity.ok().header("Authorization", "Bearer " + tokenJwt).build();
    }
}
