package com.punchedoutgames.PunchGame;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;


public class Preferences extends PreferenceActivity {
	
	//For Logging Purposes. 
	private static final String TAG = Preferences.class.getSimpleName();
	private static final boolean DEBUG = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
		
		if(DEBUG){
        	Log.v(TAG,"onCreate()'d");
        }
	}
}
