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
	 		szybkosc_przegrzewania=0.7f,
	 		szybkosc_chlodzenia = 0.4f,
	 		wspolczynnik_cieplny = 1f,
	 		max_przegrzania = 30f;
	 
	 int 	w,
	 		h,
	 		typ = -1,
	 		ilosc_karabinow = 2,
	 		wystrzelonych = 0 ;
	
	 Sprite 	tex;
	 
	 Bullet[] 	bullet,to_shot;
	 boolean[] 	isattached,gotowy;//gotowy - tablica dla kazdego pocisku, jesli true wtedy mozna go uzywac znowu jesli false to nie 
	 boolean 	czymoznastrzelac = true,FIRST_PLAY = true, czyprzegrzany = false;
	 
TextureRegion 	pocisktex;
	 
	 
	 
	 int tmp = -1;//eh
	 
	 
	 
	Samolot( int typ)
	{
		act = MainActivity.getSharedInstance();
		  w = act.WIDTH; 
		  h = act.HEIGHT;
		
		this.typ = typ;
		
		bullet = new Bullet[46];//na mapie moga byc jednoczesnie 32 pociski
	
		tex = new Sprite(0, 0, new lb("s"+Integer.toString(typ), 512, 512).region);
			tex.setScaleCenter(0, 0);
			tex.setScale(w / tex.getWidth() / 4);
			tex.setPosition(w * 0.6f - tex.getWidthScaled(), h  * 0.8f - tex.getHeightScaled());
		pocisktex = new lb("Pocisk", 8, 16).region;
		
		switch(typ)
		{
			case 1:
				speed = 1.5f;
				szybkosc_przegrzewania = 0.37f;
				break;
			case 2: 
				speed = 1.25f;
				szybkosc_przegrzewania = 0.28f;
				break;
			case 3:
				speed = 1f;
				szybkosc_przegrzewania = 0.18f;
				break;
		}

		for(int i = 0 ; i < bullet.length ; i++)
		{		
			bullet[i] = new Bullet(pocisktex);	
		}
		

	}
	
	
	
	public void shot(int kombinacja) //strzela podan¹ kombinacj¹ , 0 - seria, 1 - ci¹g³y ogieñ.
	{
		
		prepareFirstly(ilosc_karabinow);
		{
		 switch(kombinacja)
		 {
		 	case 0:
		 		to_shot = rezerwuj(ilosc_karabinow);		if (czymoznastrzelac==false ||  czyprzegrzany == true )return;
	 			
		 		to_shot[0].shot(tex.getX() + tex.getWidthScaled()/2 - to_shot[0].sprite.getWidthScaled()*1.1f , tex.getY());
		 		to_shot[1].shot(tex.getX() + tex.getWidthScaled()/2 + to_shot[0].sprite.getWidthScaled()*1.1f , tex.getY());
		 		
	 			
	 			

		    break;
		 	case 1:
		 		
		 	
		 	break;
		 }
		}
	}
	
	void przegrzej()
	{
		przegrzanie += szybkosc_przegrzewania * wspolczynnik_cieplny;
		if(przegrzanie >= max_przegrzania) {przegrzanie = max_przegrzania; czyprzegrzany = true;}
	}
	
	
	void chlodz()
	{
		przegrzanie -= szybkosc_chlodzenia * wspolczynnik_cieplny;
		if(przegrzanie <= 0 ) {przegrzanie = 0 ; czyprzegrzany = false;}
	}

	void prepareFirstly(int is)
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
	
	  wystrzelonych = bullet.length - c;
	  
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

	void koliduj(Przeciwnik[] x)
	{
		for(int i = 0 ; i < x.length; i++)
		{ if(x[i].is_alive)	 
			if(tex.getX() > x[i].tex.getX() && tex.getX() + tex.getWidthScaled() < x[i].tex.getX() + x[i].tex.getWidthScaled() &&
					tex.getY() > x[i].tex.getY() && tex.getY() + tex.getHeightScaled() < x[i].tex.getY() + x[i].tex.getHeightScaled() )
			{
				System.out.println("Kurwa jeb³em! PAcz jak jeb³em!");
				//co ma robic po dedzie
			}
		}
	}

	
	
	
	
	
	public void move(SensorEvent event)
	{
	
		
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
		
		//if( tex.getY() > 0 ) //KOLIZJA GÓRY
		//	tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		//else
		//{
		//	tex.setPosition(tex.getX(), 0);	
		//	tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		//}
		
		//if( tex.getY() < h - tex.getHeightScaled() ) // KOLIZJA DO£U
		//	tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		//else
		//{
		//	tex.setPosition(tex.getX(), h - tex.getHeightScaled() );	
		//	tex.setPosition(tex.getX(), tex.getY() + event.values[1]/speed);
		//}
	}
	
	
	


	
}
