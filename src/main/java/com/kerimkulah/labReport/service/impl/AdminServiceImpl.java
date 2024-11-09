package com.kerimkulah.labReport.service.impl;

import com.kerimkulah.labReport.entity.User;
import com.kerimkulah.labReport.enums.Role;
import com.kerimkulah.labReport.repository.UserRepository;
import com.kerimkulah.labReport.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public void makeAdmin(long UserId) {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

    @Override
    public void removeAdmin(long UserId) {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));
        user.setRole(Role.EMPLOYEE);
        userRepository.save(user);
    }
}
