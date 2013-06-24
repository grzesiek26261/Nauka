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
	int w,h;

	Sceneria sceneria;
	Sprite pocisk;
	Samolot mig;
	_HUD_ hud;
	
	TimerHandler timerHandler,sceneriatimer;
	int tick = 0 ; //zegar
	
	
    private SensorManager sensorManager;
    
	Gra(int typ)
	{
		act = MainActivity.getSharedInstance();
		w = act.WIDTH;
		h = act.HEIGHT;
		act.ID = 3;
		act.game = this;
		act.mCurrentScene = this;
		hud = new _HUD_();
		hud.setVisible(true);
		sceneria = new Sceneria();
		
		
		FPSLogger a = new FPSLogger();
		registerUpdateHandler(a);
	    mig = new Samolot(typ);
	    

		
		attachChild(sceneria.mapa);
		
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
		if(tick%100 == 0)hud.updateHUD();//za kazdym razem updatuje 
		sceneria.update();
		
		if(tick>=1000) tick = 0 ;
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


 