package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.dtos.RegisterRequest;
import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.services.AuthorityService;
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

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private AuthorityService authorityService;

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
                    User user = (User) auth.getPrincipal();
                    String token = jwtTokenUtil.generateToken(user);

                    response.put("accessKey", token);
                    String userRole = auth.getAuthorities().stream().findFirst().get().getAuthority();
                    response.put("roles", userRole);
                    return ResponseEntity.ok().header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    ).body(response);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }

    @PostMapping("register")
    public String createUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        if(registerRequest.getRole() == null)
            return ("Role Required");
        try {
            User newUser = new User(
                    LocalDate.now(),
                    registerRequest.getUsername(),
                    registerRequest.getPassword());
            Authority authority = new Authority(registerRequest.getRole());
            authority.setUser(newUser);
            newUser.setAuthorities(Collections.singletonList(authority));
            userDetailService.saveUser(newUser);
            return "Successfully Registered.";
        } catch (Exception e) {
            return "Registration Failed.";
        }

    }
}
