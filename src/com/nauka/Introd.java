package com.nauka;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;

public class Introd extends Scene 
{
	MainActivity act;
	int w,h;
	Sprite intro;
	
	Introd()
	{
		act = MainActivity.getSharedInstance();
		w = act.WIDTH;
		h = act.HEIGHT;
		lb intro_t = new lb("Intro", 512, 512);
		act.ID = 0;
		intro = new Sprite(0, 0, intro_t.region)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					act.setCurrentScene(new Menu());
                	return true;
				}
				else
					return false;
			}
		};
		intro.setScaleCenter(0, 0);
		intro.setWidth(w);
		intro.setHeight(h);
		
		registerTouchArea(intro);
		attachChild(intro);
	}
}
