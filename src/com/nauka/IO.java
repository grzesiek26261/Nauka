package com.nauka;

import android.content.Context;
import android.content.SharedPreferences;

public class IO {

	MainActivity act;
	private SharedPreferences preferences;//klasa zapisu danych .. mniej wiecej

	private SharedPreferences.Editor preferencesEditor;
	public static final String prename = "save";
	
	IO()
	{
		act = MainActivity.getSharedInstance();
		
		preferences = act.getSharedPreferences(prename, Context.MODE_PRIVATE);
		preferencesEditor = preferences.edit();

	}
	
	void putData(String data,String key){ //wklada dane pod podany klucz
		preferencesEditor.putString(key, data);
		preferencesEditor.commit();//commit danych
	}
	
	String getData(String key)
	{
		 String ret = preferences.getString(key, "null");
		 return ret;
	}
	 
	    
	void FirstGameDeclare()
	{
		putData("true","first_play");
		putData("true","level_lock0");
		putData("0","stars_reached0");
		for (int i = 1 ; i < 21 ; i++ )//21 rund planowanych bylo na poczatku
		{
			putData("false","level_lock" + Integer.toString(i));
			putData("0","stars_reached" + Integer.toString(i));
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
