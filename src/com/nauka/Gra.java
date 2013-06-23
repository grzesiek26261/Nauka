package com.nauka;
//test
import org.anddev.andengine.engine.handler.IUpdateHandler;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gra extends Scene implements SensorEventListener,IOnSceneTouchListener
{
	MainActivity act;
	int w,h;
	Sprite mapa;
	Sprite pocisk;
	Samolot mig;

    private SensorManager sensorManager;
    
	Gra(int typ)
	{
		act = MainActivity.getSharedInstance();
		w = act.WIDTH;
		h = act.HEIGHT;
		lb mapa_t = new lb("Mapa", 512, 512);
		act.game = this;
		act.mCurrentScene = this;
		
		
	    mig = new Samolot(typ);
	    
		mapa = new Sprite(0, 0, mapa_t.region);
		mapa.setScaleCenter(0, 0);
		mapa.setWidth(w);
		mapa.setHeight(h);
		
		attachChild(mapa);
		attachChild(mig.tex);
		
		sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this, 
					  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
					  SensorManager.SENSOR_DELAY_GAME);
		registerUpdateHandler(new IUpdateHandler() 
		{ 
			@Override
			public void onUpdate(float pSecondsElapsed) 
			{
				//updateSpritePosition();
            }
			@Override
			public void reset() 
			{}
		});		
		
		this.setOnSceneTouchListener(this);
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
	public boolean onSceneTouchEvent(Scene arg0, TouchEvent event)
    {
		if(event.isActionDown())
		{
			if(mig.ilosc_karabinow == 4)
			{
					mig.shot(0,8);
			}
		}
			
			
				
		
		return false;
	}
}


 