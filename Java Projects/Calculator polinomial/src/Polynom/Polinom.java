package Polynom;
import java.util.ArrayList;

public class Polinom {
	private ArrayList<Monom> monom;

	public Polinom() {
		this.setMo(new ArrayList<Monom>());
	}
	//Convertim un string la polinom
	public ArrayList<Monom> Polinom1(String s) {
		setMo(new ArrayList<Monom>());//definim lista
		String v=s.replace("-","-n");//inlocuim toate - din string cu -n ca sa stim care coeficienti vor fi negativi
		String[] monom = v.split("\\+|\\-");//spargem stringul in functie de plus si minus
		for(String t : monom){//Foreach string din vectorul de stringuri
			if(t.equals(""))//daca dupa ce am spart primul string e null trecem la urmatorul
				continue;
			String[] mon = t.split("\\^");//spargein functie de ^ si creeam un vector de string cu 2 elemente
			int power = 0;
			int coef = 0;
			if(mon.length == 2)//in mon[1] vom avea puterea in tot deauna
				power = Integer.parseInt(mon[1]);//convetim a int
			if(mon[0].substring(0, 1).equals("n")){	// daca primul caracte din mon[0] e n stim ca vom avea un coefcient negativ
				if(mon[0].substring(1, 2).equals("x"))//daca al doilea caracter e x vom avea coef=-1
					coef=-1;
					else {
					coef=Integer.parseInt(mon[0].substring(1, 2))*(-1);//luam coeficientul si il convetim 
					}
			}
			else 
				if(mon[0].equals("x")){//daca monp[0]=x coef ii 1
					coef=1;
				}
				else
					if(mon[0].length()==1){
						coef=Integer.parseInt(mon[0]);//luam coeficientul pozitiva 
						power=0;
					}
					else{
						coef=Integer.parseInt(mon[0].substring(0,mon[0].length()-1));
					}
			this.getMo().add(new Monom(coef,power));	// adaugam in lista noul monom		
		}
		return this.getMo();//returnam Polinomul
	}
	//Returneaza un anumit monom
	public Monom getMonomIndex(int i) {
		return this.getMo().get(i);
	}
	//returneaza nr de monoame dintr-o lista
	public int size() {
		return this.getMo().size();
	}	
	//returneaza lista de monoame
	public ArrayList<Monom> getMo() {
		return monom;
	}
	
	public void setMo(ArrayList<Monom> monom) {
		this.monom = monom;
	}
	//toString suprascrie toString din object si returneaza polinomul ca string
	public String toString() {
		
    String s = new String();
    if((this.size()==0))
    	s="Polinomul va fi egal 0";
    else
    for (Monom i: this.monom) {
       if(i.getGrad()==0 )
       {	
          if (i.getCoef() > 0)
    	   s=s+"+"+i.getCoef();
          else
        	  if (i.getCoef() < 0)
        		  s=s+i.getCoef();
        	  else
        		  s=s+"Polinomul va fi egal 0";
       }
       	if(i.getGrad()>0 && i.getCoef()==1 )
       	 s = s + "+" +"x^"+i.getGrad();
       	
       	if(i.getGrad()>0 && i.getCoef()==-1)
       		 s = s + "-" +"x^"+i.getGrad();
       	
        if (i.getCoef() > 1 && i.getGrad()>0) 
            s = s + "+" + (i.getCoef()+"x^"+i.getGrad());
        
        if (i.getCoef() < -1 && i.getGrad()>0 ) 
        	s = s + (i.getCoef()+"x^"+i.getGrad());
        
    }
    int t=s.length();;
    if (s.substring(0,1).equals("+"))
    	s=s.substring(1,t);
    return s;
}
	//UN TO STRING PENTRU Polinoamele rezultat cu coeficient real
	public String doString() {
		 String s = new String();
		 double v;
		    for (Monom i: this.monom) {
		       	
		         if (i.getCoefD() > 0 ) {
		        	 v=Math.round(i.getCoefD()*100.0)/100.0;
		            s = s + "+" + v +"x^"+i.getGrad();
		         }
		        else 
		        	if (i.getCoefD() < 0 ) {
			        	 v=Math.round(i.getCoefD()*100.0)/100.0;
			            s = s  + v +"x^"+i.getGrad();
			         }
		    }
		    int t=s.length();;
		    if (s.substring(0,1).equals("+"))
		    	s=s.substring(1,t);
		    return s;
	}
}
