#pragma once
#include <stdbool.h>


typedef struct Complex {
	float real;
	float imag;
}Complex;

Complex complex_create(float real, float imag);

float get_real(Complex z);
float get_imag(Complex z);

void set_real(Complex* z, float real);
void set_imag(Complex* z, float imag);

Complex complex_conjugate(Complex c);
Complex complex_add(Complex c1, Complex c2);
Complex complex_subtract(Complex c1, Complex c2);
Complex complex_multiply(Complex c1, Complex c2);
void complex_scalar_mult(Complex* c, float s);

bool complex_equals(Complex c1, Complex c2);

float complex_mag(Complex c);
float complex_phase(Complex c);
void complex_to_polar(Complex c, float* r, float* theta);

void complex_display(Complex c);