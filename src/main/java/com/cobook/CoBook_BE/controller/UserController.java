package com.cobook.CoBook_BE.controller;

import com.cobook.CoBook_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        String uid = loginRequest.get("uid");
        String pw = loginRequest.get("pw");
        return userService.login(uid, pw);
    }
}
