#pragma once
#include "Entity.h"

class Fox : public Entity {
public:
    EntityType what() override {
        return EntityType::FOX;
    }

    std::string toString() const override {
        return "F";
    }
};
