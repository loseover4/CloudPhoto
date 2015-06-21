package com.now.cloudphoto.onedrive.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.now.cloudphoto.onedrive.OneDriveConstant;
import com.now.cloudphoto.onedrive.authentication.OneDriveOAuth;

public class UrlManager {
	public String getDrivesUrl(){
		return appendTokens(OneDriveConstant.ROOT_URL+"/drives");
	}
	
	public String getRootItemsOfDriveUrl(String driveID){
		return appendTokens(OneDriveConstant.ROOT_URL+"/drives/" + driveID + "/root/children");
	}
	
	public String getItemUrl(String itemID){
		return appendTokens(OneDriveConstant.ROOT_URL+"/drive/items/" + itemID);
	}
	
	public String getItemChildrenUrl(String itemID){
		return appendTokens(OneDriveConstant.ROOT_URL+"/drive/items/" + itemID + "/children");
	}
	
	public String getItemChildrenWithSmallThumbnailUrl(String itemID){
		String baseURL = appendTokens(OneDriveConstant.ROOT_URL+"/drive/items/" + itemID + "/children");
		baseURL = baseURL + "&expand="+ encodeString("thumbnails(select=small)");
		//baseURL = baseURL + encodeString("?select=id");
		return baseURL;
	}
	
	public String getItemThumbnailUrl(String itemID){
		return appendTokens(OneDriveConstant.ROOT_URL+"/drive/items/" + itemID + "/thumbnails");
	}
	
	public String appendTokens(String url){
		OneDriveOAuth mOneDriveOAuth = new OneDriveOAuth();
		return url+"?access_token=" + mOneDriveOAuth.getAccessTokens();
	}
	
	public String removeTokens(String url){
		if(url == null){
			return url;
		}
		return url.split("\\?access_token=")[0];
	}
	
	private String encodeString(String parameter){
		try {
			return URLEncoder.encode(parameter, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
