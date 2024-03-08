#include "knight.h"

Knight::Knight(std::string name, int health, int level, double armor, int swordDamage)
    : Character(name, health, level), armor(armor), swordDamage(swordDamage) {}

Knight::~Knight() {}

double Knight::getArmor() const {
    return armor;
}

int Knight::getSwordDamage() const {
    return swordDamage;
}

void Knight::setArmor(double armor) {
    this->armor = armor;
}

void Knight::setSwordDamage(int swordDamage) {
    this->swordDamage = swordDamage;
}

void Knight::takeDamage(int damage) {
    armor -= (double)damage / 100.0;
    if (armor < 0) {
        armor = 0;
        setHealth(0);
    }
}
