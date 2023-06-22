package com.hcc.controllers;

import com.hcc.dtos.AuthCredentialRequest;
import com.hcc.entities.User;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private jwtUtil jwtTokenUtil;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthCredentialRequest authCredentialRequest)
        throws Exception{
        System.out.println(authCredentialRequest.getUsername() + " " + authCredentialRequest.getPassword());
//
        //usernamePasswordAuthentication
        //create an auth variable to store the user and password authentication

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authCredentialRequest.getUsername(),
                            authCredentialRequest.getPassword()
                    )
            );

            User user = (User) auth.getPrincipal();
            System.out.println(user.getUsername());
            user.setPassword(null);
            String token = jwtTokenUtil.generateToken(user);
//            String token = "test";
//            System.out.println(token);
            return ResponseEntity.ok().header(
                    HttpHeaders.AUTHORIZATION,
                    token
            ).body("user.getUsername()");
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
//

//        return authCredentialRequest.getUsername();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


}
