package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserEntityService userService;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserEntityService userService, AuthenticationManager authManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/auth/register")
    public UserEntity save(@RequestBody UserRegisterDTO userDTO){
        return this.userService.save(userDTO);
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginDTO){
        Authentication authDTO = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

        Authentication authentication = this.authManager.authenticate(authDTO);
        UserEntity user = (UserEntity) authentication.getPrincipal();

        String token = this.jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(user.getUsername(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                token);
    }
}
