package com.war_planes;

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
		sprite.setScale(act.WIDTH/90/sprite.getWidth());
		
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
	
	
	void checkcollision()
	{
		if(isready) return;
		
	 for(int i = 0 ; i < act.game.tester.length;i++)
	  if(act.game.tester[i].is_alive == true)	 
		if(sprite.collidesWith(act.game.tester[i].tex))
		{
			act.game.tester[i].take_a_shot();
			remove();//usuwa pocisk po kolizji
		}
	}
	
	
	void checkcollision(Przeciwnik[] x)
	{
		if(isready) return;
		
	for(int i = 0 ; i < x.length; i++)
	{ if(x[i].is_alive)	 
		if(sprite.getX() > x[i].tex.getX() && sprite.getX() + sprite.getWidthScaled() < x[i].tex.getX() + x[i].tex.getWidthScaled() &&
				sprite.getY() > x[i].tex.getY() && sprite.getY() + sprite.getHeightScaled() < x[i].tex.getY() + x[i].tex.getHeightScaled() )
		{
			x[i].take_a_shot();
			remove();//usuwa pocisk po kolizji
		}
	}
	}
	
	
	
}