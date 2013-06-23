package com.nauka;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.view.MotionEvent;



public class LevelSelector extends Scene implements IOnSceneTouchListener, IScrollDetectorListener
{
	MainActivity act;
	int w,h;
	TextureRegion tex;
	Sprite[] btn;
	Text[] number;
	Font font;
	boolean[] can_click;
	int przerwa ;
	private float mTouchX , mTouchY , mTouchOffsetX , mTouchOffsetY ;
	
	Sprite[] stars;
	char[] round_stars_earned;
	boolean[] level_unlocked;
	
	LevelSelector(int liczba,lb tlo, lb level, lb level_lock,lb star)
	{
		act= MainActivity.getSharedInstance();
		w = act.WIDTH; h = act.HEIGHT;
		//mTouchX = act.mCamera.getCenterX()*1.8f; mTouchY = act.mCamera.getCenterY()*1.8f; mTouchOffsetX = 0; mTouchOffsetY = 0;
		
		  Sprite background = new Sprite(0,0,tlo.region);
		  background.setSize(w,h);
		  this.setBackground(new SpriteBackground(background));
		
		
		
		
		
		przerwa = 20;
		
		font = act.mFont;
		tex = level.region;
		
		
		
		btn = new Sprite[liczba];
		can_click = new boolean[liczba];
		number = new Text[liczba];
		
		stars = new Sprite[liczba*3];
		round_stars_earned = new char[liczba];//dla kazdej rundy ile osiagnieto gwiazdek
		level_unlocked = new boolean[liczba];//czy level dostepny czy nie (z pliku)
		
		
		///// IO IO IO ///////
		IO in = new IO() ;
		String first_play = in.getData("first_play");
		if (first_play.equals("null")) in.FirstGameDeclare();

		for (int i =  0 ;  i < liczba ; i++)// ladowanie z pliku w pamieci danych
		{
			
			round_stars_earned[i] = (char) Character.getNumericValue((in.getData("stars_reached"+ Integer.toString(i))).charAt(0));
			level_unlocked[i] = Boolean.parseBoolean(in.getData("level_lock"+Integer.toString(i)));
		}
		
						
		
		for ( int i = 0 ; i < liczba;i++)
		{
			
			if (level_unlocked[i] == true){
			btn[i] = new Sprite(0,0,tex )
			{
				 @Override
		          public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		                   if(pSceneTouchEvent.getAction()==MotionEvent.ACTION_UP){
		                	//select_level(klikniety_numer_btn(this));
		                	//   act.setCurrentScene(new Game(klikniety_numer_btn(this)+1));
		                   }
		                   return true;
		          }
			};}
			else
				btn[i] = new Sprite(0,0,level_lock.region );
		
			
			
			number[i] = new Text(0,0,font,Integer.toString(i+1) );
			number[i].setColor(200/255f,133/255f,49/255f);
			
			btn[i].setScaleCenter(0, 0);
			btn[i].setScale(w/6/btn[i].getWidth());
			registerTouchArea(btn[i]);
		}
		
		
		
		

		
		//rozmieszczenie
		int c = 0 ;
		int b = 0 ;
		float przesuniecie_centrowane =( w - (btn[0].getWidthScaled() * 3))/2;
		
		for (int i = 0 ; i < liczba;i++)
			{
				if(c == 3) {b++; c = 0;}

				btn[i].setPosition(przesuniecie_centrowane + (c *( btn[i].getWidthScaled()+przerwa) ), b * (btn[i].getHeightScaled()+przerwa));
				attachChild(btn[i]);	
				number[i].setPosition(btn[i].getX() + btn[i].getWidthScaled()/2 - number[i].getWidthScaled()/2, btn[i].getY() + btn[i].getHeightScaled()/2 - number[i].getHeightScaled()/2);
				attachChild(number[i]);
				c++;
			}
		 ///////////////////////////
		// setOnSceneTouchListener(this);
		 act.mCamera.setBounds(0, 0,w,btn[0].getHeightScaled() * (liczba/3+1)+1 + przerwa * (liczba/3)+1);//zakres kamery //TODO wazne!
		 act.mCamera.setBoundsEnabled(true);
		 
