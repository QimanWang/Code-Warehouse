#include <fstream>
#include <iostream>
using namespace std;

class Image {
	friend class PointExtraction;
private:
	int numRows, numCols, minVal, maxVal;
	int** imgAry;
	int numberOfPoints;
public:
	Image(ifstream&);
	~Image();
	void loadImage(ifstream&, int);
};

Image::Image(ifstream& inFile) {
	inFile >> numRows >> numCols >> minVal >> maxVal;
	imgAry = new int*[numRows];
	for (int i = 0; i<numRows; i++) {
		imgAry[i] = new int[numCols];
		for (int j = 0; j<numCols; j++) imgAry[i][j] = 0;
	}
	loadImage(inFile, 0);
}//constructor

Image::~Image() {
	if (imgAry != nullptr) delete[] imgAry;
}//garbage collector 

void Image::loadImage(ifstream& inFile, int frameBorder) {
	for (int row = frameBorder; row<numRows + frameBorder; row++) {
		for (int col = frameBorder; col < numCols + frameBorder; col++) {
			inFile >> imgAry[row][col];
			if (imgAry[row][col] >0) {
				numberOfPoints++;
			}//count points
		}//j
	}//i
}//load image constructor

class PointExtraction {
private:
	Image* image;
public:
	PointExtraction(ifstream&);
	void runAlgoritm(ofstream&);
};

PointExtraction::PointExtraction(ifstream& inFile) {
	image = new Image(inFile);
}//constructor

void PointExtraction::runAlgoritm(ofstream& outFile) {
	outFile << image->numberOfPoints << '\n';
	outFile << image->numRows << " " << image->numCols << '\n';
	for (int i = 0; i < image->numRows; i++) {
		for (int j = 0; j < image->numCols; j++) {
			if (image->imgAry[i][j] > 0) {
				outFile << i + 1 << " " << j + 1 << '\n';
			}//check if >0
		}//forj
	}//fori
}//run algorithm

int main(int argc, char** argv) {
	ifstream inFile; inFile.open(argv[1]);
	ofstream outFile; outFile.open(argv[2]);

	PointExtraction PE(inFile);
	PE.runAlgoritm(outFile);

	inFile.close();
	outFile.close();

	return 0;
}//main

