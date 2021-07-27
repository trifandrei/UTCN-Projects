package Polynom;

public class Monom  {
	private int coef;
	private int grad;
	private double coefd;
	//constructor pentru monom cu coef intreg
 public Monom(int coef1,int grad1) {
	this.coef=coef1;
	this.grad=grad1;
 }
 //constructor pentru monom cu coef si grad 0//null
 public Monom() {
	 this.grad=0;
	 this.coef=0;
 }
 //constructor pentru monom cu coef real
public Monom(double coefd,int grad){
	 this.coefd=coefd;
	 this.grad=grad;
 }
  
 public int getCoef(){
	 	return this.coef;
 }
 
 public void setGrad(int grad1) {
	 this.grad=grad1;
 }
 
 public void setCoef(int coef) {
	 this.coef=coef;
 }
 public int getGrad() {

	 return this.grad;
 }
public double getCoefD() {
	return coefd;
}
public void setCoefD(double coefd) {
	this.coefd = coefd;
}
 
}
