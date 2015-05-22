package com.now.cloudphoto.onedrive.model.json;

public class Thumbnail {
	private int height;
	private int width;
	private String url;
	
	public Thumbnail(){
		
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
}
