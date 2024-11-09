package com.kerimkulah.labReport.security;

import com.kerimkulah.labReport.entity.User;
import com.kerimkulah.labReport.enums.Role;
import com.kerimkulah.labReport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            var token = jwtService.generateToken(admin);
            System.out.println("Admin kullanıcı oluşturuldu. Id : admin , Pw : 12345, token :" + token);
     }
    }
}