package com.cobook.CoBook_BE.service;

import com.cobook.CoBook_BE.model.User;
import com.cobook.CoBook_BE.repository.UserRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user.getUid(), user.getUname(), user.getPw(), user.getPhone());
    }

    public Optional<User> getUserDetail(String uid) {
        return userRepository.findByUserId(uid);
    }

    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findByUserId(user.getUid());
        if (existingUser.isPresent()) {
            return userRepository.save(user.getUid(), user.getUname(), user.getPw(), user.getPhone());
        }
        throw new IllegalArgumentException("User not found");
    }

    public boolean deleteUser(String uid) {
        Optional<User> existingUser = userRepository.findByUserId(uid);
        if (existingUser.isPresent()) {
            Firestore firestore = FirestoreClient.getFirestore();
            firestore.collection("users").document(uid).delete();
            return true;
        }
        return false;
    }

    public String login(String uid, String pw) {
        Optional<User> userOptional = userRepository.findByUserId(uid);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPw().equals(pw)) {
                return "Login successful!";
            }
        }
        return "Invalid credentials!";
    }
}


