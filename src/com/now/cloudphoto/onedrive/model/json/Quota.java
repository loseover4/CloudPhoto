package com.now.cloudphoto.onedrive.model.json;

public class Quota {
	private String deleted;
	private String remaining;
	private String state;
	private String total;
	private String used;
	
	public Quota(){
		
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getRemaining() {
		return remaining;
	}

	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}	
}
