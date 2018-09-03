package com.kitalda.cgu.flickrphotos.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.googlecode.flickrjandroid.people.User;
import com.kitalda.cgu.flickrphotos.R;
import com.kitalda.cgu.flickrphotos.fragments.MapFragment;
import com.kitalda.cgu.flickrphotos.fragments.PhotoListGalleryFragment;
import com.kitalda.cgu.flickrphotos.fragments.ProfileFragment;

public class MainActivity extends FragmentActivity implements
        PhotoListGalleryFragment.PhotoGalleryFragmentListener,
        MapFragment.OnMapFragmentInteractionListener,
        ProfileFragment.OnProfileFragmentInteractionListener
{
    private PhotoListGalleryFragment publicPhotos;
    private PhotoListGalleryFragment privatePhotos;
    private MapFragment mapFragment;
    private ProfileFragment profileFragment;
    private FragmentManager fragmentManager;
    private User user;
	
	/**
     * Private helper method that changes the visible fragment in the main container.
     * @param fragment
     */
    private void changeTo(Fragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.main_screen_container, fragment);
        ft.commit();
    }

    /**
     * Navigation between Fragments
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_private_photos:
                    if(privatePhotos == null) {
                        privatePhotos = new PhotoListGalleryFragment();
                    }
                    changeTo(privatePhotos);
                    return true;
                case R.id.navigation_public_photos:
                    if(publicPhotos == null) {
                        publicPhotos = new PhotoListGalleryFragment();
                    }
                    changeTo(publicPhotos);
                    return true;
                case R.id.navigation_profile:
                    if(profileFragment == null) {
                        profileFragment = new ProfileFragment();
                    }
                    changeTo(profileFragment);
                    return true;
                case R.id.navigation_map:
                    if(mapFragment == null) {
                        mapFragment = new MapFragment();
                    }
                    changeTo(mapFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //mainActivity = this;
    }

    @Override
    public void onPhotoGalleryFragmentInteraction(Uri uri) {
        //TODO
    }

    @Override
    public void onMapFragmentInteraction(Uri uri) {
        //TODO
    }

    @Override
    public void onProfileFragmentInteraction(Uri uri) {
        //TODO
    }
}
