//package com.now.cloudphoto.service;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.ResultReceiver;
//
//public class CloudResultReceiver extends ResultReceiver{
//	private IReceiver mReceiver;
//	
//	public CloudResultReceiver(Handler handler) {
//		super(handler);
//	}
//	
//	public void setReceiver(IReceiver receiver) {
//        mReceiver = receiver;
//    }
//	
//	@Override
//    protected void onReceiveResult(int resultCode, Bundle resultData) {
//        if (mReceiver != null) {
//            mReceiver.onReceiveResult(resultCode, resultData);
//        }
//    }
//	
//	public Object doInBackground(){
//		 if (mReceiver != null) {
//	     	return mReceiver.doInBackground();
//		 }
//		 return null;
//	}
//}
