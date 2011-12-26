package com.punchedoutgames.PunchGame;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

public class Doll {
	//currently unused class, still working on the particulars on how I want it to work.
	private Bitmap dollBaseImage;
	private Bitmap dollImage;
	private Rect dollRect;
	private Paint dollPaint;
	//0 = normal; 1 = hurt; 2 = very hurt; 3 = dead
	
	//Preferences, context.
	private SharedPreferences preferences;
	private Context context;
	
	private int dollStatus;
	//doll stuff
	// private final Bitmap DOLL_HURT = BitmapFactory.decodeResource(getResources(), R.drawable.doll_hurt);
	// private final Bitmap DOLL_NORM = BitmapFactory.decodeResource(getResources(), R.drawable.doll_norm);
	
	//
	
	public Doll(Context context){
		//setup context
		this.context = context;
		
		//Load preferences so we know who they like beating up on the most, default to the baby (for now)
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String dollName = preferences.getString("dollName","baby");
		
		//Drawable drawable = icons.getDrawable(0);
		//initDoll();
		
		
		
	}
	
	private void initDoll(int DOLL_NAME) {
		
		
		//switch(){
		//	case OBAMA_DOLL:
		//	//Place Holders;
		//	break;
		//}
		dollRect = new Rect(0,0,dollBaseImage.getWidth(),dollBaseImage.getHeight());
		dollPaint = new Paint();
        dollPaint.setColor(Color.BLACK);
        dollPaint.setAntiAlias(true);
	}

	public int getStatus(){
		return dollStatus;
	}
	
	public void setStatus(int status){
		dollStatus = status;
	}
	
	public void update(){
        
		//0 = normal; 1 = hurt; 2 = very hurt; 3 = dead
        switch(dollStatus){
        	case 0: 
        		//dollImage = DOLL_NORM;
        		break;
        	case 1:
        		//dollImage = DOLL_HURT; 
        		break;
        }
        
		//add image stitching so we can have location based impact graphics.
		dollImage = dollBaseImage;
	}
	
	public void doDraw(Canvas canvas){
		canvas.drawBitmap(dollImage,dollRect,canvas.getClipBounds(),dollPaint);
	}
}
