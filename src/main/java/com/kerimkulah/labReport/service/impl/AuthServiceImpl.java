package com.kerimkulah.labReport.service.impl;

import com.kerimkulah.labReport.dto.AuthResponse;
import com.kerimkulah.labReport.dto.LoginRequest;
import com.kerimkulah.labReport.dto.RegisterRequest;
import com.kerimkulah.labReport.entity.User;
import com.kerimkulah.labReport.enums.Role;
import com.kerimkulah.labReport.repository.UserRepository;
import com.kerimkulah.labReport.security.JWTService;
import com.kerimkulah.labReport.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Kullanıcı adı zaten mevcut");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Şifre boş olamaz");
        }
        User user =new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.EMPLOYEE);
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.generateToken(user);
            return AuthResponse.builder().token(token).build();
        } else {
            return AuthResponse.builder().token("Login servis hatası").build();
        }
    }
}


