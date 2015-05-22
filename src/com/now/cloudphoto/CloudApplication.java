package com.now.cloudphoto;

import android.app.Application;
import android.content.Context;

public class CloudApplication extends Application{
	 private static Context context;

	    public void onCreate(){
	        super.onCreate();
	        CloudApplication.context = getApplicationContext();
	    }

	    public static Context getAppContext() {
	        return CloudApplication.context;
	    }
}
