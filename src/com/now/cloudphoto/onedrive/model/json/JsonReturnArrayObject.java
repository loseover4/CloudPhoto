package com.now.cloudphoto.onedrive.model.json;

import com.google.gson.annotations.SerializedName;

public class JsonReturnArrayObject<T> {
	@SerializedName("@odata.context")
	private String odataContext;	
	private T value[];
	
	public JsonReturnArrayObject(){
		
	}

	public String getOdataContent() {
		return odataContext;
	}

	public void setOdataContent(String odataContent) {
		this.odataContext = odataContent;
	}

	public T[] getValue() {
		return value;
	}

	public void setValue(T[] value) {
		this.value = value;
	}
	
}
