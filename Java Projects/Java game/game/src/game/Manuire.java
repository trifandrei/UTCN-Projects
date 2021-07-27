package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Manuire {
	LinkedList<GameObject> object =new LinkedList<GameObject>();
	
	public void tick() {
		for(int i=0;i<object.size();i++)
		{
			GameObject tempObject= object.get(i);
			tempObject.tick();
		}
	}
	public void StergeEnemy() {
		
		for(int i=0 ;i<object.size();i++) {
			GameObject auxObject=object.get(i);
			
			if(auxObject.getID()!=ID.Player)
				object.clear();
				addObject(new Player(250,250,ID.Player,this));
		}
	}
	public void rander(Graphics g) {
		for(int i=0;i<object.size();i++)
		{
			GameObject tempObject= object.get(i);
			tempObject.rander(g);
		}
	}
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
}
