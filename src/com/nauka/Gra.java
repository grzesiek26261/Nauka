package com.nauka;
//test
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gra extends Scene implements SensorEventListener,IOnSceneTouchListener,ITimerCallback 
{
	MainActivity act;
			 int w,h,tick = 0 ;

	Sceneria sceneria;
	Sprite pocisk;
	Samolot mig;
	_HUD_ hud;
	
	TimerHandler timerHandler;							
    private SensorManager sensorManager;
    
    Przeciwnik[] tester;
    int cash;
    float distance;
    
    IO save;
    
	Gra(int typ)
	{
		act = MainActivity.getSharedInstance();
		w = act.WIDTH;
		h = act.HEIGHT;
			act.ID = 3;
			act.game = this;
			act.mCurrentScene = this;
			act.isgamecreated = true;
			
		save = new IO();
		String first_play = save.getData("first_play");
		if (first_play.equals("null")) save.FirstGameDeclare();
		
			cash = Integer.parseInt(save.getData("cash")) ; 
			distance = Float.parseFloat(save.getData("distance"));
			
			
		hud = new _HUD_();
		hud.setVisible(true);
		sceneria = new Sceneria();

		FPSLogger a = new FPSLogger();
			 registerUpdateHandler(a);
	    mig = new Samolot(typ);
		attachChild(sceneria.mapa);
		
		tester = new Przeciwnik[4];
			for(int i = 0 ; i < tester.length;i++)
				tester[i] = new Przeciwnik((int) (i*50),0);
		
		
		
		
		
		
		
		
		
		for(int i = 0 ; i < sceneria.cloud.length; i++)
			attachChild(sceneria.cloud[i]);
		
		attachChild(mig.tex);
		
		sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this, 
					  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
					  SensorManager.SENSOR_DELAY_GAME);
	
		timerHandler = new TimerHandler(0.001f, true, this);
		   registerUpdateHandler(timerHandler);
		   

		  
		  
		this.setOnSceneTouchListener(this);
	}

	
	@Override //UPDATER
		public void onTimePassed(TimerHandler h) {
		
		tick++;
		if(tick%100 == 0)
		{
			hud.updateHUD();
			
		}
		
		if(tick % 3 == 0 )
		{
			sceneria.update();
		}

		if(tick%10 == 0 )
		{
			for(int i = 0 ; i < tester.length;i++)
				tester[i].move();
			
			
				kolizja();
				mig.koliduj(tester);
		}
		
		distance+=0.1;

		
		
		if(tick>=1000) tick = 0 ;
	}
	
	
	void kolizja()
	{
		//for (int i = 0 ; i<mig.bullet.length;i++)
			//mig.bullet[i].checkcollision();
		
			for(int i = 0 ; i < mig.bullet.length; i++)
			{
				mig.bullet[i].checkcollision(tester);
			}
		
	}
	
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) 
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		mig.move(event);
	}

	@Override
	public boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}


 