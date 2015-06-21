package com.now.cloudphoto.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.now.cloudphoto.R;
import com.now.cloudphoto.models.BrowseListItemDataModel;
import com.squareup.picasso.Picasso;

public class BrowseListAdapter extends ArrayAdapter<BrowseListItemDataModel> {

    public BrowseListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public BrowseListAdapter(Context context, int resource, List<BrowseListItemDataModel> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View mView = convertView;

        if (mView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
            mView = mLayoutInflater.inflate(R.layout.browse_list_item, null);
        }

        BrowseListItemDataModel item = getItem(position);

        if (item != null) {
            ImageView mThumbnail = (ImageView) mView.findViewById(R.id.thumbnail_image_list_item);
            TextView mTitle = (TextView) mView.findViewById(R.id.title_list_item);
            TextView mSubtitle = (TextView) mView.findViewById(R.id.subtitle_list_item);

            if (mThumbnail != null && item.getThumbnailUrl()!= null) {
            	Picasso.with(getContext()).load(item.getThumbnailUrl()).into(mThumbnail);
            }
            else{
            	Picasso.with(getContext()).load(R.drawable.bluesquare).into(mThumbnail);
            }

            if (mTitle != null) {
            	mTitle.setText(item.getTitle());
            }

            if (mSubtitle != null) {
            	mSubtitle.setText(item.getId());
            }
        }

        return mView;
    }
//    
//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
}