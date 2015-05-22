package com.now.cloudphoto.onedrive.model.json;

import java.util.Date;

public class FileSystemInfo {
	private Date createdDateTime;
	private Date lastModifiedDateTime;
	
	public FileSystemInfo(){
		
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}	
}
