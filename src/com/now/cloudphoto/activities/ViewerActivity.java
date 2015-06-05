package com.now.cloudphoto.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.now.cloudphoto.R;
import com.now.cloudphoto.fragments.PhotoViewerFragment;
import com.now.cloudphoto.fragments.ViewerWebViewFragment;
import com.now.cloudphoto.utilities.Constants;

public class ViewerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewer);

		if (savedInstanceState == null) {
			Intent intent = getIntent();
			String url = intent.getStringExtra("url");
			String type = intent.getStringExtra("type");

			Log.d(Constants.LOG_TAG, "ViewerActivity type: "+ type);
			//Create new fragment and add url to that fragment
//			ViewerWebViewFragment mViewerWebViewFragment = new ViewerWebViewFragment();
//			Bundle args = new Bundle();
//			args.putString("url", url);
//			args.putString("type", type);
//			mViewerWebViewFragment.setArguments(args);
			
			PhotoViewerFragment mPhotoViewerFragment = new PhotoViewerFragment();
			Bundle args = new Bundle();
			args.putString("url", url);
			mPhotoViewerFragment.setArguments(args);
			
			//Start new Viewer fragment
			//getFragmentManager().beginTransaction().add(R.id.viewer_fragment_container,mViewerWebViewFragment).commit();
			getFragmentManager().beginTransaction().add(R.id.viewer_fragment_container, mPhotoViewerFragment).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.viewer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
