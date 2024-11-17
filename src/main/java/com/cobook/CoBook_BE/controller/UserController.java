package com.cobook.CoBook_BE.controller;

import com.cobook.CoBook_BE.model.User;
import com.cobook.CoBook_BE.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class UserController {
    User user;
    private final TestService testService = new TestService();

    // This returns first document contents from 'User' collection
    @GetMapping("/get")
    public ResponseEntity<User> getUser() throws Exception {
        List<User> list = testService.getUsers();
        return ResponseEntity.ok(list.get(0));
    }
}
