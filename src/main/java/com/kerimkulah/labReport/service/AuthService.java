package com.kerimkulah.labReport.service;

import com.kerimkulah.labReport.dto.AuthResponse;
import com.kerimkulah.labReport.dto.LoginRequest;
import com.kerimkulah.labReport.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
