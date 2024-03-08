#define _CRTDBG_MAP_ALLOC
#include <stdio.h>
#include <limits.h>
#include <crtdbg.h>
#include "tests.h"

// Using the function that you wrote for problem 2, write a function that computes and returns an array with all the positions of the occurrences of a character in a string.
void find_all(const char* str, char character, int* positions, unsigned int cap, unsigned int* l) {
    // TODO your code here
    for (int i = 0; i < cap; i++) {
        positions[i] = -1;
    }
    int count = 0;
    const char* p = str;
    while (*p != '\0' && count < cap) {
        if (*p == character) {
            positions[count] = p - str;
            count++;
        }
        p++;
    }

    *l = count;
    _CrtDumpMemoryLeaks();
}

int main()
{
#if  ENABLE_TESTS > 0
    run_tests(true);
#endif 

    return 0;
}