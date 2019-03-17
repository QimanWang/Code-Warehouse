#include <iostream>
#include<fstream>
#include <cmath> 
using namespace std;
static int nRow, nCol;

class Image {
public:
	int iRow, jCol, label;
	int  numRow, numCol, minVal, maxVal;
	int** zeroFramedAry;
	int** createZeroFramedAry(int** arr, int numRow, int numCol, int frameSize) {
		arr = new int*[numRow + frameSize];
		for (int i = 0; i < numRow + frameSize; i++) {
			arr[i] = new int[numCol + frameSize]();
		}//fori to create 0 ary
		return arr;
	}//crreate zeroFramedAry
	void prettyPrint(int** imgAry ) {
		for (int i = 0; i <numRow+2; i++ ) {
			for (int j = 0; j < numCol+2; j++) {
				if (imgAry[i][j]==0) {
					cout << 0 <<" ";
				}
				else {
					cout << 1<<" ";
				}
			}//fori
			cout << endl;
		}//forj
	}//pretty print
	//~~~~~~~~~
	void threshPrint(int** imgAry) {
		for (int i = 0; i <numRow + 2; i++) {
			for (int j = 0; j < numCol + 2; j++) {
				if (imgAry[i][j]  <29 ) {
					cout << 0 << " ";
				}
				else {
					cout << 1 << " ";
				}
			}//fori
			cout << endl;
		}//forj
	}//pretty print
	//~~~~~~~~~~
	Image(string inFile) {
		ifstream inputFile;
		inputFile.open(inFile);
		int crit;
		inputFile >> crit;  numRow = crit;
		inputFile >> crit;  numCol = crit;
		inputFile >> crit;  minVal = crit;
		inputFile >> crit;  maxVal = crit;
		zeroFramedAry = createZeroFramedAry(zeroFramedAry, numRow, numCol, 2);
		bool findFirst = true;
		for (int i = 1; i < numRow + 1; i++) {
			for (int j = 1; j < numCol + 1; j++) {
				inputFile >> crit;
				zeroFramedAry[i][j] = crit;
				if (findFirst) {
					if (zeroFramedAry[i][j] > 0) {
						iRow = i; jCol = j;
						label = zeroFramedAry[i][j];
						findFirst = false;
					}
				}
			}
		}
		nRow = numRow;
		nCol = numCol;
	}//constructor//constructor for image
}; // image class

class Edge {

public:

	int maskHorizontal[3][3] = {
		{1,2,1},{0,0,0},{-1,-2,-1}
	};
	int maskVertical[3][3] = {
			{ 1,0,-1 },{ 2,0,-2 },{1,0,-1}
		};
		int maskRightDiag[3][3] = {
			{0,1,2 },{ -1,0,1 },{-2,-1,0}
		};
		int maskLeftDiag[3][3] = {
			{ 2,1,0 },{ 1,0,-1 },{0,-1,-2}
		};
		int** SobelVertical;
		int** SobelHorizontal;
		int** SobelRightDiag;
		int** SobelLeftDiag;
		int** gradientEdge;
		int** createEmptyAry(int** arr, int numRow, int numCol, int frameSize) {
			arr = new int*[numRow + frameSize];
			for (int i = 0; i < numRow + frameSize; i++) {
				arr[i] = new int[numCol + frameSize]();
			}//fori to create 0 ary
			return arr;
		}//crreate zeroFramedAry
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		int convolute(int i, int j,int mask[][3] ,Image img) {
			int val=0;
			val += (mask[0][0] * img.zeroFramedAry[i - 1][j - 1]);
			val += (mask[0][1] * img.zeroFramedAry[i - 1][j ]);
			val += (mask[0][2] * img.zeroFramedAry[i - 1][j + 1]);
			val += (mask[1][0] * img.zeroFramedAry[i ][j - 1]);
			val += (mask[1][1] * img.zeroFramedAry[i ][j ]);
			val += (mask[1][2] * img.zeroFramedAry[i ][j + 1]);
			val += (mask[2][0] * img.zeroFramedAry[i + 1][j - 1]);
			val += (mask[2][1] * img.zeroFramedAry[i + 1][j ]);
			val += (mask[2][2] * img.zeroFramedAry[i + 1][j + 1]);
			return val;
			
		}//convolute
		int computeGradient(int i, int j,int** imgAry) {
			return(abs(imgAry[i][j] - imgAry[i + 1][j]) + abs(imgAry[i][j] - imgAry[i][j + 1]));
		}//compute gradient

		Edge(int row, int col) {
			SobelHorizontal = createEmptyAry(SobelHorizontal, row, col, 2);
			SobelVertical = createEmptyAry(SobelVertical, row, col, 2);
			SobelLeftDiag = createEmptyAry(SobelLeftDiag, row, col, 2);
			SobelRightDiag = createEmptyAry(SobelRightDiag, row, col, 2);
			gradientEdge = createEmptyAry(gradientEdge, row, col, 2);
		}//constructor
	
};//edge class

int main(int argc, char** argv) {
	int c;

	Image inputImage(argv[1]);
	Edge myEdge(nRow,nCol);
	cout << "image had been loaded" << endl;
	inputImage.prettyPrint(inputImage.zeroFramedAry);
	
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			cout << myEdge.maskHorizontal[i][j] << " ";
		}
		cout << endl;
	}
	

	for (int i = 1; i < inputImage.numRow + 1; i++) {
		for (int j = 0; j < inputImage.numCol + 1; j++) {
			
			myEdge.SobelHorizontal[i][j] =  abs(myEdge.convolute(i, j, myEdge.maskHorizontal, inputImage)); 
			myEdge.SobelVertical[i][j] = abs(myEdge.convolute(i, j, myEdge.maskVertical, inputImage));
			//myEdge.SobelLeftDiag[i][j] = abs(myEdge.convolute(i, j, myEdge.maskLeftDiag, inputImage));
			//myEdge.maskRightDiag[i][j] = abs(myEdge.convolute(i, j, myEdge.maskRightDiag, inputImage));
			//myEdge.gradientEdge[i][j] = myEdge.computeGradient(i,j,inputImage.zeroFramedAry);
		}//forj
	}//fori
	inputImage.threshPrint(inputImage.zeroFramedAry);
	cout<<"horizontal detetction"<<endl;
	inputImage.prettyPrint(myEdge.SobelHorizontal);
	cout << "vetical detetction" << endl;
	inputImage.prettyPrint(myEdge.SobelVertical);
	inputImage.prettyPrint(myEdge.SobelLeftDiag);
	inputImage.prettyPrint(myEdge.SobelRightDiag);
	inputImage.prettyPrint(myEdge.gradientEdge);

	cin >> c;
}