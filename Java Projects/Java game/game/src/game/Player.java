package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

	
public class Player extends GameObject {
	
	Manuire hendler;
	
	public Player(float x, float y, ID id,Manuire hendler) {
		super(x, y, id);
		this.hendler=hendler;
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}


	public void tick() {
		x +=valX;
		y +=valY;
		
		x=Game.margine((int)x, 0, Game.WIDTH-40);
		y=Game.margine((int)y, 0, Game.HEIGHT-58);
		
		coliziune();
	}
	private void coliziune() {
		
		for(int i=0 ;i<hendler.object.size();i++) {
			GameObject auxObject=hendler.object.get(i);
			
			if(auxObject.getID()==ID.NormalEnemy || auxObject.getID()==ID.FastEnemy || auxObject.getID()==ID.SmartEnemy ||auxObject.getID()==ID.Boss) {
				
				if(getBounds().intersects(auxObject.getBounds())) {
				//coliziune
					
					HUD.Viata -=1;
				}
			}
		}
	}
	
	

	public void rander(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x,(int)y, 32, 32);
	}

	

}
