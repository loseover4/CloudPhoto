package com.now.cloudphoto.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.now.cloudphoto.onedrive.OneDriveConstant;
import com.now.cloudphoto.utilities.Constants;

public class RestServices {	
	
	public String sendGetRequestAsync(String url) throws InterruptedException, ExecutionException{
		SendGetRequestAsyncTask mSendGetRequestAsyncTask = new SendGetRequestAsyncTask();
		return mSendGetRequestAsyncTask.execute(url).get();
	}
	
	public String sendGetRequest(String url) throws IOException{
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = httpclient.execute(new HttpGet(url));
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        String responseString = out.toString();
	        out.close();
	        return responseString;
	    } else{
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }
	}
	
	public String sendPostRequestAsync(String url, String contentType,
			List<NameValuePair> nameValuePairs) throws InterruptedException, ExecutionException{
		SendPostRequestAsyncTask mSendPostRequestAsyncTask = new SendPostRequestAsyncTask();
		return mSendPostRequestAsyncTask.execute(url, contentType, nameValuePairs).get();
	}
	
	public String sendPostRequest(String url, String contentType,
			List<NameValuePair> nameValuePairs) throws ClientProtocolException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader(HTTP.CONTENT_TYPE, contentType);
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,	"UTF-8"));
		HttpResponse response = httpclient.execute(httppost);
			
		String responseBody = EntityUtils.toString(response.getEntity());
		return responseBody;
	}
	
	private class SendGetRequestAsyncTask extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... params) {
			String result = null;
			try {
				result = sendGetRequest(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}		
	}
	
	private class SendPostRequestAsyncTask extends AsyncTask<Object, String, String> {
		@SuppressWarnings("unchecked")
		@Override
		protected String doInBackground(Object... params) {
			if(params.length < 3){
				return "";
			}

			String result = null;
			try {
				String url = (String) params[0];
				String contentType = (String) params[1];
				List<NameValuePair> data = (List<NameValuePair>)params[2];	
				result = sendPostRequest(url, contentType, data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}		
	}
}
