#include <iostream>
#include <fstream>
#include <string>
#define numRow = 45;
using namespace std;


int lit;
int mirrorFramedAry[49][49];
int tempAry[45][45];
int  neighborARY[9];
int  neighborAVG[8];


int computeGroup(int g, int i, int j) {
	int avg=0;
	if (g ==1) {
	 avg = (mirrorFramedAry[i - 2][j - 2] +mirrorFramedAry[i - 2][j - 1] +mirrorFramedAry[i - 2][j - 0]
			+ mirrorFramedAry[i - 1][j - 2] + mirrorFramedAry[i - 1][j - 1]+ mirrorFramedAry[i - 1][j - 0]
			+ mirrorFramedAry[i - 0][j - 2]+ mirrorFramedAry[i - 0][j - 1]+ mirrorFramedAry[i - 0][j - 0])/9;
	 cout << avg;
	 cin >> lit;
	}//1
	return avg;
}

void computeThreshold(string fileName, string outFileName) {
	ifstream infile; //create a file readr
	infile.open(fileName); // open file
	ofstream outfile(outFileName);

	int lit;
	int crit;
	infile >> crit; //int numRow = crit;
	infile >> crit;// int numCol = crit;
	infile >> crit; int minVal = crit;
	infile >> crit; int maxVal = crit;

	//ZERO
	for (int i = 0; i < 49; i++) {
		for (int j = 0; j < 49; j++) {
			mirrorFramedAry[i][j] = 0;
		}//forj
	}//fori

	for (int i = 2; i < 47; i++) {
		for (int j = 2; j < 47; j++) {
			infile >> crit;
			mirrorFramedAry[i][j] = crit;
		}//forj
	}//fori
	//print out
	
	for (int i = 0; i < 49; i++) {
		mirrorFramedAry[i][0] = mirrorFramedAry[i][2];
		mirrorFramedAry[i][1] = mirrorFramedAry[i][2];
		mirrorFramedAry[i][47] = mirrorFramedAry[i][46];
		mirrorFramedAry[i][48] = mirrorFramedAry[i][46];
	}//top to bot
	for (int j = 0; j < 49; j++) {
		mirrorFramedAry[0][j] = mirrorFramedAry[2][j];
		mirrorFramedAry[1][j] = mirrorFramedAry[2][j];
		mirrorFramedAry[47][j] = mirrorFramedAry[46][j];
		mirrorFramedAry[48][j] = mirrorFramedAry[46][j];
	}//top to bot
	
	
	for (int i = 0; i < 49; i++) {
		for (int j = 0; j < 49; j++) {
			outfile << mirrorFramedAry[i][j] << " ";
		}//forj
		outfile << endl;
	}//fori

	for (int i = 2; i < 47; i++) {
		for (int j = 2; j < 47; j++) {
			//group1
			neighborAVG[0] = computeGroup(1,i,j);
			neighborAVG[1] = computeGroup(2, i, j);
			neighborAVG[2] = computeGroup(3, i, j);
			neighborAVG[3] = computeGroup(4, i, j);
			neighborAVG[4] = computeGroup(5, i, j);
			neighborAVG[5] = computeGroup(6, i, j);
			neighborAVG[6] = computeGroup(7, i, j);
			neighborAVG[7] = computeGroup(8, i, j);

		}//forj
	}//fori
	




	infile.close();
	outfile.close();
}//corner preserve

int main(int argc, char** argv) {
	computeThreshold(argv[1], argv[2]);
	return 0;
}//main

