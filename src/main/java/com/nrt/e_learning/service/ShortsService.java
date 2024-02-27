package com.nrt.e_learning.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.nrt.e_learning.requests.ShortVideos;

@Component
public interface ShortsService {

	List<ShortVideos> getAllShorts();

	ResponseEntity<String> DeleteVideo(String videoUrl);

	void uploadShortVideo(MultipartFile file, String title, String description);

}