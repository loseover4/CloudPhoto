package com.now.cloudphoto.onedrive.model.json;

public class Hashes {
	private String sha1hash;

	public Hashes(){
		
	}
	
 	public void setSha1hash(String sha1hash) {
		this.sha1hash = sha1hash;
	}

	public java.lang.String getSha1hash() {
		return sha1hash;
	}

	private java.lang.String crc32hash;

 	public void setCrc32hash(String crc32hash) {
		this.crc32hash = crc32hash;
	}

	public java.lang.String getCrc32hash() {
		return crc32hash;
	}

}