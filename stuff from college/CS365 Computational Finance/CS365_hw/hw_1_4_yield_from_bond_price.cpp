#include <iostream>
#include <cmath>

using namespace std;

//1.4 Yield from bond price
static void price_from_yield(double F, double c, double y, int n, double & B){
    double y_decimal = y *0.01;
    double numerator = .5*c;
    double denominator = (1+.5*y_decimal);

    for(int i=1; i <=n-1; i ++){
        B+= numerator/pow(denominator,i);
    }
    B+= (F+numerator)/pow(denominator,n);

}//1.3

static int yield_from_price(double F, double c, int n, double B_market,
                            double tol, int max_iter, double & y){
    int error_code = 1;
    double y_low = 0.0;
    double y_high =100.0;

    double B_y_low = 0.0;
    //y_low
    price_from_yield(F,c,y_low,n,B_y_low);
    if (abs(B_y_low-B_market) <= tol){
        y = y_low;
        error_code = 0;
        return 0;
    }
    if (B_y_low < B_market){
        y=0;
        error_code = 1;
        return 1;
    }

    //y_high
    double B_y_high = 0.0;
    price_from_yield(F,c,y_high,n,B_y_high);
    if(abs(B_y_high - B_market) <= tol){
        y = y_high;
        error_code =0;
        return 0;
    }
    if(B_y_high>B_market){
        y=0;
        error_code=1;
        return 1;
    }

    //bisection loop
    for(int i=0; i < max_iter; ++i){
        y=(y_low+y_high)/2.0;
        double B = 0.0;
        price_from_yield(F,c,y,n,B); //12

        if (abs(B-B_market) <= tol){
            error_code = 0;
            return 0;
        }
        else if(B > B_market){
            y_low =y;
        }
        else{
            y_high=y;
        }

        if(y_high-y_low <= tol){
            error_code =0;
            return 0;
        }
    }

    //after loop
    if (error_code ==1){
        y = 0;
        return 1;
    }
}// bisection
//
//int main(){
//    double F = 100;
//    double B_market = 100;
//    double c = 40;
//    double y=0;
//    double tol = 0.001;
//    int n = 10;
//    int max_iter = 100;
//    int error_code = yield_from_price(F,c,n,B_market,tol,max_iter,y);
//    cout<<"error code: " << error_code <<'\n';
//    cout<<"y: "<< y <<" expected = "<<c<<'\n';
//
//
//    B_market =90;
//    c = 20;
//    y=0;
//    error_code = yield_from_price(F,c,n,B_market,tol,max_iter,y);
//    cout<<"error code: " << error_code <<'\n';
//    cout<<"y: "<< y <<" expected greater than "<<c<<'\n';
//
//    B_market =110;
//    c = 20;
//    y=0;
//    error_code = yield_from_price(F,c,n,B_market,tol,max_iter,y);
//    cout<<"error code: " << error_code <<'\n';
//    cout<<"y: "<< y <<" expected less than than "<<c<<'\n';
//
//    B_market =110;
//    c = 0;
//    y=0;
//    error_code = yield_from_price(F,c,n,B_market,tol,max_iter,y);
//    cout<<"error code: " << error_code <<'\n';
//    cout<<"y: "<< y <<" expected less than than "<<c<<'\n';
//
//    cout<<"hi";
//}

//error code: 0
//y: 40.0002 expected = 40
//error code: 0
//y: 23.5039 expected greater than 20
//error code: 0
//y: 16.9548 expected less than than 20
//error code: 1
//y: 0 expected less than than 0
