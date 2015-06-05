package com.now.cloudphoto.onedrive.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.now.cloudphoto.CloudApplication;
import com.now.cloudphoto.onedrive.OneDriveConstant;
import com.now.cloudphoto.service.RestServices;
import com.now.cloudphoto.utilities.Constants;

public class OneDriveOAuth {
	private String accessTokens = null;
	private String refreshTokens = null;
	private int exprireIn = -1;
	private String userID = null;
	private long expireTimeStamp = -1;
	
	private String clientID = "000000004815602E";
	private String clientSecret = "lFs71Ya01lgrcUl9DBRWvwcvOYQ97qd-";
	private String scope = "wl.signin wl.offline_access onedrive.readwrite";
	private String callbackUrl = "https://login.live.com/oauth20_desktop.srf";
	private String authUrl = "https://login.live.com/oauth20_authorize.srf?"
			+"client_id="+clientID + "&scope="+scope+"&response_type=code"+"&redirect_uri="+callbackUrl;
	
	private RestServices mRestService; 
	
	public OneDriveOAuth(){
		mRestService = new RestServices();
		loadTokens();
	}
	
	public String getAccessTokens() {
		loadTokens();
		return accessTokens;
	}

	public String getRefreshTokens() {
		loadTokens();
		return refreshTokens;
	}

	public int getExprireIn() {
		loadTokens();
		return exprireIn;
	}

	public String getUserID() {
		loadTokens();
		return userID;
	}

	public String getAuthUrl(){
		loadTokens();
		return authUrl;
	}

