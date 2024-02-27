package com.nrt.e_learning.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.nrt.e_learning.requests.ShortVideos;
import com.nrt.e_learning.service.ShortsService;

@Service
public class ShortsImpl implements ShortsService {

	@Override
	public List<ShortVideos> getAllShorts() {
		List<ShortVideos> shortsList = new ArrayList<ShortVideos>();

		Firestore db = FirestoreClient.getFirestore();

		// Reference to the "users" collection
		CollectionReference shortsRef = db.collection("shorts");

		// Retrieve all documents in the "users" collection
		ApiFuture<QuerySnapshot> future = shortsRef.get();

		try {
			// Asynchronously retrieve query snapshot
			QuerySnapshot querySnapshot = future.get();

			// Extract data from each document and add to the list
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				ShortVideos shorts = document.toObject(ShortVideos.class);
				shortsList.add(shorts);
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			// Handle exceptions
		}
		return shortsList;
	}

	public ResponseEntity<String> DeleteVideo(String videoUrl) {
		try {
			// Delete video from Cloud Storage
			deleteVideoFromStorage(videoUrl);

			// Delete video document from Firestore
			deleteVideoFromFirestore(videoUrl);

			return ResponseEntity.ok("Video Deleted Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete video");
		}
	}

	private void deleteVideoFromStorage(String videoUrl) {
		// Parse video URL to get the filename
		String[] parts = videoUrl.split("/");
		String filename = parts[parts.length - 1]; // Assuming the filename is the last part of the URL

		// Initialize Cloud Storage client
		Storage storage = StorageOptions.getDefaultInstance().getService();

		// Get reference to the storage bucket
		Bucket bucket = storage.get("<your-bucket-name>");

		// Get reference to the video file
		Blob blob = bucket.get("<your-folder-name>/" + filename); // Adjust folder name as needed

		// Delete the file from Cloud Storage
		if (blob != null) {
			blob.delete();
		}
	}

	private void deleteVideoFromFirestore(String videoUrl) throws ExecutionException, InterruptedException {
		Firestore db = FirestoreClient.getFirestore();

		// Reference to the shorts collection
		CollectionReference shortsCollection = db.collection("shorts");

		// Create a query to retrieve documents where the URL field contains the
		// specified substring
		Query query = shortsCollection.whereEqualTo("url", videoUrl);

		// Get the query snapshot containing the documents
		ApiFuture<QuerySnapshot> querySnapshot = query.get();

		// Execute the query and get the results
		for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
			// Delete each document found in the query
			deleteDocument(shortsCollection.document(document.getId()));
		}
	}

	private void deleteDocument(DocumentReference documentReference) throws ExecutionException, InterruptedException {
		Firestore db = FirestoreClient.getFirestore();

		// Initialize a WriteBatch to perform the delete operation
		WriteBatch batch = db.batch();

		// Mark the document to be deleted
		batch.delete(documentReference);

		// Commit the batch to execute the delete operation
		batch.commit().get();
	}

	@Override
	public void uploadShortVideo(MultipartFile file, String title, String description) {

		try {
			// Convert the MultipartFile to a byte array
			byte[] videoBytes = file.getBytes();

			// Encode the byte array to Base64 string
			String base64Video = Base64.getEncoder().encodeToString(videoBytes);

			// Initialize Firestore
			Firestore firestore = FirestoreClient.getFirestore();

			// Create a Firestore document reference
			DocumentReference docRef = firestore.collection("shorts").document();

			ShortVideos shortVideos = new ShortVideos(description, title, base64Video);

			ApiFuture<WriteResult> obj = docRef.set(shortVideos);
			try {
				System.out.println(obj.get().toString());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// File uploaded successfullys
			System.out.println("File uploaded successfully");
		} catch (IOException e) {
			// Handle file upload error
			System.err.println("Failed to upload file: " + e.getMessage());
		}
	}
}
