#pragma once
#ifndef CHARACTER_H
#define CHARACTER_H

#include <string>

class Character {
public:
    Character(std::string name, int health, int level);
    virtual ~Character();

    std::string getName() const;
    int getHealth() const;
    int getLevel() const;

    void setName(std::string name);
    void setHealth(int health);
    void setLevel(int level);

protected:
    std::string name;
    int health;
    int level;
};

#endif // CHARACTER_H