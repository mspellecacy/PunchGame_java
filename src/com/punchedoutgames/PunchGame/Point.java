package com.punchedoutgames.PunchGame;

class Point {
    private float x;
    private float y;

    public Point(){
    	this.x = 0;
    	this.y = 0;
    }
    
    public float getX(){
    	return x;
    }
    
    public float getY(){
    	return y;
    }
    
    public void setX(float x){
    	this.x = x;
    }
    
    public void setY(float y){
    	this.y = y;
    }
    
	public Point(float x,float y){
    	this.x = x;
    	this.y = y;
    }
    
    public String toString() {
        return x + ", " + y;
    }
}