package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class SmartEnemy extends GameObject{

	Manuire hendler;
	GameObject player;
	
	public SmartEnemy(float x, float y, ID id,Manuire hendler) {
		super(x, y, id);
		this.hendler=hendler;
		for(int i=0 ;i<hendler.object.size();i++) {
			if(hendler.object.get(i).getID()==ID.Player)
			{
				player=hendler.object.get(i);
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x+=valX;
		y+=valY;
		
		float difX= x - player.getX()-32;
		float difY= y - player.getY()-32;
		float distanta=(float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		valX= (-1/distanta)*difX;
		valY= (-1/distanta)*difY;
		
		if(y<=0 || y>=Game.HEIGHT-32) valY *=-1;
		if(x<=0 || x>=Game.WIDTH-16) valX *=-1;
		
		hendler.addObject(new Trail(x,y,ID.Trail,16,16,0.02f,Color.YELLOW,hendler));
		
	}

	
	public void rander(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)x,(int)y, 16, 16);
		
	}


}
