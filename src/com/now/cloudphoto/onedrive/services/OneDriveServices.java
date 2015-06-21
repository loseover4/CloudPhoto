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
import com.now.cloudphoto.onedrive.model.json.Thumbnails;
import com.now.cloudphoto.service.RestServices;

public class OneDriveServices {
	private UrlManager urlManager;
	private RestServices mRestServices;
	private GsonBuilder gsonBuilder;
	private Gson gson;
	private OneDriveBackStack backStack;
	
	public OneDriveServices(){
		urlManager = new UrlManager();
		mRestServices = new RestServices();
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //Format of our JSON dates
		gson = gsonBuilder.create();
		backStack = new OneDriveBackStack();
	}
	
	public Drive[] getDrives(){
		Log.d("CloudPhoto", "getDrives");
		Drive[] result = null;
		String url =  urlManager.getDrivesUrl();
		String response = getResponseUsingCache(url);
			
		Type listType = new TypeToken<JsonReturnArrayObject<Drive>>() {}.getType();
		JsonReturnArrayObject<Drive> jsonObj = gson.fromJson(response, listType);
		result = jsonObj.getValue();
		return result;						
	}
	
	public Item[] getRootItemsOfDrive(String driveID){
		Log.d("CloudPhoto", "getRootItemsOfDrive");
		Item[] result = null;
		String url =  urlManager.getRootItemsOfDriveUrl(driveID);
		String response = getResponseUsingCache(url);
		
		Type listType = new TypeToken<JsonReturnArrayObject<Item>>() {}.getType();
		JsonReturnArrayObject<Item> jsonObj = gson.fromJson(response, listType);
		result = jsonObj.getValue();

		backStack.push(url);
		return result;						
	}
	
	public Item[] getItemChildren(String itemID){
		Log.d("CloudPhoto", "getItemChildren");
		Item[] result = null;
		String url = urlManager.getItemChildrenWithSmallThumbnailUrl(itemID);
		String response = getResponseUsingCache(url);
				
		Type listType = new TypeToken<JsonReturnArrayObject<Item>>() {}.getType();
		JsonReturnArrayObject<Item> jsonObj = gson.fromJson(response, listType);
		result = jsonObj.getValue();

		backStack.push(url);
		return result;				
	}
	
	public Item getItem(String itemID){
		Item result = null;
		String url =  urlManager.getItemUrl(itemID);
		String response = getResponseUsingCache(url);
		
		Type listType = new TypeToken<Item>() {}.getType();
		result = gson.fromJson(response, listType);
		return result;						
	}
	
	public Thumbnails getItemThumbnail(String itemID){
		Thumbnails result = null;
		String url =  urlManager.getItemThumbnailUrl(itemID);
		String response = getResponseUsingCache(url);	
		
		Type listType = new TypeToken<JsonReturnArrayObject<Thumbnails>>() {}.getType();
		JsonReturnArrayObject<Thumbnails> jsonObj = gson.fromJson(response, listType);
		if(jsonObj.getValue() != null && jsonObj.getValue().length > 0){
			result = jsonObj.getValue()[0];			
		}
		return result;						
	}
	
	public Item[] getItemsFromBackStack(){
		Item[] result = null;
		String url = backStack.getPreviousURL();
		String response = getResponseUsingCache(url);

		Type listType = new TypeToken<JsonReturnArrayObject<Item>>() {}.getType();
		JsonReturnArrayObject<Item> jsonObj = gson.fromJson(response, listType);
		result = jsonObj.getValue();

		return result;			
	}
	
	public boolean isBackStackEmpty(){
		return backStack.isEmpty();
	}
	
	private String getResponseUsingCache(String url){
		String response = "";
		try {
			response = mRestServices.sendGetRequestAsync(url);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return response;
	}
}
