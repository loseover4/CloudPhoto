//package com.now.cloudphoto.service;
//
//import com.now.cloudphoto.utilities.Constants;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.ResultReceiver;
//
//public class CloudService extends IntentService {
//    public CloudService(String name) {
//		super(name);
//	}
//
//	protected void onHandleIntent(Intent intent) {
//        final CloudResultReceiver receiver = intent.getParcelableExtra("receiver");
//        Bundle b = new Bundle();
//        receiver.send(Constants.CLOUDSERVICE_STATUS_RUNNING, Bundle.EMPTY);
//        try {       
//        	Object results = receiver.doInBackground();
//            receiver.send(Constants.CLOUDSERVICE_STATUS_FINISHED, b);
//        } catch(Exception e) {
//            b.putString(Intent.EXTRA_TEXT, e.toString());
//            receiver.send(Constants.CLOUDSERVICE_STATUS_ERROR, b);
//        } 
//    }
//}
