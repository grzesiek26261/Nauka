package com.war_planes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader 
{
	MainActivity act;
	
	 InputStream in;
	 BufferedReader reader;
	 public String[] tablica;
	 public char[][] mapa;
	 int w,h;//rozmiary wczytanej mapy
	 
	FileReader(String v,int ilosc_linni_do_odczytania) throws IOException
	{
		act = MainActivity.getSharedInstance();
		tablica = new String[ilosc_linni_do_odczytania];

		 in = act.getAssets().open(v); 
		 reader = new BufferedReader(new InputStreamReader(in));
		 	for (int i = 0 ; i < ilosc_linni_do_odczytania ; i++)
			 tablica[i] = reader.readLine();
	 
	}
		 
		
	FileReader(String v) throws IOException //do tworzenia map
	{
		act = MainActivity.getSharedInstance();
		
		try {
			in = act.getAssets().open(v);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		 reader = new BufferedReader(new InputStreamReader(in));

		 
		 String firstline= reader.readLine();
		 int 	w=0,
				h=0 ;
		
		 String wt,ht;
		 wt = new StringBuilder().append(firstline.charAt(0)).append(firstline.charAt(1)).toString();
		 ht = new StringBuilder().append(firstline.charAt(2)).append(firstline.charAt(3)).toString();
		 	w = Integer.parseInt(wt);
		 	h = Integer.parseInt(ht);
		 		mapa = new char[w][h];
		 		char[] jedenwymiar = new char[w*h];

		 this.w = w;
		 this.h = h;
		 	String tmp = "",tt = null;
		 	for (int i = 0 ; i < h ; i++)
		 		{
					 tt = reader.readLine();
					 tmp += tt;
				 }
		 	
	    jedenwymiar = tmp.toCharArray();

		 int x = 0 ; int y = 0 ; 
		 for (int i = 0 ; i < w*h ; i++)
		 {
			 mapa[x][y] = jedenwymiar[i];
				 x++;
				 	if (x%w==0) {x=0;y++;}
		 }
		 

 
	}
	
	}
	

	
	

