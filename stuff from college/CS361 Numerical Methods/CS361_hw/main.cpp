#include <iostream>
#include <math.h>
#include <cstdio>

//5.5 call option
double cum_norm(double x) {
    const double root = sqrt(0.5);
    double y = 0.5 * (1.0 + erf(x * root));
    return y;
}

//Call option valuation function:
int call_option(double S, double sigma, double &call, double &delta) {
    call = 0.0;
    delta = 0.0;
    if ((S < 0.0) || (sigma < 0.0)) return 1; // fail
    if (S == 0.0) {
        call = 0.0;
        delta = 0.0;
        return 0; // ok
    }
    if (sigma == 0.0) {
        if (S > 1.0) {
            call = S - 1.0;
            delta = 1.0;
        } else {
            call = 0.0;
            delta = 0.0;
        }
        return 0; // ok
    }
    double d1 = log(S) / sigma + 0.5 * sigma;
    double d2 = d1 - sigma;
    double Nd1 = cum_norm(d1);
    double Nd2 = cum_norm(d2);
    call = S * Nd1 - Nd2;
    delta = Nd1;
    return 0; // ok
}

double F(double x, double sigma,  double h) {

    double call_positive = 0.0;
    double delta_positive = 0.0;
    double call_negative = 0.0;
    double delta_negative = 0.0;
    call_option(exp(x + h), sigma, call_positive, delta_positive);
    call_option(exp(x - h), sigma, call_negative, delta_negative);
    return (call_positive - call_negative) / (2 * h);
}

double G(double S, double sigma,  double k) {
    double call_positive = 0.0;
    double delta_positive = 0.0;
    double call_negative = 0.0;
    double delta_negative = 0.0;
    call_option(S + k, sigma, call_positive, delta_positive);
    call_option(S - k, sigma, call_negative, delta_negative);
    return (call_positive - call_negative) / (2 * k);
}


int main() {
    double S = 0.9;
    double sigma = 0.5;
    double call = 0.0;
    double delta = 0.0;
    double max_error = pow(10, -8);
    int error_code = call_option(S, sigma, call, delta);
    std::cout << error_code << '\n';
    std::cout << S << ", " << sigma << ", " << call << ", " << delta << '\n';

    std::cout << "h" << ", " << "  A" << ", " << "  B" << ", " << "   A - delta" << ", " << "   B - delta" << '\n';
    for (double h = .1; h >= max_error; h *= .1) {


        double A = (1 / S) * F(log(.9), 0.5, h);
        double B = G(0.9, 0.5, h);

        std::cout << h << ", " << A << ", " << B << ", " << A - delta << ", " << B - delta << '\n';
    }


    printf("hello world\n");
    printf("hello world");

}
