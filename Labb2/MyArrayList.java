package Labb_2;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings({ "serial", "unchecked" })
public class MyArrayList<E> implements Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess {
	private E[] elementData;
	private int size;
	// ---------------------------------------------------------------

	public static void main(String[] args) {
		MyArrayList<String> strlist = new MyArrayList<String>();
		// testa metoder härifrån

	}

	// ---------------------------------------------------------------

	public MyArrayList(int initialCapacity) {
		if (initialCapacity < 0) // Checks if the capacity is bigger than 0, else throws exception
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);

		elementData = (E[]) new Object[initialCapacity]; // Creates the array "elementData".
		size = 0; // Sets size to 0 because size only counts elements, not the "size"
	}

	public MyArrayList() { // Constructor, starts myArrayList with 10 capacity, as a regular ArrayList
		this(10);
	}

	// -- 1 --

	@Override
	public int size() { // Returns the value of the variable size
		return size;
	}

	@Override
	public boolean isEmpty() { // If empty return true, else false
		return size == 0;
	}

	@Override
	public void clear() { // Sets the size 0
		size = 0;
	}

	// -- 2 --

	public void ensureCapacity(int minCapacity) {
		if (minCapacity <= elementData.length) { // If elementData is big enough, returns
			return;
		}

		int newCapacity = elementData.length; // Saves the current size of elementData

		while (newCapacity < minCapacity) { // Adds 1 capacity every loop until its big enough
			newCapacity = newCapacity + 1;
		}

		E[] newArray = (E[]) new Object[newCapacity]; // Creates a new array with the correct size

		for (int i = 0; i < size; i++) { // Copies every element in the old array to the new
			newArray[i] = elementData[i]; // Loops thru the entire array
		}

		elementData = newArray; // Change over to the new array
	}

	public void trimToSize() { // Method to remove unused space
		if (size == elementData.length) { // If the size is the same as length, return
			return;
		}

		E[] newArray = (E[]) new Object[size]; // Creates a new array with the new size
		for (int i = 0; i < size; i++) { // Copies over every element to the new array
			newArray[i] = elementData[i];
		}

		elementData = newArray; // Change over to the new array
	}

	// -- 3 --

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) { // Checks if index is 0 or bigger and not outside the current size
			throw new IndexOutOfBoundsException();
		}

		ensureCapacity(size + 1); // Making room for one more element

		for (int i = size; i > index; i--) { // Moves every element from index and to the right moves 1 step to the
												// right
			elementData[i] = elementData[i - 1];// To create space for the new element at the specified index
		}

		elementData[index] = element; // Adds element at specified index

		size = size + 1; // We have 1 more element, the size is +1 bigger and needs to be updated
	}

	@Override
	public boolean add(E e) { // Adds element at the end of the array and returns true if successful
		add(size, e);
		return true;
	}

	// -- 4 --

	@Override
	public E get(int index) { // Returns element at specified index
		if (index < 0 || index >= size) { // Checks if index is 0 or bigger and not outside the current size
			throw new IndexOutOfBoundsException();
		}

		return elementData[index];
	}

	@Override
	public E set(int index, E element) { // Replaces a value at an specified index and returns the old one
		if (index < 0 || index >= size) { // Checks if index is 0 or bigger and not outside the current size
			throw new IndexOutOfBoundsException();
		}

		E value = elementData[index]; // Saves the old element
		elementData[index] = element; // Replaces the old value with the new

		return value; // Returns old value
	}

	// -- 5 --

	@Override
	public E remove(int index) { // Remove element at specified index
		if (index < 0 || index >= size) { // Checks if index is 0 or bigger and not outside the current size
			throw new IndexOutOfBoundsException();
		}

		E removed = elementData[index]; // Saves the old element

		for (int i = index; i < size - 1; i++) { // Starts at the removed index and moves every element to the left
			elementData[i] = elementData[i + 1];
		}

		elementData[size - 1] = null; // Sets the last empty space to null

		size = size - 1; // Updates the size

		return removed; // Returns the removed value
	}

	protected void removeRange(int fromIndex, int toIndex) { // Removes several elements in a range
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) { // Checks that the index given is within the size
			throw new IndexOutOfBoundsException();
		}
		int removedspots = toIndex - fromIndex; // How many elements to be removed in total

		for (int i = 0; i < removedspots; i++) {// Loops thru the array and removes elements
			remove(fromIndex); // Calls the function remove
		}
	}

	// -- 6 --

	@Override
	public int indexOf(Object o) { // Look up elements
		if (o == null) { // Checks if the object is null
			for (int i = 0; i < size; i++) { // Goes through the array and compares
				if (elementData[i] == null) { // if the element at the index is null
					return i; // Returns index
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData[i])) { // If the object matches the element at index returns index
					return i;
				}
			}
		}

		return -1; // If the object does not exist returns -1
	}

	@Override
	public boolean remove(Object o) { // Removes object if it exists
		int index = indexOf(o); // Calls indexOf and asks for index
		if (index != -1) { // If it returns -1, object does not exist
			remove(index); // Else, removes the object at index

			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		if (indexOf(o) == -1) { // Simple check to see if object exists
			return false;
		}
		return true;
	}

	// -- 7 --

	@Override
	public Object clone() { // Copies the array and returns copy
		MyArrayList<E> clone = new MyArrayList<>(size); // Creates new array with the same size

		for (int i = 0; i < size; i++) { // Goes through the current array and adds every element to the copy
			clone.add(elementData[i]);
		}

		return clone;
	}

	@Override
	public Object[] toArray() { // Returns array
		Object[] array = new Object[size]; // Creates new object

		for (int i = 0; i < size; i++) { // Copies every element in the array
			array[i] = elementData[i];
		}

		return array; // Returns array
	}

	// --- Rör ej nedanstående kod -----------------------------------

	public MyArrayList(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	private class InternalIterator implements Iterator {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public Object next() {
			return get(current++);

		}

	}

	@Override
	public Iterator<E> iterator() {
		return new InternalIterator();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void forEach(Consumer<? super E> action) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Spliterator<E> spliterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

}
