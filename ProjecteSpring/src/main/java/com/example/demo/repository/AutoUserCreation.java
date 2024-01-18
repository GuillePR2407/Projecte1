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
        // Crear un usuario de ejemplo con contraseña codificada
        UserDetails userDetails = createUserDetails();

        try {
            // Crear una solicitud de autenticación con la contraseña codificada
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));

            // Establecer la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar el token
            String token = generateToken(userDetails);

            // Devolver el token por la consola de Spring Boot
            System.out.println("Token del usuario " + userDetails.getUsername() + ": " + token);
        } catch (AuthenticationException e) {
            // Manejar la excepción de autenticación (credenciales incorrectas, cuenta bloqueada, etc.)
            System.err.println("Error durante la autenticación: " + e.getMessage());
        }
    }

    private UserDetails createUserDetails() {
        // Puede personalizar esta parte según sus necesidades, por ejemplo, cargar el usuario desde la base de datos
        String rawPassword = "admin"; // Contraseña sin codificar
        String encodedPassword = passwordEncoder.encode(rawPassword); // Codificar la contraseña

        return new UserEntity(1L, "admin", encodedPassword,
                "admin@admin.com", null);
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
