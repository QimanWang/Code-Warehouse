#include<iostream>
#include<fstream>
#include<algorithm>
using namespace std;
//global variable
int lit;
int numRow, numCol, minVal, maxVal;
int p1NeighborAry[4];
int p2NeighborAry[4];
int skeletonNeighborAry[8];
int maxValAfterProcess = 0;

int** createZeroAry(string inputFile) {
	ifstream inFile; //create a file reader
	inFile.open(inputFile); // open file
	int crit;
	inFile >> crit;  numRow = crit;
	inFile >> crit;  numCol = crit;
	inFile >> crit;  minVal = crit;
	inFile >> crit;  maxVal = crit;
	//create 
	int** zeroFramedAry = new int*[numRow + 2];
	for (int i = 0; i < numRow + 2; i++) {
		zeroFramedAry[i] = new int[numCol + 2]();
	}//fori to create 0 array

	for (int i = 1; i < numRow + 1; i++) {
		for (int j = 1; j < numCol + 1; j++) {
			inFile >> crit;
			zeroFramedAry[i][j] = crit;
		}//fori
	}//fori
	return zeroFramedAry;
}//create Zero Array

int** pass1(int** imgAry) {

	for (int i = 1; i < numRow + 1; i++) {
		for (int j = 1; j < numCol + 1; j++) {
			if (imgAry[i][j] > 0) {
				p1NeighborAry[0] = imgAry[i - 1][j - 1];
				p1NeighborAry[1] = imgAry[i - 1][j];
				p1NeighborAry[2] = imgAry[i - 1][j + 1];
				p1NeighborAry[3] = imgAry[i][j - 1];
				sort(p1NeighborAry, p1NeighborAry + 4);
				imgAry[i][j] = p1NeighborAry[0] + 1;
			}//if 1			
		}//forj
	}//fori loop through each entrie that is >0
	return imgAry;
}//pass one

int** pass2(int** imgAry) {
	for (int i = numRow + 1; i > 0; i--) {
		for (int j = numCol + 1; j > 0; j--) {
			if (imgAry[i][j] > 0) {
				p2NeighborAry[0] = imgAry[i][j + 1];
				p2NeighborAry[1] = imgAry[i + 1][j - 1];
				p2NeighborAry[2] = imgAry[i + 1][j];
				p2NeighborAry[3] = imgAry[i + 1][j + 1];
				sort(p2NeighborAry, p2NeighborAry + 4);
				if (p2NeighborAry[0] + 1 < imgAry[i][j]) {
					imgAry[i][j] = p2NeighborAry[0] + 1;
					if (imgAry[i][j] > maxValAfterProcess) {
						maxValAfterProcess = imgAry[i][j];
					}//set new max
				}//if case
			}//if 1
		}//forj
	}//i
	return imgAry;
}//pass 2

int** computeSkeleton(int** imgAry) {
	//create 
	int** skeletonImgAry = new int*[numRow + 2];
	for (int i = 0; i < numRow + 2; i++) {
		skeletonImgAry[i] = new int[numCol + 2]();
	}//fori to create 0 array
	for (int i = 1; i < numRow + 1; i++) {
		for (int j = 0; j < numCol + 1; j++) {
			if (imgAry[i][j] == 0) {
				skeletonImgAry[i][j] = 0;
			}//if 0, print 0
			else {
				//assign neighbors to array
				skeletonNeighborAry[0] = imgAry[i - 1][j - 1];
				skeletonNeighborAry[1] = imgAry[i - 1][j];
				skeletonNeighborAry[2] = imgAry[i - 1][j + 1];
				skeletonNeighborAry[3] = imgAry[i][j - 1];
				skeletonNeighborAry[4] = imgAry[i][j + 1];
				skeletonNeighborAry[5] = imgAry[i + 1][j - 1];
				skeletonNeighborAry[6] = imgAry[i + 1][j];
				skeletonNeighborAry[7] = imgAry[i + 1][j + 1];
				sort(skeletonNeighborAry, skeletonNeighborAry + 8);
				if (imgAry[i][j] >= skeletonNeighborAry[7]) {
					skeletonImgAry[i][j] = imgAry[i][j];
				}//if > all neighbor
				else {
					skeletonImgAry[i][j] = 0;
				}//else 0
			}//if not 0
		}//forj
	}//fori
	return skeletonImgAry;
}//compute Skeleton

void prettyPrintDistance(int** imgAry, string outFileName, string passNumber) {
	ofstream outfile(outFileName + passNumber + ".txt");
	for (int i = 1; i < numRow + 1; i++) {
		for (int j = 1; j < numCol + 1; j++) {
			if (imgAry[i][j] > 0) {
				if (imgAry[i][j] <= 9) {
					outfile << " " << imgAry[i][j] << " ";
				}//one digit
				else {
					outfile << imgAry[i][j] << " ";
				}//two digit
			}//if >0
			else {
				outfile << "   ";
			}
		}//forj
		outfile << endl;
	}//fori
}//ppDistance

void createImageWithHeader(int** imgAry, string outFileName) {
	ofstream outfile(outFileName + ".txt");
	outfile << numRow << " " << numCol << " " << minVal << " " << maxValAfterProcess << endl;
	for (int i = 1; i < numRow + 1; i++) {
		for (int j = 1; j < numCol + 1; j++) {
			if (imgAry[i][j] >= 10) {
				outfile << imgAry[i][j] << " ";
			}//if 2 digit
			else {
				outfile << " " << imgAry[i][j] << " ";
			}//else one digit
		}//forj
		outfile << endl;
	}//fori
}//createImageWithHeader

int main(int argc, char** argv) {
	int** imageArray = createZeroAry(argv[1]);
	pass1(imageArray);
	prettyPrintDistance(imageArray, argv[4], "PP_pass1");
	pass2(imageArray);
	prettyPrintDistance(imageArray, argv[4], "PP_pass2");
	createImageWithHeader(imageArray, argv[2]);
	int** skeletonFinalImg = computeSkeleton(imageArray);
	prettyPrintDistance(skeletonFinalImg, argv[4], "PP_skeleton");
	createImageWithHeader(skeletonFinalImg, argv[3]);
	cin >> lit;
}//main