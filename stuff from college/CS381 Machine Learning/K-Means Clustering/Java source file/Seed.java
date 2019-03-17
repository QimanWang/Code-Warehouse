
public class Seed {
	double s1 ;
	double s2;
	double s3 ;
	double s4;
	double s5;
	double s6;
	double s7;
	int clusterPile;
//initilaize the 
	public Seed (){
		
	}
	
	public Seed (double s1, double s2,double s3, double s4,double s5, double s6, double s7 ){
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
		this.s4=s4;
		this.s5=s5;
		this.s6=s6;
		this.s7=s7;
		
	}//constructor
	
	//get components
	public double getd1(){
		return this.s1;
	}
	public double getd2(){
		return this.s2;
	}
	public double getd3(){
		return this.s3;
	}
	public double getd4(){
		return this.s4;
	}
	public double getd5(){
		return this.s5;
	}
	public double getd6(){
		return this.s6;
	}
	public double getd7(){
		return this.s7;
	}
	//set components
	public void setd1(double d1){
		this.s1 = d1;
	}
	public void setd2(double d2){
		this.s2 = d2;
	}
	public void setd3(double d3){
		this.s3 = d3;
	}
	public void setd4(double d4){
		this.s4 = d4;
	}
	public void setd5(double d5){
		this.s5 = d5;
	}
	public void setd6(double d6){
		this.s6 = d6;
	}
	public void setd7(double d7){
		this.s7 = d7;
	}
	
	//set cluster method
	public void setCluster (int clusterPile ){
		this.clusterPile = clusterPile;
	
	}//cluster number method
	
	public int getCluster( ){
		return this.clusterPile;
	}
	

}//class
