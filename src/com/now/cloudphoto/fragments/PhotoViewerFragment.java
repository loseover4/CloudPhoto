package com.now.cloudphoto.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.now.cloudphoto.R;
import com.now.cloudphoto.tasks.DisplayImageFromUrl;
import com.now.nowtools.customviews.photoviewer.PhotoViewer;

public class PhotoViewerFragment  extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_photo_viewer, container, false);
		
		//Get url that is sent from activity
		Bundle agrs = getArguments();
		String url = agrs.getString("url");	
		
		PhotoViewer mPhotoViewer = (PhotoViewer) rootView.findViewById(R.id.photo_viewer);
		DisplayImageFromUrl mDisplayImageFromUrl = new DisplayImageFromUrl(mPhotoViewer);
		mDisplayImageFromUrl.execute(url);
	
		return rootView;
	}
}
