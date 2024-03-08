#include "character.h"

Character::Character(std::string name, int health, int level)
    : name(name), health(health), level(level) {}

Character::~Character() {}

std::string Character::getName() const {
    return name;
}

int Character::getHealth() const {
    return health;
}

int Character::getLevel() const {
    return level;
}

void Character::setName(std::string name) {
    this->name = name;
}

void Character::setHealth(int health) {
    this->health = health;
}

void Character::setLevel(int level) {
    this->level = level;
}