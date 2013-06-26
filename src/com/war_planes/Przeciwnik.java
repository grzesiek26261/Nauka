package com.war_planes;

import java.util.Random;
import org.anddev.andengine.entity.sprite.AnimatedSprite;


public class Przeciwnik
{
	MainActivity act;
	AnimatedSprite tex;
	
	float 	hp=100,
			defence=0.9f,//obrona wartosc miedzy 0 a 1 zeby dzialalo
			speed = 0.8f,
			obrazenia = 6f;
	
	
	boolean is_alive = true,animation_lot =false,animation_uszkodzenie = false,animation_wybuch = false;
	long[] time_uszkodzenie;
	long[] time_lot;
	Random r;
	
	Przeciwnik(int x, int y)
	{
		act = MainActivity.getSharedInstance();
		r = new Random();
		tex = new AnimatedSprite(x,y,new stb("s2",2400,346,6,1).tiledT);
			tex.setCurrentTileIndex(0);
			tex.setScaleCenter(0, 0);
			tex.setScale(act.WIDTH / 6 / tex.getWidth());
			tex.setRotationCenter(tex.getWidthScaled()/2, tex.getHeightScaled()/2);
			tex.setRotation(180f);
		act.game.attachChild(tex);
		
		time_lot = new long[2];//2 klatki lotu
		time_lot[0] = time_lot[1]=40;
		
		time_uszkodzenie = new long[3];//bo 2 klatki obecnie sa animowane
		time_uszkodzenie[0] = time_uszkodzenie[1] = time_uszkodzenie[2] = 5;
		
		
		
	}
	
	void move()
	{
		tex.setPosition(tex.getX(), tex.getY()+speed);
		if(tex.getY() > act.HEIGHT) 
			{
			tex.setPosition(r.nextInt(act.WIDTH),-r.nextInt(act.HEIGHT));
			hp = 100;
			}
		if(hp <= 50) animate_uszkodzenie();
		else
		animate_lot();
		if(hp <= 0){ animate_wybuch();}
	}
	
	
	
	void kill()
	{
		if (is_alive == false) return;
		hp = 0;
		defence = 0 ;
		is_alive = false;
		tex.stopAnimation();
		tex.detachSelf();//dziala
		act.game.cash += 100;
		
	}
	
	void take_a_shot()
	{
		hp -= defence * obrazenia;
	}
	
	private void animate_lot()
	{
		if(animation_lot) return;
		tex.stopAnimation();
		tex.animate(time_lot,0,1,true);
		animation_lot = true;
	}
	
	private void animate_uszkodzenie()
	{
		if(animation_uszkodzenie) return;
		tex.stopAnimation();
		tex.animate(time_uszkodzenie,2,4,true);
		animation_uszkodzenie = true;
	}
	
	private void animate_wybuch()
	{
		animation_wybuch = true;
		tex.stopAnimation();
		long[] a = new long[3]; a[0]=a[1]=a[2]=15;
		tex.animate(a,3,5,true);
		kill();
	}
	
	
	
}
