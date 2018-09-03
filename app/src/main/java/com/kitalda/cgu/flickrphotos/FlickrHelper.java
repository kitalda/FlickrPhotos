package com.kitalda.cgu.flickrphotos;

import android.util.Log;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.REST;
import com.googlecode.flickrjandroid.RequestContext;
import com.googlecode.flickrjandroid.interestingness.InterestingnessInterface;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthInterface;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import com.googlecode.flickrjandroid.photos.PhotosInterface;

import org.json.JSONException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Singleton for handling the interface to the flickrjandroid library.
 */
public final class FlickrHelper {

    private static FlickrHelper instance = null;
    private static final String API_KEY = "d6c0bf02ad9bbfb0c411907e4d439ec8";
    private static final String API_SEC = "6e629be92fd14f5e";
	
	/**
	 * Private constructor as per singleton pattern
	 */
	private FlickrHelper() {
    }
	
	/**
	 * Get the one instance of the class.
	 * @return instance : {@link FlickrHelper}
	 */
	public static FlickrHelper getInstance() {
        if (instance == null) {
            instance = new FlickrHelper();
        }
        return instance;
    }
	
	/**
	 * Private so other classes can't access the library outside of this class
	 * @return f : {@link Flickr}
	 */
	private Flickr getFlickr() {
        try {
            Flickr f = new Flickr(API_KEY, API_SEC, new REST());
            return f;
        } catch (ParserConfigurationException e) {
            return null;
        }
    }
	
	/**
	 * Private so other classes can't access it.
	 * @param token : {@link String}
	 * @param secret : {@link String}
	 * @return f : {@link Flickr}
	 */
	private Flickr getFlickrAuthed(String token, String secret) {
		Flickr f = getFlickr();
		RequestContext requestContext = RequestContext.getRequestContext();
		OAuth auth = new OAuth();
		auth.setToken(new OAuthToken(token, secret));
		requestContext.setOAuth(auth);
		return f;
	}
	
	/**
	 * Get a user from the API
	 * @param userId : {@link String}
	 * @return user : {@link User}
	 * @throws IOException
	 * @throws JSONException
	 * @throws FlickrException
	 */
    public User getUser(String userId) throws IOException, JSONException, FlickrException{
    	Flickr f = getFlickrAuthed(API_KEY, API_SEC);
    	return f.getPeopleInterface().getInfo(userId);
	}
	
	/**
	 * Get a user from the API
	 * @param username : {@link String}
	 * @return user : {@link User}
	 * @throws FlickrException
	 * @throws JSONException
	 * @throws IOException
	 */
	public User getUserFromUsername(String username) throws FlickrException, JSONException, IOException{
    	Flickr f = getFlickrAuthed(API_KEY, API_SEC);
    	User user = f.getPeopleInterface().findByUsername(username);
		Log.d("FlickrHelper", "getUserFromUsername: tried getting user " + username + " result was " + user);
    	return user;
	}
	
	/**
	 * Get a user from the API
	 * @param email : {@link String}
	 * @return user : {@link User}
	 * @throws FlickrException
	 * @throws JSONException
	 * @throws IOException
	 */
	public User getUserFromEmail(String email) throws FlickrException, JSONException, IOException{
		Flickr f = getFlickrAuthed(API_KEY, API_SEC);
		return f.getPeopleInterface().findByEmail(email);
	}
	
	/**
	 * Access the {@link InterestingnessInterface} from the library.
	 * @return {@link InterestingnessInterface}
	 */
    public InterestingnessInterface getInterestingInterface() {
        Flickr f = getFlickr();
        if (f != null) {
            return f.getInterestingnessInterface();
        } else {
            return null;
        }
    }
	
	/**
	 * Access the {@link PhotosInterface} from the library.
	 * @return {@link PhotosInterface}
	 */
	public PhotosInterface getPhotosInterface() {
        Flickr f = getFlickr();
        if (f != null) {
            return f.getPhotosInterface();
        } else {
            return null;
        }
    }
	
	/**
	 * Access the {@link OAuthInterface} from the library.
	 * @return {@link OAuthInterface}
	 */
	public OAuthInterface getOAuthInterface() {
    	Flickr f = getFlickr();
    	if (f != null) {
    		return f.getOAuthInterface();
		} else {
    		return null;
		}
	}

}
