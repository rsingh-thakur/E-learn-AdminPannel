package com.nrt.e_learning.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoUploadRequest {

	private String videoUri;

	private String videoTitle;

	private String thumbnailUri;  

	private String description;

	private String CategoryName;

	public VideoUploadRequest(String videoUri, String videoTitle, String thumbnailUri, String description,
			String categoryName) {
		super();
		this.videoUri = videoUri;
		this.videoTitle = videoTitle;
		this.thumbnailUri = thumbnailUri;
		this.description = description;
		this.CategoryName = categoryName;
	}

	public String getVideoUri() {
		return videoUri;
	}

	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getThumbnailUri() {
		return thumbnailUri;
	}

	public void setThumbnailUri(String thumbnailUri) {
		this.thumbnailUri = thumbnailUri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	
	

}
