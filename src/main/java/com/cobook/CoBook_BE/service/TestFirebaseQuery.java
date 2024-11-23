package com.cobook.CoBook_BE.service;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.cobook.CoBook_BE.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public String findSpecificSpaceDoc(String sid) throws Exception {

        String documentId = null;

        List<QueryDocumentSnapshot> documents = spaceCollection.get().get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            if (document.toObject(Space.class).sid.equals(sid)) {
                documentId = document.getId();
                break;
            }
        }

        return documentId;
    }

    // Search specific space
    public Space getSpace(String sid) throws Exception{
        Space space;

        space = spaceCollection.document(findSpecificSpaceDoc(sid))
                .get().get().toObject(Space.class);

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

    public List<Receipt> getReceipts(String sid) throws Exception{
        List<Receipt> receiptList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();
        List<QueryDocumentSnapshot> receipts = spaceCollection.document(findSpecificSpaceDoc(sid))
                .collection("Receipt")
                .get().get().getDocuments();
        List<QueryDocumentSnapshot> items = new ArrayList<>();
        List<String> receiptIds = new ArrayList<>();

        for (QueryDocumentSnapshot receipt : receipts) {
            receiptList.add(receipt.toObject(Receipt.class));
            receiptIds.add(receipt.getId());
        }

        for (String ids : receiptIds) {
            for (QueryDocumentSnapshot item : spaceCollection.document(findSpecificSpaceDoc(sid)).collection("Receipt").document(ids).collection("Item").get().get().getDocuments()) {
                itemList.add(item.toObject(Item.class));
            }

            for (Receipt receipt : receiptList) {
                receipt.setItems(itemList);
            }
        }

        return receiptList;
    }
}
