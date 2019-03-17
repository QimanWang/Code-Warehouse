import java.util.Scanner;
import java.io.*;
public class Project1 {

	public static void main(String args[]){
	
	//open the file project1.txt and a loop to process each poly
		try{
            Scanner inputfile = new Scanner(new FileReader("project1.txt"));
            String line1 = inputfile.nextLine();
            String line2 = inputfile.nextLine();
            String line3 = inputfile.nextLine();
            
            Polynomial P1 = new Polynomial(line1);
            Polynomial P2 = new Polynomial(line2);
            
            if (line3.equalsIgnoreCase("add")) {
                Polynomial result = P1.add(P2);
                
                System.out.println(P1);
                System.out.println("+");
                System.out.println(P2);
                System.out.println("=");
                System.out.println(result);
            }
            else if (line3.equalsIgnoreCase("subtract")) {
                Polynomial result = P1.subtract(P2);
                System.out.println(P1);
                System.out.println("-");
                System.out.println(P2);
                System.out.println("=");
                System.out.println(result);
            }
                
            else {
                Polynomial result = P1.multiply(P2);
                System.out.println(P1);
                System.out.println("*");
                System.out.println(P2);
                System.out.println("=");
                System.out.println(result);
            }
                
            
            inputfile.close();
           }
           
           catch(Exception e)
           {System.out.println("Error using file(s).");}   

	
		
		
	}	
}
