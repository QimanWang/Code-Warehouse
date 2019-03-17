#include<iostream>
using namespace std;

//1.1
//GCD method
long gcd_euclid(long a, long b) {
    //negative
    if ((a < 0 || b < 0) || (a == 0 && b == 0))
        return 0;
    if (a == 0 && b > 0)
        return b;
    if (b == 0 && a > 0)
        return a;

    if (a < b) {
        return gcd_euclid(b, a);
    }

    long c = (a % b);
    if (c == 0)
        return b;
    else if (c == 1)
        return 1;

    return gcd_euclid(b, c);
}//gcd

long LCM(long a, long b){
    if (a<= 0 || b <=0)
        return 0;
    return (a*b)/(gcd_euclid(a,b));
}//LCM
//
//
//int main() {
////    cout<<"hello World\n";
//    cout << gcd_euclid(-1,-1);
//    cout << gcd_euclid(0, 0);
//    cout << gcd_euclid(1, 0);
//    cout << gcd_euclid(0, 1);
//    cout << gcd_euclid(1, 1);
//    cout << gcd_euclid(4, 12);
//    cout << "\n\n\n";
//    cout << LCM(2, 11);
//    cout << "\n\n\n";
//
//
//
//}