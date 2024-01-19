package com.example.demo.repository;

import com.example.demo.model.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AutoUserCreation {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.security.jwt.expiration}")
    private long jwtExpirationMs;

    @Autowired
    public AutoUserCreation(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        autenticarYGenerarToken();
    }

    public void autenticarYGenerarToken() {
        UserDetails userDetails = createUserDetails();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "admin"));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = generateToken(userDetails);

            System.out.println("Authentication successful for user: " + userDetails.getUsername());
            System.out.println("Token for user " + userDetails.getUsername() + ": " + token);

        } catch (AuthenticationException e) {
            System.err.println("Error during authentication: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private UserDetails createUserDetails() {
        // Customize this part according to your needs, e.g., load the user from the database
        String rawPassword = "admin"; // Plain text password
        String encodedPassword = passwordEncoder.encode(rawPassword); // Encode the password

        return new UserEntity(1L, "admin", encodedPassword, "admin@admin.com", null);
    }

    private String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
