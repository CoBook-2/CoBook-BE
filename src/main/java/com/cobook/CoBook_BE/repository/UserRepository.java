package com.cobook.CoBook_BE.repository;

import com.cobook.CoBook_BE.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(String uid, String uname, String pw, String phone);
    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(String userId);
    List<User> findAll();
}