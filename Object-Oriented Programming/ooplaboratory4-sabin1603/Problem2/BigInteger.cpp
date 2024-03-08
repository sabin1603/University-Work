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
		digits[i] = str[i]-'0';
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

int BigInteger::compare(const BigInteger& n) const {
	if (positive != n.positive) {
		if (positive) {
			return 1;
		}
		else {
			return -1;
		}
	}

	int i, j;

	if (digits[0] == '+') {
		i = 1;
	}
	else
		i = 0;

	if (n.digits[0] == '+')
		j = 1;
	else j = 0;

	if (numDigits - i > n.numDigits - j) {
		if (positive)
			return 1;
		else
			return -1;
	}
	else if (numDigits - i < n.numDigits - j) {
		if (positive)
			return -1;
		else return 1;
	}
	else {
		for (; i < numDigits && j < n.numDigits; i++, j++) {
			if (digits[i] > n.digits[j]) {
				if (positive)
					return 1;
				else return -1;
			}
			else if (digits[i] < n.digits[j]) {
				if (positive)
					return -1;
				else return 1;
			}
		}
	}
	return 0;
}

int BigInteger::sgn() {
	if (digits == nullptr || numDigits == 0) {
		return 0;
	}
	return positive;
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