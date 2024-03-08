#define _CRT_SECURE_DEBUG_NO_WARNINGS
#include <iostream>
#include <cmath>
#include "complex_number.h"

using namespace std;


#include "complex_test.h"

#define B_RED "\x1b[41m"
#define AC_BLACK "\x1b[30m"
#define AC_RED "\x1b[31m"
#define AC_GREEN "\x1b[32m"
#define AC_YELLOW "\x1b[33m"
#define AC_BLUE "\x1b[34m"
#define AC_MAGENTA "\x1b[35m"
#define AC_CYAN "\x1b[36m"
#define AC_WHITE "\x1b[37m"
#define AC_NORMAL "\x1b[m"

void display_mandelbrot(int width, int height, int max_its)
{

	const float x_start = -3.0f;
	const float x_fin = 1.0f;
	const float y_start = -1.0f;
	const float y_fin = 1.0f;

	double dx = (x_fin - x_start) / (width - 1);
	double dy = (y_fin - y_start) / (height - 1);

	for (int y = 0; y < height; ++y)
	{
		for (int x = 0; x < width; ++x)
		{
			// TODO your code here
			// create complex number z = 0 + 0i
			// create complex number c =  x_start+ x*dx + (y_start+y*dy)i
			Complex z(0, 0);
			Complex c(x_start + x * dx, y_start + y * dy);

			int iteration = 0;
			// while |z| < 2 and we haven't reach max_its
			while (z.magnitude() < 2 && ++iteration < max_its) {
				// apply the rule:  z =  z*z + c
				z = z * z + c;
			}

			// TODO: your code here (modify the code to display the mandelbrot fractal
			if (iteration == max_its) {
				printf("%s*", AC_CYAN);
			}
			else {
				printf("%s-", AC_MAGENTA);
			}

		}
		printf("\n");
	}
}

int main(int argc, char** argv) {
	display_mandelbrot(100, 25, 100);
	getchar();

	// On the stack
	Complex c1(7, 2);
	printf("First complex number:\n");
	float r, theta, result;
	c1.setDisplayType(DisplayType::RECTANGULAR_FORM);
	cout << c1;
	cout << '\n';
	printf("The complex magnitude is %f\n", c1.magnitude());
	printf("The complex phase is %f\n", c1.phase());
	c1.toPolar(&r, &theta);
	c1.setDisplayType(DisplayType::POLAR_FORM);
	cout << c1 << '\n';
	Complex d(7, 2);
	d.conjugate();
	d.setDisplayType(DisplayType::RECTANGULAR_FORM);
	cout << d << '\n';
	c1.multiply(4);
	c1.setDisplayType(DisplayType::RECTANGULAR_FORM);
	cout << c1 << '\n';

	printf("\n\n");

	// On the heap
	Complex* c2 = (Complex*)malloc(sizeof(Complex));
	c2->setReal(9);
	c2->setImag(3);
	printf("Second complex number:\n");
	c2->setDisplayType(DisplayType::RECTANGULAR_FORM);
	cout << *c2;
	printf("The complex magnitude is %f\n", c2->magnitude());
	printf("The complex phase is %f\n", c2->phase());
	c2->toPolar(&r, &theta);
	c2->setDisplayType(DisplayType::POLAR_FORM);
	cout << *c2 << '\n';
	c2->conjugate();
	c2->setDisplayType(DisplayType::RECTANGULAR_FORM);
	cout << *c2 << '\n';
	c2->multiply(4);
	cout << *c2 << '\n';

	printf("\n\n");

	printf("Operations with both complex numbers:\n");
	c1.setReal(7);
	c1.setImag(2);
	c2->setReal(9);
	c2->setImag(3);
	cout << "Sum: " << c1 + *c2 << '\n';
	cout << "Subtract: " << c1 - *c2 << '\n';
	cout << "Multiplication: " << c1 * *c2 << '\n';
	free(c2);
#if ENABLE_TESTS > 0
	run_complex_tests(false);
#endif
	return 0;

}