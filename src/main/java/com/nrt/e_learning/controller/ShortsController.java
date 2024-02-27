package com.nrt.e_learning.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.e_learning.requests.ShortVideos;
import com.nrt.e_learning.service.ShortsService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/shorts")
public class ShortsController {

	@Autowired
	ShortsService shortsService;

	Logger logger = (Logger) LoggerFactory.getLogger(ShortsController.class);

	@GetMapping("/getShortsPage")
	public ModelAndView getShortsPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/shortsList");
		return modelAndView;
	}

	@GetMapping("/getUploadShortsPage")
	public ModelAndView getUploadShortsPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/uploadShortVideo");
		return modelAndView;
	}

	@GetMapping("/getAllShorts")
	public ResponseEntity<List<ShortVideos>> getAllUsersData() {
		List<ShortVideos> usersList = shortsService.getAllShorts();
		return new ResponseEntity<List<ShortVideos>>(usersList, null, HttpStatus.OK);
	}

	@GetMapping("/getShortsCount")
	public ResponseEntity<Integer> getAllUsersCount() {
		int totalShortsCount = shortsService.getAllShorts().size();
		return new ResponseEntity<Integer>(totalShortsCount, null, HttpStatus.OK);
	}

	@DeleteMapping("/")
	public ResponseEntity<String> getAllUsersCount(@RequestParam("videoUrl") String videoUrl) {
		logger.info("the url is " + videoUrl);
		return shortsService.DeleteVideo(videoUrl);

	}

	
	
	@PostMapping("/uploadVideo")
	public ResponseEntity<String> handleFileUpload(@RequestParam("videoFile") MultipartFile file,
			@RequestParam("videoTitle") String title, @RequestParam("videoDesc") String description) {
		 try {
			 		
			  shortsService.uploadShortVideo(file, title, description);
			return ResponseEntity.ok().body("Video uploaded successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload video: " + e.getMessage());
		}
	}
}
