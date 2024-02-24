package com.nrt.e_learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.e_learning.entity.User;
import com.nrt.e_learning.requests.VideoUploadRequest;
import com.nrt.e_learning.service.FirestoreService;

@RestController
public class FirebaseController {
	
	@Autowired 
	private FirestoreService firestoreService;
	
	
	@GetMapping("/")
	public ModelAndView getLoginPage(ModelAndView modelAndView) {
		modelAndView.setViewName("html/usersList");
		return modelAndView;

	}
	
	
	@PostMapping("/savesData")
	public void addDataToFirestoreDB(@RequestBody VideoUploadRequest  request) {
		System.out.println("getCategoryName id: " + request.getCategoryName());
		firestoreService.saveData(request);
 
	}

	
	
   @GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsersData( ) {
	   List<User> usersList = firestoreService.getAllUsers();
	   return new ResponseEntity<List<User>>( usersList, null, HttpStatus.OK);
	   
	}
}
