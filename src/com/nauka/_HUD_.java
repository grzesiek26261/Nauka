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
		   			spriteHUDf,
		            spriteZaslaniaczPocisku;
	private float stoprocent ;
	float przegrzanie_zmienna;
	_HUD_()
	{
		act = MainActivity.getSharedInstance();
		przegrzanie = new AnimatedSprite(0, 0, new stb("Bar", 512, 64,2,1).tiledT);
		przegrzanie.setScaleCenter(0, 0);
		przegrzanie.setScale(act.WIDTH /2/ przegrzanie.getWidth());
		przegrzanie.setHeight(0.15f * act.HEIGHT);
		
		spriteZaslaniaczPocisku = new Sprite(0, 0, new lb("Zaslaniacz", 1024, 1024).region);
		spriteZaslaniaczPocisku.setScaleCenter(0, 0);
		spriteZaslaniaczPocisku.setWidth(act.WIDTH);
		spriteZaslaniaczPocisku.setHeight(act.HEIGHT * 0.22f );
		spriteZaslaniaczPocisku.setPosition(0, act.HEIGHT - spriteZaslaniaczPocisku.getHeightScaled());
		
		
		
		spriteHUD = new Sprite(0, 0, new lb("HUD", 1024, 1024).region);
		spriteHUD.setScaleCenter(0, 0);
		spriteHUD.setWidth(act.WIDTH);
		spriteHUD.setHeight(act.HEIGHT * 0.22f );
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
		attachChild(spriteZaslaniaczPocisku);
		attachChild(przegrzanie);
		attachChild(spriteHUD);
		attachChild(spriteHUDf);
		
		
		act.mCamera.setHUD(this);
	}
	
	
	void setPrzegrzanie()
	{
		przegrzanie_zmienna = (act.game.mig.przegrzanie * stoprocent)/act.game.mig.max_przegrzania  ;
		przegrzanie.setWidth(przegrzanie_zmienna*1.5f);	
		przegrzanie.setPosition(0, 0.92f * act.HEIGHT);
	}
	
	void updateHUD()
	{
		act.game.mig.chlodz();
		setPrzegrzanie();
	}
	
	
	
	
	
	
	
}
