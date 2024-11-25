package com.cobook.CoBook_BE.controller;

import com.cobook.CoBook_BE.model.User;
import com.cobook.CoBook_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 사용자 추가
    @PostMapping("/save")
    public User insertUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 사용자 정보 조회
    @GetMapping("/get/{uid}")
    public Optional<User> getUserDetail(@RequestParam String uid) {
        return userService.getUserDetail(uid);
    }

    // 사용자 정보 업데이트
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    // 사용자 삭제
    @DeleteMapping("/deleteUser")
    public boolean deleteUser(@RequestParam String uid) {
        return userService.deleteUser(uid);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getUid(), user.getPw());
    }
}


