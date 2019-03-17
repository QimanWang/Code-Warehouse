#include <iostream>
#include <math.h>

//2.1 Fibonacci Number
int Fibonacci_calc(int n, int F1, int F2) {
    int i;
    int F[n+1] = {};

    F[1] = F1;
    F[2] = F2;
    for (i = 3; i <=n; i++) {
        F[i] = F[i - 1] + F[i - 2];
    }
    for(int j=0; j <=n; j++){
        std::cout<<"F"<<j<<" :"<< F[j]<<'\n';
    }

    int result = F[n];

    //(relase allocated memory)
    return result;


}
//
//
//int main() {
//
//    int n = 8;
//    int F1 = 1;
//    int F2 = 1;
//
//    int ans = Fibonacci_calc(n, F1, F2);
//    std::cout << ans<<'\n';
//
//    std::cout<<"n =" <<n<<" F1= "<<F1 << " F2 "<< F2;
//}