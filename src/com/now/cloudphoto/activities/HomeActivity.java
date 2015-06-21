package com.now.cloudphoto.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.now.cloudphoto.R;
import com.now.cloudphoto.fragments.BrowseFragment;
import com.now.cloudphoto.fragments.DrivesFragment;
import com.now.cloudphoto.fragments.DrivesFragment.OnDriveSelectedListener;

public class HomeActivity extends FragmentActivity implements OnDriveSelectedListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new DrivesFragment()).commit();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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

	@Override
	public void onDriveSelected(String id) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		BrowseFragment mBrowseFragment = new BrowseFragment();
		Bundle browsFragmentBundle = new Bundle();
		browsFragmentBundle.putString("parentId", id);
		browsFragmentBundle.putBoolean("isFromDrive", true);
		mBrowseFragment.setArguments(browsFragmentBundle);
		
		ft.replace(R.id.container, mBrowseFragment);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	@Override
	public void onBackPressed() {
		boolean isParentGoBack = true;
		Fragment mFragment = getFragmentManager().findFragmentById(R.id.container);

		if(mFragment instanceof BrowseFragment){
			BrowseFragment mBrowseFragment = (BrowseFragment) mFragment;
			if(mBrowseFragment.isAllowParentBack() == false){
				mBrowseFragment.goBack();
				isParentGoBack = false;
			}
		}
		if(isParentGoBack == true){
			super.onBackPressed();
		}
	}
}
