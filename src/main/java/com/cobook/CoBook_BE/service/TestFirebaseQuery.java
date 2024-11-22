package com.cobook.CoBook_BE.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.cobook.CoBook_BE.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestFirebaseQuery {

    Firestore db = FirestoreClient.getFirestore();
    CollectionReference userCollection = db.collection("User");
    CollectionReference spaceCollection = db.collection("Space");

    public List<User> getUsers() throws Exception{
        List<User> list = new ArrayList<>();

        List<QueryDocumentSnapshot> documents = userCollection.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(User.class));
        }
        return list;
    }

    // Search all spaces in DB
    public List<Space> getSpaces() throws Exception{
        List<Space> list = new ArrayList<>();

        List<QueryDocumentSnapshot> documents = spaceCollection.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(Space.class));
        }
        return list;
    }

    // Search specific space
    public Space getSpace(String sid) throws Exception{
        Space space;
        String documentId = null;

        List<QueryDocumentSnapshot> documents = spaceCollection.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.toObject(Space.class).sid.equals(sid)) {
                documentId = document.getId();
                break;
            }
        }

        if (documentId == null) {
            return null;
        }

        space = spaceCollection.document(documentId).get().get().toObject(Space.class);

        return space;
    }

    // Search space members in specific space
    public List<SpaceMember> getSpaceMembers(String sid) throws Exception{
        List<SpaceMember> list = new ArrayList<>();
        String documentId;
        List<QueryDocumentSnapshot> spaces = spaceCollection.get().get().getDocuments();

        for (QueryDocumentSnapshot document : spaces) {
            if(document.toObject(Space.class).sid.equals(sid)) {
                documentId = document.getId();
                for (QueryDocumentSnapshot space : spaceCollection.document(documentId).collection("Member").get().get().getDocuments()) {
                    list.add(space.toObject(SpaceMember.class));
                }
                break;
            }
        }

        return list;
    }
}
