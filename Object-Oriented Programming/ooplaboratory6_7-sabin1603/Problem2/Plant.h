#pragma once
#include "Entity.h"

class Plant : public Entity {
public:
    EntityType what() override {
        return EntityType::PLANT;
    }

    std::string toString() const override {
        return "P";
    }

    friend std::ostream& operator<<(std::ostream& os, const Plant& plant) {
        os << plant.toString();
        return os;
    }
};
