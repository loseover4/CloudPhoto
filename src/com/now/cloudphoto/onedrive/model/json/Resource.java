package com.now.cloudphoto.onedrive.model.json;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Resource {
	@SerializedName("@content.downloadUrl")
	private String contentDownloadUrl;
	private Date createdDateTime;
	private String cTag;
	private String eTag;
	private String id;
	private String lastModifiedBy;
	private String name;
	private String size;
	private String image;
	private String file;
	
	public Resource(){
		
	}
	
	public String getContentDownloadUrl() {
		return contentDownloadUrl;
	}
	public void setContentDownloadUrl(String contentDownloadUrl) {
		this.contentDownloadUrl = contentDownloadUrl;
	}
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getcTag() {
		return cTag;
	}
	public void setcTag(String cTag) {
		this.cTag = cTag;
	}
	public String geteTag() {
		return eTag;
	}
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}	
}
