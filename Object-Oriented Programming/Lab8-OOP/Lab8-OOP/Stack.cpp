#include "Stack.h"
#include <stdexcept>

template<typename T>
void Stack<T>::push(T elem) {
	if (size == MAX_SIZE) {
		throw std::overflow_error("Stack overflow");
	}
	elements[size++] = elem;
}

template<typename T>
T Stack<T>::top() const {
	if (isEmpty()) {
		throw std::underflow_error("Stack underflow");
	}
	return elements[size - 1];
}

template<typename T>
T Stack<T>::pop() {
	if (isEmpty()) {
		throw std::underflow_error("Stack underflow");
	}
	return elements[--size];
}

template<typename T>
bool Stack<T>::isEmpty() const {
	return size == 0;
}