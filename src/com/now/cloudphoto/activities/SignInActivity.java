package com.now.cloudphoto.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.now.cloudphoto.R;
import com.now.cloudphoto.onedrive.authentication.OneDriveOAuth;

public class SignInActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sign_in);
		
		WebView webView = (WebView) findViewById(R.id.sign_in_web_view);
		final OneDriveOAuth mOneDriveOAuth = new OneDriveOAuth();		

		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				Log.d("CloudPhoto", "finish url: "+url);
				boolean status = mOneDriveOAuth.getTokens(url);
				if(status == true){
					goToHomeActivity();
				}
			}
		});
		
		if(mOneDriveOAuth.isSignInSuccessful() == true){
			goToHomeActivity();
		}
		else{
			webView.loadUrl(mOneDriveOAuth.getAuthUrl());				
		}		
	}
	
	private void goToHomeActivity(){
		Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
		startActivity(intent);
		finish();
	}
}
