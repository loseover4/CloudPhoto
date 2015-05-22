package com.now.cloudphoto.onedrive.model.json;

public class Reference {
	private String driveId;
	private String id;
	private String path;
	
	public Reference(){
		
	}

	public String getDriveId() {
		return driveId;
	}

	public void setDriveId(String driveId) {
		this.driveId = driveId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
}

