#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <iostream>
#include <vector>
#include <exception>
using namespace std;

//O(n) complexity
SortedMultiMap::SortedMultiMap(Relation r) {
	//TODO - Implementation
	capacity = 10;
	elements = new Node[capacity];
	nexts = new int[capacity];
	head = -1;
	firstEmpty = 0;
	count = 0;
	relation = r;

	for (int i = 0; i < capacity - 1; i++) {
		nexts[i] = i + 1;
	}
	nexts[capacity - 1] = -1;
}

//O(n) complexity
void SortedMultiMap::add(TKey c, TValue v) {
	//TODO - Implementation
	if (firstEmpty == -1) {
		resize();
	}

	int newNode = firstEmpty;
	firstEmpty = nexts[firstEmpty];

	elements[newNode].element = std::make_pair(c, v);
	elements[newNode].next = -1;

	if (head == -1) {
		head = newNode;
	}
	else {
		if (relation(c, elements[head].element.first)) {
			elements[newNode].next = head;
			head = newNode;
		}
		else {
			int current = head;
			int prev = -1;
			while (current != -1 && !relation(c, elements[current].element.first)) {
				prev = current;
				current = elements[current].next;
			}
			elements[newNode].next = current;
			if (prev != -1) {
				elements[prev].next = newNode;
			}
		}
	}
	count++;
}

//O(n) complexity
vector<TValue> SortedMultiMap::search(TKey c) const {
	//TODO - Implementation
	vector<TValue> values;
	int current = head;
	while (current != -1) {
		if (elements[current].element.first == c) {
			values.push_back(elements[current].element.second);
		}
		current = elements[current].next;
	}
	return values;
}

//O(n) complexity
bool SortedMultiMap::remove(TKey c, TValue v) {
	//TODO - Implementation
	int current = head;
	int prev = -1;
	while (current != -1) {
		if (elements[current].element.first == c && elements[current].element.second == v) {
			if (prev != -1) {
				elements[prev].next = elements[current].next;
			}
			else {
				head = elements[current].next;
			}
			elements[current].next = firstEmpty;
			firstEmpty = current;
			count--;
			return true;
		}
		prev = current;
		current = elements[current].next;
	}
	return false;
}


//Theta(1) complexity
int SortedMultiMap::size() const {
	//TODO - Implementation
	return count;
}

//Theta(1) complexity
bool SortedMultiMap::isEmpty() const {
	//TODO - Implementation
	return count == 0;
}

//Theta(1) complexity
SMMIterator SortedMultiMap::iterator() const {
	return SMMIterator(*this);
}

SortedMultiMap::~SortedMultiMap() {
	//TODO - Implementation
	delete[] elements;
	delete[] nexts;
}

//O(n) complexity
void SortedMultiMap::resize() {
	// Helper function for resizing the arrays
	int newCapacity = capacity * 2;
	Node* newElements = new Node[newCapacity];
	int* newNexts = new int[newCapacity];

	for (int i = 0; i < capacity; i++) {
		newElements[i] = elements[i];
		newNexts[i] = nexts[i];
	}

	for (int i = capacity; i < newCapacity - 1; i++) {
		newNexts[i] = i + 1;
	}
	newNexts[newCapacity - 1] = -1;

	delete[] elements;
	delete[] nexts;

	elements = newElements;
	nexts = newNexts;
	firstEmpty = capacity;
	capacity = newCapacity;
}

