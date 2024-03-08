#pragma once
#ifndef BIGINTEGER_H
#define BIGINTEGER_H

#include<string>
#include<iostream>

class BigInteger {

public:
    BigInteger();
    BigInteger(const std::string& str);
    BigInteger(const BigInteger& other);
    ~BigInteger();
    BigInteger& operator=(const BigInteger& other);

    int sgn();
    int compare(const BigInteger& n) const;

    bool operator==(const BigInteger& N) const;
    bool operator<(const BigInteger& N) const;
    bool operator<=(const BigInteger& N) const;
    bool operator>(const BigInteger& N) const;
    bool operator>=(const BigInteger& N) const;


    BigInteger& operator+=(const BigInteger& N);
    BigInteger& operator-=(const BigInteger& N);
    BigInteger operator+(const BigInteger& N) const;
    BigInteger operator-(const BigInteger& N) const;
    BigInteger& operator++(); // pre-increment operator (++num)
    BigInteger operator++(int); //post-increment operator


    void negate();

private:
    int* digits;
    int numDigits;
    int positive;
    
};

#endif