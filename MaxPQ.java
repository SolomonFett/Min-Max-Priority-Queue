/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          (p3 running median)
// FILE:             (MaxPQ)
//
// TEAM:    (individual)
// Authors: (Be sure to check if programming pairs or teams are allowed)
// Author1: (Nati Solomon Fett, solomonfett@wisc.edu,9075183963,lec 001)
// Author2: (name2,email2,netID2,lecture number2)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Identify persons by name, relationship to you, and email. 
// Describe in detail the the ideas and help they provided. 
// 
// Online sources: stackExchange: priority queue, MaxHeap
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * GENERAL DIRECTIONS -
 *
 * 1. You may add private data fields and private methods as you see fit. 2.
 * Implement ALL the methods defined in the PriorityQueueADT interface. 3. DO
 * NOT change the name of the methods defined in the PriorityQueueADT interface.
 * 4. DO NOT edit the PriorityQueueADT interface. 5. DO NOT implement a shadow
 * array.
 */

public class MaxPQ<E extends Comparable<E>> implements PriorityQueueADT<E> {
	//our array that we fill with incoming items
	private E[] items;
	private int numItems;
	private int size;
	private static final int INITIAL_SIZE = 10;
	// ADD MORE DATA PRIVATE DATA FIELDS AS YOU NEED.
	public MaxPQ() {
		this.items = (E[]) new Comparable[INITIAL_SIZE];
		numItems = 0;
		size = INITIAL_SIZE;
	}
	/**
	 * Adds a data item to the priority queue. Reorders all the other data items
	 * in the queue accordingly.
	 *
	 * If the size if equal to the capacity of the priority queue, double the
	 * capacity and then add the new item.
	 *
	 * @param item
	 *            the item to add
	 * @throws IllegalArgumentException
	 *             if item is null
	 */
	public void insert(E item) throws IllegalArgumentException {
		// where the item will be inserted
		int indexInsert = 0;
		// insert at index 0 if the array is empty
		if (numItems == 0) {
			items[0] = item;
			numItems++;
			return;
		}
		// when the array is full the size will have to be doubled
		if (size == numItems) {
			E[] tempArray = (E[]) new Comparable[size * 2];
			for (int i = 0; i < size; i++) {
				tempArray[i] = items[i];
			}
			items = tempArray;
			size = size * 2;
		}
		// compares item to the items in the array
		for (int i = 0; i < numItems; i++) {
			if (item.compareTo(items[i]) > 0) {
				indexInsert++;
			}
		}
		// after determining the point of insertion, this code
		//inserts the new element into the open space
		for (int i = 0; i < items.length - 1; i++) {
			if (i == indexInsert) {
				for (int j = items.length - 1; j > indexInsert; j--) {
					items[j] = items[j - 1];
				}
				items[indexInsert] = item;
			}
		}
		numItems++;
	}
	/**
	 * Returns the highest priority item in the priority queue.
	 *
	 * MaxPriorityQueue => it will return the largest valued element.
	 *
	 * @return the highest priority item in the priority queue.
	 * @throws EmptyQueueException
	 *             if priority queue is empty.
	 */
	public E getMax() throws EmptyQueueException {
		if (numItems == 0) {
			throw new EmptyQueueException();
		} else {
			// returns the smallest double
			return items[0];
		}
	}
	/**
	 * Returns and removes the highest priority item in the priority queue.
	 * Reorders all the other data items in the queue accordingly.
	 *
	 * MaxPriorityQueue => it will return and remove the largest valued element.
	 *
	 * @return the highest priority item in the priority queue.
	 * @throws EmptyQueueException
	 *             if priority queue is empty.
	 */
	public E removeMax() throws EmptyQueueException {
		if (numItems == 0) {
			throw new EmptyQueueException();
		} else {
			E temp = items[0];
			for (int i = 0; i < numItems; i++) {
				// shifts all the elements to the right, erasing the one at the
				// top
				items[i] = items[i + 1];
			}
			numItems--;
			return temp;
		}
	}
	/**
	 * Returns the number of elements in the priority queue.
	 *
	 * @return number of elements in the queue.
	 */
	public int size() {
		return numItems;
	}
	/**
	 * Checks if the priority queue has any elements and returns true if no
	 * elements, false otherwise.
	 *
	 * @return true if no elements in queue, false otherwise.
	 */
	public boolean isEmpty() {
		if (numItems == 0) {
			return true;
		} else
			return false;
	}
}