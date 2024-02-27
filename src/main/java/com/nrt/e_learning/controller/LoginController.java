package com.nrt.e_learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.e_learning.entity.User;
import com.nrt.e_learning.service.FirestoreService;

@RestController
public class LoginController {

	@Autowired
	private FirestoreService firestoreService;

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsersData() {
		List<User> usersList = firestoreService.getAllUsers();
		return new ResponseEntity<List<User>>(usersList, null, HttpStatus.OK);
	}

	@GetMapping("/getUserPage")
	public ModelAndView getLoginPage(ModelAndView modelAndView) {
		modelAndView.setViewName("html/usersList");
		return modelAndView;

	}

	@GetMapping("/getUsersCount")
	public ResponseEntity<Integer> getAllUsersCount() {
		int totalUsersCount = firestoreService.getAllUsers().size();
		return new ResponseEntity<Integer>(totalUsersCount, null, HttpStatus.OK);
	}

}
