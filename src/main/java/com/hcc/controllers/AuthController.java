package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
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

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authCredentialRequest.getUsername(),
                            authCredentialRequest.getPassword()
                    )
            );
                    String token = jwtTokenUtil.generateToken((User) auth.getPrincipal());
                    return ResponseEntity.ok().header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    ).body("hey");

//            return ResponseEntity.ok().body("testtestsetst");
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }
    @GetMapping("test")
    public String test() {
        return "test";
    }


}
