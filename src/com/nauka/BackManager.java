package com.nauka;

public class BackManager 
{
	MainActivity act;
	int current_id;
	
	BackManager()
	{
		act = MainActivity.getSharedInstance();
		current_id = act.ID;
		
		
		
		
	}
	
	
	void backfrom(int id)
	{
		switch(id)
		{
		case 0:
			act.finish();
			break;
		case 1:
			act.finish();
			
			break;
		case 2:
			act.setCurrentScene(new Menu());
			break;
		case 3:
			act.game.hud.setVisible(false);
			act.setCurrentScene(new Wybor());
			
			break;
		case 4:
			break;
		}
	}
	
	
	
	
	
	
	
}
