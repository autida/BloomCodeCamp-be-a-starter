package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.dtos.RegisterRequest;
import com.hcc.entities.User;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private jwtUtil jwtTokenUtil;



    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthCredentialRequest authCredentialRequest)
        throws Exception{
        HashMap<String, String> response = new HashMap<>();
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authCredentialRequest.getUsername(),
                            authCredentialRequest.getPassword()
                    )
            );
                    String token = jwtTokenUtil.generateToken((User) auth.getPrincipal());
                    response.put("accessKey", token);
                    response.put("roles", "2001");
                    return ResponseEntity.ok().header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    ).body(response);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }

    @PostMapping("register")
    public User createUser(@RequestBody RegisterRequest registerRequest) {
            User newUser = new User(registerRequest.getCohortStartDate(),
                     registerRequest.getUsername(),
                    registerRequest.getPassword());
            return userDetailService.saveUser(newUser);
    }
}
