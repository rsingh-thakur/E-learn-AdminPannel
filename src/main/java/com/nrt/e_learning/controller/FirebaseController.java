package com.nrt.e_learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nrt.e_learning.requests.VideoUploadRequest;
import com.nrt.e_learning.service.FirestoreService;

@RestController
public class FirebaseController {
	
	@Autowired 
	private FirestoreService firestoreService;
	
	@PostMapping("/savesData")
	public void addDataToFirestoreDB(@RequestBody VideoUploadRequest  request) {
		firestoreService.saveData();
 
	}

	
   @GetMapping("/saveData")
	public void addDataToFirestoreDB( ) {
		firestoreService.saveData();
 
	}
}
