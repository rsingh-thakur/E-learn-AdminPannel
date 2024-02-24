package com.nrt.e_learning.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.nrt.e_learning.ELearningApplication;

import jakarta.annotation.PostConstruct;

@Service
public class FirestoreConfig {

   
    @PostConstruct
    public void firestoreConfigurer() throws IOException {
		
		ClassLoader classLoader = ELearningApplication.class.getClassLoader();

		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://e-learn-f1603-default-rtdb.firebaseio.com").build();

		FirebaseApp.initializeApp(options);
		
    }
}
