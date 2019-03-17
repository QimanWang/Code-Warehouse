//#include <iostream>
//#include <math.h>
////5.2 Logarithmic difference
//
//double F(double x , double h){
//    return(pow(x+h,2) - pow(x-h,2))/(2*h);
//}
//
//double G(double y , double k){
//    return(pow(log(y+k),2) - pow(log(y-k),2))/(2*k);
//}
//
//int main() {
//
//    double x =0.5;
//    double y_05 = exp(.5);
//    double maxError = pow(10,-32);
//
//    double h = 2.0;
//    std::cout <<"h,k = "<< h<<":   "<< F(x,h)<<" | " <<G(y_05,h)<< '\n';
//    h = .1;
//    std::cout <<"h,k = "<< h<<":   "<< F(x,h)<<" | " <<G(y_05,h)<< '\n';
//
//    for(h = .01; h>=maxError; h*=.01 ){
//        std::cout <<"h,k = "<< h<<":   "<< F(x,h)<<" | " <<G(y_05,h)<< '\n';
//    }//for
//}