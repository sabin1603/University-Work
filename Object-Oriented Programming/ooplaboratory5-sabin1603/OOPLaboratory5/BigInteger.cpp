# define CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "BigInteger.h"

BigInteger::BigInteger() {
	digits = nullptr;
	numDigits = 0;
	positive = 1;
}

BigInteger::BigInteger(const std::string& str) {
	digits = new int[str.length()];
	numDigits = str.length();
	if (str[0] == '-')
		positive = -1;
	else if (str[0] == '0')
		positive = 0;
	else
		positive = 1;
	for (int i = 0; i < numDigits; i++) {
		digits[i] = str[i];
	}
}

BigInteger::BigInteger(const BigInteger& other) {
	numDigits = other.numDigits;
	positive = other.positive;
	digits = new int[numDigits];
	for (int i = 0; i < numDigits; i++) {
		digits[i] = other.digits[i];
	}
}

BigInteger::~BigInteger() {
	
	delete[] digits;
}

BigInteger& BigInteger::operator=(const BigInteger& other) {
	if (this != &other) {
		delete[] digits;
		numDigits = other.numDigits;
		positive = other.positive;
		digits = new int[numDigits];
		for (int i = 0; i < numDigits; i++) {
			digits[i] = other.digits[i];
		}
	}
	return *this;
}

void negate(int* arr, int size) {
	for (int i = 0; i < size; i++) {
		arr[i] *= -1;
	}
}

int BigInteger::sgn() {
	if (digits == nullptr || numDigits == 0) {
		return 0;
	}
	return positive;
}

int BigInteger::compare(const BigInteger& n) const {
	if (positive != n.positive) {
		if (positive) {
			return 1;
		}
		else {
			return -1;
		}
	}
	int i = (digits[0] == '+') ? 1 : 0;
	int j = (n.digits[0] == '+') ? 1 : 0;

	if (numDigits - i > n.numDigits - j) {
		return positive ? 1 : -1;
	}
	else if (numDigits - i < n.numDigits - j) {
		return positive ? -1 : 1;
	}
	else {
		for (; i < numDigits && j < n.numDigits; i++, j++) {
			if (digits[i] > n.digits[j]) {
				return positive ? 1 : -1;
			}
			else if (digits[i] < n.digits[j]) {
				return positive ? -1 : 1;
			}
		}
	}
	return 0;
}

bool BigInteger::operator==(const BigInteger& N) const {
	return compare(N) == 0;
}

bool BigInteger::operator<(const BigInteger& N) const {
	return compare(N) == -1;
}

bool BigInteger::operator<=(const BigInteger& N) const {
	int cmp = compare(N);
	return cmp == -1 || cmp == 0;
}

bool BigInteger::operator>(const BigInteger& N) const {
	return compare(N) == 1;
}

bool BigInteger::operator>=(const BigInteger& N) const {
	int cmp = compare(N);
	return cmp == 1 || cmp == 0;
}


