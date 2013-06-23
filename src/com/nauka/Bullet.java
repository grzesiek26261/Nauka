package com.nauka;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.MoveYModifier;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class Bullet 
{
 MainActivity act;
	Sprite sprite;
	float x=0,y=0,speed=0.1f;
	boolean is_attached=false,isready=true;
	
	
	Bullet(TextureRegion tex)
	{
		act = MainActivity.getSharedInstance();
		
		sprite = new Sprite(x,y,tex);
		sprite.setScaleCenter(0, 0);
		sprite.setScale(act.WIDTH/30/sprite.getWidth());

	}
	
	void setVisible()
	{
		sprite.setVisible(true);
	}
	
	void setInVisible()
	{
		sprite.setVisible(false);
	}
	
	void remove()
	{
		isready = true;
		setInVisible();
		sprite.setPosition(-100, -100);
	}
	
	
	
	void shot(float c, float v)
	{
		isready = false;
		setVisible();
		sprite.setPosition(c,v);
		sprite.registerEntityModifier(new MoveYModifier(0.9f,v,  0-sprite.getHeightScaled())
		   {
			  @Override
	 		    protected void onModifierFinished(IEntity pItem)
	 		    {
				 remove();
	 		    }
		   });
		 
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
