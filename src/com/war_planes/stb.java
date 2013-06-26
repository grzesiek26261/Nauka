package com.war_planes;

import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;


public class stb
{
	MainActivity act;
	
	 public BitmapTextureAtlas A; //atlas
	 public TextureRegion T;//tesktura
	 public TiledTextureRegion tiledT;
	
	stb(String filename ,int w , int h)
	{
		act = MainActivity.getSharedInstance();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		w = poteguj(w);
		h = poteguj(h);
		A = new BitmapTextureAtlas(w,h, TextureOptions.DEFAULT);
  	  	T = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.A, act, filename + ".png", 0, 0);
  	    act.getTextureManager().loadTexture(A);
	}
	
	
	stb(String filename ,int w , int h,int row,int column)//row - ile w szerokosci obrazkow column w pionie 
	{
		
		act = MainActivity.getSharedInstance();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		w = poteguj(w);
		h = poteguj(h);
		A = new BitmapTextureAtlas(w,h, TextureOptions.DEFAULT);
  	  	tiledT = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(A, act, filename + ".png", 0, 0, row, column);
  	    act.getTextureManager().loadTexture(A);
	}
	
	
	

	private int poteguj(int x)
	{
	  for(int i = 0 ; i < 50; i++)
		if(Math.pow(2, i) == x) return x;

	  for(int i = 0 ; i < 50 ;i++)
	  {
		  if (Math.pow(2, i) > x) return (int) Math.pow(2, i);
	  }
	  
	  return -1;
	}
	
	
	
	
	
}
