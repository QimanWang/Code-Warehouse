#include <iostream>
#include<vector>
#include<cstdlib>
#include <opencv/cv.hpp>
#include <opencv/highgui.h>
using namespace std;
using namespace cv;

Point2i findWindowSize(vector<Point2f> points) // function to determine window size
{
	Point2i maxPoint = Point2i(INT_MIN, INT_MIN); // maximum screen size allowed

	for (int i = 0; i < points.size(); ++i) 
	{
		int x = points.at(i).x;
		int y = points.at(i).y;
		cout << i<<":" << x << endl;
		cout << i <<":"<< y << endl;
		maxPoint.x = x > maxPoint.x ? x : maxPoint.x; //if greater change it, otherwise leave it
		maxPoint.y = y > maxPoint.y ? y : maxPoint.y;
	}
	cout << maxPoint;
	return maxPoint;
}


void drawFractal(float xDist, float yDist, vector<Point2f> points, int iterations = 100000)
{
	Point2f lastPosition = Point2f(0, 0); //floatpoint contain 2 dimension
	const int stopPoint = points.size();
	Point2i topRightVertex = findWindowSize(points); // determines the upperright edge pixel coordinate


	Mat fractalImage = Mat(topRightVertex.x, topRightVertex.y, CV_8UC1, Scalar::all(0));  //8uc1 = black white 8 bit, set all = 0


	for (int i = 0; i < iterations; ++i)//repeat the iterations to generate all the points
	{
		int rnd = rand() % stopPoint;
		Point2f pointTo = points.at(rnd);
		Point2f diff = pointTo - lastPosition;

		lastPosition.x += xDist*diff.x;
		lastPosition.y += xDist*diff.y;

		fractalImage.at<uchar>(lastPosition.x, lastPosition.y) = 255; //white
	}
	imshow("Sierpinski Triangle", fractalImage);
	waitKey(); // close with a key press
}
int main()
{
	vector<Point2f> points;
	points.push_back(Point2f(0, 0));
	
	points.push_back(Point2f(1000, 0));
	points.push_back(Point2f(500, 1000));
	drawFractal(0.5, 0.5, points, 1000000); //main call function 
	return 0;
}




