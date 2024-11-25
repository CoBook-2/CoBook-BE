package com.cobook.CoBook_BE.repository;

import com.cobook.CoBook_BE.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class FirestoreUserRepository implements UserRepository {

    private static final String COLLECTION_NAME = "User";

    @Override
    public User save(String uid, String uname, String pw, String phone) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(uid);

        User user = new User();
        user.setUid(uid);
        user.setUname(uname);
        user.setPw(pw);
        user.setPhone(phone);

        ApiFuture<WriteResult> writeResult = documentReference.set(user);
        try {
            writeResult.get(); // 저장 완료 대기
            return user;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference users = firestore.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = users.whereEqualTo("uname", username).get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            if (!documents.isEmpty()) {
                return Optional.of(documents.get(0).toObject(User.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(userId);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return Optional.of(document.toObject(User.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference users = firestore.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = users.get();
        List<User> userList = new ArrayList<>();
        try {
            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                userList.add(document.toObject(User.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
