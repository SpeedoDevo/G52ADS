import java.util.Arrays;


/** DRAFT
 * 
 * Class for sorting methods. Currently only implements heapsort using some implementation of PQ_IntFace (in this case BinaryHeap).
 *
 * @author Warren Jackson
 *
 */
public class Sorts
{
	/**
	 * 
	 * Sorts the array into non-decreasing elements using heapsort. (note: access this method using Sorts.heapSort(array);)
	 * @param array The array to sort
	 */
	public static void heap2Sort(int[] array)
	{
		PQ_IntFace pq = new Heap2(array.length);
		for(int value : array)
		{
			pq.insert(value);
		}
		
		for(int i = 0; i < array.length; i++)
		{
			array[i] = pq.removeMin();
		}
	}

	/**
	 * 
	 * Sorts the array into non-decreasing elements using heapsort. (note: access this method using Sorts.heapSort(array);)
	 * @param array The array to sort
	 */
	public static void heap3Sort(int[] array)
	{
		PQ_IntFace pq = new Heap2(array.length);
		for(int value : array)
		{
			pq.insert(value);
		}
		
		for(int i = 0; i < array.length; i++)
		{
			array[i] = pq.removeMin();
		}
	}
	/**
	 * 
	 * Sorts the array into non-decreasing elements using heapsort. (note: access this method using Sorts.heapSort(array);)
	 * @param array The array to sort
	 */
	public static void heap4Sort(int[] array)
	{
		PQ_IntFace pq = new Heap2(array.length);
		for(int value : array)
		{
			pq.insert(value);
		}
		
		for(int i = 0; i < array.length; i++)
		{
			array[i] = pq.removeMin();
		}
	}

	public static void heapSort(int[] array, PQ_IntFace pq){
		pq.setCapacity(array.length);
		for(int value : array)
		{
			pq.insert(value);
		}
		
		for(int i = 0; i < array.length; i++)
		{
			array[i] = pq.removeMin();
		}
		
	}
}
