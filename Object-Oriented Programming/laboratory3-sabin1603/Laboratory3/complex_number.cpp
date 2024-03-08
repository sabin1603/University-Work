#define CRT_SECURE_NO_WARNINGS
#include <math.h>
#include <stdio.h>

#include "complex_number.h"


Complex complex_create(float real, float imag) {
	Complex c;
	c.real = real;
	c.imag = imag;
	return c;
}


float get_real(Complex z) {
	return z.real;
}

float get_imag(Complex z) {
	return z.imag;
}

void set_real(Complex* z, float real) {
	z->real = real;
}

void set_imag(Complex* z, float imag) {
	z->imag = imag;
}

Complex complex_conjugate(Complex c) {
	c.imag = -c.imag;
	return c;
}

Complex complex_add(Complex c1, Complex c2) {
	Complex c;
	c.real = c1.real + c2.real;
	c.imag = c1.imag + c2.imag;
	return c;
}

Complex complex_subtract(Complex c1, Complex c2) {
	Complex c;
	c.real = c1.real - c2.real;
	c.imag = c1.imag - c2.imag;
	return c;
}

Complex complex_multiply(Complex c1, Complex c2) {
	Complex c;
	c.real = c1.real * c2.real - c1.imag * c2.imag;
	c.imag = c1.real * c2.imag + c1.imag * c2.real;
	return c;
}

void complex_scalar_mult(Complex* c, float s) {
	c->real = c->real * s;
	c->imag = c->imag * s;
}

bool complex_equals(Complex c1, Complex c2) {
	if (c1.real == c2.real && c1.imag == c2.imag)
		return true;
	return false;
}

float complex_mag(Complex c) {
	float result = (float)sqrt(c.real * c.real + c.imag * c.imag);
	return result;
}

float complex_phase(Complex c) {
	float result = (float)atan2(c.imag, c.real);
	return result;
}

void complex_to_polar(Complex c, float* r, float* theta) {
	*r = (float)sqrt(c.real * c.real + c.imag * c.imag);
	*theta = (float)atan2(c.imag, c.real);
	c.real = *r * (float)cos(*theta);
	c.imag = *r * (float)sin(*theta);
}

void complex_display(Complex c) {
	printf("The complex number is:%f + %fi\n", c.real, c.imag);
}