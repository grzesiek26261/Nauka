package com.nauka;

import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class lb 
{
	MainActivity act;
	
	BitmapTextureAtlas atlas;
	TextureRegion region; 
	
	lb(String name, int w, int h)
	{
		act = MainActivity.getSharedInstance();
		w = poteguj(w);
		h = poteguj(h);

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		atlas = new BitmapTextureAtlas(w, h,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, act, name+".png", 0, 0);
		act.getTextureManager().loadTexture(atlas);
		
		
	}
	
	
	
	private int poteguj(int x)
	{
	  for(int i = 0 ; i < 13; i++)
		if(Math.pow(2, i) == x) return x;

	  for(int i = 0 ; i < 13 ;i++)
	  {
		  if (Math.pow(2, i) > x) return (int) Math.pow(2, i);
	  }
	  
	  return -1;
	}
	
	
	
	
}

