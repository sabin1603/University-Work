#pragma once
#include "Entity.h"

class Gopher : public Entity {
public:
    EntityType what() override {
        return EntityType::GOPHER;
    }

    std::string toString() const override {
        return "G";
    }
};
