#include <iostream>
#include <math.h>
using namespace std;

//1.1 future value
//) today’s cashflow F0, today’s time t0,  future time t1,
// continuously compounded interest rate r in %
double future_value(double F0, double t0, double t1, double r)
{
    double r_decimal = 0.01*r;
    double F1 = F0*exp(r_decimal*(t1-t0));
    return F1;
}

//
//int main(){
//    cout<<future_value(1000.0,5.0,10.0,20.0);
//
//}