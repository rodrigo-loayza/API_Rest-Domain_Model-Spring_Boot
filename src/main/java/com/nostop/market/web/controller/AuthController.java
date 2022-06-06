package com.nostop.market.web.controller;

import com.nostop.market.domain.dto.AuthenticationRequest;
import com.nostop.market.domain.dto.AuthenticationResponse;
import com.nostop.market.domain.service.NostopUserDetailsService;
import com.nostop.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired // gestor de autenticacion de spring para validar user y pwd
    private AuthenticationManager authenticationManager;

    @Autowired
    private NostopUserDetailsService nostopUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = nostopUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
