package com.kitalda.cgu.flickrphotos.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.googlecode.flickrjandroid.photos.PhotoList;
import com.kitalda.cgu.flickrphotos.R;
import com.kitalda.cgu.flickrphotos.tasks.PhotoListTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotoGalleryFragmentListener} interface
 * to handle interaction events.
 * Use the {@link PhotoListGalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoListGalleryFragment extends Fragment implements PhotoListTask.PhotoListTaskListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    
    private PhotoList photos;
    private PhotoGalleryFragmentListener mListener;

    public PhotoListGalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PhotoListGalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoListGalleryFragment newInstance() {
        PhotoListGalleryFragment fragment = new PhotoListGalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = container.findViewById(R.id.gallery);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPhotoGalleryFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PhotoGalleryFragmentListener) {
            mListener = (PhotoGalleryFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PhotoGalleryFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
	
	@Override
	public void setPhotoListTaskResult(PhotoList photos) {
		this.photos = photos;
	}
	
	/**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface PhotoGalleryFragmentListener {
        // TODO: Update argument type and name
        void onPhotoGalleryFragmentInteraction(Uri uri);
    }
}
