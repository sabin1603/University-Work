#pragma once
#ifndef WIZARD_H
#define WIZARD_H

#include "character.h"
#include <vector>

class Wizard : public Character {
public:
    Wizard(std::string name, int health, int level, int mana, int spellPower);

    int getMana() const;
    int getSpellPower() const;
    const std::vector<std::string>& getSpells() const;

    void setMana(int mana);
    void setSpellPower(int spellPower);
    void setSpells(std::vector<std::string> spells);

    bool castSpell(const std::string& spell);

private:
    int mana;
    int spellPower;
    std::vector<std::string> spells;
};

#endif // WIZARD_H
