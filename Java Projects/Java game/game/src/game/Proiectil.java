package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class Proiectil extends GameObject {

		Manuire hendler;
		Random r=new Random();
		
		public Proiectil(float x, float y, ID id,Manuire hendler) {
			super(x, y, id);
			this.hendler=hendler;
			valX=r.nextInt(5 - -5)+ -5;
			valY=5;
		}

		public Rectangle getBounds() {
			return new Rectangle((int)x,(int)y,16,16);
		}
		
		public void tick() {
			x+=valX;
			y+=valY;
			
			if(y>Game.HEIGHT) hendler.removeObject(this);
			
			hendler.addObject(new Trail(x,y,ID.Trail,16,16,0.03f,Color.ORANGE,hendler));
		}
		
		public void rander(Graphics g) {
			g.setColor(Color.ORANGE);
			g.fillRect((int)x,(int)y, 16, 16);
			
		}

	}

