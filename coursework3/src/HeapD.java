import java.util.ArrayList;
import java.util.List;


public class HeapD implements PQ_IntFace {

	//error codes
	public static final int SUCCESS              =  0;
	public static final int NEGATIVE_VALUE_ERROR = -1;
	public static final int ARRAY_EMPTY_ERROR    = -1;
	public static final int CAPACITY_TOO_SMALL   = -1;
	public static final int ARRAY_FULL_ERROR     = -2;
	public static final int NOT_ENOUGH_MEMORY    = -2;
	
	protected static final int INIT_CAPACITY = 10;
	private static final int ROOT_INDEX      =  1;
	private static final int NUM_CHILD       =  2;

	private int[] heap;
	private int size = 0;
	private int d;
	private boolean shiftable = false;
	private int shift = 0;

	public HeapD() {
		this(INIT_CAPACITY, NUM_CHILD);
	}
	
	/** Sets the initial maximum capacity to the size specified by <code> size </code>
	 * @param size The initial maximum size to set the heap
	 */
	public HeapD(int size, int d) {
		//set size to size + 1 since head is stored at index 1 rather than index 0
		if (d <= 1) {
			System.out.println("d must be greater than 1, defaulting to binary heap.");
			this.d = NUM_CHILD;
		} else {
			this.d = d;
		}
		this.heap = new int[size + ROOT_INDEX];
	}


	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int setCapacity(int newCapacity) {
		int returnCode;
		if(size + ROOT_INDEX <= newCapacity) {
			try {
				int[] newHeap = new int[newCapacity + ROOT_INDEX];
				System.arraycopy(heap, 0, newHeap, 0, size + ROOT_INDEX);
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

	public int insert(int value) {
		if ( value < 0 ) { return NEGATIVE_VALUE_ERROR; }
		if ( heap.length - ROOT_INDEX <= size) { return ARRAY_FULL_ERROR; }

		// -------------- HEAP D: INSERT AND UPHEAP -- START ----------

		this.heap[size + ROOT_INDEX] = value;
		int currentNode = ++size;
		while(ROOT_INDEX < currentNode) {
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

		// -------------- HEAP D: INSERT AND UPHEAP -- END ----------
		return SUCCESS;
	}

	public int removeMin() {
		if ( size <= 0 )  { return ARRAY_EMPTY_ERROR; }
		//record min value, to be returned at end
		int min = viewMin();
		// -------------- HEAP D: REMOVE AND DOWNHEAP -- START ----------
		
		heap[ROOT_INDEX] = heap[ROOT_INDEX + --size]; // effectively removes 'm' the entry that was swapped to the root
		
		int currentNode = ROOT_INDEX; // starting position
		while(kthChild(currentNode, 1) <= size) {
			//get the smallest child
			int smallestChild = minChild(currentNode);
			
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
		
		// -------------- HEAP D: REMOVE AND DOWNHEAP -- END ----------
		return min;
	}


	public int viewMin() {
		return (0 < size) ? heap[ROOT_INDEX] : ARRAY_EMPTY_ERROR;
	}

	public int[] getHeapArrayRef() {
		return heap;
	}

	public int getLastIndex() {
		return size;
	}

	private int getParentIndex(int i) {
		return (i - 1 - ROOT_INDEX) / d + ROOT_INDEX;
	}

	private int kthChild(int i, int k) {
		return d * (i - ROOT_INDEX) + k + ROOT_INDEX;
	}

	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < size + ROOT_INDEX)) {
			if (heap[pos] < heap[bestChild])
				bestChild = pos;
			pos = kthChild(ind, ++k);
		}
		return bestChild;
	}

	public String toString() {
		return toString("",true,ROOT_INDEX);
	}

	private String toString(String prefix,boolean isTail, int index) {
		List<Integer> children = new ArrayList<Integer>();
		for(int i=1; i<=d;i++) {
			int childIndex = kthChild(index,i);
			if(childIndex < size + ROOT_INDEX) {
				children.add(childIndex);
			} else {
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(prefix + (isTail ? "\\--- " : "|--- ") + heap[index] + " (i: " + index + ")" + "\n");
		for (int i = 0; i < children.size() - 1; i++) {
			sb.append(toString(prefix + (isTail ? "     " : "|    "), false, kthChild(index,i+1)));
		}
		if (0 < children.size()) {
			sb.append(toString(prefix + (isTail ? "     " : "|    "), true, kthChild(index,children.size())));
		} else if (children.size() == 0 && isTail) {
			sb.append(prefix + (isTail ? "     " : "|    ") + "\n");
		}
		return sb.toString();
	}

}
