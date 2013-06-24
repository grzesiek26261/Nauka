package com.nauka;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;

import android.view.MotionEvent;

public class Wybor extends Scene implements IOnSceneTouchListener, IScrollDetectorListener
{
	MainActivity act;
	
	Sprite background;
	Sprite is1;
	Sprite is2;
	Sprite is3;
	int w,h;
	private float mTouchX , mTouchY , mTouchOffsetX , mTouchOffsetY ;
	boolean canchange = false;
	
	
	
	Wybor()
	{
		act = MainActivity.getSharedInstance();
		
		w = act.WIDTH; h = act.HEIGHT;
		act.mCamera.setCenter(w / 2 , h / 2);
		
		 
		act.ID = 2;
		lb background_t = new lb("Menu", 512, 512);
		background = new Sprite(0, 0, background_t.region);
		background.setScaleCenter(0, 0);
		background.setWidth(w);
		background.setHeight(h);		
	
		lb is1_t = new lb("is1", 512, 512);
		is1 = new Sprite(0, 0, is1_t.region)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown()&& canchange == true)
				{
					act.setCurrentScene(new Gra(1));
					return true;
				}
				else
					return false;
			}
		};
		is1.setScaleCenter(0, 0);
		is1.setScale(w / is1.getWidth() / 1f);
		is1.setPosition(w * 0.99f - is1.getWidthScaled(), h  * 0.6f - is1.getHeightScaled());
		
		lb is2_t = new lb("is2", 512, 512);
		is2 = new Sprite(0, 0, is2_t.region)
	    {
	    	@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown()&& canchange == true)
				{
					act.setCurrentScene(new Gra(2));
					return true;
				}
				else
					return false;
			}
		};
		is2.setScaleCenter(0, 0);
		is2.setScale(w / is2.getWidth() / 1f);
		is2.setPosition(w * 0.99f - is2.getWidthScaled(), h  * 1.1f - is2.getHeightScaled());
		
		lb is3_t = new lb("is3", 512, 512);
		is3 = new Sprite(0, 0, is3_t.region)
	    {
	    	@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown() && canchange == true)
				{
					act.setCurrentScene(new Gra(3));
					return true;
				}
				else
					return false;
			}
		};
		is3.setScaleCenter(0, 0);
		is3.setScale(w / is3.getWidth() );
		is3.setPosition(w * 0.99f - is3.getWidthScaled(), h  * 1.6f - is3.getHeightScaled());
		
		attachChild(background);
		attachChild(is1); registerTouchArea(is1);
		attachChild(is2); registerTouchArea(is2);
		attachChild(is3); registerTouchArea(is3);
		
		
		this.setOnSceneTouchListener(this);
	}

	@Override
	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pTouchEvent) {

		   if(pTouchEvent.getAction() == MotionEvent.ACTION_DOWN )
	        {
			   canchange =true;
	                mTouchX = pTouchEvent.getMotionEvent().getX();
	                mTouchY = pTouchEvent.getMotionEvent().getY();          
	        }
	        else if(pTouchEvent.getAction() == MotionEvent.ACTION_MOVE)
	        {
	        	canchange = false;
	                float newX = pTouchEvent.getMotionEvent().getX() ;
	                float newY = pTouchEvent.getMotionEvent().getY();             
	                mTouchOffsetX = (newX - mTouchX);
	                mTouchOffsetY = (newY - mTouchY);
	                float newScrollX = act.mCamera.getCenterX() - mTouchOffsetX;
	                float newScrollY = act.mCamera.getCenterY() - mTouchOffsetY;               
	                act.mCamera.setCenter(newScrollX, newScrollY);
	                mTouchX = newX;
	                mTouchY = newY;                
	        }
		   //return mPinch.handle(pTouchEvent);
		
		return false;
	}
}
