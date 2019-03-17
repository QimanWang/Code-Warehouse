#include <iostream>
#include <math.h>
using namespace std;

//1.2 discount factor
//inputs: today’s cashflow F0, future cashflow F1, today’s time t0 , future time t1.
//output : discount factor df, continuously compounded interest rate r
int df_and_r(double F0, double F1, double t0, double t1, double & df, double & r)
{
    if (t1-t0 == 0.0) {
        df = 0;
        r = 0;
        return -1;
    }
    if ((F0 < 0.0) || (F1 < 0.0)) {
        df = 0;
        r = 0;
        return -2;
    }

// *** you have to write the rest ***
    df = F0/F1;
    r =  -(log(df)/(t1-t0));


    return 0;
}


//int main(){
//    double F0 = 1000;
//    double F1 = 2718.28;
//    double t0 = 5.0;
//    double t1 = 10.0;
//    double df = 0.0;
//    double r = 0.0;
//
//    cout<<"Error code: " <<df_and_r(F0,F1,t0,t1,df,r);
//    cout<<"\ndiscount factor: " <<df << "\ninterest rate: "<< r*100 <<"%" ;
//}
//
////Error code: 0
////discount factor: 0.36788
////interest rate: 20%