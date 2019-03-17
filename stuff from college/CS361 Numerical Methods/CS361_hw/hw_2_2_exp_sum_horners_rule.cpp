//
// Created by qiman on 9/6/2017.
//

#include <iostream>
#include <math.h>
//2.2 Horner's rule

double exp_sum(double x)
{
    double total =1;
    for(int i=30; i >0; --i){
        total = total*(x/i) +1;
    }
    return total;
}

//int main(){
//
//    double input =.8;
//    std::cout<<exp(input)<<'\n';
//    std::cout<<exp_sum(input)<<'\n';
//    std::cout << exp_sum(input) - exp(input)<<'\n';
//}