	private static String signInError = null;
	public boolean getTokens(String url){
		boolean status = false;
		loadTokens();
		
		if(isTokensExpired()){
			return refreshTokens();
		}
		else if(accessTokens == null){
			signInError = null;
			if(url.contains(callbackUrl)){
				String [] splits = url.split("code=");
				if(splits.length>=2){
					String[] splits2 = splits[1].split("&");
					//Add data
					List<NameValuePair> data = new ArrayList<NameValuePair>(2);
					data.add(new BasicNameValuePair("client_id", clientID));
					data.add(new BasicNameValuePair("redirect_uri",callbackUrl));
					data.add(new BasicNameValuePair("client_secret",clientSecret));
					data.add(new BasicNameValuePair("code", splits2[0]));
					data.add(new BasicNameValuePair("grant_type",	"authorization_code"));
					try {	
						String responseBody = mRestService.sendPostRequestAsync(OneDriveConstant.AUTH_URL, 
								OneDriveConstant.CONTENT_TYPE, data);							
						processTokens(responseBody);
						status = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		else{
			status = true;
		}
		
		if(status == false){
			//TODO: process error from url
			signInError = url;
		}
		
		return status;
	}
	
	public boolean isSignInSuccessful(){
		return accessTokens != null && isTokensExpired() == false;
	}
	
	public String getSignInError(){
		return signInError;
	}
	
	public boolean refreshTokens(){
		Log.d(Constants.LOG_TAG, "refreshTokens");
		boolean status = true;
		//new RefreshAccessTokensTask().execute();		
		List<NameValuePair> data = new ArrayList<NameValuePair>(2);
		data.add(new BasicNameValuePair("client_id", clientID));
		data.add(new BasicNameValuePair("redirect_uri",callbackUrl));
		data.add(new BasicNameValuePair("client_secret",clientSecret));
		data.add(new BasicNameValuePair("refresh_token", refreshTokens));
		data.add(new BasicNameValuePair("grant_type",	"refresh_token"));

		try {				
			String responseBody = mRestService.sendPostRequestAsync(OneDriveConstant.AUTH_URL, 
					OneDriveConstant.CONTENT_TYPE, data);
			processTokens(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	private void processTokens(String responseBody){
		Log.d("CloudPhoto", "responseBody: " + responseBody);
		JSONObject jObject;
		try {
			jObject = new JSONObject(responseBody);
			Log.d("CloudPhoto", "user_id: " + jObject.getString("user_id"));
			userID = jObject.getString("user_id");
			exprireIn = jObject.getInt("expires_in");
			accessTokens = jObject.getString("access_token");
			refreshTokens = jObject.getString("refresh_token");
			saveTokens();
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}
	
	private void saveTokens(){
		long currentTimeStampLong = System.currentTimeMillis()/1000;
		long expireTimeStampLong = currentTimeStampLong + exprireIn - 100;	
		
		SharedPreferences userDetails = CloudApplication.getAppContext()
				.getSharedPreferences(OneDriveConstant.OAUTH_SHAREDPREFERENCES, Context.MODE_PRIVATE);
		Editor edit = userDetails.edit();
		edit.putString("accessTokens", accessTokens);
		edit.putString("refreshTokens", refreshTokens);
		edit.putString("userID", userID);
		edit.putInt("exprireIn", exprireIn);
		edit.putLong("expireTimeStamp", expireTimeStampLong);
		edit.commit();
	}
	
	private void loadTokens(){
		SharedPreferences prefs = CloudApplication.getAppContext()
				.getSharedPreferences(OneDriveConstant.OAUTH_SHAREDPREFERENCES, Context.MODE_PRIVATE); 
		accessTokens = prefs.getString("accessTokens", null);
		refreshTokens = prefs.getString("refreshTokens", null);
		exprireIn = prefs.getInt("exprireIn", -1);
		userID = prefs.getString("userID", null);
		expireTimeStamp = prefs.getLong("expireTimeStamp", -1);
	}
	
	public boolean isTokensExpired(){
		long currentTimeStamp= System.currentTimeMillis()/1000;
		Log.d(Constants.LOG_TAG, "currentTimeStamp: " + currentTimeStamp);
		Log.d(Constants.LOG_TAG, "expireTimeStamp: " + expireTimeStamp);
		return (currentTimeStamp > expireTimeStamp);
	}
	
//	private class RequestAccessTokensTask extends AsyncTask {
//		@Override
//		protected Object doInBackground(Object... params) {
//			postData((String) params[0]);
//			return null;
//		}
//		
//		// protected void onPostExecute(Bitmap result) {
//		// mImageView.setImageBitmap(result);
//		// }
//
//		public void postData(String authorizationCode) {
//			Log.d("CloudPhoto", "authorizationCode: " + authorizationCode);
//			
//			//Add data
//			List<NameValuePair> data = new ArrayList<NameValuePair>(2);
//			data.add(new BasicNameValuePair("client_id", clientID));
//			data.add(new BasicNameValuePair("redirect_uri",callbackUrl));
//			data.add(new BasicNameValuePair("client_secret",clientSecret));
//			data.add(new BasicNameValuePair("code", authorizationCode));
//			data.add(new BasicNameValuePair("grant_type",	"authorization_code"));
//
//			try {				
//				String responseBody = mRestService.sendPostRequest(OneDriveConstant.AUTH_URL, 
//						OneDriveConstant.CONTENT_TYPE, data);
//				processTokens(responseBody);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
//	private class RefreshAccessTokensTask extends AsyncTask {
//		@Override
//		protected Object doInBackground(Object... params) {
//			if(refreshTokens != null){
//				postData();				
//			}
//			return null;
//		}		
//
//		public void postData() {						
//			//Add data
//			List<NameValuePair> data = new ArrayList<NameValuePair>(2);
//			data.add(new BasicNameValuePair("client_id", clientID));
//			data.add(new BasicNameValuePair("redirect_uri",callbackUrl));
//			data.add(new BasicNameValuePair("client_secret",clientSecret));
//			data.add(new BasicNameValuePair("refresh_token", refreshTokens));
//			data.add(new BasicNameValuePair("grant_type",	"refresh_token"));
//
//			try {				
//				String responseBody = mRestService.sendPostRequest(OneDriveConstant.AUTH_URL, 
//						OneDriveConstant.CONTENT_TYPE, data);
//				processTokens(responseBody);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}	
}
