package com.punchedoutgames.PunchGame;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = ViewThread.class.getSimpleName();
	
    private static GameTimer gTimer;
    private static Random rand = new Random();
    private ViewThread mMainThread;
    
    //0 = normal; 1 = hurt; 2 = very hurt; 3 = dead
    private int HIT_STATUS = 0;
    private Point mHitLocation = new Point();
    private SoundManager mSoundManager = new SoundManager();
    
    //Since most of the images are static just preemptively load them. (yet I can't declare them static..)
    private final Bitmap BACKGROUND = BitmapFactory.decodeResource(getResources(), R.drawable.background);
    private final Bitmap DOLL_HURT = BitmapFactory.decodeResource(getResources(), R.drawable.doll_hurt);
    private final Bitmap DOLL_NORM = BitmapFactory.decodeResource(getResources(), R.drawable.doll_norm);
    
    //Animations Stuff
    private final Bitmap IMPLEMENT_SPRITE = BitmapFactory.decodeResource(getResources(), R.drawable.hit_implement);
    private ImplementAnimator implement;
    
    //Other Bitmaps
    private Bitmap curState;
    
    Paint paint = new Paint();

    public Panel(Context context) {
        super(context);
        init();
    }

    public Panel(Context context, AttributeSet attrs) {
    	  super(context, attrs);
    	  init();
    }
    
    public Panel(Context context, AttributeSet attrs, int defStyle) {
    	  super(context, attrs, defStyle);
    	  init();
    }
    
    private void init(){
        getHolder().addCallback(this);
        mMainThread = new ViewThread(getHolder(), this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDrawingCacheEnabled(true);
        setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
        
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        
        //load up the sounds...
        mSoundManager.initSounds(getContext());
        mSoundManager.addSound(1, R.raw.hit_sound1);
        mSoundManager.addSound(2, R.raw.hit_sound2);
        mSoundManager.addSound(3, R.raw.hit_sound3);
        mSoundManager.addSound(4, R.raw.hit_response1);
        mSoundManager.addSound(5, R.raw.hit_response2);
        mSoundManager.addSound(6, R.raw.hit_response3);
        
        //setup implement animation
        implement = new ImplementAnimator(
        		IMPLEMENT_SPRITE,
        		1, 1,
        		104, 150,
        		2, 4);
        

        Log.v(TAG,"DrawView Started");
    }
    public void doDraw(Canvas canvas) {
        
        canvas.drawBitmap(BACKGROUND, null, canvas.getClipBounds(), paint);
        //0 = normal; 1 = hurt; 2 = very hurt; 3 = dead
        switch(HIT_STATUS){
        	case 0: 
        		curState = DOLL_NORM;
        		break;
        	case 1:
        		curState = DOLL_HURT; 
        		break;
        }
        
        canvas.drawBitmap(curState, null, canvas.getClipBounds(), paint);
        
    	if(mHitLocation.x!=0 & mHitLocation.y!=0){
    		drawHit(canvas, (int) mHitLocation.x, (int) mHitLocation.y);
    	}
    }
    
    public void drawHit(Canvas canvas, int x, int y){
		int mX = (int) mHitLocation.x-IMPLEMENT_SPRITE.getHeight()/2;
    	int mY = (int) mHitLocation.y-(IMPLEMENT_SPRITE.getWidth()/4)/2;
    	
    	implement.setX(mX);
    	implement.setY(mY);
    	implement.draw(canvas);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	mHitLocation.x = event.getX();
    	mHitLocation.y = event.getY();
    	
        if(event.getAction() != MotionEvent.ACTION_DOWN)
        {
        	return super.onTouchEvent(event);
        }
        
        mSoundManager.playSound(rand.nextInt(2)+1);
    	if(HIT_STATUS==0){
    		mSoundManager.playSound(rand.nextInt(2)+4);
        	gTimer = new GameTimer(2000,1);
        	gTimer.start();
    	}
    	HIT_STATUS = 1;
    	invalidate();
    	//reset the animations before drawing...
    	implement.setCurrentLoop(0);
    	implement.setCurrentFrame(0);
    	return super.onTouchEvent(event);
    }

	public void update() {
		implement.update(System.currentTimeMillis());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!mMainThread.isAlive()) {
	        mMainThread = new ViewThread(holder, this);
	        mMainThread.setRunning(true);
	        mMainThread.start();
	    }
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	    if (mMainThread.isAlive()) {
	        mMainThread.setRunning(false);
	    }
	}
	
	public class GameTimer extends CountDownTimer{
		
		public GameTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			// Zero out all the variables to reset it back to 'DOLL_NORM'
			HIT_STATUS = 0;
			mHitLocation.x = 0;
			mHitLocation.y = 0;
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	}
}

class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}