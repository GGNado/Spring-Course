package com.giggi.firstproject.controller;

import com.giggi.firstproject.dto.LoginRequest;
import com.giggi.firstproject.dto.LoginResponse;
import com.giggi.firstproject.entity.LoginRegistry;
import com.giggi.firstproject.security.jwt.JwtUtils;
import com.giggi.firstproject.service.def.LoginRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {


    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private LoginRegistryService loginRegistryService;

    @Autowired
    public AuthController(JwtUtils jwtUtils, AuthenticationManager authenticationManager, LoginRegistryService loginRegistryService) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.loginRegistryService = loginRegistryService;
    }



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse loginResponse = new LoginResponse(jwtToken, userDetails.getUsername(), roles, jwtUtils.getJwtExpirationMs());
        LoginRegistry loginRegistry = new LoginRegistry(0, userDetails.getUsername(), LocalDate.now().toString(), LocalTime.now().toString(), "12.12.12.12", true);


        loginRegistryService.registerLogin(loginRegistry);
        return ResponseEntity.ok(loginResponse);
    }
}
