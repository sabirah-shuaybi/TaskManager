import java.lang.reflect.Array;

/**
 * The BinaryHeap class is an implementation of Heap Interface, specifically of a max heap.
 *  It contains methods for building min heaps, min-heapifying, adding elements, getting right/left child or 
 *  parent indexes, checking size or capacity, swapping two elements, etc.
 * 
 * Created by peter on 9/22/16.
 * @Author: Sabirah Shuaybi (modified)
 */

public class BinaryHeap<T extends Comparable<T>> implements Heap<T> {

	// array to hold the heap
	private Comparable[] internalArray;
	// keep track of the heap size (different from the capacity)
	private int heapSize;
	// keep track of total capacity (different from heapSize)
	private int capacity;

	/**
	 * BinaryHeap constructor that takes an array of type Comparable,
	 * internalizes the array (as an instance variable) and builds a heap
	 * from the array. 
	 */
	public BinaryHeap(Comparable[] a) {
		//According to the TA, if a heap capacity is not explicitly set (through the second constructor),
		//it is assumed that capacity is equal to the length of the array passed in
		capacity = a.length;

		//Copy the array passed in into internalArray
		internalArray = a;

		heapSize = internalArray.length;

		// make the array a heap
		buildMinHeap();
	}

	/**
	 * A second BinaryHeap constructor that also takes in a number, n
	 * for the capacity of the heap. Builds an array of size n and then
	 * a heap from the array.
	 */
	public BinaryHeap(Comparable[] a, int n) {
		capacity = n;

		//creates a BinaryHeap of capacity n
		// Starting with the elements in a
		internalArray = (Comparable[]) new Comparable[n];
		System.arraycopy(a, 0, internalArray, 0, a.length);

		heapSize = 0;

		// make the array a heap.
		buildMinHeap();
	}

	public BinaryHeap(int n) {
		capacity = n;
		// make the array a heap.
		internalArray = (Comparable[]) new Comparable[n];

		heapSize = 0;
		buildMinHeap();
	}

	/**
	 * Adds a new element to the heap by creating a larger sized array, 
	 * copying the contents of original array and rebuilding heap with
	 * "new" array that now contains the new element
	 * 
	 * @param e: a new value/element to be inserted into heap
	 */
	public void addElement(Comparable e) {
		
		if (heapSize == capacity) {
			//Temporary array to hold the value of original internalArray 
			Comparable[] tempArray = internalArray;
			internalArray = new Comparable [internalArray.length + capacity];
			System.arraycopy(tempArray, 0, internalArray, 0, tempArray.length);
		}
		//Add the new element e to the end of the array
		internalArray[heapSize] = e; 
		heapSize ++;
		//rebuild the heap in the case that e might have violated max heap property
		buildMinHeap(); //note: the reason for building the heap from scratch instead of using 
		//maxheapify is because we technically have a new array so its easier to
		//simply use it to build a new max heap
	}


	/**
	 * Constructs a max heap from internalArray only if internalArray is not empty
	 */
	public void buildMinHeap() {
		//Build max heap only if array passed in is not empty
		if(!isEmpty()) {
			//This for loops starts at the last node that has children and iterates back to the root
			for(int i = heapSize/2; i>0; i--) {
				//Call maxHeapify to ensure that max-heap property is maintained for each parent node
				minHeapify(i);
			}
		}

	}

	/**
	 * Arranges node index and its subtrees to satisfy the max-heap property
	 * 
	 * @param i: index at which to begin subtree comparisons
	 */
	public void minHeapify(int i) {
		int left = leftChild(i);
		int right = rightChild(i);
		int largest = 0;
		
		//If there is a left child and the left child's element is greater than current node,
		if(left <= heapSize && internalArray[left-1].compareTo(internalArray[i-1]) < 0) {
			//assign the index of left to largest
			largest = left;
		}
		else {
			//If no left child or left child is smaller than the node, assign the index of node to largest
			largest = i;
		}
		
		//If there is a right child and the right child's element is greater than index containing the largest value,
		if(right <= heapSize && internalArray[right-1].compareTo(internalArray[largest-1]) < 0) {
			//assign the index of right to largest
			largest = right;
		}

		//If largest is not the original index passed in, do a swap
		if(largest != i) {
			//exchange a[i] with a[largest]
			swap(largest, i);
			//RECURSIVE call with largest index (to ensure that max-heap property maintained throughout)
			minHeapify(largest);
		}
	}

	/**
	 * Swaps the elements of two nodes in the heap
	 *
	 *@param i1: first index
	 *@param i2: second index
	 */
	public void swap(int i1, int i2) {
		Comparable temp = internalArray[i1-1];
		internalArray[i1-1] = internalArray[i2-1];
		internalArray[i2-1] = temp;
	}


	/**
	 * Retrieves the element/value at the index passed in
	 * 
	 * @param i: index of current node in reference
	 * @return the element at that index
	 */
	public Comparable getElement(int i) {
		if(isEmpty()) {
			return null;
		}
		
		return internalArray[i-1];
	}

	/** Sets the element at the specified index to the new element passed in */
	public void setElement(int i, Comparable element) {
		if(internalArray[i-1] != null) {
			internalArray[i-1] = element;
		}
			
	}

	/**
	 * Sets the size of the heap
	 * 
	 * @param: the new size 
	 */
	public void setSize(int size) {
		heapSize = size;
	}

	/**
	 * Indicates whether or not the heap is empty
	 * 
	 * @return true if there are no elements in heap, else false
	 */
	public boolean isEmpty() {
		return (heapSize == 0);
	}

	/**
	 * Gets the parent node's index
	 * 
	 * @param i: index of current node 
	 * @return index/location of parent in heap
	 */
	public int parent(int i) {
		return (i/2);
	}

	/**
	 * Gets the left child node's index
	 * 
	 * @param i: index of current node 
	 * @return index/location of left child in heap
	 */
	public int leftChild(int i) {
		return (2 * i);
	}

	/**
	 * Gets the right child node's index
	 * 
	 * @param i: index of current node 
	 * @return index/location of right child in heap
	 */
	public int rightChild(int i) {
		return (2 * i  + 1);
	}

	/**
	 * Gets the size of the heap (different than capacity)
	 * 
	 * @return the number of elements inside the heap
	 */
	public int size() {
		return heapSize;
	}

	/**
	 * Gets the capacity of the heap
	 * 
	 * @return the capacity of the heap
	 */
	public int capacity() {
		return capacity;
	}

	/**
	 * Helper method for testing purposes that prints out the contents of the heap
	 * (as they are ordered in internalArray)
	 */
	public void printHeap() {
		for(int i=0; i<internalArray.length; i++) {
			System.out.println("Index" + i + ": " + internalArray[i]);
		}
	}
	
	public Comparable[] getInternalArray() {
		return internalArray;
	}

}
