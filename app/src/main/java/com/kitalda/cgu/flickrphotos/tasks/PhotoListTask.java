package com.kitalda.cgu.flickrphotos.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.googlecode.flickrjandroid.photos.PhotoList;
import com.kitalda.cgu.flickrphotos.FlickrHelper;

public class PhotoListTask extends AsyncTask {
	
	private PhotoList photos;
	private PhotoListTaskListener listener;
	
	public PhotoListTask(PhotoListTaskListener l) {
		listener = l;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected PhotoList doInBackground(Object[] objects) {
		FlickrHelper helper = FlickrHelper.getInstance();
		try {
			photos = helper.getPhotosInterface().getRecent(null, 10, 1);
		} catch (Exception e) {
			Log.d("PhotoListTask", "doInBackground: failed", e);
			return null;
		}
		return photos;
	}
	
	@Override
	protected void onPostExecute(Object o) {
		listener.setPhotoListTaskResult(photos);
		super.onPostExecute(o);
	}
	
	/**
	 * Interface to be implemented by listener
	 */
	public interface PhotoListTaskListener {
		public void setPhotoListTaskResult(PhotoList photos);
	}
}
