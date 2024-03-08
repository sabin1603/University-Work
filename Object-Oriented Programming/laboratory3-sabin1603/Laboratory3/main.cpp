#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include "complex_number.h"

#include "complex_test.h"

int main(int argc, char** argv) {

	run_complex_tests(true);

	// TODO your code here
	// On the stack
	Complex c1;
	c1.real = 5;
	c1.imag = 2;
	float r, theta, result;
	complex_display(c1);
	result = complex_mag(c1);
	printf("Magnitude is %f\n", result);
	result = complex_phase(c1);
	printf("Phase is %f\n", result);
	complex_to_polar(c1, &r, &theta);
	printf("Polar representation: c = %f(cos%f + isin%f)\n", r, theta, theta);
	Complex d = complex_conjugate(c1);
	complex_display(d);
	complex_scalar_mult(&c1, 4);
	complex_display(c1);

	// On the heap
	Complex* c2 = (Complex*)malloc(sizeof(Complex));
	c2->real = 6;
	c2->imag = 4;
	complex_display(*c2);
	result = complex_mag(*c2);
	printf("Magnitude is %f\n", result);
	result = complex_phase(*c2);
	printf("Phase is %f\n", result);
	complex_to_polar(*c2, &r, &theta);
	printf("Polar representation: c = %f(cos%f + isin%f)\n", r, theta, theta);
	Complex c3 = complex_conjugate(*c2);
	complex_display(c3);
	complex_scalar_mult(c2, 4);
	complex_display(*c2);

	c1 = complex_create(5, 2);
	*c2 = complex_create(6, 4);
	Complex add = complex_add(c1, *c2);
	complex_display(add);
	Complex subtract = complex_subtract(c1, *c2);
	complex_display(subtract);
	Complex multiplication = complex_multiply(c1, *c2);
	complex_display(multiplication);
	free(c2);
	return 0;
}