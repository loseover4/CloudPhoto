package com.now.cloudphoto.onedrive.services;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.now.cloudphoto.onedrive.manager.UrlManager;
import com.now.cloudphoto.onedrive.model.json.Drive;
import com.now.cloudphoto.onedrive.model.json.Item;
import com.now.cloudphoto.onedrive.model.json.JsonReturnArrayObject;
import com.now.cloudphoto.onedrive.model.json.Thumbnail;
import com.now.cloudphoto.onedrive.model.json.Thumbnails;
import com.now.cloudphoto.service.RestServices;
import com.now.cloudphoto.utilities.FileUtil;

public class OneDriveServices {
	private UrlManager urlManager;
	private RestServices mRestServices;
	private GsonBuilder gsonBuilder;
	private Gson gson;
	
	public OneDriveServices(){
		urlManager = new UrlManager();
		mRestServices = new RestServices();
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //Format of our JSON dates
		gson = gsonBuilder.create();
	}
	public Drive[] getDrives(){
		Drive[] result = null;
		try {
			String url =  urlManager.getDrivesUrl();
			String response = mRestServices.sendGetRequestAsync(url);
			Log.d("CloudPhoto", "drive: "+ response);
			//FileUtil.writeTextToFile(response);
			
			Type listType = new TypeToken<JsonReturnArrayObject<Drive>>() {}.getType();
			JsonReturnArrayObject<Drive> jsonObj = gson.fromJson(response, listType);
			result = jsonObj.getValue();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;						
	}
	
	public Item[] getRootItemsOfDrive(String driveID){
		Item[] result = null;
		try {			
			String url =  urlManager.getRootItemsOfDriveUrl(driveID);
			String response = mRestServices.sendGetRequestAsync(url);
			Log.d("CloudPhoto", "items: "+ response);
			FileUtil.writeTextToFile(response);			
			
			Type listType = new TypeToken<JsonReturnArrayObject<Item>>() {}.getType();
			JsonReturnArrayObject<Item> jsonObj = gson.fromJson(response, listType);
			result = jsonObj.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;						
	}
	
	public Item[] getItemChildren(String itemID){
		Item[] result = null;
		try {			
			String url =  urlManager.getItemChildrenUrl(itemID);
			String response = mRestServices.sendGetRequestAsync(url);
			Log.d("CloudPhoto", "items: "+ response);
			FileUtil.writeTextToFile(response);			
			
			Type listType = new TypeToken<JsonReturnArrayObject<Item>>() {}.getType();
			JsonReturnArrayObject<Item> jsonObj = gson.fromJson(response, listType);
			result = jsonObj.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;						
	}
	
	public Item getItem(String itemID){
		Item result = null;
		try {			
			String url =  urlManager.getItemUrl(itemID);
			String response = mRestServices.sendGetRequestAsync(url);
			Log.d("CloudPhoto", "item: "+ response);
			FileUtil.writeTextToFile(response);			
			
			Type listType = new TypeToken<Item>() {}.getType();
			result = gson.fromJson(response, listType);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;						
	}
	
	public Thumbnails getItemThumbnail(String itemID){
		Thumbnails result = null;
		try {			
			String url =  urlManager.getItemThumbnailUrl(itemID);
			String response = mRestServices.sendGetRequestAsync(url);
			Log.d("CloudPhoto", "item: "+ response);
			FileUtil.writeTextToFile(response);			
			
			Type listType = new TypeToken<JsonReturnArrayObject<Thumbnails>>() {}.getType();
			JsonReturnArrayObject<Thumbnails> jsonObj = gson.fromJson(response, listType);
			if(jsonObj.getValue() != null && jsonObj.getValue().length > 0){
				result = jsonObj.getValue()[0];			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;						
	}
}
