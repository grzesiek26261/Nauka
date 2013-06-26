package com.war_planes;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;

public class Wybor extends Scene 
{
	MainActivity act;
	
	Sprite background;
	int active;
	Sprite is1;
	Sprite is2;
	Sprite is3;
	Sprite arrow_right,arrow_left;
	int w,h;
	
	boolean canchange = false;
	Text kasa;
	
	
	Wybor()
	{
		act = MainActivity.getSharedInstance();	
		w = act.WIDTH; h = act.HEIGHT;
		act.ID = 2;
		
		active = 0 ;
		
		
		IO save = new IO();
		String first_play = save.getData("first_play");
		if (first_play.equals("null")) save.FirstGameDeclare();
		
		String cash = save.getData("cash") ; 
		kasa = new Text(0,0,act.mFont,cash+"$");
		kasa.setColor(220/255f,175/255f,5/255f);//bo w paincie R=255,G=200,B=5
		kasa.setPosition(w/2 - kasa.getWidth()/2 , 0);
		
		
		
		
		arrow_right = new Sprite(-100,-100,new lb("arrow_right",256,256).region){
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					active++; if (active > 3) active = 0;
					change_active();
				}
			return false;
			}
		};
		
		arrow_left = new Sprite(-100,-100,new lb("arrow_left",256,256).region){
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					active--; if (active <0 ) active = 3;
					change_active();
				}
			return false;
			}
		};	
		 
		
		
		
		
		lb background_t = new lb("Menu", 512, 512);
			background = new Sprite(0, 0, background_t.region);
			background.setScaleCenter(0, 0);
			background.setWidth(w);
			background.setHeight(h);		
	
		lb is1_t = new lb("is1", 512, 512);
		is1 = new Sprite(-2230, 0, is1_t.region)
		{
			@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					act.setCurrentScene(new Gra(1));	
				}
					return false;
			}
		};
			is1.setScaleCenter(0, 0);
			is1.setScale(w / is1.getWidth() );
			is1.setPosition(w * 0.99f - is1.getWidthScaled(), h  * 0.6f - is1.getHeightScaled());
		
		
		is2 = new Sprite(-2230, 0, new lb("is2", 512, 512).region)
	    {
	    	@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					if(act.game.cash >= 120000 )
						act.setCurrentScene(new Gra(2));
				}
					return false;
			}
		};
		is2.setScaleCenter(0, 0);
		is2.setScale(w / is2.getWidth() );
				
		lb is3_t = new lb("is3", 512, 512);
		is3 = new Sprite(-2230, 0, is3_t.region)
	    {
	    	@Override
			public boolean onAreaTouched(TouchEvent pEvent, float pX, float pY)
			{
				if(pEvent.isActionDown())
				{
					if(act.game.cash >= 350000 )
					act.setCurrentScene(new Gra(3));
				}
					return false;
			}
		};
		is3.setScaleCenter(0, 0);
		is3.setScale(w / is3.getWidth() );
		
		
	
		
		attachChild(background);
		attachChild(is1); registerTouchArea(is1);
		attachChild(is2); registerTouchArea(is2);
		attachChild(is3); registerTouchArea(is3);
		
		arrow_right.setScaleCenter(0, 0);
		arrow_right.setScale(w/4/arrow_right.getWidth());
		arrow_right.setPosition(w-arrow_right.getWidthScaled(), h - arrow_right.getHeightScaled());
		attachChild(arrow_right);registerTouchArea(arrow_right);
	
		arrow_left.setScaleCenter(0, 0);
		arrow_left.setScale(w/4/arrow_left.getWidth());
		arrow_left.setPosition(0, h - arrow_left.getHeightScaled());
		attachChild(arrow_left);registerTouchArea(arrow_left);
		attachChild(kasa);
	}


	
	void change_active()
	{
		switch(active)
		{
		case 0:
			is1.setPosition(w * 0.99f - is1.getWidthScaled(), h  * 0.6f - is1.getHeightScaled());
			is2.setPosition(-2230, 0);
			is3.setPosition(-2230, 0);
			break;
		case 1:
			is2.setPosition(w * 0.99f - is1.getWidthScaled(), h  * 0.6f - is1.getHeightScaled());
			is1.setPosition(-2230, 0);
			is3.setPosition(-2230, 0);
			break;
		case 2:
			is3.setPosition(w * 0.99f - is1.getWidthScaled(), h  * 0.6f - is1.getHeightScaled());
			is2.setPosition(-2230, 0);
			is1.setPosition(-2230, 0);
			break;
		}
	}
	
	
	
	
	
	
}
