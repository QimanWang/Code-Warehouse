#include <iostream>
#include <math.h>
using namespace std;

//1.3 Bond price from yield
//yield = 5% -> y = 5
void price_from_yield(double F, double c, double y, int n, double & B){
    double y_decimal = y *0.01;
    double numerator = .5*c;
    double denominator = (1+.5*y_decimal);

    for(int i=1; i <=n-1; i ++){
        B+= numerator/pow(denominator,i);
    }
    B+= (F+numerator)/pow(denominator,n);

}

//
//int main() {
//    double F = 100;
//    double c = 0;
//    double y = 0;
//    int n = 10;
//    double B = 0.0;
//    price_from_yield(F, c, y, n, B);
//    cout << "B: " << B;
//    cout << " expected: 100\n";
//}
//    F = 100;
//    c = 5;
//    y = 0;
//    B = 0.0;
//    price_from_yield(F,c,y,n,B);
//    cout<<"B: " << B;
//    cout<<" expected: "<<F+(n*c/2);
//
//    F = 100;
//    c = 0;
//    y = 5;
//    B = 0.0;
//    price_from_yield(F,c,y,n,B);
//    cout<<"\nB: " << B;
//    cout<<" expected: "<<F/(pow(1+(.5*y*0.01),n));
//
//}

//B: 100 expected: 100
//B: 125 expected: 125
//B: 78.1198 expected: 78.1198