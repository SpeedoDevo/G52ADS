/** DRAFT
Implementation of the PQ_IntFace using a Heap2 (binary) represented as an array 
 * @author Warren Jackson and Andrew Parkes
 */
public class Heap2 implements PQ_IntFace
{
	//error codes
	public static final int SUCCESS              =  0;
	public static final int NEGATIVE_VALUE_ERROR = -1;
	public static final int ARRAY_EMPTY_ERROR    = -1;
	public static final int CAPACITY_TOO_SMALL   = -1;
	public static final int ARRAY_FULL_ERROR     = -2;
	public static final int NOT_ENOUGH_MEMORY    = -2;
	
	private static final int INIT_CAPACITY = 10;
	private static final int ROOT_INDEX    =  1;

	private int[] heap;
	private int size = 0;
	
	/** Default constructor setting the initial maximum capacity to 10
	 */
	public Heap2() {
		this(INIT_CAPACITY);
	}
	
	/** Sets the initial maximum capacity to the size specified by <code> size </code>
	 * @param size The initial maximum size to set the heap
	 */
	public Heap2(int size) {
		//set size to size + 1 since head is stored at index 1 rather than index 0
		this.heap = new int[size + 1];
	}

	/** @param value A non-negative integer to insert into the heap
	 * @return A status code indicating the success of the insertion of the value into the heap
	 */
	public int insert(int value) {
		if ( value < 0 ) { return NEGATIVE_VALUE_ERROR; }
		if ( size >= heap.length - 1) { return ARRAY_FULL_ERROR; }

		// -------------- HEAP 2: INSERT AND UPHEAP -- START ----------
		//insert at left most free position of current level 'last'
		this.heap[size + 1] = value;
		size++;
		int currentNode = size;
		while(currentNode > ROOT_INDEX) {
			int parentNode = getParentIndex(currentNode);
			if(heap[currentNode] < heap[parentNode]) {
				int temp = heap[currentNode];  // swap
				heap[currentNode] = heap[parentNode];
				heap[parentNode] = temp;
				currentNode = parentNode; // move to the parent node
			}
			else { //heap is already sorted, and can finish
				break;
			}
		}
		// -------------- HEAP 2: INSERT AND UPHEAP -- END ----------
		return SUCCESS;
	}
	
	/** Returns the smallest value in the heap
	 * @return The value of the smallest node in the heap if successful, otherwise an error code
	 */
	public int viewMin() {
		return (size() > 0) ? heap[ROOT_INDEX] : ARRAY_EMPTY_ERROR;
	}

	/** Removes and returns the smallest value in the heap.
	 * @return The value of the smallest node in the heap if successful, otherwise an error code
	 */
	public int removeMin() {
		if ( size() <= 0 )  { return ARRAY_EMPTY_ERROR; }
		//record min value, to be returned at end
		int min = viewMin();
		// -------------- HEAP 2: REMOVE AND DOWNHEAP -- START ----------
		//swap right-most leaf at bottom level with root
		heap[ROOT_INDEX] = heap[size];
		size--;  // effectively removes 'm' the entry that was swapped to the root
		
		int leftChild;
		int rightChild;
		int smallestChild;
		int currentNode = ROOT_INDEX; // starting position
		while(!isLeafNode(currentNode)) {
			//get the smallest child
			leftChild = getLeftChildIndex(currentNode);
			rightChild = getRightChildIndex(currentNode);
			smallestChild = (heap[leftChild] <= heap[rightChild]) ? leftChild : rightChild;
			
			//if has only one child then must be left child
			smallestChild = (rightChild > size) ? leftChild : smallestChild;
			
			//swap if smaller than current
			if(heap[currentNode] > heap[smallestChild]) {
				int temp = heap[currentNode];
				heap[currentNode] = heap[smallestChild];
				heap[smallestChild] = temp;
				currentNode = smallestChild;
			}
			else { //already sorted and can finish
				break;
			}
		}
		// -------------- HEAP 2: REMOVE AND DOWNHEAP -- END ----------
		return min;
	}

	/** @return The number of values stored in the heap
	 */
	public int size() {
		return size;
	}

	/** @return Whether the heap is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/** @param newCapacity The maximum capacity to set the new heap
	 * @return Status code indicating success or reason for failure
	 */
	public int setCapacity(int newCapacity) {
		int returnCode;
		if(size + 1 <= newCapacity) {
			try {
				int[] newHeap = new int[newCapacity + 1];
				System.arraycopy(heap, 0, newHeap, 0, size + 1);
				heap = newHeap;
				returnCode = SUCCESS;
			}
			catch(OutOfMemoryError e) {
				returnCode = NOT_ENOUGH_MEMORY;
			}
		}
		else {
			returnCode = CAPACITY_TOO_SMALL;
		}
		return returnCode;
	}
	
	/** @return The reference to the array storing the heap
	 */
	public int[] getHeapArrayRef() {
		return this.heap;
	}
	
	/** @return The index of the last node
	 */
	public int getLastIndex() {
		return size();
	}
	
	/** * @param currentIndex The index of the current node
	 * @return The index of the current node's parent
	 */
	private int getParentIndex(int currentIndex) {
		return currentIndex >> 1;
	}
	
	/** @param currentIndex The index of the current node
	 * @return The index of the current node's left child
	 */
	private int getLeftChildIndex(int currentIndex) {
		return currentIndex << 1;
	}
	
	/** @param currentIndex
	 * @return The index of the current node's right child
	 */
	private int getRightChildIndex(int currentIndex) {
		return (currentIndex << 1) + 1;
	}
	
	/** @param currentIndex The index of the current node
	 * @return True if the node relating to currentIndex is a leaf node (i.e. has no children)
	 */
	private boolean isLeafNode(int currentIndex) {
		return size < getLeftChildIndex(currentIndex);
	}
}
