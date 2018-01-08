/**
 *  An interface for PriorityQueue that contains necessary operations for 
 *  this data structure implementation such as insert, minimum, extractMin, etc. 
 * 
 * @author Sabirah Shuaybi
 * @author Tseki Lhamo
 * @param <T>
 */

public interface PriorityQueue<T extends Comparable<T>> {
	
	/** Inserts a new element into the PQ */
	public void insert (Comparable element); 
	
	/** Returns the max element */
	public Comparable minimum();
	
	/** Removes and returns the max element */
	public Comparable extractMinimum();
	
	/**Sets the element at specified index to the new element */
	public void increaseValue(int index, Comparable element);
	
	/**Returns whether or not the priority queue is empty */
	public boolean isEmpty();
	
	/**Returns size of priority queue */
	public int getSize();
	
	/** Gets the element at specified index of Priority Queue */
	public Comparable getElementAt(int i);
	
	/** Returns the interanal array */
	public Comparable[] getInternalArray();
}
