#include "SMMIterator.h"
#include "SortedMultiMap.h"

//Theta(1) complexity
SMMIterator::SMMIterator(const SortedMultiMap& d) : map(d){
	//TODO - Implementation
	currentNode = d.head;
}

//Theta(1) complexity
void SMMIterator::first(){
	//TODO - Implementation
	currentNode = map.head;
}

//Thetaa(1) complexity
void SMMIterator::next(){
	//TODO - Implementation
	if (!valid()) {
		throw std::exception();
	}
	currentNode = map.elements[currentNode].next;
}

//Theta(1) complexity
bool SMMIterator::valid() const{
	//TODO - Implementation
	return currentNode != -1;
}

//Theta(1) complexity
TElem SMMIterator::getCurrent() const{
	//TODO - Implementation
	if (!valid()) {
		throw std::exception();
	}
	return map.elements[currentNode].element;
}


