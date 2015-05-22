package com.now.cloudphoto.onedrive.model.json;

public class Drive {
	private String id;
	private String driveType;
	private UserWrapper owner;
	private Quota quota;
	
	public Drive(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public UserWrapper getOwner() {
		return owner;
	}

	public void setOwner(UserWrapper owner) {
		this.owner = owner;
	}

	public Quota getQuota() {
		return quota;
	}

	public void setQuota(Quota quota) {
		this.quota = quota;
	}	
}
