#include <iostream>
#include<fstream>
using namespace std;

class Point {
public:
	int row, col;
	Point() {
	}
	Point(int x,int y) {
		row =x;
		col = y;
	}
	void setRow(int x) {
		row = x;
	}
	void setCol(int y) {
		col = y;
	}
};//point class

class ChainCode {
public:
	Point neighborCoord[8];
	Point currentP;
	Point nextP;
	int lastQ;
	int nextDirTable[8] = { 6,0,0,0,2,4,4,4 };
	int nextDir;
	int Pchain;
	int nextQ;
	int chainDir;	
	
	ChainCode() {
	}

	void loadNeighborCoord(Point coordinate) {
		int x = coordinate.row;
		int y = coordinate.col;
		neighborCoord[0].setRow(x);
		neighborCoord[0].setCol(y + 1);
		neighborCoord[1].setRow(x-1);
		neighborCoord[1].setCol(y + 1);
		neighborCoord[2].setRow(x-1);
		neighborCoord[2].setCol(y );
		neighborCoord[3].setRow(x-1);
		neighborCoord[3].setCol(y-1);
		neighborCoord[4].setRow(x);
		neighborCoord[4].setCol(y - 1);
		neighborCoord[5].setRow(x + 1);
		neighborCoord[5].setCol(y - 1);
		neighborCoord[6].setRow(x + 1);
		neighborCoord[6].setCol(y);
		neighborCoord[7].setRow(x + 1);
		neighborCoord[7].setCol(y + 1);	
	}//loaded neighbors
	//find nextP
	
	int findNextP(Point currentP, int nextQ, Point nextPoint,int** zeroFramedAry,int label) {
		loadNeighborCoord(currentP);
		for (int i = 0; i < 8; i++) {
			if (zeroFramedAry[neighborCoord[(nextQ + i) % 8].row]
				[neighborCoord[(nextQ + i) % 8].col] ==label) {
				chainDir = (nextQ + i) % 8;
				nextP.row = neighborCoord[chainDir].row;
				nextP.col = neighborCoord[chainDir].col;
				break;
			}//if found non zero neighbor
		}//for loop 8 times to check all 8 neighbor for non zero
		return chainDir;
	}//find  next p
};//chain code class

class Image{
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
		}//constructor//constructor for image
}; // image class

int main(int argc, char** argv) {

	Image inputImage(argv[1]);
	ofstream outfile1(argv[2]);
	ofstream outfile2(argv[3]);
	// start chain code
	Point startP(inputImage.iRow, inputImage.jCol);
	ChainCode cc;
	cc.currentP.setRow(inputImage.iRow); cc.currentP.setCol(inputImage.jCol);
	cc.nextP.setRow(inputImage.iRow); cc.nextP.setCol(inputImage.jCol);
	cout << "start(" << cc.currentP.row << "," << cc.currentP.col<<'\n';
	cc.lastQ = 4;
	outfile1 << inputImage.numRow << " " << inputImage.numCol << " "
		<< inputImage.minVal << " " << inputImage.maxVal <<'\n';
	outfile2 << inputImage.numRow << " " << inputImage.numCol << " "
		<< inputImage.minVal << " " << inputImage.maxVal << '\n';
	outfile2 << "Start Row: " << inputImage.iRow << " Start Col: " << inputImage.jCol<<'\n';

	int token = 0;
	do {
		cc.nextQ = (cc.lastQ + 1) % 8;
		cc.Pchain = cc.findNextP(cc.currentP, cc.nextQ, cc.currentP,
			inputImage.zeroFramedAry, inputImage.label);
		cc.lastQ = cc.nextDirTable[cc.Pchain];
		cc.currentP.setRow(cc.nextP.row);
		cc.currentP.setCol(cc.nextP.col);
		token++;
		if (token % 20 == 0) {
			outfile2 << '\n';
		}
		outfile1 << cc.Pchain << ",";
		outfile2 << cc.Pchain << ",";
	} while (cc.currentP.row != startP.row || cc.currentP.col != startP.col);
}//main