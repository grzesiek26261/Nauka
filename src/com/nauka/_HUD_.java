package com.nauka;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.sprite.Sprite;

public class _HUD_ extends HUD{

	MainActivity act;
	Sprite przegrzanie;
	private float stoprocent ;
	
	_HUD_()
	{
		act = MainActivity.getSharedInstance();
		przegrzanie = new Sprite(0,0,new lb("Bar",256,64).region);
		przegrzanie.setScaleCenter(0, 0);
		przegrzanie.setScale(act.WIDTH /2/ przegrzanie.getWidth());
		przegrzanie.setHeight(20);
		
		stoprocent = przegrzanie.getWidthScaled();
		attachChild(przegrzanie);
		
		
		act.mCamera.setHUD(this);
	}
	
	
	void setPrzegrzanie(float procent)
	{
		
		przegrzanie.setWidth((stoprocent * procent) / 100);	
		przegrzanie.setPosition(act.WIDTH/2 - przegrzanie.getWidthScaled()/2 , 0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
