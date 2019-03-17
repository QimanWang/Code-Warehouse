#include <iostream>
#include <fstream>
#include <string>
using namespace std;

void computeThreshold(string fileName, string outFileName, int thresholdValue) {
	ifstream infile; //create a file readr
	infile.open(fileName); // open file

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

	//declare output file name
	string extension1 = "_thr_";
	string extension2 = to_string(thresholdValue);
	string extension3 = ".txt" ;

	ofstream outfile(outFileName + extension1+extension2+extension3);

	//read in number and print out desired result
	
	int inNum;
	int columncount = 0;
	outfile << numRow << " " << numCol << " " << "0 " << "1"<<'\n';
	while (infile >> inNum) {
		if (inNum <thresholdValue) { outfile <<"0 "; }
		else { outfile << "1 "; }
		columncount++;
		if (columncount == numCol) {
			outfile << '\n';
			columncount = 0;
		}//if
	}//while file has more num

	infile.close();
	outfile.close();

}//thresholding

void PrettyPrint(string fileName) {

	ifstream infile; //create a file readr
	infile.open(fileName); // open file

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

	//declare output file name   
	string extension = "_pp.txt";
	ofstream outfile(fileName.substr(0, fileName.length() - 4) + extension);

	//read in number and print out desired result
	int inNum;
	int columncount = 0;
	while (infile >> inNum) {
		if (inNum >0) { outfile << inNum << " "; }
		else { outfile << "  "; }
		columncount++;
		if (columncount == numCol) {
			outfile << '\n';
			columncount = 0;
		}//if
	}//while file has more num

	infile.close();
	outfile.close();

}//PrettyPrint method

int main(int argc, char** argv) {
	int thresholdValue;
	cout << "enter a threshold value" << endl;
	cin >> thresholdValue;
	computeThreshold(argv[1],argv[2],thresholdValue);
	PrettyPrint(argv[3]);

	//incase pretty print dont work from command line argument
	string extension1 = "_thr_";
	string extension2 = to_string(thresholdValue);
	string extension3 = ".txt";

	PrettyPrint(argv[2] + extension1 + extension2 + extension3);

	return 0;
}//main