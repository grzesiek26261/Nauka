package com.war_planes;

import java.util.Random;

import org.anddev.andengine.entity.sprite.Sprite;

public class Sceneria 
{
  MainActivity act;
	Sprite mapa;
	Sprite[] cloud;
	float[][] pos;
	int c=0;
	
	
	Random r;
	Sceneria()
	{
		act = MainActivity.getSharedInstance();
		lb mapa_t = new lb("Mapa", 512, 512);
		
		r = new Random();
		pos = new float[200][2];
			for(int i = 0 ; i < 200; i++)
				{
					pos[i][0] = r.nextInt((int) (act.WIDTH));
					pos[i][1] = - r.nextInt(act.HEIGHT * 3 );
				}
		
		mapa = new Sprite(0, 0, mapa_t.region);
		mapa.setScaleCenter(0, 0);
		mapa.setWidth(act.WIDTH);
		mapa.setHeight(act.HEIGHT);
		
		cloud = new Sprite[9];
		
		for(int i = 0 ; i < cloud.length ; i++)
		{
			
		cloud[i] = new Sprite (0,0,act.M.cloud);
		cloud[i].setScaleCenter(0, 0);
		cloud[i].setScale(act.HEIGHT/13/cloud[i].getHeight());
		cloud[i].setPosition(i * cloud[i].getWidthScaled() + 45,r.nextInt(150));
		}
		
		
		
		
	}
	
	void update()
	{
		
	for(int i = 0 ; i < cloud.length ; i++)
	{	
		cloud[i].setPosition(cloud[i].getX(), cloud[i].getY()+1.5f );
		if(cloud[i].getY() > act.HEIGHT)reset(cloud[i]);
	}	
		
	}
	
	
	
	
	void reset(Sprite x)
	{
		c++;
		if(c>=pos.length) c = 0 ;
		x.setPosition(pos[c][0],pos[c][1]);
		
	}
	
	
	
	
	
	
	
}
