package com.nrt.e_learning.requests;

import java.io.Serializable;

 
public class ShortVideos implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	String desc;
	String title;
	String url;
	
	
	
	public ShortVideos(String desc, String title, String url) {
		super();
		this.desc = desc;
		this.title = title;
		this.url = url;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public ShortVideos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
