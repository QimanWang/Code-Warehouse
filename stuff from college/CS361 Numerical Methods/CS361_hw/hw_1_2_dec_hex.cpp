#include<iostream>
//#include <vector>
#include<algorithm>

//1.2.1 decimal to hex
long dec_hex(long a, std::vector<char> &digits) {
    const long base = 16;
    char alphanum[16] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    digits.clear();
    if (a < 0) return -1;
    if (a == 0) {
        digits.push_back(alphanum[0]);
        return 0;
    }
    while (a > 0) {
        long rem = a % base;
        char c = alphanum[rem];
        digits.push_back(c);
        a /= base;
    }
    std::reverse(digits.begin(), digits.end()); // reverse the digits
    return 0;
}//dec_hex

//1.2.2
long dec_base(long a, long b, std::vector<char> &digits) {
    if (b < 2 || b > 20) {
        digits.clear();
        return -2;
    }

    const long base = b;
    char alphanum[20] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                         'J'};
    digits.clear();
    if (a < 0) return -1;
    if (a == 0) {
        digits.push_back(alphanum[0]);
        return 0;
    }
    while (a > 0) {
        long rem = a % base;
        char c = alphanum[rem];
        digits.push_back(c);
        a /= base;
    }
    std::reverse(digits.begin(), digits.end()); // reverse the digits
    return 0;

}//dec_base
//
//int main() {
//    long a = 106;
//    std::vector<char> digits;
//    int errorCode = 0;
//    errorCode = dec_base(a,10, digits);
//    std::cout << "error code: " << errorCode << "\n";
//
//    for (long i = 0; i < (long) digits.size(); ++i) {
//        std::cout << digits[i];
//    }
//
//
//}//main