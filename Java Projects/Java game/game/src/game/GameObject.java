package game;

import java.awt.Graphics;
import java.awt.Rectangle;

abstract class GameObject {
	
	protected float x,y;
	protected ID id;
	protected float valX,valY; //Controleaza vieza pe directia axelor;
	
	public GameObject(float x,float y,ID id){
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	public abstract void tick();
	public abstract void rander(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x=x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y=y;
	}
	public void setID(ID id) {
		this.id=id;
	}
	public ID getID() {
		return this.id;
	}
	public void setValX(float valX) {
		this.valX = valX;
	}
	public float getValX(){
		return this.valX;
	}
	public void setValY(float valY) {
		this.valY = valY;
	}
	public float getValY(){
		return this.valY;
	}
	
}
