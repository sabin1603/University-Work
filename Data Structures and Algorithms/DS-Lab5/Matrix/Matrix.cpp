#include "Matrix.h"
#include <exception>

// Constructor
Matrix::Matrix(int nrLines, int nrCols) {
    this->lines = nrLines;
    this->columns = nrCols;
    this->size = 0;
    this->root = nullptr;
}

// Destructor
Matrix::~Matrix() {
    // TODO: Implement the destructor logic to delete all the nodes in the matrix
    // You can use a recursive function to delete the nodes
    // Make sure to handle both the left and right subtrees of each node
}

//Theta(1) complexity
// Returns the number of lines
int Matrix::nrLines() const {
    return lines;
}

//Theta(1) complexity
// Returns the number of columns
int Matrix::nrColumns() const {
    return columns;
}

//O(n) complexity
// Returns the element at line i and column j (indexing starts from 0)
// Throws an exception if (i,j) is not a valid position in the Matrix
TElem Matrix::element(int i, int j) const {
    if (i < 0 || i >= lines || j < 0 || j >= columns)
        throw std::exception("Invalid position");

    Node* current = root;

    while (current != nullptr) {
        if (current->line == i && current->column == j) {
            return current->value;
        }
        else if (current->line < i || (current->line == i && current->column < j)) {
            current = current->rightChild;
        }
        else {
            current = current->leftChild;
        }
    }

    return NULL_TELEM;
}


//O(n) complexity
// Modifies the value at line i and column j
// Returns the previous value from the position
// Throws an exception if (i,j) is not a valid position in the Matrix
TElem Matrix::modify(int i, int j, TElem e) {
    if (i < 0 || i >= lines || j < 0 || j >= columns)
        throw std::exception("Invalid position");

    if (root == nullptr) {
        if (e == NULL_TELEM)
            return NULL_TELEM;

        root = new Node(e, i, j);
        return NULL_TELEM;
    }

    Node* parent = nullptr;
    Node* current = root;

    while (current != nullptr) {
        if (current->line == i && current->column == j) {
            TElem old = current->value;

            if (e == NULL_TELEM) { // Delete
                // First case: node is a leaf
                if (current->leftChild == nullptr && current->rightChild == nullptr) {
                    if (parent == nullptr) { // Root is the only node
                        delete current;
                        root = nullptr;
                        return old;
                    }

                    if (parent->value < current->value)
                        parent->rightChild = nullptr;
                    else
                        parent->leftChild = nullptr;

                    delete current;
                    return old;
                }
                // Second case: node has one child
                if (current->leftChild == nullptr) {
                    if (parent == nullptr) { // Delete root
                        root = current->rightChild;
                        delete current;
                        return old;
                    }

                    if (parent->leftChild == current)
                        parent->leftChild = current->rightChild;
                    else
                        parent->rightChild = current->rightChild;
                    delete current;
                    return old;
                }
                if (current->rightChild == nullptr) {
                    if (parent == nullptr) { // Delete root
                        root = current->leftChild;
                        delete current;
                        return old;
                    }

                    if (parent->leftChild == current)
                        parent->leftChild = current->leftChild;
                    else
                        parent->rightChild = current->leftChild;

                    delete current;
                    return old;
                }
                // Third case: node has two children
                Node* successorParent = current;
                Node* successor = current->rightChild;

                while (successor->leftChild != nullptr) {
                    successorParent = successor;
                    successor = successor->leftChild;
                }

                current->value = successor->value;
                current->line = successor->line;
                current->column = successor->column;

                if (successorParent == current)
                    successorParent->rightChild = successor->rightChild;
                else
                    successorParent->leftChild = successor->rightChild;

                delete successor;
                return old;
            }

            // Update the value if the element already exists
            current->value = e;
            return old;
        }

        parent = current;

        if (current->line < i || (current->line == i && current->column < j))
            current = current->rightChild;
        else
            current = current->leftChild;
    }

    // The element does not exist, create a new node
    Node* newNode = new Node(e, i, j);

    if (parent->line < i || (parent->line == i && parent->column < j))
        parent->rightChild = newNode;
    else
        parent->leftChild = newNode;

    return NULL_TELEM;
}
