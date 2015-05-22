package com.now.cloudphoto.models;

public class BrowseListItemDataModel {
	private String title;
	private String id;
	private String thumbnailUrl;
	private boolean isFolder;
	
	public BrowseListItemDataModel(){
		
	}
	
	public BrowseListItemDataModel(String mId, String mTitle, boolean mIsFolder){
		title = mTitle;
		id = mId;
		isFolder = mIsFolder;
	}
	
	public BrowseListItemDataModel(String mId, String mTitle, String mThumbnailUrl, boolean mIsFolder){
		title = mTitle;
		id = mId;
		isFolder = mIsFolder;
		thumbnailUrl = mThumbnailUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString(){
		return getTitle();
	}
}
