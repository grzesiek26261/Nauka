package com.war_planes;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.ui.activity.BaseActivity;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Display;



public class MainActivity extends BaseGameActivity
{

	 public Font mFont; //czcionka do pisania na ekranie
	 public SmoothCamera mCamera ;//uchwyt do kamery
	 public Scene mCurrentScene; //uchwyt do obecnie aktywnej sceny           
	 
	 public Gra game;
	 public Manager M;
	 public static BaseActivity instance; //?
	 public int ID = 0 ;//id sceny aktywnej
	 private BackManager backmanager;
	 boolean isgamecreated = false;
	 
	 int WIDTH,HEIGHT;//wys i szer okna gry
	  
	    @SuppressWarnings("deprecation")
		@Override
	    public Engine onLoadEngine() {//c-structor dla startowania gry ONCREATE
	        
	        instance = this;
	        
	        final Display display = getWindowManager().getDefaultDisplay();
	        WIDTH = display.getWidth();
	        HEIGHT = display.getHeight();
	        mCamera = new SmoothCamera(0, 0, WIDTH, HEIGHT,35000,35000,1);
	        
	        return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT,//+down
	               new RatioResolutionPolicy(WIDTH, HEIGHT), mCamera)); //to jest jedna linia! 
  
	    }
	    
	    @Override
		public void onLoadResources() 
	    { //tu zalaczam wszystkie zasoby
	    	
	    	FontFactory.setAssetBasePath("Fonts/"); //ustawiam sciezke dla czcionek w folderze Assets/Fonts/ 	
	    	BitmapTextureAtlas mDroidFontTexture = new BitmapTextureAtlas( 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    	
	    	mFont = FontFactory.createFromAsset(mDroidFontTexture,this,"popwarner.ttf",
	    			40f ,true,Color.WHITE);
	    	getFontManager().loadFont(mFont);
	    	

	    
	    	//mFont = new Font(mDroidFontTexture, Typeface.create(Typeface.DEFAULT,
	    	  //  Typeface.BOLD), 40, true, Color.WHITE);
	    	mEngine.getTextureManager().loadTexture(mDroidFontTexture);
	    	mEngine.getFontManager().loadFont(mFont);
	    	
	    	
	    	
	    	
	    	backmanager = new BackManager();
	    	M = new Manager();

	    }
	 
	    
	    @Override
	    public void onBackPressed()
	    {
	    	backmanager.backfrom(ID);
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
	    	mCamera.setCenter(WIDTH / 2 , HEIGHT / 2);	
	    	
	        getEngine().setScene(mCurrentScene);
	    }

	    @Override
		public void onLoadComplete() 
	    {
			// TODO Auto-generated method stub
	    	this.setCurrentScene(new Wybor());
		}
	}
	
	
	
	
	

