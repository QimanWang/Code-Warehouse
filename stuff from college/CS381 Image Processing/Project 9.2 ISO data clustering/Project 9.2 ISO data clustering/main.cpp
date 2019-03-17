#include <fstream>
#include <iostream>
using namespace std;

class IsoDataClustering {
private:
	int numpts, k, numRow, numCol;
	Point* pointSet;
	xyCoord* centroids;
	double* XX2ndMM, YY2ndMM, XY2ndMM;
	int** imageArray;
public:
	IsoDataClustering(ifstream&, int);
	void loadPointSet(ifstream&, Point*);
	void assignLable(Point*);
	void mapPoint2Image(Point*, int**);
	void IsoClustering();
	//void printPointSet();
	void prettyPrint(ofstream&, int**);
	void runIDCAlgorithm(ofstream&);
};//iso class

IsoDataClustering::IsoDataClustering(ifstream& inFile, int kluster) {
	k = kluster;
	inFile >> numpts >> numRow >> numCol;
	//dynamically allocate imgAry & init to zeroes
	imageArray = new int*[numRow];
	for (int i = 0; i<numRow; i++) {
		imageArray[i] = new int[numCol];
		for (int j = 0; j<numCol; j++) imageArray[i][j] = 0;
	}//initialize empty array with 0's
	pointSet = new Point*[numpts];
	centroids = new xyCoord[k];
	XX2ndMM = new double[k];
	YY2ndMM = new double[k];
	XY2ndMM = new double[k];

}//constructor

IsoDataClustering::loadPointSet(ifstream&, Point* pointSet) {

}


class Point {
private:
	int x, y, clusterID;
};//point class 

class xyCoord {
private:
	int x, y;
};//xy coordiate class

int main(int argc, char** argv) {
	ifstream inFile; inFile.open(argv[1]);
	ofstream outFile; outFile.open(argv[2]);

	int k;
	cout << "Enter the cluster number\n";
	cin >> k;
	cout << "You've entered: " << k << '\n';


	inFile.close();
	outFile.close();

	return 0;
}//main

