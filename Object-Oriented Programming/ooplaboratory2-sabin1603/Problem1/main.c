#define _CRTDBG_MAP_ALLOC
#include <stdio.h>
#include <limits.h>
#include <crtdbg.h>
#include "tests.h"

// Write a function that takes as an input an array of integer numbers (both positive and negative numbers and returns the value of the triplet with the maximum product, as well as the elements that form this triplet (in increasing order). 
// Use pass by pointer/address to return the elements that form that triplet. 
// Think about the appropriate data type for the result. If the size of the array is less than 3, you should return the minimum
// representable value of the data type and the elements that form the triplet should be set to 0.
long long computeMaxTriplet(int arr[], unsigned int sz, int* t1, int* t2, int* t3) {
    // TODO your code here

   
    if (sz < 3) {
        *t1 = 0;
        *t2 = 0;
        *t3 = 0;
        _CrtDumpMemoryLeaks();
        return LLONG_MIN;
    }

    long long max1 = -9999999, max2 = -9999999, max3 = -9999999;
    long long min1 = 9999999, min2 = 9999999;

    for (int i = 0; i < sz; i++) {
        if (arr[i] > max1) {
            max3 = max2;
            max2 = max1;
            max1 = arr[i];
        }
        else if (arr[i] > max2) {
            max3 = max2;
            max2 = arr[i];
        }
        else if (arr[i] > max3) {
            max3 = arr[i];
        }

        if (arr[i] < min1) {
            min2 = min1;
            min1 = arr[i];
        }
        else if (arr[i] < min2) {
            min2 = arr[i];
        }
    }
    if (min1 == 9999999) {
        min1 = 0;
    }
    if (min2 == 9999999) {
        min2 = 0;
    }

    long long prod1 = max1 * max2 * max3;
    long long prod2 = max1 * min1 * min2;

    if (prod1 >= prod2) {
        *t1 = max3;
        *t2 = max2;
        *t3 = max1;
        _CrtDumpMemoryLeaks();
        return prod1;
    }
    else {
        *t1 = min1;
        *t2 = min2;
        *t3 = max1;
        _CrtDumpMemoryLeaks();
        return prod2;
    }
    _CrtDumpMemoryLeaks();
    return 0;
}

int main()
{
#if ENABLE_TESTS > 0
    run_tests(true);
#endif

    return 0;
}