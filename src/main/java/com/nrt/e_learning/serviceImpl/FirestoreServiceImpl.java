package com.nrt.e_learning.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.nrt.e_learning.entity.User;
import com.nrt.e_learning.requests.VideoUploadRequest;
import com.nrt.e_learning.service.FirestoreService;

@Service
public class FirestoreServiceImpl implements FirestoreService {

	static Logger logger = java.util.logging.Logger.getLogger(FirestoreServiceImpl.class.getName());

	
	@Override
	public void saveData(VideoUploadRequest uploadRequest) {

		Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = firestore.collection("Videoss").document();

		System.out.println("document id: " + documentReference.getId());
		logger.log(Level.INFO, "the cate name is " + uploadRequest.getCategoryName(), FirestoreServiceImpl.class);

		ApiFuture<WriteResult> responsApiFuture = documentReference.set(uploadRequest);

		System.out.println("document id: " + responsApiFuture);
	}

	
	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();

		Firestore db = FirestoreClient.getFirestore();

		// Reference to the "users" collection
		CollectionReference usersRef = db.collection("users");

		// Retrieve all documents in the "users" collection
		ApiFuture<QuerySnapshot> future = usersRef.get();

		try {
			// Asynchronously retrieve query snapshot
			QuerySnapshot querySnapshot = future.get();

			// Extract data from each document and add to the list
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				User user = document.toObject(User.class);
				userList.add(user);
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			// Handle exceptions
		}

		return userList;
	}
}