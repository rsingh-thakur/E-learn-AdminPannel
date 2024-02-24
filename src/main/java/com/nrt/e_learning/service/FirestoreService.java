package com.nrt.e_learning.service;

import java.util.List;

import com.nrt.e_learning.entity.User;
import com.nrt.e_learning.requests.VideoUploadRequest;

public interface FirestoreService {
    
    public void saveData(VideoUploadRequest request) ;

	List<User> getAllUsers();
       
}