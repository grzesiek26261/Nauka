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
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		atlas = new BitmapTextureAtlas(w, h,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, act, name+".png", 0, 0);
		act.getTextureManager().loadTexture(atlas);
		
		
	}
}
