package com.nauka;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

public class _HUD_ extends HUD
{

	MainActivity act;
	AnimatedSprite 	przegrzanie;
		   Sprite 	spriteHUD,
		   			spriteHUDf;
	private float stoprocent ;
	float przegrzanie_zmienna;
	_HUD_()
	{
		act = MainActivity.getSharedInstance();
		przegrzanie = new AnimatedSprite(0, 0, new stb("Bar", 512, 64,2,1).tiledT);
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
					{
						if(act.game.mig.czyprzegrzany == true) {if(przegrzanie.isAnimationRunning()==false) przegrzanie.animate(150);	return true;}
							if(przegrzanie.isAnimationRunning()) przegrzanie.stopAnimation();
						
							przegrzanie.setCurrentTileIndex(0);
							act.game.mig.shot(0);
							act.game.mig.przegrzej();
							
							setPrzegrzanie();
					}
				
			return true;
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
	
	
	void setPrzegrzanie()
	{
		przegrzanie_zmienna = (act.game.mig.przegrzanie * stoprocent)/act.game.mig.max_przegrzania  ;
		przegrzanie.setWidth(przegrzanie_zmienna*2);	
		przegrzanie.setPosition(act.WIDTH / 2 - przegrzanie.getWidthScaled() /2 , 0);
	}
	
	void updateHUD()
	{
		act.game.mig.chlodz();
		setPrzegrzanie();
	}
	
	
	
	
	
	
	
}
