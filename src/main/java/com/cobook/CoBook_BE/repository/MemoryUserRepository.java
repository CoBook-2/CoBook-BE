package com.cobook.CoBook_BE.repository;

import com.cobook.CoBook_BE.model.User;

import java.util.*;

public class MemoryUserRepository implements UserRepository {

    private static final Map<String, User> store = new HashMap<>();

    @Override
    public User save(String uid, String uname, String pw, String phone) {
        User user = new User();
        user.setUid(uid);
        user.setUname(uname);
        user.setPw(pw);
        user.setPhone(phone);
        store.put(uid, user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.values().stream()
                .filter(user -> user.getUid().equals(username))
                .findAny();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}