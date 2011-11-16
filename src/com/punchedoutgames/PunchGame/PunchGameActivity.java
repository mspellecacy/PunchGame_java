package com.punchedoutgames.PunchGame;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;

public class PunchGameActivity extends Activity {
	
    /** Called when the activity is first created. */
	private static final String TAG = ViewThread.class.getSimpleName();
    
	//Menus
	private static final int MENU_QUIT = 0;
	private static final int MENU_CREDITS = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        setContentView(R.layout.main);
        Log.v(TAG,"PunchGame Started");
    }
    
    public void showCredits(){
    	Toast.makeText(getApplicationContext(), "Code By: Michael Spellecacy", Toast.LENGTH_LONG).show();
    	Toast.makeText(getApplicationContext(), "Art By: Cody Sowl", Toast.LENGTH_LONG).show();
    	Toast.makeText(getApplicationContext(), "Email: punchedout@frakle.com", Toast.LENGTH_LONG).show();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_QUIT, 0, "Quit");
		menu.add(0, MENU_CREDITS, 0, "Credits");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_QUIT:
			finish();
			break;
		case MENU_CREDITS:
			showCredits();
			break;
		}
		return true;
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	}
}