#pragma once
#define MAX_ROWS 100
#define MAX_COLS 100
// forward class declaration for Entity
class Entity;
class SimulationGrid
{
	friend class Entity;
	int getRows();
	int getCols();
	bool isValid(int row, int col) const;
	SimulationGrid();
	SimulationGrid(const SimulationGrid& other);
	~SimulationGrid();
	SimulationGrid& operator=(const SimulationGrid& other);
private:
	int rows;
	int cols;
	Entity* grid[MAX_ROWS][MAX_COLS];
};