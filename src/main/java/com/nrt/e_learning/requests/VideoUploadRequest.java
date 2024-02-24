package com.nrt.e_learning.requests;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class VideoUploadRequest {

	private String videoUri;
	
	private String videoTitle;
	
	private String thumbnailUri;

	private String description;

	private String CategoryName;

}
