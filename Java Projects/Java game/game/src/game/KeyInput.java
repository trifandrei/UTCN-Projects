package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Manuire hendler;
	private boolean[] keys=new boolean[4];
	
	public KeyInput(Manuire hendler){
		this.hendler=hendler;
		
		keys[0]=false;
		keys[1]=false;
		keys[2]=false;
		keys[3]=false;
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key=e.getKeyCode();
		
		
		for(int i=0 ;i<hendler.object.size();i++) {
			GameObject auxObject=hendler.object.get(i);
			
			if(auxObject.getID()==ID.Player) {
				//toate evenimentele playerului 1;	
				if(key==KeyEvent.VK_W) { auxObject.setValY(-5); keys[0]=true;}
				if(key==KeyEvent.VK_S) { auxObject.setValY(5);  keys[1]=true;}
				if(key==KeyEvent.VK_D) { auxObject.setValX(5);  keys[2]=true;}
				if(key==KeyEvent.VK_A) { auxObject.setValX(-5); keys[3]=true;}
				
			}
			if(key==KeyEvent.VK_ESCAPE) System.exit(0);;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i=0 ;i<hendler.object.size();i++) {
			GameObject auxObject=hendler.object.get(i);
			
			if(auxObject.getID()==ID.Player) {
				//toate evenimentele playerului 1;	
				if(key==KeyEvent.VK_W) keys[0]=false; //auxObject.setValY(0);
				if(key==KeyEvent.VK_S) keys[1]=false; //auxObject.setValY(0);
				if(key==KeyEvent.VK_D) keys[2]=false; //auxObject.setValX(0);
				if(key==KeyEvent.VK_A) keys[3]=false; // auxObject.setValX(0);
				
				//miscarea pe verticala
				if(!keys[0] && !keys[1]) auxObject.setValY(0);
				
				//miscarea pe orizontala
				if(!keys[2] && !keys[3]) auxObject.setValX(0);
			}
		}
	}
}
