#include "WizardTest.h"
#include "Wizard.h"
#include <iostream>
#include <cassert>

void WizardTest::runTests() {
    testCastSpell();
    std::cout << "All Wizard tests passed!\n";
}

void WizardTest::testCastSpell() {
    Wizard w("Gandalf", 100, 10, 50, 20);
    w.setSpells({ "fireball", "icebolt", "magic missile" });
    assert(w.castSpell("fireball") == true && "Failed to cast known spell with enough mana");
    assert(w.getMana() == 40 && "Mana not decreased correctly after casting spell");
    assert(w.castSpell("magic missile") == true && "Failed to cast known spell with enough mana");
    assert(w.getMana() == 30 && "Mana not decreased correctly after casting spell");
    assert(w.castSpell("unknown") == false && "Casted unknown spell");
    assert(w.getMana() == 30 && "Mana decreased after failing to cast spell with not enough mana");
}
