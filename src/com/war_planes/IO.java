package com.war_planes;

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
		putData("0","cash");
		putData("0","distance");
	}
	
	
	
	
	
	
}
