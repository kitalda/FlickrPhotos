package com.kitalda.cgu.flickrphotos;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;

/**
 * Singleton class for handling preferences.
 */
public class PreferencesHelper {
	
	private static PreferencesHelper instance;
	
	private static final String PREFS_NAME = "flickr_photos_login_pref";
	private static final String KEY_OAUTH_TOKEN = "flickr_photos_login_oauthToken";
	private static final String KEY_TOKEN_SECRET = "flickr_photos_login_tokenSecret";
	private static final String KEY_USER_NAME = "flickr_photos_login_userName";
	private static final String KEY_USER_ID = "flickr_photos_login_userId";
	
	/**
	 * Empty, private constructor.
	 */
	private PreferencesHelper()
	{ }
	
	/**
	 * The aquisition method as per singleton pattern.
	 * @return instance: PreferencesHelper
	 */
	public static PreferencesHelper getInstance() {
		if (instance == null) {
			instance = new PreferencesHelper();
		}
		return instance;
	}
	
	/**
	 * Saves the OAuth authorization token in the preferences, so the user can stay logged in
	 * between sessions.
	 * @param userName : String
	 * @param userId : String
	 * @param token : String
	 * @param tokenSecret : String
	 * @param context : Context
	 */
	public void saveOAuthToken(String userName, String userId, String token, String tokenSecret, Context context) {
		SharedPreferences sp = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(KEY_OAUTH_TOKEN, token);
		editor.putString(KEY_TOKEN_SECRET, tokenSecret);
		editor.putString(KEY_USER_NAME, userName);
		editor.putString(KEY_USER_ID, userId);
		//Using apply instead of commit to allow it to happen in the background
		editor.apply();
	}
	
	/**
	 * Saves the OAuth authorization token in the preferences, so the user can stay logged in
	 * between sessions.
	 * @param auth : flickrjandroid.oauth.OAuth
	 * @param context : Context
	 */
	public void saveOAuth(OAuth auth, Context context) {
		User u = auth.getUser();
		saveOAuthToken(u.getUsername(), u.getId(), auth.getToken().getOauthToken(), auth.getToken().getOauthTokenSecret(), context);
	}
	
	/**
	 * Fetches the stored user id from the preferences.
	 * @param context : Context
	 * @return userid : String
	 */
	public String getUserId(Context context){
		SharedPreferences sp = context.getSharedPreferences(PREFS_NAME, Context
				.MODE_PRIVATE);
		return sp.getString(KEY_USER_ID, null);
	}
	
	/**
	 * Fetches the stored  OAuth from preferences.
	 * @param context : Context
	 * @return oauth : flickrjandroid.oauth.OAuth
	 */
	public OAuth getOAuth(Context context) {
		SharedPreferences sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		String oauthTokenString = sp.getString(KEY_OAUTH_TOKEN, null);
		String tokenSecret = sp.getString(KEY_TOKEN_SECRET, null);
		if (oauthTokenString == null && tokenSecret == null) {
			Log.d("PreferencesHelper", "getOAuth: token not retrieved");
			return null;
		}
		OAuth oauth = new OAuth();
		String userName = sp.getString(KEY_USER_NAME, null);
		String userId = sp.getString(KEY_USER_ID, null);
		if (userId != null) {
			User user = new User();
			user.setUsername(userName);
			user.setId(userId);
			oauth.setUser(user);
		}
		OAuthToken oauthToken = new OAuthToken();
		oauth.setToken(oauthToken);
		oauthToken.setOauthToken(oauthTokenString);
		oauthToken.setOauthTokenSecret(tokenSecret);
		return oauth;
	}
}
