// Wizard.cpp

#include "Wizard.h"

Wizard::Wizard(std::string name, int health, int level, int mana, int spellPower) :
    Character(name, health, level), mana(mana), spellPower(spellPower) {}

int Wizard::getMana() const {
    return mana;
}

int Wizard::getSpellPower() const {
    return spellPower;
}

const std::vector<std::string>& Wizard::getSpells() const {
    return spells;
}

void Wizard::setMana(int mana) {
    this->mana = mana;
}

void Wizard::setSpellPower(int spellPower) {
    this->spellPower = spellPower;
}

void Wizard::setSpells(std::vector<std::string> spells) {
    this->spells = spells;
}

bool Wizard::castSpell(const std::string& spell) {
    // Each spell costs 10 mana
    const int spellCost = 10;
    std::vector<std::string>::iterator it;
    it = std::find(spells.begin(), spells.end(), spell);
    if (it != spells.end() && mana >= spellCost) {
        mana -= spellCost;
        return true;
    }
    return false;
}
