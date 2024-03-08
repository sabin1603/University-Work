#pragma once
#include <string>

class Shape
{
public:
    Shape(float x, float y);
    virtual ~Shape(); // virtual destructor
    virtual float area() = 0;
    virtual std::string toString() = 0;

protected:
    float m_x, m_y;
};