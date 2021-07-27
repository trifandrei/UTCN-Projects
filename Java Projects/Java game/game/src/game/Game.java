package game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 8747795779713838939L;
	public static int WIDTH=960,HEIGHT=640;
	
	private Thread thread;
	private boolean merge=false;
	private Manuire hendler;
	private HUD hud;
	private Inviere invi;
	private Meniu meniu;
	
	public enum Faze{
		Meniu,
		Game
	};
	public Faze gameFaza=Faze.Meniu;
	
	public Game() {
		
		hendler = new Manuire();
		meniu=new Meniu(this,hendler);
		
		this.addKeyListener(new KeyInput(hendler));
		this.addMouseListener(meniu);
		
		new Fereastra (WIDTH,HEIGHT,"<-GAME->",this);
		
		hud = new HUD();
		invi=new Inviere(hendler,hud);
		
		
		if(gameFaza==Faze.Game) {
			hendler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,hendler));
			hendler.addObject(new NormalEnemy(50,50,ID.NormalEnemy,hendler));
		}
		
	
		
		
	}

	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		merge=true;
	}	
	public synchronized void stop() {
		try {
			thread.join();
			merge=false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void run (){
		this.requestFocus();
		long lastTime= System.nanoTime();
		double numarTick= 60.0;
		double ns = 1000000000 / numarTick;
		double delta= 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(merge){
			long now = System.nanoTime();
			delta +=(now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(merge)
				rander();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000) {
				timer +=1000;
				System.out.println("FPS: "+frames);
				frames = 0;
			}	
		}
		stop();
	}
	private void tick() {
		
		hendler.tick();
		
		if(gameFaza==Faze.Game) {
			
			hud.tick();
			invi.tick();
			
		}else 
			if(gameFaza==Faze.Meniu){
			meniu.tick();
		}
		
	}
	private void rander() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		hendler.rander(g);
		
		if(gameFaza==Faze.Game) {
		
		hud.render(g);
		
		}else
			if(gameFaza==Faze.Meniu){
			meniu.render(g);;
		}
		g.dispose();
		bs.show();
	}
	public static float margine (float val,float min,float max) {
		if (val>=max)
			return val=max;
		else if(val<=min)
			return val=min;
		return val;
		
	}
	

	public static void main(String args[])
	{
		new Game();
	}
}

