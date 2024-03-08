#pragma once
#include <stdexcept>

template<typename T>
class Stack
{
private:
	// Maximum size of stack
	static const int MAX_SIZE = 1000;
	// Current size of stack
	int size;
	// Array of elements
	T elements[MAX_SIZE];

public:
	// Constructor
	Stack() : size(0) {}

	// Pushes an element to the top of the stack
	void push(T elem) {
		if (size == MAX_SIZE) {
			throw std::overflow_error("Stack overflow");
		}
		elements[size++] = elem;
	}

	// Returns the element from the top of the stack
	// Throws exception if the stack is empty
	T top() const {
		if (isEmpty()) {
			throw std::underflow_error("Stack underflow");
		}
		return elements[size - 1];
	}

	// Removes and returns the element from the top of the stack
	// Throws exception if the stack is empty
	T pop() {
		if (isEmpty()) {
			throw std::underflow_error("Stack underflow");
		}
		return elements[--size];
	}

	// Checks if the stack is empty
	bool isEmpty() const {
		return size == 0;
	}

	// Destructor
	~Stack() {}
};
