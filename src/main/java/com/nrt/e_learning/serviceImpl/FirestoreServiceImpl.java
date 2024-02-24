package com.nrt.e_learning.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.nrt.e_learning.service.FirestoreService;

@Service
public class FirestoreServiceImpl  implements  FirestoreService{

    @Autowired
    private Firestore firestore;

    public void saveData() {
        CollectionReference users = firestore.collection("users");
        DocumentReference docRef = users.document("user123");
        docRef.set(Map.of("name", "John", "age", 30));
    }
}