package com.war_planes;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

public class Menu extends Scene
{
	MainActivity act;
	int w,h;
	Sprite menu;
	Sprite play;
	Sprite exit;
	
	Menu()
	{
		act = MainActivity.getSharedInstance();
		w = act.WIDTH;
		h = act.HEIGHT;
		act.ID = 1;
		lb menu_t = new lb("Menu", 512, 512);
		lb play_t = new lb("Play", 512, 512);
		lb exit_t = new lb("Exit", 512, 512);
		
		menu = new Sprite(0, 0, menu_t.region);
		menu.setWidth(w);
		menu.setHeight(h);
		
		play = new Sprite(0, 0, play_t.region)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					act.setCurrentScene(new Wybor());
					return true;
				}
				else
					return false;
			}
		};
		
		play.setScaleCenter(0, 0);
		play.setScale(w / play.getWidth() / 3);
		play.setPosition(w * 0.9f - play.getWidthScaled(), h  * 0.14f - play.getHeightScaled());
		
		exit = new Sprite(0, 0, exit_t.region) 
		{
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					act.finish();
                   	return true;
				}
				else
					return false;
			}
		};
		
		exit.setScaleCenter(0, 0);
		exit.setScale(w / exit.getWidth() / 3);
		exit.setPosition(w * 0.9f - play.getWidthScaled(), h  * 0.28f - play.getHeightScaled());
		
		registerTouchArea(play);
		registerTouchArea(exit);
		attachChild(menu);
		attachChild(play);
		attachChild(exit);
	}
	
	
}
