package assign10;

import java.util.NoSuchElementException;

/**
 * This interface represents the priority queue abstract data type,
 * defining the operations and running times expected of any data 
 * structure used to implement a priority queue.  
 * 
 * NOTE: For Assignment 10, the element with the highest priority is 
 * the "minimum" element.
 * 
 * @author CS 2420 course staff
 * @version November 14, 2024
 */
public interface PriorityQueue<E> {
	
	/**
	 * Adds the given element to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param element - element to be added to this priority queue
	 */
	public void add(E element);
	
	/**
	 * Gets the highest-priority element this priority queue.
	 * O(1)
	 * 
	 * @return highest-priority element in this priority queue
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException;
	
	/**
	 * Gets and removes the highest-priority element this priority queue.
	 * O(log N)
	 * 
	 * @return highest-priority element in this priority queue
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extract() throws NoSuchElementException;

	/**
	 * Gets the number of elements in this priority queue.
	 * O(1)
	 * 
	 * @return number of elements in this priority queue
	 */
	public int size();
	
	/**
	 * Determines whether this priority queue is empty.
	 * O(1)
	 * 
	 * @return true if this priority queue is empty, false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Empties this priority queue of elements.
	 * O(1)
	 */
	public void clear();
	
	/** 
	 * Generates an array of the elements in this priority queue,
	 * in the same order they appear in the backing array.
	 * O(N)
	 * 
	 * (NOTE: This method is used for grading. The root element 
	 * must be stored at index 0 in the returned array, regardless of 
	 * whether it is in stored there in the backing array.) 
	 */
	public Object[] toArray();
}