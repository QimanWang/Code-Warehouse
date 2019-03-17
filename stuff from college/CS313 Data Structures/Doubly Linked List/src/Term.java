
public class Term implements Comparable<Term> 
{

	
	//defult constructor
	public double coef ;
	public int exp ; 
	
	//argument constructor
	public Term(double c, int e){
		coef =c;
		exp = e;
	}
	
	//get methods
	public double getcoef() {return coef;}
	public int getexp() {return exp;}
	
	//set method
	public void setcoef(double c){
		coef = c;
	}
	public void setexp(int e){
		exp = e;
	
		}
	public int compareTo(Term a)
{		if (a.exp <= exp ) {
			return -1;
}
		else{
			return 1;
		}
}
	
	
	
	
    
}
