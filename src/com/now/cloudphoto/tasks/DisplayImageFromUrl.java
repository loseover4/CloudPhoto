package com.now.cloudphoto.tasks;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.now.nowtools.customviews.photoviewer.PhotoViewer;

public class DisplayImageFromUrl extends AsyncTask<String, Void, Bitmap> {
	
    PhotoViewer photoViewer;
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("TestHung", "Loading...");
    }
    
    public DisplayImageFromUrl(PhotoViewer mPhotoViewer) {
        this.photoViewer = mPhotoViewer;
    }
    
    protected Bitmap doInBackground(String... urls) {
        Log.d("TestHung", "Downloading...");
        String urldisplay = urls[0];
        Log.d("TestHung", "urldisplay: "+ urldisplay);
        Bitmap mPhoto = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mPhoto = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("TestHung", e.getMessage());
            e.printStackTrace();
        }

        return mPhoto;

    }
    protected void onPostExecute(Bitmap result) {
        Log.d("TestHung", "Showing...");
    	photoViewer.setImageBitmap(result);
    }
}
