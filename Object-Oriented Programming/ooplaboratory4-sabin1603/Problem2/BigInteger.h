#pragma once
#include<string>
#include<iostream>


class BigInteger {

public:
    BigInteger();
    BigInteger(const std::string& str);
    BigInteger(const BigInteger& other);
    int compare(const BigInteger& n) const;

    int sgn();

    bool operator==(const BigInteger& N) const;
    bool operator<(const BigInteger& N) const;
    bool operator<=(const BigInteger& N) const;
    bool operator>(const BigInteger& N) const;
    bool operator>=(const BigInteger& N) const;

private:
    int* digits;
    int numDigits;
    int positive;
};