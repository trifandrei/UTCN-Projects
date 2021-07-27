package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SuperEnemy extends GameObject{
	
	Manuire hendler;
	private int timer=20;
	private int timer2=40;
	Random r=new Random();
	
	public SuperEnemy(float x, float y, ID id,Manuire hendler) {
		super(x, y, id);
		this.hendler=hendler;
		valX=0;
		valY=5;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	public void tick() {
		x+=valX;
		y+=valY;
		
		if(timer<=0) 
			valY=0;
		else timer--;
		
		if(timer<=0 )timer2--;
		
		if(timer2<=0) {
			if(valX==0) valX=2;
			int spawn=r.nextInt(20);
				if(spawn==0) hendler.addObject(new Proiectil(x+40,y+40,ID.NormalEnemy,hendler));
		}			
			
		if(x<=0 || x>=Game.WIDTH-64) valX *=-1;
		hendler.addObject(new Trail(x,y,ID.Trail,64,64,0.1f,Color.red,hendler));
		
	}

	
	public void rander(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x,(int)y, 64, 64);
		
	}

}
