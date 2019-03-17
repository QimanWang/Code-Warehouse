//#include <iostream>
//#include <cmath>
//
//using namespace std;
////2.4 Bisection
//
////the error function is a build in c++ function called erf()
//static double cum_norm(double x) {
//    const double root = sqrt(0.5);
//    return 0.5 * (1.0 + erf(x * root));
//}
//
//static int cum_norm_bisection(double y_target, double tol, int max_iter, double &x, int &num_iter) {
//    num_iter = 0;
//    int error_code = 1;
//    double x_low = -10.0;
//    double x_high = 10.0;
//    double y_low = cum_norm(x_low);
//
//    if (abs(y_low - y_target) <= tol) {
//        x = x_low;
//        error_code = 0;
//        return error_code;
//    }//success, no need to test
//
//    if (y_low > y_target) {
//        x = 0;
//        error_code = 1;
//        return error_code;
//    }//fail validation test
//
//    double y_high = cum_norm(x_high);
//    if (abs(y_high - y_target) <= tol) {
//        x = x_high;
//        error_code = 0;
//        return error_code;
//    }//success, no need to do bisection loop
//
//    if (y_high < y_target) {
//        x = 0;
//        error_code = 1;
//        return error_code;
//    }//fail validation test.
//
//    //we made it
//    int i = 0;
//    double y = 0.0;
//    for (i = 1; i <= max_iter; ++i) {
//        x = (x_low + x_high) / 2;
//        y = cum_norm(x);
//
//        if (abs(y - y_target) <= tol) {
//            num_iter = i;
//            error_code = 0;
//            break;
//        }// success, found
//        else if (y < y_target) {
//            x_low = x;
//        } else {
//            x_high = x;
//        }
//
//        if (x_high - x_low <= tol) {
//            num_iter = i;
//            error_code = 0;
//            break;
//        }//17
//
//    }//forloop for bisection
//
//    return error_code;
//}//bisection
//
//
////int main() {
////    double x = 0.0;
////    int num_iter = 0;
////
////    int error_code = 0;
////
////    for(double y_target =.5; y_target < 1.0; y_target += .05 ) {
////
////        for (double gap = .01; gap > .00000000001; gap *= .10) {
////            cout << gap << '\n';
////
////            error_code = cum_norm_bisection(y_target, gap, 100, x, num_iter);
////            std::cout << "error code: " << error_code << '\n';
////
////            cout << "x: " << x << '\n';
////            cout<<"y_target: "<<y_target<<'\n';
////            cout << "num_iter: " << num_iter << '\n';
////
////
////        }//iterate all tol, from [0.1 to 1e-011
////
////    }//for y_target, from [.50 to 1.0]
////}
//
////y=.6
////x: 0.253347
////num_iter 55
//
////y=.4
////error code: 0
////x: -0.253347
////num_iter 55
//
//
