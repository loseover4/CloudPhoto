package com.now.cloudphoto.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.now.cloudphoto.R;
import com.now.cloudphoto.activities.ViewerActivity;
import com.now.cloudphoto.adapters.BrowseListAdapter;
import com.now.cloudphoto.models.BrowseListItemDataModel;
import com.now.cloudphoto.onedrive.manager.UrlManager;
import com.now.cloudphoto.onedrive.model.json.Item;
import com.now.cloudphoto.onedrive.model.json.Thumbnails;
import com.now.cloudphoto.onedrive.services.OneDriveServices;
import com.now.cloudphoto.utilities.Constants;
import com.now.cloudphoto.utilities.Util;

public class BrowseFragment extends Fragment {
	private OneDriveServices mOneDriveServices;
	
	private ListView browseListView;
	private List<BrowseListItemDataModel> browseListItems;
	private ArrayAdapter browseAdapter;
	
	public BrowseFragment(){		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_browse, container, false);			
		mOneDriveServices = new OneDriveServices();	
		browseListView = (ListView) rootView.findViewById(R.id.listview_browse);
		
		//Get agrs
		Bundle agrs = getArguments();
		if(agrs != null){
			String parentId = getArguments().getString("parentId");
			boolean isFromDrive = getArguments().getBoolean("isFromDrive");
			updateBrowseList(isFromDrive, parentId);
		}		
		
		return rootView;
	}
		
	public void updateBrowseList(boolean isFromDrive, String parentID){
		if(Util.isStringNullOrEmpty(parentID)){
			return;
		}
		if(isFromDrive == true){
			browseListItems = getRootItemsOfDrive(parentID);
		}
		else{
			browseListItems = getItemChildren(parentID);
		}	

		browseAdapter = new BrowseListAdapter(getActivity(), R.layout.browse_list_item, browseListItems);
		browseListView.setAdapter(browseAdapter);
		browseListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				BrowseListItemDataModel selectedItem = (BrowseListItemDataModel) parent.getItemAtPosition(position);
				
				if(selectedItem.isFolder() == true){						
					List<BrowseListItemDataModel> listChildren = getItemChildren(selectedItem.getId());
					
					browseListItems.clear();			
					browseListItems.addAll(listChildren);
					browseAdapter.notifyDataSetChanged();
				}
				else{
					Item file = mOneDriveServices.getItem(selectedItem.getId());
					Log.d(Constants.LOG_TAG, "web Url: "+ file.getContentDownloadUrl());
					
					//Start Viewer activity
					Intent myIntent = new Intent(getActivity(),ViewerActivity.class);
					myIntent.putExtra("url", file.getContentDownloadUrl());
					myIntent.putExtra("type", file.getFile().getMimetype());
					Log.d(Constants.LOG_TAG, "file.getFile().getMimetype(): "+ file.getFile().getMimetype());
					startActivity(myIntent);					

//					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(file.getContentDownloadUrl()));
//					startActivity(browserIntent);
				}
			}
		});
	}
	
	public boolean isAllowParentBack(){
		return mOneDriveServices.isBackStackEmpty();
	}
	
	public void goBack(){
		browseListItems.clear();				
		browseListItems.addAll(getItemsFromBackStack());
		browseAdapter.notifyDataSetChanged();
	}
	
	private List<BrowseListItemDataModel> getRootItemsOfDrive(String driveID){
		Item[] items = mOneDriveServices.getRootItemsOfDrive(driveID);
		return getBrowseListItemDataModelList(items);
	}
	
	private List<BrowseListItemDataModel> getItemChildren(String parentID){
		Item[] items = mOneDriveServices.getItemChildren(parentID);
		return getBrowseListItemDataModelList(items);
	}
	
	private List<BrowseListItemDataModel> getItemsFromBackStack(){
		Item[] items = mOneDriveServices.getItemsFromBackStack();
		return getBrowseListItemDataModelList(items);
	}
	
	private List<BrowseListItemDataModel> getBrowseListItemDataModelList(Item[] items){
		List<BrowseListItemDataModel> listItems = new ArrayList<BrowseListItemDataModel>();
		for(int i = 0 ; i < items.length; i++){
			BrowseListItemDataModel model = new BrowseListItemDataModel(items[i].getId(), items[i].getName(), items[i].isFolder());
			if(items[i].isImage() == true && items[i].getThumbnails() != null && items[i].getThumbnails().length > 0){
				model.setThumbnailUrl(items[i].getThumbnails()[0].getSmall().getUrl());
			}
			listItems.add(model);
		}
		return listItems;
	}
}
