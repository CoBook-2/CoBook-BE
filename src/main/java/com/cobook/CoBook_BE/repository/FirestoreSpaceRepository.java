package com.cobook.CoBook_BE.repository;

import com.cobook.CoBook_BE.model.Space;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class FirestoreSpaceRepository implements SpaceRepository{
    private static final String COLLECTION_NAME = "Space";

    @Override
    public Space spaceCreate(String sid, String sname, String tag, int budget) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(sid);

        Space space = new Space();
        space.setSid(sid);
        space.setSname(sname);
        space.setTag(tag);
        space.setBudget(budget);

        ApiFuture<WriteResult> writeResult = documentReference.set(space);
        try {
            writeResult.get(); // 저장 완료 대기
            return space;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Space> findBySpaceId(String sid) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference users = firestore.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = users.whereEqualTo("sid", sid).get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            if (!documents.isEmpty()) {
                return Optional.of(documents.get(0).toObject(Space.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Space> findBySpaceName(String sname) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference users = firestore.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = users.whereEqualTo("sname", sname).get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            if (!documents.isEmpty()) {
                return Optional.of(documents.get(0).toObject(Space.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public Optional<Space> findBySpaceTag(String tag) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference users = firestore.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = users.whereEqualTo("sid", tag).get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
            if (!documents.isEmpty()) {
                return Optional.of(documents.get(0).toObject(Space.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public List<Space> findAll() {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference users = firestore.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = users.get();
        List<Space> userList = new ArrayList<>();
        try {
            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                userList.add(document.toObject(Space.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Space spaceUpdate(String sid, String sname, String tag, int budget) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(sid);

        try {
            // 먼저 문서가 존재하는지 확인
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // 기존 데이터 가져오기
                Space space = document.toObject(Space.class);

                // sid는 유지하고 다른 필드만 업데이트
                if (space != null) {
                    space.setSname(sname);
                    space.setTag(tag);
                    space.setBudget(budget);

                    // 변경된 데이터 저장
                    ApiFuture<WriteResult> writeResult = documentReference.set(space);
                    writeResult.get(); // 저장 완료 대기
                    return space;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null; // 업데이트 실패 시 null 반환
    }

    @Override
    public Space spaceDelete(String sid, String sname) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(sid);

        try {
            // 문서가 존재하는지 확인
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                Space space = document.toObject(Space.class);

                // sname을 비교해 일치하면 삭제
                if (space != null && space.getSname().equals(sname) ) {
                    ApiFuture<WriteResult> writeResult = documentReference.delete();
                    writeResult.get(); // 삭제 완료 대기
                    return space; // 삭제된 Space 반환
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null; // 삭제 실패 시 null 반환
    }



}
