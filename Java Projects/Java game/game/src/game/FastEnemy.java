package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
		
		Manuire hendler;
		
		public FastEnemy(float x, float y, ID id,Manuire hendler) {
			super(x, y, id);
			this.hendler=hendler;
			valX=2;
			valY=9;
		}

		public Rectangle getBounds() {
			return new Rectangle((int)x,(int)y,16,16);
		}
		
		public void tick() {
			x+=valX;
			y+=valY;
			
			if(y<=0 || y>=Game.HEIGHT-32) valY *=-1;
			if(x<=0 || x>=Game.WIDTH-16) valX *=-1;
			
			hendler.addObject(new Trail(x,y,ID.Trail,16,16,0.03f,Color.white,hendler));
			
		}

		
		public void rander(Graphics g) {
			g.setColor(Color.white);
			g.fillRect((int)x, (int)y, 16, 16);
			
		}

	}

