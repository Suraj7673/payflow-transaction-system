package com.payflow.controller;

import com.payflow.dto.LoginRequest;
import com.payflow.model.User;
import com.payflow.repository.UserRepository;
import com.payflow.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // ✅ STATIC CALL (correct)
        String token = JwtUtil.generateToken(user.getEmail());

        return Map.of("token", token);
    }
}