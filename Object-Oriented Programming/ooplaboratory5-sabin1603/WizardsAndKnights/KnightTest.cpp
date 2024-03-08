#include "KnightTest.h"
#include "Knight.h"
#include <iostream>
#include <cassert>

void KnightTest::runTests() {
    testTakeDamage();
    std::cout << "All Knight tests passed!\n";
}

void KnightTest::testTakeDamage() {
    Knight k("Arthur", 100, 1, 0.5, 10);
    k.takeDamage(5);
    assert(k.getArmor() == 0.45 && "Armor not decreased correctly");
    assert(k.getHealth() == 100 && "Health decreased even though armor > 0");
    k.takeDamage(7);
    assert(k.getArmor() == 0.38 && "Armor not decreased correctly");
    assert(k.getHealth() == 100 && "Health decreased even though armor > 0");
    k.takeDamage(10);
    assert(k.getArmor() == 0.28 && "Armor not decreased correctly");
    assert(k.getHealth() == 100 && "Health decreased even though armor > 0");
   
}
