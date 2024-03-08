#include "SimulationGrid.h"
#include "Entity.h"

SimulationGrid::SimulationGrid() : rows(0), cols(0)
{
    for (int i = 0; i < MAX_ROWS; i++) {
        for (int j = 0; j < MAX_COLS; j++) {
            grid[i][j] = nullptr;
        }
    }
}

SimulationGrid::~SimulationGrid()
{
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            delete grid[i][j];
            grid[i][j] = nullptr;
        }
    }
}

SimulationGrid& SimulationGrid::operator=(const SimulationGrid& other)
{
    
    if (this == &other) {
        return *this;
    }

    // Copy other's data members
    rows = other.rows;
    cols = other.cols;

    // Copy the grid
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (other.grid[i][j] == nullptr) {
                grid[i][j] = nullptr;
            }
            else if (dynamic_cast<Plant*>(other.grid[i][j]) != nullptr) {
                // Copy a Plant
                Plant* otherPlant = dynamic_cast<Plant*>(other.grid[i][j]);
                Plant* plantCopy = new Plant(*otherPlant);
                grid[i][j] = plantCopy;
            }
            else if (dynamic_cast<Fox*>(other.grid[i][j]) != nullptr) {
                // Copy a Fox
                Fox* otherFox = dynamic_cast<Fox*>(other.grid[i][j]);
                Fox* foxCopy = new Fox(*otherFox);
                grid[i][j] = foxCopy;
            }
            else if (dynamic_cast<Gopher*>(other.grid[i][j]) != nullptr) {
                // Copy a Gopher
                Gopher* otherGopher = dynamic_cast<Gopher*>(other.grid[i][j]);
                Gopher* gopherCopy = new Gopher(*otherGopher);
                grid[i][j] = gopherCopy;
            }
        }
    }

    return *this;
}

int SimulationGrid::getRows()
{
	return rows;
}

int SimulationGrid::getCols()
{
	return cols;
}

bool SimulationGrid::isValid(int row, int col) const
{
	return (row >= 0 && row < rows && col >= 0 && col < cols);
}
