package com.cobook.CoBook_BE.controller;

import com.cobook.CoBook_BE.model.User;
import com.cobook.CoBook_BE.service.TestFirebaseQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("testUser")
public class UserController {
    User user;
    private final TestFirebaseQuery testFirebaseQuery = new TestFirebaseQuery();

    // This returns first document contents from 'User' collection
    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser() throws Exception {
        List<User> list = testFirebaseQuery.getUsers();
        return ResponseEntity.ok(list);
    }
}
