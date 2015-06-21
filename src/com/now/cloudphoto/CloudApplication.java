package com.now.cloudphoto;

import java.io.File;
import java.io.IOException;

import com.now.cloudphoto.utilities.Constants;

import android.app.Application;
import android.content.Context;
import android.net.http.HttpResponseCache;
import android.util.Log;

public class CloudApplication extends Application{
	 private static Context context;

	    public void onCreate(){
	        super.onCreate();
	        CloudApplication.context = getApplicationContext();
	        
	        try {
	            File httpCacheDir = new File(context.getCacheDir(), "http");
	            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
	            HttpResponseCache.install(httpCacheDir, httpCacheSize);
	        }
	         catch (IOException e) {
	            Log.e(Constants.LOG_TAG, "HTTP response cache installation failed:" + e);
	        }
	    }

	    public static Context getAppContext() {
	        return CloudApplication.context;
	    }
	    
	    public void onStop() {
	        HttpResponseCache cache = HttpResponseCache.getInstalled();
	        if (cache != null) {
	            cache.flush();
	        }
	    }
}
