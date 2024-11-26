package com.cobook.CoBook_BE.service;

import com.google.api.client.json.Json;
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

    // Search all users in DB
    public List<User> getUsers() throws Exception{
        return getDocumentsFromCollection(userCollection, User.class);
    }

    // Search all spaces in DB
    public List<Space> getSpaces() throws Exception{
        return getDocumentsFromCollection(spaceCollection, Space.class);
    }

    // Find document id by field name and its value
    public String findDocumentIdByField(CollectionReference collection, String field, String value) throws Exception {

        List<QueryDocumentSnapshot> documents = getDocumentSnapshots(collection);

        for (QueryDocumentSnapshot document : documents) {
            if (value.equals(document.getString(field))) {
                return document.getId();
            }
        }

        return null;
    }

    // Search specific space
    public Space getSpace(String sid) throws Exception{

        String documentId = findDocumentIdByField(spaceCollection, "sid", sid);

        return documentId != null ? spaceCollection.document(documentId).get().get().toObject(Space.class) : null;
    }

    // Make data fields in documents into precise class in model package
    public <T> List<T> getDocumentsFromCollection(CollectionReference collection, Class<T> clas) throws Exception{

        List<T> list = new ArrayList<>();
        for (QueryDocumentSnapshot document : getDocumentSnapshots(collection)) {
            list.add(document.toObject(clas));
        }

        return list;
    }

    // Get child collection from specific parent collection
    public CollectionReference getChildCollection(CollectionReference collection, String documentId, String collectionName) throws Exception{
        return collection.document(documentId).collection(collectionName);
    }

    // Firebase document searching method
    public List<QueryDocumentSnapshot> getDocumentSnapshots(CollectionReference collection) throws Exception{
        return collection.get().get().getDocuments();
    }

    // Get 'items' from receipt in specific space
    public List<Item> getItemsFromReceipt(String sid, String docId) throws Exception{

        List<Item> items = new ArrayList<>();

        for (QueryDocumentSnapshot item : getDocumentSnapshots(getChildCollection(spaceCollection, sid, "Receipt").document(docId).collection("Item"))) {
            items.add(item.toObject(Item.class));
        }

        return items;
    }

    // Search space members in specific space
    public List<SpaceMember> getSpaceMembers(String sid) throws Exception{

        List<SpaceMember> list = new ArrayList<>();
        String documentId = findDocumentIdByField(spaceCollection, "sid", sid);

        return getDocumentsFromCollection(getChildCollection(spaceCollection, documentId, "Member"), SpaceMember.class);
    }

    // Get all receipts from specific space
    public List<Receipt> getReceipts(String sid) throws Exception {
        String documentId = findDocumentIdByField(spaceCollection, "sid", sid);

        List<String> ids = new ArrayList<>();
        List<QueryDocumentSnapshot> receiptColl = getDocumentSnapshots(getChildCollection(spaceCollection, documentId, "Receipt"));
        List<Receipt> receipts = getDocumentsFromCollection(getChildCollection(spaceCollection, documentId, "Receipt"), Receipt.class);

        for (QueryDocumentSnapshot document : receiptColl) {
            ids.add(document.getId());
        }

        for (Receipt receipt : receipts) {
            List<Item> items = getItemsFromReceipt(documentId, ids.get(0));
            receipt.setItems(items);
            ids.remove(0);
        }

        return receipts;
    }


    public List<String> jsonParsing(Json image) {
        List<String> list = new ArrayList<>();

        return list;
    }
}
