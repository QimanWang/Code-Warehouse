#include <iostream>
#include <cmath>
#include <cstdio>

double pert(double p, double r, double t){
    return p*exp(r*t);

}

int main(){

    std::cout<<pert(100,(.05),1);
}
