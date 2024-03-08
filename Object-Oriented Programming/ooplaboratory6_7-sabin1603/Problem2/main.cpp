// Problem2.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include "Entity.h"
#include "EntityType.h"


using namespace std;

int main()
{
    Entity* entities[4];
    entities[0] = new Plant(0, 0);
    entities[1] = new Fox(1, 1);
    entities[2] = new Gopher(2, 2);
    entities[3] = new Empty(3, 3);

    for (int i = 0; i < 4; ++i) {
        std::cout << entities[i]->toString() << '\n';
    }

    // Clean up memory
    for (int i = 0; i < 4; ++i) {
        delete entities[i];
    }

    return 0;
}
