#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#include "tests.h"

// Substitution cipher or Caesar’s cipher.

// This program which reads a natural number n and a string s. The string s is encoded using Caesar’s cipher with a displacement of n (either positive or negative).
// The program decodes the message and display it on the screen. Punctuation marks and numbers are left as they are.


// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
void encrypt(char s[], int n) {
    // TODO your code here
    // add your encryption code here. the string s will be modified in place, using a displacement of n

    char ch;
    n = -n;
    //visiting each character 
    for (int i = 0; s[i] != '\0'; i++) {
        ch = s[i];
        // check for valid characters
        if (isalnum(ch)) {
            // lower case characters
            if (islower(ch)) {
                ch = (ch - 'a' - n + 26) % 26 + 'a';
            }
            // uppercase characters
            if (isupper(ch)) {
                ch = (ch - 'A' - n + 26) % 26 + 'A';
            }
            if (isdigit(ch)) {
                ch = ch;
            }
        }
        // asding decoded character back 
        s[i] = ch;
    }
}

int main()
{
#if ENABLE_TESTS > 0
    run_tests(true);
#endif
    char s[500];
    int n = 0;
    fgets(s, 500, stdin);
    printf("Ender the displacement: ");
    scanf("%d", &n);
    encrypt(s, n);
    printf("%s", s);
    // To enable the tests, ctrl+click on ENABLE_TESTS (or go to the file "tests.h") and change the line
    // #define ENABLE_TESTS 0
    // to:
    // #define ENABLE_TESTS 1


    // TODO your code here
    return 0;
}