import java.util.StringTokenizer;

public class Polynomial {
//data member is linked list
	
	
	DoublyLinkedList<Term> poly;
	//constructor
	public Polynomial (String line) {
	    
	    poly = new DoublyLinkedList<Term>();
	    
	    StringTokenizer st = new StringTokenizer(line);
	    while ( st.hasMoreTokens() ) {
	        
	        double c = Double.parseDouble( st.nextToken() ); // index 0

	        int e = Integer.parseInt ( st.nextToken() ); // at index2
	        
	        Term t = new Term (c,e);
	        
	        poly.add(t);
	    }

	}
	
	public Polynomial () { // default constructor
	    
	    poly = new DoublyLinkedList<Term>(); // just initialize
	    // no terms added on the polynomial
	}
	    
	    
	    public Polynomial add (Polynomial p)  {
	        
	        // we are adding "poly" and "p.poly"
	        Polynomial result = new Polynomial(); //
	        
	        for (int i=0; i<poly.size(); i++) {
	            
	            Term t = poly.get(i); // first polynomila
	            
	            boolean flag = false;
	            
	            for (int j=0; j<p.poly.size(); j++) {
	                
	                Term u = p.poly.get(j); // 2nd polynomail
	                
	                if ( t.getexp() == u.getexp() ) {
	                    
	                    double result_coef = t.getcoef() + u.getcoef();
	                    int result_exp = t.getexp(); 
	                    
	                    Term result_term = new Term (result_coef, result_exp);
	                    
	                    result.poly.add(result_term);
	                    
	                    flag = true;   
	                }
	            }
	            
	            if (flag == false) {
	                result.poly.add(t);
	            }
	            
	        }
	        
	        
	        return result;
	    }
	    
	    
	    public Polynomial multiply (Polynomial p) {
	        
	        Polynomial result = new Polynomial();
	        
	        for (int i=0; i<poly.size(); i++) {
	            
	            Term t = poly.get(i); // first polynomila

	            for (int j=0; j<p.poly.size(); j++) {
	                
	                Term u = p.poly.get(j); // 2nd polynomail
	                
	                double res_coef = t.getcoef() * u.getcoef();
	                int res_exp = t.getexp() + u.getexp();
	                
	                Term res = new Term(res_coef, res_exp);
	                
	                result.poly.add(res);
	            }
	            
	        }
	        return result;
	    }
	    
	    public Polynomial subtract (Polynomial p)  {
	        
	        // we are adding "poly" and "p.poly"
	        Polynomial result = new Polynomial(); //
	        
	        for (int i=0; i<poly.size(); i++) {
	            
	            Term t = poly.get(i); // first polynomila
	            
	            boolean flag = false;
	            
	            for (int j=0; j<p.poly.size(); j++) {
	                
	                Term u = p.poly.get(j); // 2nd polynomail
	                
	                if ( t.getexp() == u.getexp() ) {
	                    
	                    double result_coef = t.getcoef() - u.getcoef();
	                    int result_exp = t.getexp(); 
	                    
	                    Term result_term = new Term (result_coef, result_exp);
	                    
	                    result.poly.add(result_term);
	                    
	                    flag = true;   
	                }
	            }
	            
	            if (flag == false) {
	                result.poly.add(t);
	            }
	            
	        }
	        
	        
	        return result;
	    }
	    
	    
	    
	    public String toString() {
	        
	        // example: x^3 + 4x^2 + 3x – 5 
	        
	        String output = "";
	        
	        for (int i=0; i<poly.size(); i++) {
	            
	            Term t = poly.get(i);
	            
	            if (t.getcoef()==1 && t.getexp()==1)
	                output += "x";
	            else if (t.getcoef() == 1)
	                output += "x^" + t.getexp() + " + ";
	            else if (t.getexp() == 1)
	                output += t.getcoef() + "x + ";
	            else if (t.getexp() == 0)
	                output += t.getcoef();
	            else
	                output += t.getcoef() + "x^" + t.getexp() + " + ";
	            
	        }
	        
	        return output;
	    }
	    
	}
