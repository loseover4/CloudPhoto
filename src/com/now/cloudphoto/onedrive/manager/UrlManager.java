package com.now.cloudphoto.onedrive.manager;

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
	
	public String getItemThumbnailUrl(String itemID){
		return appendTokens(OneDriveConstant.ROOT_URL+"/drive/items/" + itemID + "/thumbnails");
	}
	
	private String appendTokens(String url){
		OneDriveOAuth mOneDriveOAuth = new OneDriveOAuth();
		return url+"?access_token=" + mOneDriveOAuth.getAccessTokens();
	}
}
