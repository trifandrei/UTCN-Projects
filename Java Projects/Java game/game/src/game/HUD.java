package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int Viata=100;
	private int  verde=255;
	private int scor=0;
	private int nivel=1;
	
	public void tick() {
		Viata =(int) Game.margine(Viata, 0, 100);
		verde= (int) Game.margine(verde, 0, 255);
		verde=Viata*2;
		scor++;
	}
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75,verde,0));
		g.fillRect(15, 15,Viata*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Scor: "+scor,15, 64);
		g.drawString("Nivel: "+nivel,15, 84);
	}
	public void setScor(int scor) {
	 this.scor=scor;
	}
	public int getScor() {
		return scor;
	}
	public void setNivel(int nivel) {
		 this.nivel=nivel;
	}
		public int getNivel() {
			return nivel;
	}
}
