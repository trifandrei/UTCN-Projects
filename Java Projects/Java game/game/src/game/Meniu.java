package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Game.Faze;

public class Meniu extends MouseAdapter{
	 
	private Game game;
	private Manuire hendler;
	
	public Meniu(Game game,Manuire hendler)
	{
		this.game=game;
		this.hendler=hendler;
		
	}
	public void mousePressed(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		
		System.out.println("LAS"+mx);
		//play
		
		if(MouseOver(mx,my,400,100,150,64)) {
			game.gameFaza=Faze.Game;
			hendler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,hendler));
			hendler.addObject(new NormalEnemy(50,50,ID.NormalEnemy,hendler));
		}
		///quit
		if(MouseOver(mx,my,400,200,150,64)) {
			System.exit(1);
			
		}
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	private boolean MouseOver(int mx,int my,int x,int y,int width,int height) {
		if(mx > x && mx < x+width) {
			if ((my>y) && (my < y+height)) {
				return true;
			}else return false;
		}else return false;
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		Font fnt=new Font("arial",1,50);
		Font fnt2=new Font("arial",1,30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Meniu", 400, 70);
		
		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("Play", 440, 140);
		g.drawRect(400, 100, 150, 64);
		
		g.setColor(Color.WHITE);
		g.drawRect(400, 200, 150, 64);
		g.drawString("Quit", 440, 240);
		
	}

}
