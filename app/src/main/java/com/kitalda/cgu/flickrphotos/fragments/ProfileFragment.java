package com.kitalda.cgu.flickrphotos.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.googlecode.flickrjandroid.people.User;
import com.kitalda.cgu.flickrphotos.R;
import com.kitalda.cgu.flickrphotos.tasks.ProfileTask;

/**
 * Fragment subclass to show profile data
 */
public class ProfileFragment extends Fragment implements ProfileTask.ProfileTaskListener{
	
	TextView name;
	TextView username;

    private OnProfileFragmentInteractionListener listener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_fragment_layout, container, false);
		name = view.findViewById(R.id.txt_name);
		username = view.findViewById(R.id.txt_username);
		
		//Start the task of getting profile data
		ProfileTask profileTask = new ProfileTask(getContext(), this);
		profileTask.execute((Void) null);
        return view;
    }
    
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onProfileFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProfileFragmentInteractionListener) {
            listener = (OnProfileFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProfileFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
	
	/**
	 * For listening for the result of the task to get profile info.
	 * @param user : User
	 */
	@Override
	public void setProfileTaskResult(User user) {
		name.setText(user.getRealName());
		username.setText(user.getUsername());
		// This is where more data from the user should be populated into the view.
	}
	
	/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnProfileFragmentInteractionListener {
        void onProfileFragmentInteraction(Uri uri);
    }
}
