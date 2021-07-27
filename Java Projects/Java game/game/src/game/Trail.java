package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public  class Trail extends GameObject {

	Manuire hendler;
	private float alpha=1;
	private Color color;
	private int width,height;
	private float life;
	
	public Trail(float x, float y, ID id,int width,int height,float life,Color color,Manuire hendler) {
		super(x, y, id);
		this.color=color;
		this.height=height;
		this.width=width;
		this.life=life;
		this.hendler=hendler;
	}
	
	public void tick() {
		if (alpha>life) {
			alpha-=(life-0.001f);
		}else hendler.removeObject(this);
		
	}
	public void rander(Graphics g) {
		
		Graphics2D g2d=(Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x,(int)y,width, height);
		
		g2d.setComposite(makeTransparent(1));
	}
	private AlphaComposite makeTransparent(float alpha) {
		
		int type=AlphaComposite.SRC_OVER;
		
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	public Rectangle getBounds() {
		return null;
	}

}
