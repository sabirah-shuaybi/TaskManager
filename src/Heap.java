/**
 *  This interface is specifically intended for a Max-Heap.
 *  Contains methods for adding, removing from heap..
 * 
 * @author Sabirah Shuaybi
 * @param <T>
 */

public interface Heap<T extends Comparable<T>> {

	/** Builds a max heap with internal array */
	public void buildMinHeap();  
	
	/** Maintains max-heap property for subtree of index i */
	public void minHeapify(int i); 
	
	/** Adds a new element to heap, and ensures maintenance of max heap property */
	public void addElement(Comparable e); 
	
	/** Sets the heapsize of heap */
	public void setSize(int size);
	
	/** Returns the element at index i */
	public Comparable getElement(int i);
	
	/** Gets the index of parent node */
	public int parent(int i);
	
	/** Gets the index of left child node */
	public int leftChild(int i);
	
	/** Gets the index of right child node */
	public int rightChild(int i);
	
	/** Exchanges the elements of two nodes */
	public void swap(int i1, int i2);
	
	/** Checks if heap is empty or not  */
	public boolean isEmpty();
	
	/** Returns the number or elements in heap (aka heapsize)  */
	public int size();
	
	/** Returns the capacity of the heap */
	public int capacity();
	
	
}
