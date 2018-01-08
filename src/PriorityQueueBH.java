/**
 * An implementation of the PriorityQueue interface
 * Contains two constructors depending on which type of arguments are best suited.
 * 
 * @author Sabirah Shuaybi
 * @author Tseki Lhamo 
 */
public class PriorityQueueBH <T extends Comparable<T>> implements PriorityQueue<T> {

	protected BinaryHeap pqHeap;
	
	/** Constructor that takes only a capacity and creates an empty heap with that capacity **/
	public PriorityQueueBH (int capacity) {
		pqHeap = new BinaryHeap<T>(capacity);
	} 
	
	/** Constructor that takes a Comparable array and builds a heap with that array **/
	public PriorityQueueBH (Comparable[] array) {
		pqHeap = new BinaryHeap<T>(array);
	} 

	 /** 
	  * Inserts a new element into the priority queue
	  * 
	  * @param element: the new element to be added to the queue
	  */
	public void insert(Comparable element) {
		pqHeap.addElement(element);
	} 
	
	/**
	  * Sets the element at specified index to new elements. 
	  * Fixes heap (through swaps) to move element to the correct position. 
	  * Throws an exception if new element is less than the current element at that index
	  * 
	  * @param index: the position in PQ that contains the element that is to be increased
	  * @param element: the new value 
	  */
	public void increaseValue(int index, Comparable element) {
		//If new element is less than current element, throw exception
		Comparable e = pqHeap.getElement(index);
		if(0 > element.compareTo(e)) {
			throw new IllegalArgumentException("New element is smaller than current element");
		}
		else  {
			//replace the value at specified index with the new element 
			pqHeap.setElement(index, element);	
			while (index > 1 && pqHeap.getElement(pqHeap.parent(index)).compareTo(pqHeap.getElement(index)) < 0 ) {
				pqHeap.swap(index, pqHeap.parent(index));
				index = pqHeap.parent(index);
			}
		}
	}
	
	/**
	 * Removes the minimum element in PQ (the root)
	 * 
	 * @return the min element that was removed
	 */
	public Comparable extractMinimum() {
		if(!pqHeap.isEmpty()) {
			Comparable max = minimum();
			pqHeap.swap(1, pqHeap.size());
			pqHeap.setSize(pqHeap.size() - 1);
			pqHeap.minHeapify(1);
			return max;
		}
		return null;
	}

	 /** Retrieves the minimum element in the PQ
	  * 
	  * @return the minimum element (root)
	  */
	public Comparable minimum() {
		return pqHeap.getElement(1);
	}
	
	public Comparable getElementAt(int i) {
		return pqHeap.getElement(i);
	}
	
	 /** 
	  * Method for determining if priority queue is empty
	  * 
	  * @return true if PQ is empty, else false
	  */
	public boolean isEmpty() {
		return pqHeap.isEmpty();
	}
	
	 /** 
	  * Helper method to print the priority queue (used during debugging) 
	  */
	public void printPQ() {
		pqHeap.printHeap();
	}
	
	/**
	 * Gets size of heap 
	 */
	public int getSize() {
		return pqHeap.size();
	}
	
	public Comparable[] getInternalArray() {
		return pqHeap.getInternalArray();
	}
}
