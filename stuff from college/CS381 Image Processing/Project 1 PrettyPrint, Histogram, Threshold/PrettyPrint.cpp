#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
using namespace std;

      //prettyprint method
      void PrettyPrint(string fileName  ){
                    
      ifstream infile; //create a file readr
      infile.open(fileName);
      
      //read first 4 numebr which contain the header info
      int crit;
      infile >> crit;      
      int numRow = crit;
      infile >> crit;    
      int numCol = crit;
      infile >> crit;   
      int minVal = crit;
      infile >> crit;
      int maxVal = crit;
     
      
      // write
      //declare output file name   
      string extension = "pp.txt";   
      ofstream outfile (fileName.substr(0,fileName.length()-4)+ extension);
      int inNum ;
     
      //if the file contains more numbers to read
      int columncount = 0;
      while(infile >> inNum){
          if(inNum >0 ) { outfile << inNum << " ";}
          else{ outfile << " " << " ";}
          columncount++;
          if(columncount == numCol){
              outfile<<'\n';
              columncount = 0;
              }
      
      }
      //write to the output file
      
      
      //done reading and writing, close file
      infile.close();
      outfile.close();
                          
      }//PrettyPrint method
                                 


int main(int argc, char** argv){
    
    PrettyPrint(argv[1]);
 return 0;   
}
