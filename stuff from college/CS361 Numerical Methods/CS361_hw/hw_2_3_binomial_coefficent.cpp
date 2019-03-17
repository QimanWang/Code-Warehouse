//#include <iostream>
//#include <math.h>
////2.3 Binomial coefficient
//
//int binom(int n, int k)
//{
//    if ((n < 0) || (k < 0) || (k > n)) return 0;
//    if ((k == 0) || (k == n)) return 1;
//    int i = 0;
//    int result = 1;
//
//    if (k > n-k)
//        k = n-k;
//    //since k is smaller, the loop is smaller.
//    for (i = 1; i <= k; ++i) {
//        result /= i;
//        result *= (n+1-i);
//    }
//    return result;
//}
//
//int factorial(int n)
//{
//    return (n == 1 || n == 0) ? 1 : factorial(n - 1) * n;
//}
//
////int main(){
////
////    int n = 10;
////    int k = 2;
////
////    std::cout<<binom(n,k)<<'\n';
////    std::cout<<(factorial(n)/(factorial(k)*factorial(n-k)));
////}