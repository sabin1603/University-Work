#include <iostream>
#include <string>
#include "Stack.h"

int main() {
    Stack<int> intStack;
    intStack.push(1);
    intStack.push(2);
    intStack.push(3);
    std::cout << intStack.pop() << std::endl; // Output: 3
    std::cout << intStack.pop() << std::endl; // Output: 2

    Stack<double> doubleStack;
    doubleStack.push(1.1);
    doubleStack.push(2.2);
    doubleStack.push(3.3);
    std::cout << doubleStack.pop() << std::endl; // Output: 3.3
    std::cout << doubleStack.pop() << std::endl; // Output: 2.2

    Stack<std::string> stringStack;
    stringStack.push("hello");
    stringStack.push("world");
    std::cout << stringStack.pop() << std::endl; // Output: world
    std::cout << stringStack.pop() << std::endl; // Output: hello

    return 0;
}
