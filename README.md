# Stack - Generic Stack Implementation in Java

A fully featured generic **Stack** implementation in Java using a singly linked list.

This stack follows the **LIFO** (Last In, First Out) principle and supports common operations as well as useful utility methods for flexibility and convenience.

---

## Table of Contents

- [Stack - Generic Stack Implementation in Java](#stack---generic-stack-implementation-in-java)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Usage](#usage)
  - [Running Tests](#running-tests)
  - [Design Notes](#design-notes)
  - [Example](#example)
  - [Author](#author)
  - [License](#license)
  - [Contact](#contact)

---

## Features

- Generic: works with any type (`Stack<T>`)
- Core stack operations:
  - `push(T item)` — push an element onto the stack
  - `top()` — remove and return the top element (equivalent to `pop`)
  - `peek()` — return (without removing) the top element
  - `isEmpty()` — check if the stack is empty
  - `size()` — number of elements in the stack
- Iterable: supports iteration from top to bottom with enhanced for-loops
- Utility methods:
  - `clear()` — empties the stack
  - `contains(T item)` — checks if the stack contains a given element (supports `null`)
  - `popAll(Consumer<T> action)` — pops all elements applying an action to each
  - `toArray()` — returns an array of elements in LIFO order
  - `clone()` — creates a shallow copy of the stack
  - `reverse()` — reverses the order of elements in the stack
- Properly overrides `equals(Object)` and `hashCode()`
- Throws `NoSuchElementException` when popping or peeking from an empty stack
- Well documented with Javadoc comments

---

## Usage

```java
Stack<Integer> stack = new Stack<>();
stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek());  // Output: 30

while (!stack.isEmpty()) {
    System.out.println(stack.top());  // Pops and prints: 30, 20, 10
}
```

---

## Running Tests

The implementation comes with comprehensive unit tests using **JUnit 5**.

To run the tests:

1. Ensure you have JUnit 5 set up in your build system (Maven, Gradle, or your IDE).
2. Run `StackTest.java` as a JUnit test suite.
3. All tests cover normal operations, edge cases (empty stack, `null` elements), and utility methods.

---

## Design Notes

- Internally implemented with a singly linked list (`Node<T>`) for dynamic size and efficient push/pop.
- Iterator returns elements from top to bottom.
- Supports `null` elements safely in methods like `contains()`, `equals()`, and `hashCode()`.
- `clone()` creates a shallow copy preserving element order.
- `reverse()` modifies the stack in-place reversing the element order.

---

## Example

```java
Stack<String> names = new Stack<>();
names.push("Alice");
names.push("Bob");
names.push("Charlie");

names.reverse();

for (String name : names) {
    System.out.println(name);
}
// Prints:
// Alice
// Bob
// Charlie
```

---

## Author

trigologiaa

---

## License

This project is released under the MIT License. Feel free to use, modify, and distribute.

---

## Contact

For questions or contributions, please open an issue or contact the author.