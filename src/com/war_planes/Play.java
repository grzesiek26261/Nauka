package com.war_planes;

import java.io.IOException;



public class Play 
{
MainActivity act;
	String filename ;
	org.anddev.andengine.audio.music.Music file;
	
	Play(String f)
	{
		act = MainActivity.getSharedInstance();
		filename = f ;
		
		org.anddev.andengine.audio.music.MusicFactory.setAssetBasePath("sfx/");
	  
	  
	  try
	  {
	      file = org.anddev.andengine.audio.music.MusicFactory.createMusicFromAsset(act.getMusicManager(), act,filename+".mp3");
	      System.out.println("done!");
	  }
	  catch (IOException e)
	  {
	      e.printStackTrace();
	      System.out.println("err");
	  }
	}
	
	
	

}
