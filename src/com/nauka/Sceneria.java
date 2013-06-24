package com.nauka;

import java.util.Random;

import org.anddev.andengine.entity.sprite.Sprite;

public class Sceneria 
{
  MainActivity act;
	Sprite mapa;
	Sprite[] cloud;
	
	Sceneria()
	{
		act = MainActivity.getSharedInstance();
		lb mapa_t = new lb("Mapa", 512, 512);
		
		Random r = new Random();
		
		
		mapa = new Sprite(0, 0, mapa_t.region);
		mapa.setScaleCenter(0, 0);
		mapa.setWidth(act.WIDTH);
		mapa.setHeight(act.HEIGHT);
		
		cloud = new Sprite[3];
		
		for(int i = 0 ; i < cloud.length ; i++)
		{
			
		cloud[i] = new Sprite (0,0,new lb("cloud",256,256).region);
		cloud[i].setScaleCenter(0, 0);
		cloud[i].setScale(act.HEIGHT/13/cloud[i].getHeight());
		cloud[i].setPosition(i * cloud[i].getWidthScaled() + 45,r.nextInt(150));
		}
		
		
		
		
	}
	
	void update()
	{
		
	for(int i = 0 ; i < cloud.length ; i++)
	{	
		cloud[i].setPosition(cloud[i].getX(), cloud[i].getY()+1 );
		if(cloud[i].getY() > act.HEIGHT) cloud[i].setPosition(cloud[i].getX(), -cloud[i].getHeightScaled());
	}	
		
	}
	
	
}
