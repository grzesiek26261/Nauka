package com.nauka;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import android.hardware.SensorEvent;

public class Samolot 
{
	MainActivity act;
	 float 	x,
	 		y,
	 		speed,
	 		przegrzanie=0,
	 		szybkosc_przegrzewania=1f,
	 		max_przegrzania = 10f;
	 
	 int 	w,
	 		h,
	 		typ = -1,
	 		ilosc_karabinow;
	
	 Sprite 	tex;
	 
	 Bullet[] 	bullet,to_shot;
	 boolean[] 	isattached,gotowy;//gotowy - tablica dla kazdego pocisku, jesli true wtedy mozna go uzywac znowu jesli false to nie 
	 boolean 	czymoznastrzelac = true,FIRST_PLAY = true;
	 
TextureRegion 	pocisktex;
	 
	 
	 
	 int tmp = -1;//eh
	 
	 
	 
	Samolot( int typ)
	{
		act = MainActivity.getSharedInstance();
		  w = act.WIDTH; 
		  h = act.HEIGHT;
		
		this.typ = typ;
		
		bullet = new Bullet[32];//na mapie moga byc jednoczesnie 32 pociski

		tex = new Sprite(0, 0, new lb("s"+Integer.toString(typ), 512, 512).region);
			tex.setScaleCenter(0, 0);
			tex.setScale(w / tex.getWidth() / 4);
			tex.setPosition(w * 0.6f - tex.getWidthScaled(), h  * 0.9f - tex.getHeightScaled());
		pocisktex = new lb("Pocisk", 8, 16).region;
		
		switch(typ)
		{
			case 1:
				speed = 1.5f;
				ilosc_karabinow = 4;
				break;
			case 2: 
				speed = 1.25f;
				ilosc_karabinow = 6;
				break;
			case 3:
				speed = 1f;
				ilosc_karabinow = 8;
				break;
		}

		for(int i = 0 ; i < bullet.length ; i++)
		{		
			bullet[i] = new Bullet(pocisktex);	
		}
		

	}
	
	
	
	public void shot(int kombinacja,int ilosc_pociskow) //strzela podan¹ kombinacj¹ , 0 - seria, 1 - ci¹g³y ogieñ.
	{
		
		prepareFirstly();
		{
		 switch(kombinacja)
		 {
		 	case 0:
		 		to_shot = rezerwuj(ilosc_pociskow);		if (czymoznastrzelac==false)return;
	 			
	 			for(int i = 0 ; i < ilosc_pociskow;i++)
	 			{
	 				to_shot[i].shot(tex.getX() + ( i *( tex.getWidthScaled()/ilosc_pociskow)), tex.getY());
	 			}

		    break;
		 	case 1:
		 		
		 	
		 	break;
		 }
		}
	}
	
	

	void prepareFirstly()
	{
	if(FIRST_PLAY)
	{
	 for(int i = 0 ; i < bullet.length; i++)
		{act.mCurrentScene.attachChild(bullet[i].sprite);
	    bullet[i].sprite.setPosition(-100, -100);
		}
	 	FIRST_PLAY = false;
	}
	return;
	}
	
	
	
	private Bullet[] rezerwuj(int ilosc)//rezerwuje pociski.zwraca index od ktorego strzelamy
	{
	  Bullet[] ret = new Bullet[ilosc];
	  
	  
	  int c=0;//check
	  
	  for(int i = 0 ; i < bullet.length;i++)
	  {
		  if(bullet[i].isready) {c++;}
	  }
	
	  czymoznastrzelac = false;
	  int k = 0 ; 
	  if(c>=ilosc)
	  {
		  for(int i = 0 ; i < bullet.length;i++)
		  {
			  if(bullet[i].isready) {ret[k] = bullet[i];k++;}
			  if(k==ilosc) break;
		  }
		  czymoznastrzelac = true; 
	  }
		
		
	return ret;
	  
	}



	
	
	
	
	
	public void move(SensorEvent event)
	{
		if(przegrzanie > 0){act.game.hud.setPrzegrzanie((max_przegrzania / przegrzanie));System.out.println(przegrzanie);}
		
		
		if( tex.getX() < w - tex.getWidthScaled() ) //KOLIZJA PRAWEJ STRONY
			tex.setPosition(tex.getX() - event.values[0]/speed, tex.getY());
		else
		{
			tex.setPosition(w - tex.getWidthScaled(), tex.getY());	
			tex.setPosition(tex.getX() - event.values[0]/speed, tex.getY());
		}
		
		if( tex.getX() > 0 ) //KOLIZJA LEWEJ STRONY
			tex.setPosition(tex.getX() - event.values[0]/speed, tex.getY());
		else
		{
			tex.setPosition(0, tex.getY());	
			tex.setPosition(tex.getX() - event.values[0]/speed, tex.getY());
		}
		
		if( tex.getY() > 0 ) //KOLIZJA GÓRY
			tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		else
		{
			tex.setPosition(tex.getX(), 0);	
			tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		}
		
		if( tex.getY() < h - tex.getHeightScaled() ) // KOLIZJA DO£U
			tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		else
		{
			tex.setPosition(tex.getX(), h - tex.getHeightScaled() );	
			tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		}
	}
	
	
	


	
}
