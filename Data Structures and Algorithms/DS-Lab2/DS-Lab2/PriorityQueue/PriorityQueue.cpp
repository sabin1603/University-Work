
#include "PriorityQueue.h"
#include <exception>
using namespace std;

//O(1) complexity
PriorityQueue::PriorityQueue(Relation r) {
	//TODO - Implementation
    // Initialize head and tail pointers to nullptr
    head = tail = nullptr;
    // Set the relation function to the given one
    relation = r;
}

//O(n) complexity
void PriorityQueue::push(TElem e, TPriority p) {
	//TODO - Implementation
    Element elem{ e, p };
    Node* newNode = new Node(elem);

    if (head == nullptr) {
        head = tail = newNode;
        return;
    }

    Node* current = head;
    while (current != nullptr && relation(p, current->data.second)) {
        current = current->next;
    }

    if (current == head) {
        newNode->next = head;
        head->prev = newNode;
        head = newNode;
    }
    else if (current == nullptr) {
        tail->next = newNode;
        newNode->prev = tail;
        tail = newNode;
    }
    else {
        newNode->prev = current->prev;
        newNode->next = current;
        current->prev->next = newNode;
        current->prev = newNode;
    }
}

//O(n) complexity
//throws exception if the queue is empty
Element PriorityQueue::top() const {
	//TODO - Implementation
    if (isEmpty()) {
        throw std::exception("Queue is empty");
    }
    Node* current = head;
    Element max_elem = head->data;
    while (current != nullptr) {
        if (relation(current->data.second, max_elem.second)) {
            max_elem = current->data;
        }
        current = current->next;
    }
    return max_elem;
}

//O(1) complexity
Element PriorityQueue::pop() {
	//TODO - Implementation
    if (isEmpty()) {
        throw std::exception();
    }

    Element result = tail->data;
    Node* temp = tail;
    if (head == tail) {
        head = tail = nullptr;
    }
    else {
        tail = tail->prev;
        tail->next = nullptr;
    }
    delete temp;
    return result;
}

//O(1) complexity
bool PriorityQueue::isEmpty() const {
	//TODO - Implementation
    return head == nullptr;
}

//O(n) complexity
PriorityQueue::~PriorityQueue() {
	//TODO - Implementation
    while (head != nullptr) {
        Node* temp = head;
        head = head->next;
        delete temp;
    }
    tail = nullptr;
};

