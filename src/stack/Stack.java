package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * <p>
 * A generic stack (LIFO - Last In, First Out) implementation using a singly
 * linked list.
 * </p>
 * 
 * <p>
 * This stack supports typical operations such as {@code push}, {@code pop} (via
 * {@code top()}), {@code peek}, {@code size}, {@code isEmpty}, and iteration
 * through its elements from top to bottom.
 * </p>
 * 
 * <p>
 * It also provides utility methods like {@code clear}, {@code contains},
 * {@code toArray}, {@code clone}, {@code reverse}, and {@code popAll}.
 * </p>
 * 
 * @param <T> The type of elements held in this stack.
 * 
 * @author trigologiaa
 */
public class Stack<T> implements Iterable<T> {

	/**
	 * A node in the singly linked list used to implement the stack.
	 * 
	 * @param <T> The type of element stored in the node.
	 */
	private static class Node<T> {

		/**
		 * The data stored in this node.
		 */
		private T data;

		/**
		 * Reference to the next node in the stack.
		 */
		private Node<T> next;

		/**
		 * Creates a new node with the specified data and next node reference.
		 * 
		 * @param data The data stored in this node.
		 * @param next The reference to the next node.
		 */
		Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

	}

	/**
	 * Reference to the top node of the stack.
	 */
	private Node<T> top;

	/**
	 * The number of elements currently in the stack.
	 */
	private int size;

	/**
	 * Constructs an empty stack.
	 */
	public Stack() {
		top = null;
		size = 0;
	}

	/**
	 * Pushes an item onto the top of this stack.
	 * 
	 * @param item The item to be pushed onto this stack.
	 */
	public void push(T item) {
		top = new Node<>(item, top);
		size++;
	}

	/**
	 * Removes and returns the item at the top of this stack.
	 * 
	 * @return The item at the top of this stack.
	 * 
	 * @throws NoSuchElementException If the stack is empty.
	 */
	public T top() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty stack");
		}
		T item = top.data;
		top = top.next;
		size--;
		return item;
	}

	/**
	 * Returns (but does not remove) the item at the top of this stack.
	 * 
	 * @return The item at the top of this stack.
	 * 
	 * @throws NoSuchElementException If the stack is empty.
	 */
	public T peek() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty stack");
		}
		return top.data;
	}

	/**
	 * Returns {@code true} if this stack contains no elements.
	 * 
	 * @return {@code true} if this stack is empty; {@code false} otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of elements in this stack.
	 * 
	 * @return The number of elements in this stack.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an iterator over the elements in this stack, starting from the top.
	 * 
	 * @return an {@code Iterator} over the elements in this stack.
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			/**
			 * The current node being iterated over.
			 */
			private Node<T> current = top;

			/**
			 * Returns {@code true} if there are more elements to iterate over.
			 * 
			 * @return {@code true} if there are more elements; {@code false} otherwise.
			 */
			@Override
			public boolean hasNext() {
				return current != null;
			}

			/**
			 * Returns the next element in the iteration.
			 * 
			 * @return The next element in the iteration.
			 * 
			 * @throws NoSuchElementException If there are no more elements.
			 */
			@Override
			public T next() throws NoSuchElementException {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T item = current.data;
				current = current.next;
				return item;
			}

		};
	}

	/**
	 * Returns a string representation of this stack.
	 * 
	 * @return A string representation of the stack.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Stack: [");
		for (T item : this) {
			sb.append(item).append(", ");
		}
		if (!isEmpty()) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Removes all elements from this stack.
	 */
	public void clear() {
		top = null;
		size = 0;
	}

	/**
	 * Returns {@code true} if this stack contains the specified element.
	 * 
	 * @param item The element whose presence is to be tested.
	 * 
	 * @return {@code true} if this stack contains the specified element; {@code false} otherwise.
	 */
	public boolean contains(T item) {
		for (T element : this) {
			if ((item == null && element == null) || (item != null && item.equals(element))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Pops all elements from this stack, applying the given action to each.
	 * 
	 * @param action The action to apply to each element as it is popped.
	 */
	public void popAll(Consumer<T> action) {
		while (!isEmpty()) {
			action.accept(top());
		}
	}

	/**
	 * Returns an array containing all of the elements in this stack in proper sequence (from top to bottom).
	 * 
	 * @return An array containing all of the elements in this stack.
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (T item : this) {
			array[i++] = item;
		}
		return array;
	}

	/**
	 * Compares the specified object with this stack for equality.
	 * 
	 * @param o The object to be compared for equality with this stack.
	 * 
	 * @return {@code true} if the specified object is equal to this stack.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Stack<?>)) {
			return false;
		}
		Stack<?> other = (Stack<?>) o;
		if (this.size != other.size) {
			return false;
		}
		Iterator<T> it1 = this.iterator();
		Iterator<?> it2 = other.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			T e1 = it1.next();
			Object e2 = it2.next();
			if (!(e1 == null ? e2 == null : e1.equals(e2))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the hash code value for this stack.
	 * 
	 * @return The hash code value for the stack.
	 */
	@Override
	public int hashCode() {
		int hash = 1;
		for (T item : this) {
			hash = 31 * hash + (item == null ? 0 : item.hashCode());
		}
		return hash;
	}

	/**
	 * Returns a shallow copy of this stack.
	 * 
	 * @return A clone of the stack.
	 */
	@Override
	public Stack<T> clone() {
	    Stack<T> cloned = new Stack<>();
	    Stack<T> temp = new Stack<>();
	    for (T item : this) {
	        temp.push(item);
	    }
	    for (T item : temp) {
	        cloned.push(item);
	    }
	    return cloned;
	}

	/**
	 * Reverses the order of elements in this stack.
	 */
	public void reverse() {
		Node<T> prev = null;
		Node<T> current = top;
		while (current != null) {
			Node<T> next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		top = prev;
	}

}