package com.punchedoutgames.PunchGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Doll {
	//currently unused class, still working on the particulars on how I want it to work.
	private Bitmap dollBaseImage;
	private Bitmap dollImage;
	private Rect dollRect;
	private Paint dollPaint;
	//0 = normal; 1 = hurt; 2 = very hurt; 3 = dead
	private int dollStatus;
	//doll stuff
   // private final Bitmap DOLL_HURT = BitmapFactory.decodeResource(getResources(), R.drawable.doll_hurt);
   // private final Bitmap DOLL_NORM = BitmapFactory.decodeResource(getResources(), R.drawable.doll_norm);
	
	public void Doll(Bitmap dollBaseImage){
		this.dollBaseImage = dollBaseImage;
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
