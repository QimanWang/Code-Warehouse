//#include <iostream>
//#include <math.h>
//#include <cstdio>
//
//// perform validation checks n > 0, etc.
//double f(double x) {
//    double num = (1 + pow(x, 2));
//    double den = sqrt(1 - (.5 * pow(x, 2)));
//    return num / den;
//}
//
//double f_gamma(double x, double gamma) {
//    double num = (1 + pow(x, 2));
//    double den = sqrt(1 - (pow(gamma,2) * pow(x, 2)));
//    return num / den;
//}
//
//double simpsons_gamma_rule(double a, double b, double n,double gamma){
//    int i;
//    double h = (b-a)/n;
//    double sum1 = f_gamma(a,gamma) + f_gamma(b,gamma);
//    double sum2 = 0.0;
//    for (i = 1; i < n; i += 2) {
//        double x_i = a +i*h;
//        sum2 += f_gamma(x_i,gamma);
//    }
//    double sum3 = 0.0;
//    for (i = 2; i < n; i += 2) {
//        double x_i = a +i*h;
//        sum3 += f_gamma(x_i,gamma);
//    }
//    double result = (sum1 +4.0*sum2 +2.0*sum3)*h/3.0;
//    return result;
//}
//
//
//double trapzoid_rule(double a, double b, double n) {
//
//    double h = (b - a) / n;
//    double sum = 0.5 * (f(a) + f(b));
//    for (int i = 1; i < n; ++i) {
//        double x_i = a + i * h;
//        sum += f(x_i);
//    }
//    double result = sum * h;
//    return result;
//}//midpoint rule
//
//double midpoint_rule(double a, double b, double n) {
//    double h = (b - a) / n;
//    double sum = 0;
//    for (int i = 0; i < n; ++i) {
//        double x_i =  a + (i+0.5)*h;
//        sum += f(x_i);
//    }
//    double result = sum * h;
//    return result;
//}
//double simpsons_rule(double a, double b, double n){
//    int i;
//    double h = (b-a)/n;
//    double sum1 = f(a) + f(b);
//    double sum2 = 0.0;
//    for (i = 1; i < n; i += 2) {
//        double x_i = a +i*h;
//        sum2 += f(x_i);
//    }
//    double sum3 = 0.0;
//    for (i = 2; i < n; i += 2) {
//        double x_i = a +i*h;
//        sum3 += f(x_i);
//    }
//    double result = (sum1 +4.0*sum2 +2.0*sum3)*h/3.0;
//    return result;
//}
//
//
//int main() {
//
//    printf("Trapzoid rule:\n");
//    for (int n = 2; n <= 22; n += 2) {
//        std::cout << n << " : " << trapzoid_rule(0, 1, n) << '\n';
//    }
//
//    printf("Midpoint rule:\n");
//    for (int n = 2; n <= 22; n += 2) {
//        std::cout << n << " : " << midpoint_rule(0, 1, n) << '\n';
//    }
//
//    printf("Simpson rule:\n");
//    for (int n = 2; n <= 22; n += 2) {
//        std::cout << n << " : " << simpsons_rule(0, 1, n) << '\n';
//    }
//
////    printf("Simpson rule with gamma factor:\n");
////    for(double g = -1.0; g <=1.0; g+=.1){
////        std::cout<<"("<<g<<" , "<<simpsons_gamma_rule(0,1,10,g)<<"),"<<'\n';
////    }
//
//    printf("Simpson rule with gamma factor:\n");
//    for(double g = -1.0; g <=1.0; g+=.1){
//        std::cout<<g<<" , "<<simpsons_gamma_rule(0,1,10,g)<<'\n';
//    }
//
//}