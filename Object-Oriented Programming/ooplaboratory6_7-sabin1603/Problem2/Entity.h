#pragma once
#include "EntityType.h"
#include "SimulationGrid.h"
#include <sstream>
#include <string>

class Entity
{
public:
    Entity(int r, int c);

    virtual ~Entity() {}

    virtual EntityType what() = 0;

    virtual std::string toString() const = 0;

    virtual Entity* next(const SimulationGrid& g) = 0;
protected:
    int row;
    int col;

    void demographics(unsigned int population[], const SimulationGrid& g);
};

class Empty : public Entity
{
public:
    Empty(int row, int column) : Entity(row, column) {}
    EntityType what();

    std::string toString() const;

    Entity* next(const SimulationGrid& g);
};
class Plant : public Entity
{
public:
    Plant(int row, int column) : Entity(row, column), age(0) {}
    EntityType what();

    std::string toString() const;

    Entity* next(const SimulationGrid& g);
private:
    int age;
};

std::ostream& operator<<(std::ostream& os, const Entity& e);

class Animal : public Entity {
public:
    Animal(int row, int column) : Entity(row, column) {}
    Animal(int row, int column, int a) : Entity(row, column), age(a) {}
    virtual ~Animal() {}

    virtual EntityType what() = 0;

    virtual std::string toString() const = 0;

    virtual Entity* next(const SimulationGrid& g) = 0;

    int getAge() const;
    void setAge(int newAge);

protected:
    int age = 0;
};

class Gopher : public Animal {
public:
    Gopher(int row, int col);
    Gopher(int row, int col, int age);
    EntityType what();

    std::string toString() const;

    Entity* next(const SimulationGrid& g);
};

class Fox : public Animal {
public:
    Fox(int row, int col);
    Fox(int row, int col, int age);
    EntityType what();

    std::string toString() const;

    Entity* next(const SimulationGrid& g);
};