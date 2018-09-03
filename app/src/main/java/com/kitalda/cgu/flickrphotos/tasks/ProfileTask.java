package com.kitalda.cgu.flickrphotos.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.googlecode.flickrjandroid.people.User;
import com.kitalda.cgu.flickrphotos.FlickrHelper;
import com.kitalda.cgu.flickrphotos.PreferencesHelper;
import com.kitalda.cgu.flickrphotos.R;

/**
 * AsyncTask for aquiring profile data.
 */
public class ProfileTask extends AsyncTask<Void, Integer, User> {

    private Context context;
    private ProgressDialog progressDialog;
    private ProfileTaskListener listener;

    /**
     * Constructor
     * @param context : Context
     */
    public ProfileTask(Context context, ProfileTaskListener l){
        super();
        this.context = context;
        listener = l;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show a progress dialog so the user know something is happening.
        progressDialog = ProgressDialog.show(context, "",
                context.getString(R.string.aq_profile));
    }
    
    @Override
    protected User doInBackground(Void... voids) {
    	//Fetch the user
        String id = PreferencesHelper.getInstance().getUserId(context);
        FlickrHelper helper = FlickrHelper.getInstance();
        User user = null;
        try {
        	user = helper.getUser(id);
        	//This is where more user data should be gathered as well
        } catch (Exception e) {
			Log.d(this.getClass().toString(), "Tried getting profile info", e);
        }
        return user;
    }
	
	@Override
	protected void onPostExecute(User result) {
    	//First dismiss the progress dialog
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
		//Then notify the listener of the result
		if (result != null) {
			listener.setProfileTaskResult(result);
		} else {
			Toast.makeText(context, "result was null", Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * Interface that those classes, that would use this Task, must implement to be
	 * informed of the result.
	 */
	public interface ProfileTaskListener {
    	void setProfileTaskResult(User user);
	}
}
