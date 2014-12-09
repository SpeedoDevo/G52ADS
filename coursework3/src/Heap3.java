/** DRAFT
Implementation of the PQ_IntFace using a Heap3 (3-ary or ternary) represented as an array 
 * @author Warren Jackson and Andrew Parkes
 
 Search for "TODO:" to find the places you need to change.
 
 */
public class Heap3 extends HeapD
{
	/** Default constructor setting the initial maximum capacity to 10
	 */
	public Heap3() {
		super(INIT_CAPACITY,3);
	}
	
	/** Sets the initial maximum capacity to the size specified by <code> size </code>
	 * @param size The initial maximum size to set the heap
	 */
	public Heap3(int size) {
		//set size to size + 1 since head is stored at index 1 rather than index 0
		super(size,3);
	}
}
