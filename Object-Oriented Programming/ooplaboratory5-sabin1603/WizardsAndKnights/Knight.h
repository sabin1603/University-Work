#pragma once
#ifndef KNIGHT_H
#define KNIGHT_H

#include "character.h"

class Knight : public Character {
public:
    Knight(std::string name, int health, int level, double armor, int swordDamage);
    virtual ~Knight();

    double getArmor() const;
    int getSwordDamage() const;

    void setArmor(double armor);
    void setSwordDamage(int swordDamage);

    void takeDamage(int damage);

private:
    double armor;
    int swordDamage;
};

#endif // KNIGHT_H