package com.now.cloudphoto;

import android.app.ActivityManager;
import android.content.Context;
import android.util.LruCache;

public class CacheManager {
	private static LruCache<String, Object> dataCache;
	
	public CacheManager(){
		if(dataCache == null){
			Context context = CloudApplication.getAppContext();
			ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		    int maxKb = am.getMemoryClass() * 1024;
		    int limitKb = maxKb / 8; // 1/8th of total ram
		    dataCache = new LruCache<String, Object>(limitKb);
		}
	}
	
	public void putCache(String key, Object value){
		dataCache.put(key, value);
	}
	
	public Object getCache(String key){
		return dataCache.get(key);
	}	
}
