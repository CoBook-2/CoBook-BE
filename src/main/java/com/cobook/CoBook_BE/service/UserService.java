package com.cobook.CoBook_BE.service;

import com.cobook.CoBook_BE.model.User;
import com.cobook.CoBook_BE.repository.UserRepository;
import com.cobook.CoBook_BE.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, Object> login(String uid, String pw) {
        Optional<User> userOptional = userRepository.findByUserIdAndPassword(uid, pw);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPw().equals(pw)) {
                // JWT 토큰 생성
                String token = jwtUtil.generateToken(uid);

                // User 객체와 토큰 반환
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("user", user);
                return response;
            }
        }
        throw new IllegalArgumentException("Invalid credentials!");
    }
}
