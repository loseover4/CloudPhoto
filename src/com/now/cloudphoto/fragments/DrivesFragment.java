package com.now.cloudphoto.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.now.cloudphoto.R;
import com.now.cloudphoto.models.BrowseListItemDataModel;
import com.now.cloudphoto.onedrive.model.json.Drive;
import com.now.cloudphoto.onedrive.services.OneDriveServices;

public class DrivesFragment extends Fragment {
	private ListView drivesListView;
	private OneDriveServices mOneDriveServices;
	private OnDriveSelectedListener mOnDriveSelectedListener;
	
	public DrivesFragment(){		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			mOnDriveSelectedListener = (OnDriveSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnDriveSelectedListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_drives, container, false);
		
		mOneDriveServices = new OneDriveServices();
		
		Drive[] drives = mOneDriveServices.getDrives();		
		final List<BrowseListItemDataModel> listItems = new ArrayList<BrowseListItemDataModel>();
		for(int i = 0 ; i < drives.length; i++){
			listItems.add(new BrowseListItemDataModel(drives[i].getId(), drives[i].getDriveType(), false));
		}
		
		drivesListView = (ListView) rootView.findViewById(R.id.listview_drives);		
		final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
		drivesListView.setAdapter(adapter);		
		drivesListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				BrowseListItemDataModel selectedItem = (BrowseListItemDataModel) parent.getItemAtPosition(position);
				mOnDriveSelectedListener.onDriveSelected(selectedItem.getId());
			}
		});
		
		return rootView;
	}
	
    public interface OnDriveSelectedListener {
        public void onDriveSelected(String id);
    }
}
