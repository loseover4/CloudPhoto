package com.now.cloudphoto.fragments;

import com.now.cloudphoto.R;
import com.now.cloudphoto.utilities.Constants;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class ViewerWebViewFragment extends Fragment{
	private WebView webView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_viewer, container, false);
		webView = (WebView) rootView.findViewById(R.id.viewer_web_view);
		
		//Get url that is sent from activity
		Bundle agrs = getArguments();
		String url = agrs.getString("url");
		String type = agrs.getString("type");
		
		// Load data using webview
		if(url != null){	
			Log.d(Constants.LOG_TAG, "ViewerWebViewFragment type: "+ type);
			url = processUrl(url, type);
			Log.d(Constants.LOG_TAG, "webView viewer Url: "+ url);
			webView.loadUrl(url);
		}
		
		return rootView;
	}
	
	private String processUrl(String url, String type){
		if(url == null || type == null){
			return url;
		}
		
		//Keep url for image for now
		if(type.contains("image")){
			return url;
		}
		
		//The rest is using google docs
		return "https://docs.google.com/gview?embedded=true&url="+url;
	}
	
}
