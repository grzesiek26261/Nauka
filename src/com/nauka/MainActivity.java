package com.nauka;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseActivity;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Display;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;



public class MainActivity extends BaseGameActivity
{

	 public Font mFont; //czcionka do pisania na ekranie
	 public Camera mCamera ;//uchwyt do kamery
	 public Scene mCurrentScene; //uchwyt do obecnie aktywnej sceny           
	 
	 public Gra game;
	 public static BaseActivity instance; //?

	 int WIDTH,HEIGHT;//wys i szer okna gry
	  
	    @SuppressWarnings("deprecation")
		@Override
	    public Engine onLoadEngine() {//c-structor dla startowania gry ONCREATE
	        
	        instance = this;
	        
	        final Display display = getWindowManager().getDefaultDisplay();
	        WIDTH = display.getWidth();
	        HEIGHT = display.getHeight();
	        mCamera = new Camera(0, 0, WIDTH, HEIGHT);
	        
	        return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT,//+down
	               new RatioResolutionPolicy(WIDTH, HEIGHT), mCamera)); //to jest jedna linia! 
  
	    }
	    
	    @Override
		public void onLoadResources() 
	    { //tu zalaczam wszystkie zasoby
	    	this.setCurrentScene(new Menu());
	    	
	    }
	 
	    @Override
		public Scene onLoadScene() 
	    {
	        return mCurrentScene;
	    }
	    
	    public Scene getCurrentScene()
	    {
	    	return mCurrentScene;
	    }
	    
	    
	    public static MainActivity getSharedInstance() //do pobierania aktywnosci okna glownego
	    {
	        return (MainActivity) instance;
	    }

	    public void setCurrentScene(Scene scene)//zmienia aktywn¹ scene 
	    {
	    	mCurrentScene = scene;
	        getEngine().setScene(mCurrentScene);
	    }

	    @Override
		public void onLoadComplete() 
	    {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
	
	

