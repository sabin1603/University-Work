#pragma once
#include "Entity.h"
#include <iostream>

class Empty : public Entity {
public:
    EntityType what() override {
        return EntityType::EMPTY;
    }

    std::string toString() const override {
        return " ";
    }

    friend std::ostream& operator<<(std::ostream& os, const Empty& empty) {
        os << empty.toString();
        return os;
    }
};
