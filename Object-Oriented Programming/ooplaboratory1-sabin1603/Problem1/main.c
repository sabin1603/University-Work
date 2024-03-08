#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>



int main(int argc, char* argv[]) {
	int year = 0;
	char name[100];
	printf("Enter your name: ");
	scanf("%99s", name);
	printf("Enter your birth year: ");
	scanf("%d", &year);
	year = 2023 - year;
	printf("Hello %s! You are %d years old!", name, year);
	return 0;
}

