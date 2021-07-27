package Operatii;
import Polynom.Monom;
import Polynom.Polinom;

public class Operation {
	//polinom r=polinom a + polinom b
	public Polinom addP(Polinom a,Polinom b) {
		Polinom r=new Polinom();
		int i=0;
		int j=0;
			while(i<a.size() &&j<b.size())
					if(a.getMonomIndex(i).getGrad()>b.getMonomIndex(j).getGrad()){
						r.getMo().add(new Monom(a.getMonomIndex(i).getCoef(),a.getMonomIndex(i).getGrad()));
						i++;	}
					else 
						if(a.getMonomIndex(i).getGrad()==b.getMonomIndex(j).getGrad()) {
						r.getMo().add(new Monom(a.getMonomIndex(i).getCoef()+b.getMonomIndex(j).getCoef(),a.getMonomIndex(i).getGrad()));
						i++;
						j++;
					}
					else
						if(b.getMonomIndex(j).getGrad()>a.getMonomIndex(i).getGrad()){
							r.getMo().add(new Monom(b.getMonomIndex(j).getCoef(),b.getMonomIndex(j).getGrad()));
							j++;
						}
			while(i<a.size()) {
				r.getMo().add(new Monom(a.getMonomIndex(i).getCoef(),a.getMonomIndex(i).getGrad()));
				i++;
			}		
			while(j<b.size()) {
				r.getMo().add(new Monom(b.getMonomIndex(j).getCoef(),b.getMonomIndex(j).getGrad()));
				j++;
			}		
		return r;
	}	
	//polinom r=polinom a - polinom b
	public Polinom minusP(Polinom a,Polinom b) {
		Polinom r=new Polinom();
		int i=0;
		int j=0;
			while(i<a.size() &&j<b.size())
					if(a.getMonomIndex(i).getGrad()>b.getMonomIndex(j).getGrad()){
						r.getMo().add(new Monom(a.getMonomIndex(i).getCoef(),a.getMonomIndex(i).getGrad()));
						i++;	}
					else 
						if(a.getMonomIndex(i).getGrad()==b.getMonomIndex(j).getGrad()) {
						r.getMo().add(new Monom(a.getMonomIndex(i).getCoef()-b.getMonomIndex(j).getCoef(),a.getMonomIndex(i).getGrad()));
						i++;
						j++;
					}
					else
						if(b.getMonomIndex(j).getGrad()>a.getMonomIndex(i).getGrad()){
							r.getMo().add(new Monom(b.getMonomIndex(j).getCoef()*(-1),b.getMonomIndex(j).getGrad()));
							j++;
						}
			while(i<a.size()) {
				r.getMo().add(new Monom(a.getMonomIndex(i).getCoef(),a.getMonomIndex(i).getGrad()));
				i++;
			}		
			while(j<b.size()) {
				r.getMo().add(new Monom(b.getMonomIndex(j).getCoef()*(-1),b.getMonomIndex(j).getGrad()));
				j++;
			}		
		return r;
	}	
	//polinom r=polinom a * polinom b
	public Polinom mulP(Polinom a,Polinom b) {
		Polinom r=new Polinom();
			
		if (a.size()>b.size()) {
			for(int j=0;j<b.size();j++){
				for(int i=0;i<a.size();i++){
					r.getMo().add(new Monom(a.getMonomIndex(i).getCoef()*b.getMonomIndex(j).getCoef(),a.getMonomIndex(i).getGrad()+b.getMonomIndex(j).getGrad()));
				}			
			}
		}
		if (a.size()<=b.size()) {
			for(int i=0;i<a.size();i++) {
				for(int j=0;j<b.size();j++) {
					r.getMo().add(new Monom(a.getMonomIndex(i).getCoef()*b.getMonomIndex(j).getCoef(),a.getMonomIndex(i).getGrad()+b.getMonomIndex(j).getGrad()));
				}			
			}
		}	
		for(int i=0;i<r.size();i++) {
			int j=i+1;
			while(j<r.size()) {
				if(r.getMonomIndex(i).getGrad()==r.getMonomIndex(j).getGrad()) {
					r.getMonomIndex(i).setCoef(r.getMonomIndex(i).getCoef()+r.getMonomIndex(j).getCoef());
					r.getMo().remove(j);
				}
				else
					j++;
			}	
		}
	return r;
	}
	//Polinom r=derivata polinomului a
	public Polinom derivP(Polinom a) {
		Polinom r=new Polinom();
		
		for(Monom i: a.getMo() ) {
			if(i.getGrad()-1<0)
				break;
			else
				r.getMo().add(new Monom(i.getCoef()*i.getGrad(),i.getGrad()-1));
		}
		return r;
	}
	// Polinom r=integrala polinomului a
	public Polinom integP(Polinom a) {
		Polinom r=new Polinom();
		int g;
		double m;
		int t=0;
		for(Monom i: a.getMo() ) {
			g=i.getGrad()+1;
			i.setCoefD(i.getCoef());
			m=i.getCoefD()/g;
				r.getMo().add(new Monom(m,0));
				r.getMonomIndex(t).setGrad(g);
				t++;
		}
		
		return r;
	}
}