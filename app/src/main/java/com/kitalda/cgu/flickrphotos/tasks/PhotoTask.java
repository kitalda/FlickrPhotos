package com.kitalda.cgu.flickrphotos.tasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.googlecode.flickrjandroid.photos.Photo;
import com.kitalda.cgu.flickrphotos.FlickrHelper;

/**
 * AsyncTask for loading a photo from its metadata (in form of the Photo class from flickrjandroid
 */
public class PhotoTask extends AsyncTask {
	
	Photo photo;
	Drawable image;
	PhotoTaskListener listener;
	
	public PhotoTask (Photo photo, PhotoTaskListener listener) {
		this.photo = photo;
		this.listener = listener;
	}
	
	//TODO preexecute that shows progress dialog
	
	@Override
	protected Object doInBackground(Object[] objects) {
		FlickrHelper helper = FlickrHelper.getInstance();
		try {
			image = Drawable.createFromStream(helper.getPhotosInterface().getImageAsStream(photo, 1), "Flickr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Object o) {
		super.onPostExecute(o);
		listener.setPhotoTaskResult(image);
	}
	
	/**
	 * Interface that must be implemented by classes that intend to use this task.
	 */
	public interface PhotoTaskListener {
		public void setPhotoTaskResult(Drawable image);
	}
}
