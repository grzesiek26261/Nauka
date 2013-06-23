package com.nauka;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

public class _HUD_ extends HUD
{

	MainActivity act;
	Sprite przegrzanie,
		   spriteHUD,
	       spriteHUDf;
	private float stoprocent ;
	
	_HUD_()
	{
		act = MainActivity.getSharedInstance();
		przegrzanie = new Sprite(0, 0, new lb("Bar", 256, 64).region);
		przegrzanie.setScaleCenter(0, 0);
		przegrzanie.setScale(act.WIDTH /2/ przegrzanie.getWidth());
		przegrzanie.setHeight(20);
		
		spriteHUD = new Sprite(0, 0, new lb("HUD", 256, 256).region);
		spriteHUD.setScaleCenter(0, 0);
		spriteHUD.setWidth(act.WIDTH);
		spriteHUD.setHeight(spriteHUD.getHeight() * 1.8f );
		spriteHUD.setPosition(0, act.HEIGHT - spriteHUD.getHeightScaled());
		
		spriteHUDf = new Sprite(0, 0, new lb("HUDfire", 128, 128).region)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
		    {
				if(pEvent.isActionDown())
				{
					if(act.game.mig.ilosc_karabinow == 4 || act.game.mig.ilosc_karabinow == 8)
					{
							act.game.mig.shot(0,8);
					}
				}
			return false;
		    }
		};
	    spriteHUDf.setScaleCenter(0, 0);
	    spriteHUDf.setScale(act.WIDTH / spriteHUDf.getWidth() / 3f);
	    spriteHUDf.setPosition(act.WIDTH * 0.98f - spriteHUDf.getWidthScaled(),
	    		               act.HEIGHT * 1.02f - spriteHUDf.getHeightScaled());
		
		stoprocent = przegrzanie.getWidthScaled();
		
		registerTouchArea(spriteHUDf);
		attachChild(przegrzanie);
		attachChild(spriteHUD);
		attachChild(spriteHUDf);
		
		
		act.mCamera.setHUD(this);
	}
	
	
	void setPrzegrzanie(float procent)
	{
		
		przegrzanie.setWidth((stoprocent * procent) / 100);	
		przegrzanie.setPosition(act.WIDTH/2 - przegrzanie.getWidthScaled()/2 , 0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
