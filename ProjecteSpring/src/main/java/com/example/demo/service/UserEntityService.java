package com.example.demo.service;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.model.UserAuthority;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserEntityRepository;
import io.jsonwebtoken.security.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UserEntityService {

    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByUsername(String username){
        return this.repository.findByUsername(username);
    }

    public UserEntity save(UserRegisterDTO userDTO){
        UserEntity user = new UserEntity(
                null,
                userDTO.username(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.email(),
                List.of(UserAuthority.READ)
        );
        return this.repository.save(user);
    }
}