		 ////////////////////////////////////////////////////////////////
		 /////////////////////////STARS://///////////////////////////////
		 ////////////////////////////////////////////////////////////////
		 for (int i = 0 ; i < liczba*3; i++)
		 {
			 stars[i]=new Sprite(i * 20 , 0 , star.region);
			 stars[i].setScaleCenter(0, 0);
			 stars[i].setScale(w/6/btn[0].getWidth());	 
		 }

		 
		 float przesuniecie = (btn[0].getWidthScaled()/2 - (stars[0].getWidthScaled() * 3 )/2 ); 
		 for (int i = 0 ; i < liczba * 3 ; i+=3)
		 {
			 stars[i].setPosition(btn[(int) Math.floor(i/3)].getX() +przesuniecie , btn[(int) Math.floor(i/3)].getY()+ btn[0].getHeightScaled() - stars[0].getHeightScaled());
			 stars[i+1].setPosition(btn[(int) Math.floor(i/3)].getX() +przesuniecie+ (stars[0].getWidthScaled()* 1) , btn[(int) Math.floor(i/3)].getY() + btn[0].getHeightScaled() - stars[0].getHeightScaled());
			 stars[i+2].setPosition(btn[(int) Math.floor(i/3)].getX() +przesuniecie+ (stars[0].getWidthScaled()* 2) , btn[(int) Math.floor(i/3)].getY() + btn[0].getHeightScaled() - stars[0].getHeightScaled()); 
		 }
		 
		 
		 
		 
		 for (int i = 0 ; i < liczba ; i++ )
		 {
			 if (level_unlocked[i]) 
				 {
				  switch ( round_stars_earned[i] ) 
				  {
				  case 1: attachChild(stars[i*3]);
					  break;
				  case 2:  attachChild(stars[i*3]);  attachChild(stars[i*3+1]);
					  break;
				  case 3:  attachChild(stars[i*3]); attachChild(stars[i*3+1]); attachChild(stars[i*3+2]);
					  break;
				  }
				 }
		 }
		 
		 
		 
		 
		 
		 
		 
		 
	}
	
	
	
	
	
	void zaladuj_czcionke()
	{
    	font = act.mFont;
	}
	
	
	@Override // poruszanie po mapie , przesuwanie gestem
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pTouchEvent) {
		   //if (przesuwanie == false ) return false;
		
		   if(pTouchEvent.getAction() == MotionEvent.ACTION_DOWN)
	        {
	                mTouchX = pTouchEvent.getMotionEvent().getX();
	                mTouchY = pTouchEvent.getMotionEvent().getY();          
	        }
	        else if(pTouchEvent.getAction() == MotionEvent.ACTION_MOVE)
	        {
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
		return true;
	}

	
	
	void select_level(int level)
	{
		switch(level)
		{
		case 0:
			act.setCurrentScene(new Scene());
		case 1:
			act.setCurrentScene(new Scene());
		case 2:
			act.setCurrentScene(new Scene());
		case 3:
			act.setCurrentScene(new Scene());
		case 4:
			act.setCurrentScene(new Scene());
		case 5:
			act.setCurrentScene(new Scene());
		case 6:
			act.setCurrentScene(new Scene());
		case 7:
			act.setCurrentScene(new Scene());
		case 8:
			act.setCurrentScene(new Scene());
		case 9:
			act.setCurrentScene(new Scene());
		case 10:
			act.setCurrentScene(new Scene());
		case 11:
			act.setCurrentScene(new Scene());
		case 12:
			act.setCurrentScene(new Scene());
		case 13:
			act.setCurrentScene(new Scene());
		case 14:
			act.setCurrentScene(new Scene());
		case 15:
			act.setCurrentScene(new Scene());
		case 16:
			act.setCurrentScene(new Scene());
		case 17:
			act.setCurrentScene(new Scene());
		case 18:
			act.setCurrentScene(new Scene());
		case 19:
			act.setCurrentScene(new Scene());
		case 20:
			act.setCurrentScene(new Scene());
		case 21:
			act.setCurrentScene(new Scene());
		}
	}
	
	void tmp_change(int c,int x)
	{
		c = x;
	}
	
	
	int klikniety_numer_btn(Sprite c)
	{
		for (int i = 0 ; i < 21 ; i++)
			if (btn[i] == c) return i;
		
		return -1;
	}




	@Override
	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY) {
		// TODO Auto-generated method stub
		
	}




	
	
	
	
	
}
