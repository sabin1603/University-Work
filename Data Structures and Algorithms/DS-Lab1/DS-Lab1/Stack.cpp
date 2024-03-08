#include "Stack.h"
#include <exception>


using namespace std;

void Stack::resize() {

	// O(n) complexity

	//We allocate a new array with double capacity somewhere in the computer's memory
	TElem* eNou = new int[2 * cp];

	//We copy all the elements from the old array into the new array
	for (int i = 0; i < n; i++)
		eNou[i] = e[i];

	//We double the capacity
	cp = 2 * cp;

	//We release the memory space occupied by the old array
	delete[] e;

	//We make the field e to refer the new array (with double capacity)
	e = eNou;
}

Stack::Stack() {
	//TODO - Implementation
	
	//O(1) complexity

	//We initialize the capacity
	this->cp = 1;

	//We allocate memory space for an array with at most cp TElems
	e = new TElem[cp];

	//We initialize the number of elements 
	this->n = 0;
}


void Stack::push(TElem e) {
	//TODO - Implementation
	 
	//O(1) - best and average case complexities
	//O(n) - worst case complexity
	
	//If the maximum capacity has been reached, then we resize the array of elements
	if (n == cp)
		resize();

	//We add the element to the end of the array
	this->e[n++] = e;
}

TElem Stack::top() const {
	//TODO - Implementation

	//O(1) complexity


	if (isEmpty()) {
		throw exception("Stack is empty");
	}
	return e[n - 1];
}

TElem Stack::pop() {
	//TODO - Implementation

	//O(1) complexity

	if (isEmpty()) {
		throw exception("Stack is empty");
	}
	return e[--n];
}


bool Stack::isEmpty() const {
	//TODO - Implementation

	//O(1) complexity 


	return (n == 0);
}

Stack::~Stack() {
	//TODO - Implementation

	//O(1) complexity


	delete[] e;
}