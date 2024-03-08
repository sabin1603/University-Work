#include "complex_number.h"
#include <sstream>
#include <iomanip>
#include <sstream>
#include <iostream>
#include <math.h>
using namespace std;
#define M_PI  3.1415926535f 

DisplayType Complex::DISPLAY_TYPE = DisplayType::RECTANGULAR_FORM;

Complex::Complex() :real{ 0.0f }, imag{ 0.0f }
{
}

Complex::Complex(float r, float i) :real{ r }, imag{ i }
{
}

void Complex::setDisplayType(DisplayType dt)
{
	DISPLAY_TYPE = dt;
}

DisplayType Complex::getDisplayType()
{
	return DISPLAY_TYPE;
}

Complex Complex::add(const Complex& c)
{
	return Complex(real + c.real, imag + c.imag);
}

Complex Complex::subtract(const Complex& c)
{
	return Complex(real - c.real, imag - c.imag);
}

Complex Complex::multiply(const Complex& c) const
{
	float r = real * c.real - imag * c.imag;
	float i = real * c.imag + imag * c.real;
	return Complex(r, i);
}

void Complex::multiply(float c)
{
	real *= c;
	imag *= c;
}

bool Complex::equals(const Complex& c) const
{
	if ((real == c.real) && (imag == c.imag))
		return true;
	return false;
}

Complex Complex::conjugate()
{
	return Complex(real, -imag);
}

float Complex::phase() const
{
	return atan2(imag, real);
}

float Complex::magnitude() const
{
	return sqrt(real * real + imag * imag);
}

void Complex::setImag(float i)
{
	imag = i;
}

void Complex::setReal(float r)
{
	real = r;
}

float Complex::getImag() const
{
	return imag;
}

float Complex::getReal() const
{
	return real;
}

Complex Complex::operator+(const Complex& other)
{
	return add(other);
}

Complex Complex::operator-(const Complex& other)
{
	return subtract(other);
}

void Complex::toPolar(float* r, float* theta) const
{
	*r = magnitude();
	*theta = phase();
}

std::string Complex::toString() const
{
	std::ostringstream ss;
	if (DISPLAY_TYPE == DisplayType::RECTANGULAR_FORM) {
		if (real == 0.0f && imag == 0.0f)
			ss << "0";
		else {
			if (imag > 0) {
				ss << std::fixed << std::setprecision(2) << real << "+" << imag << "i";
			}
			else {
				ss << std::fixed << std::setprecision(2) << real << imag << "i";
			}

		}
	}
	else {
		float r, theta;
		toPolar(&r, &theta);
		if (real == 0.0f && imag == 0.0f)
			ss << "0";
		else {
			ss << std::fixed << std::setprecision(2) << r << "*(cos(" << theta * (180 / M_PI) << ") + i*sin(" << theta * (180 / M_PI) << "))";
		}
	}
	return ss.str();
}

Complex operator*(const Complex& c1, const Complex& c2)
{
	return c1.multiply(c2);
}

std::ostream& operator<<(std::ostream& os, const Complex& c)
{
	os << c.toString();
	return os;
}

std::istream& operator>>(std::istream& is, Complex& c)
{
	float real, imag;
	is >> real;
	if (is.fail()) {
		// Failed to read real component
		return is;
	}
	if (is.peek() == '\n' || is.peek() == EOF) {
		// Only one value present; set imaginary component to 0
		imag = 0.0f;
	}
	else {
		// Read imaginary component
		is >> imag;
		if (is.fail()) {
			// Failed to read imaginary component
			return is;
		}
	}
	c.setReal(real);
	c.setImag(imag);
	return is;
}