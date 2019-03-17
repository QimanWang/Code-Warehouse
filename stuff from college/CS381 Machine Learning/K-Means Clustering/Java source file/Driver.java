import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Driver {
	static Seed [] madSeeds =  new Seed [210];
	static Centroid centroid1;
	static Centroid centroid2;
	static Centroid centroid3;
	static Centroid centroidTemp1;
	static Centroid centroidTemp2;
	static Centroid centroidTemp3;
	static  double dist1 = 0; 
	static double dist2 = 0;
	static double dist3 = 0;
	static boolean finish = true;
	static int iteration = 0;
	
	//method to calculate the distance between a data point and a centroid
	public static double dist(Seed s, Centroid c){
		return Math.sqrt(Math.pow((s.getd1()-c.getd1()), 2)+Math.pow((s.getd2()-c.getd2()), 2)+Math.pow((s.getd3()-c.getd3()), 2)+
				Math.pow((s.getd4()-c.getd4()), 2)+Math.pow((s.getd5()-c.getd5()), 2)+Math.pow((s.getd6()-c.getd6()), 2)+
				Math.pow((s.getd7()-c.getd7()), 2));
	}//dist
	//method to calculate the distance between two seeds
	public static double distSeed(Seed s, Seed t){
		return Math.sqrt(Math.pow((s.getd1()-t.getd1()), 2)+Math.pow((s.getd2()-t.getd2()), 2)+Math.pow((s.getd3()-t.getd3()), 2)+
				Math.pow((s.getd4()-t.getd4()), 2)+Math.pow((s.getd5()-t.getd5()), 2)+Math.pow((s.getd6()-t.getd6()), 2)+
				Math.pow((s.getd7()-t.getd7()), 2));
	}//distSeed
	// method to update the cluster pile number that a seed belong to
	 public static Boolean updateClusterPile(){
		 Boolean isDifferent = false;
		 for(int i =0; i < 210; i++ ){
			 dist1 = dist(madSeeds[i],centroid1);						 
			 dist2 = dist(madSeeds[i], centroid2);						 
			 dist3 = dist(madSeeds[i], centroid3);
	
		if (dist1 <= dist2 && dist1 <= dist3){
			if (madSeeds[i].getCluster() != 1){
				isDifferent = true;
			}//diff1
			madSeeds[i].setCluster(1);
		}//if1
		if (dist2 <= dist3 && dist2 <= dist1){
			if (madSeeds[i].getCluster() != 2){
				isDifferent = true;
			}//diff2
			madSeeds[i].setCluster(2);
		}//if2
		if (dist3 <= dist1 && dist3 <= dist2){
			if (madSeeds[i].getCluster() != 3){
				isDifferent = true;
			}//diff3
			madSeeds[i].setCluster(3);
		}//if3
		
}//for assign the clusterpile number for each seed
		 return isDifferent; // return true if a seed's cluster changed 
	 }//updateClusterPile
	 //method to compute a new centroid by going through all the seeds's in the same cluster pile
	public static void computeCentroid(Seed[] seedlist ){	 //compute centroid
	 	double total1s1=0;
		double total1s2=0;
		double total1s3=0;
		double total1s4=0;
		double total1s5=0;
		double total1s6=0;
		double total1s7=0;
		
		double total2s1=0;
		double total2s2=0;
		double total2s3=0;
		double total2s4=0;
		double total2s5=0;
		double total2s6=0;
		double total2s7=0;
		
		double total3s1=0;
		double total3s2=0;
		double total3s3=0;
		double total3s4=0;
		double total3s5=0;
		double total3s6=0;
		double total3s7=0;
		
		double clus1 = 0;
		double clus2 = 0;
		double clus3 = 0;
		
		 for(int i = 0; i < 210; i++){
		 if (madSeeds[i].getCluster() == 1) {
				 clus1 ++;
			total1s1 += madSeeds[i].getd1();
			total1s2 += madSeeds[i].getd2();
			total1s3 += madSeeds[i].getd3();
			total1s4 += madSeeds[i].getd4();
			total1s5 += madSeeds[i].getd5();
			total1s6 += madSeeds[i].getd6();
			total1s7 += madSeeds[i].getd7();
		
			 }//cluster 1
			 if (madSeeds[i].getCluster() == 2) {
				 clus2 ++;
			total2s1 += madSeeds[i].getd1();
			total2s2 += madSeeds[i].getd2();
			total2s3 += madSeeds[i].getd3();
			total2s4 += madSeeds[i].getd4();
			total2s5 += madSeeds[i].getd5();
			total2s6 += madSeeds[i].getd6();
			total2s7 += madSeeds[i].getd7();
	
			 }//cluster 2
			 if (madSeeds[i].getCluster() == 3) {
				 clus3 ++;
			total3s1 += madSeeds[i].getd1();
			total3s2 += madSeeds[i].getd2();
			total3s3 += madSeeds[i].getd3();
			total3s4 += madSeeds[i].getd4();
			total3s5 += madSeeds[i].getd5();
			total3s6 += madSeeds[i].getd6();
			total3s7 += madSeeds[i].getd7();
	
			 }//cluster 3
		 }//for loop to check each point
		 //assign the new centroid by the mean of all data point's components
		centroid1 = new Centroid(total1s1/clus1,total1s2/clus1,total1s3/clus1,total1s4/clus1,total1s5/clus1, total1s6/clus1,total1s7/clus1);
		 centroid2 = new Centroid(total2s1/clus2,total2s2/clus2,total2s3/clus2,total2s4/clus2,total2s5/clus2, total2s6/clus2,total2s7/clus2);
		  centroid3 = new Centroid(total3s1/clus3,total3s2/clus3,total3s3/clus3,total3s4/clus3,total3s5/clus3, total3s6/clus3,total3s7/clus3);
		
		  //print out the  centroid components
		 System.out.println(centroid1.toString());
		 System.out.println(centroid2.toString());
		 System.out.println(centroid3.toString());
}//computeCentroid		 

 //compute IV
	public static double computeIV1(Centroid cen){
		double IV1 = 0;
		for(int i = 0; i <210; i++){
			if(madSeeds[i].getCluster()==1){
				IV1+=dist( madSeeds[i],centroid1);
			} //test if in same cluster
		}//for
		return IV1;
	}//computeIV1
	public static double computeIV2(Centroid cen){
		double IV2 = 0;
		for(int i = 0; i <210; i++){
			if(madSeeds[i].getCluster()==2){
				IV2+=dist( madSeeds[i],centroid2);
			} //test if in same cluster
		}//for
		return IV2;
	}//computeIV2
		 
	public static double computeIV3(Centroid cen){
		double IV3 = 0;
		for(int i = 0; i <210; i++){
			if(madSeeds[i].getCluster()==3){
				IV3+=dist( madSeeds[i],centroid3);
			} //test if in same cluster
		}//for
		return IV3;
	}//computeIV3
		 //COMPUTE EV!
	public static double computeEV(){
		double EV = 0;
		for(int i = 0; i <210; i++){
			for (int j=0;j<210;j++){
				if(madSeeds[i].getCluster()!=madSeeds[j].getCluster()){
					EV+=distSeed( madSeeds[i],madSeeds[j]);
				} 
			}
			//test if in same cluster
		}//for
		return EV;
	}//computeEV
	
	
//MAIN~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static void main(String[] args) {
		try{
			
			Scanner inputFile = new Scanner(new FileReader("seeds_dataset.txt"));
			//open the seed dataset file
			
		//method to read in per line and assign those double values to a seed array
			 for ( int i = 0; i < 210; i++ ){
				 Seed newSeed; //new data point
					//component of the seed data point
					double s1 = inputFile.nextDouble() ;
					double s2 = inputFile.nextDouble() ;
					double s3 = inputFile.nextDouble() ;
					double s4 = inputFile.nextDouble() ;
					double s5 = inputFile.nextDouble() ;
					double s6 = inputFile.nextDouble() ;
					double s7 = inputFile.nextDouble() ;
					//initialize the new seed with the 7 features/components
					newSeed = new Seed(s1,s2,s3,s4,s5,s6,s7);
					Random dice = new Random();
					int number = (dice.nextInt(3) +1);
					newSeed.setCluster(number ); // assign a random cluster K value to each data point. K = 1,2,3
					
					madSeeds[i] = newSeed; // add the seed to the seed array
	
			 }//for assign the 210 seeds the 7 feature values
			 System.out.println("The initial random centroids chosen are the following...");
			 double IVTOTALi = 0;
			 double EVTOTALi =0;
		computeCentroid(madSeeds);
		//first time computing the centroid
		 IVTOTALi = computeIV1(centroid1)+computeIV2(centroid2)+computeIV3(centroid3);
		 System.out.println("IV: "+ IVTOTALi);
		 EVTOTALi = computeEV();
		 System.out.println("EV: "+ EVTOTALi/420);
		 System.out.println(IVTOTALi/(EVTOTALi/420));
		//first time computign the IV, EV value
		 
		
				 // assign and compute new centroid ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 //while loop to keep updating the centroid until the data stop moving to different clusters
		 while(finish){
				 double IVTOTAL = 0;
				 double EVTOTAL =0;
			iteration++; //keep track of how many times the centroids changed to know the # of iteration it takes to converge
				System.out.println("this is the " +iteration +"th iteration");
			  finish = updateClusterPile();  // finish will get the boolean value from the updateClusterPile, 
			  //if no data is diffrent then this will return false and so the while opp will terminate
				 computeCentroid(madSeeds); // compute centroid
				 //compute IV and EV
				 IVTOTAL = computeIV1(centroid1)+computeIV2(centroid2)+computeIV3(centroid3);
				 System.out.println("IV: "+ IVTOTAL);
				 EVTOTAL = computeEV();
				 System.out.println("EV: "+ EVTOTAL/420); // 420 cuz it's lit and also because double counted when calling the computeEv method
				 System.out.println("IV/EV: " + IVTOTAL/(EVTOTAL/420));
		}
		
			
		}//catch
		 catch (FileNotFoundException ex) {
		     System.out.println("cannot find the file.. " );
		 }//catch message
		
	}//main

}//class

