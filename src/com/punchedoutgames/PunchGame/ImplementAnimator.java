package com.punchedoutgames.PunchGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class ImplementAnimator {
	
		private static final String TAG = ImplementAnimator.class.getSimpleName();

		private Bitmap bitmap = null;		// the animation sequence
		private Rect sourceRect;	// the rectangle to be drawn from the animation bitmap
		private int frameNr;		// number of frames in animation
		private int currentFrame;	// the current frame
		private long frameTicker;	// the time of the last frame update
		private int framePeriod;	// milliseconds between each frame (1000/fps)

		private int spriteWidth;	// the width of the sprite to calculate the cut out rectangle
		private int spriteHeight;	// the height of the sprite

		private int x;				// the X coordinate of the object (top left of the image)
		private int y;				// the Y coordinate of the object (top left of the image)
		private int loopCount;		// number of times to loop this animation
		private int currentLoop;    // number of loops this animation has currently run
		
		public Bitmap getBitmap() {
			return bitmap;
		}
		public void setBitmap(Bitmap bitmap) {
			this.bitmap = bitmap;
		}
		public Rect getSourceRect() {
			return sourceRect;
		}
		public void setSourceRect(Rect sourceRect) {
			this.sourceRect = sourceRect;
		}
		public int getFrameNr() {
			return frameNr;
		}
		public void setFrameNr(int frameNr) {
			this.frameNr = frameNr;
		}
		public int getCurrentFrame() {
			return currentFrame;
		}
		public void setCurrentFrame(int currentFrame) {
			this.currentFrame = currentFrame;
		}
		public int getFramePeriod() {
			return framePeriod;
		}
		public void setFramePeriod(int framePeriod) {
			this.framePeriod = framePeriod;
		}
		public int getSpriteWidth() {
			return spriteWidth;
		}
		public void setSpriteWidth(int spriteWidth) {
			this.spriteWidth = spriteWidth;
		}
		public int getSpriteHeight() {
			return spriteHeight;
		}
		public void setSpriteHeight(int spriteHeight) {
			this.spriteHeight = spriteHeight;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getLoopCount() {
			return loopCount;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void setLoopCount(int loopCount) {
			this.loopCount = loopCount;
		}
		
		public void setCurrentLoop(int currentLoop) {
			this.currentLoop = currentLoop;
		}
		public int getCurrentLoop() {
			return currentLoop;
		}
		
		
		public ImplementAnimator(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
			this.bitmap = bitmap;
			this.x = x;
			this.y = y;
			currentLoop = 0;
			currentFrame = 0;
			frameNr = frameCount;
			spriteWidth = bitmap.getWidth() / frameCount;
			spriteHeight = bitmap.getHeight();
			sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
			framePeriod = 200 / fps;
			frameTicker = 0l;
			Log.v(TAG,"new animation");
		}
		
		// the update method for the implement
		public void update(long gameTime) {
			if (gameTime > frameTicker + framePeriod) {
				frameTicker = gameTime;
				// increment the frame
				currentFrame++;
				if (currentFrame >= frameNr) {
					currentFrame = 0;
					currentLoop++;
					
				}
			}
			// define the rectangle to cut out sprite
			this.sourceRect.left = currentFrame * spriteWidth;
			this.sourceRect.right = this.sourceRect.left + spriteWidth;
		}
		
		// the draw method which draws the corresponding frame
		public void draw(Canvas canvas) {
			// where to draw the sprite
			if(currentLoop <= loopCount){ 
				Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
				canvas.drawBitmap(bitmap, sourceRect, destRect, null);
			}

		}

}


