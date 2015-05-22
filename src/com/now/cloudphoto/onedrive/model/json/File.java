
package com.now.cloudphoto.onedrive.model.json;

public class File {
	private String mimeType;
	private Hashes hashes;

	public File(){
		
	}
	
 	public void setMimetype(String mimetype) {
		this.mimeType = mimetype;
	}

	public java.lang.String getMimetype() {
		return mimeType;
	}

 	public void setHashes(Hashes hashes) {
		this.hashes = hashes;
	}

	public Hashes getHashes() {
		return hashes;
	}

}