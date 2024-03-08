#include "Entity.h"
#include "EntityType.h"
#include <sstream>
#include <algorithm> // for std::fill

Entity::Entity(int row, int column) : row(row), col(column) {}

void Entity::demographics(unsigned int population[], const SimulationGrid& g) {
	// set the population array to 0; we have 4 types of elements : Empty, Fox, Gopher, Plant
	std::fill(population, population + 4, 0);
	// offsets for the neighbors' coordinates
	int dx[]{ 0, 0, 1, 1, 1, -1, -1, -1 };
	int dy[]{ 1, -1, -1, 0, 1,  -1, 0, 1 };
	// row : row+1, row-1
	// col: col+0, col+0
	unsigned int numNeigh{ sizeof(dy) / sizeof(dy[0]) };

	for (unsigned int i{ 0 }; i < numNeigh; ++i) {
		int r{ row + dy[i] };
		int c{ col + dx[i] };
		if (g.isValid(r, c)) { // check that r and c are within bounds
			EntityType et = g.grid[r][c]->what();
			// convert the enum class to int, you can use to_underlying
			population[to_underlying(et)]++;
		}
	}
}


EntityType Empty::what() {
	return EntityType::EMPTY;
}

std::string Empty::toString() const {
	return "E";
}

Entity* Empty::next(const SimulationGrid& g) {
	unsigned int population[4];
	demographics(population, g);
	if (population[to_underlying(EntityType::GOPHER)] > 1) {
		return new Gopher(row, col, 0);
	}
	else if (population[to_underlying(EntityType::FOX)] > 1) {
		return new Fox(row, col, 0);
	}
	else if (population[to_underlying(EntityType::PLANT)] >= 1) {
		return new Plant(row, col);
	}
	else {
		return new Empty(row, col);
	}
}

std::ostream& operator<<(std::ostream& os, const Empty& empty) {
	os << empty.toString();
	return os;
}

int Animal::getAge() const
{
	return age;
}

void Animal::setAge(int newAge)
{
	age = newAge;
}

Fox::Fox(int row, int column) : Animal(row, column) {}

Fox::Fox(int row, int column, int age) : Animal(row, column, age) {}

EntityType Fox::what() {
	return EntityType::FOX;
}

std::string Fox::toString() const {
	return "F";
}

Entity* Fox::next(const SimulationGrid& g) {
	unsigned int population[4];
	demographics(population, g);
	if (age >= 5) {
		return new Empty(row, col);
	}
	if (population[to_underlying(EntityType::GOPHER)] == 0) {
		return new Empty(row, col);
	}
	age++;
	return new Fox(row, col);
}

Gopher::Gopher(int row, int column) : Animal(row, column) {}
Gopher::Gopher(int row, int column, int age) : Animal(row, col, age) {}

EntityType Gopher::what() {
	return EntityType::GOPHER;
}

std::string Gopher::toString() const {
	return "G";
}

EntityType Plant::what()
{
	return EntityType::PLANT;
}

std::string Plant::toString() const {
	return "P";
}

Entity* Plant::next(const SimulationGrid& g)
{
	return nullptr;
}

Entity* Gopher::next(const SimulationGrid& g) {
	unsigned int population[4];
	demographics(population, g);
	if (age == 4) {
		return new Empty(row, col);
	}
	if (population[to_underlying(EntityType::PLANT)] == 0) {
		return new Empty(row, col);
	}
	if (population[to_underlying(EntityType::FOX)] >= population[to_underlying(EntityType::GOPHER)]) {
		return new Fox(row, col);
	}
	age++;
	return new Gopher(row, col);
}