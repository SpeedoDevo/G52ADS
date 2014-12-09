// DRAFT

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 
 * @author Warren Jackson
 *
 * Non-exhaustive test script for testing the implementation of the heap
 *
 * Will need to be modified to test Heap3 and Heap4
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeapTest
{
	/**
	 * PriorityQueue.toArray() does not include the initial '0'
	 * and should be removed from the expected's arrays! 
	 */
	@Test
	public void testInsertion()
	{
		PQ_IntFace heap = new HeapD(15,2);
		int[] values = new int[]{10,11,3,4,14,0,1,2,5,12,13,6,7,8,9};
		
		for(int i = 0; i < values.length; i++)
		{
			heap.insert(values[i]);
		}
		
		//extra '0' at beginning for padding of representation
		int[] expected = new int[]{0,0,2,1,4,12,6,3,11,5,14,13,10,7,8,9};
		int[] actual = heap.getHeapArrayRef();
	
		System.out.println(Arrays.toString(actual) + "\n\n" + heap);

		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testRemoveOnce()
	{
		PQ_IntFace heap = new Heap2(15);
		int[] values = new int[]{10,11,3,4,14,0,1,2,5,12,13,6,7,8,9};
		
		for(int i = 0; i < values.length; i++)
		{
			heap.insert(values[i]);
		}
		
		
		//remove once
		heap.removeMin();

		int[] temp = heap.getHeapArrayRef();
		int[] expected = new int[]{0,1,2,3,4,12,6,8,11,5,14,13,10,7,9};
		//don't care what is after right most leaf
		int[] actual = new int[heap.size() + 1];
		System.arraycopy(temp, 0, actual, 0, heap.size() + 1);
		
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void heapSort()
	{
		PQ_IntFace heap = new Heap2(1024);
		Random random = new Random(5330);
		int[] values = new int[1024];
		for(int i = 0; i < 1024; i++)
		{
			int rand = random.nextInt(9999);
			values[i] = rand;
			heap.insert(rand);
		}
		
		Arrays.sort(values);
		
		int[] expected = values;
		int[] actual = new int[1024];
		for(int i = 0; i < 1024; i++)
		{
			actual[i] = heap.removeMin();
		}
		
		Assert.assertArrayEquals(expected, actual);
	}
	
	/*
	 * Student to add more tests here...
	 */

	@Test
	public void testInsertion3()
	{
		PQ_IntFace heap = new Heap3(15);
		int[] values = new int[]{10,11,3,4,14,0,1,2,5,12,13,6,7,8,9};
		
		for(int i = 0; i < values.length; i++)
		{
			heap.insert(values[i]);
		}
		
		//extra '0' at beginning for padding of representation
		int[] expected = new int[]{0,0,1,2,4,8,11,3,10,5,12,13,6,7,14,9};
		int[] actual = heap.getHeapArrayRef();

		System.out.println(Arrays.toString(actual) + "\n\n" + heap);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testRemoveOnce3()
	{
		PQ_IntFace heap = new Heap3(15);
		int[] values = new int[]{10,11,3,4,14,0,1,2,5,12,13,6,7,8,9};
		
		for(int i = 0; i < values.length; i++)
		{
			heap.insert(values[i]);
		}
		
		
		//remove once
		heap.removeMin();

		int[] temp = heap.getHeapArrayRef();
		int[] expected = new int[]{0,1,3,2,4,8,11,9,10,5,12,13,6,7,14};
		//don't care what is after right most leaf
		int[] actual = new int[heap.size() + 1];
		System.arraycopy(temp, 0, actual, 0, heap.size() + 1);
		
		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void heapSort3()
	{
		PQ_IntFace heap = new Heap3(1024);
		Random random = new Random(new Date().getTime());
		int[] values = new int[1024];
		for(int i = 0; i < 1024; i++)
		{
			int rand = random.nextInt(9999);
			values[i] = rand;
			heap.insert(rand);
		}
		
		Arrays.sort(values);
		
		int[] expected = values;
		int[] actual = new int[1024];
		for(int i = 0; i < 1024; i++)
		{
			actual[i] = heap.removeMin();
		}
		
		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testInsertion4()
	{
		PQ_IntFace heap = new Heap4(15);
		int[] values = new int[]{10,11,3,4,14,0,1,2,5,12,13,6,7,8,9};
		
		for(int i = 0; i < values.length; i++)
		{
			heap.insert(values[i]);
		}

		//extra '0' at beginning for padding of representation
		int[] expected = new int[]{0,0,1,6,4,14,11,3,2,5,12,13,10,7,8,9};
		int[] actual = heap.getHeapArrayRef();
	
		System.out.println(Arrays.toString(actual) + "\n\n" + heap);

		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void testRemoveOnce4()
	{
		PQ_IntFace heap = new Heap4(15);
		int[] values = new int[]{10,11,3,4,14,0,1,2,5,12,13,6,7,8,9};
		
		for(int i = 0; i < values.length; i++)
		{
			heap.insert(values[i]);
		}
		
		
		//remove once
		heap.removeMin();

		int[] temp = heap.getHeapArrayRef();
		int[] expected = new int[]{0,1,2,6,4,14,11,3,9,5,12,13,10,7,8};
		//don't care what is after right most leaf
		int[] actual = new int[heap.size() + 1];
		System.arraycopy(temp, 0, actual, 0, heap.size() + 1);
		
		Assert.assertArrayEquals(expected, actual);
	}

	@Test
	public void heapSort4()
	{
		PQ_IntFace heap = new Heap4(1024);
		Random random = new Random(new Date().getTime());
		int[] values = new int[1024];
		for(int i = 0; i < 1024; i++)
		{
			int rand = random.nextInt(9999);
			values[i] = rand;
			heap.insert(rand);
		}
		
		Arrays.sort(values);
		
		int[] expected = values;
		int[] actual = new int[1024];
		for(int i = 0; i < 1024; i++)
		{
			actual[i] = heap.removeMin();
		}
		
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void xheapSortSort()
	{
		int size = 10;
		int[] values = new int[size];
		for (int i = 0 ; i < size ; i++ ) { values[i] = size-1-i; }
		Sorts.heapSort(values,new HeapD(1,2));
		boolean err = false;
		for (int i = 0 ; i < size ; i++ ) { // CHECK VALUES WERE CORRECTLY SORTED!
			if (values[i] != i) {
				System.out.println("ERROR i=" + i + " values[i]= " + values[i]);
				err = true;
			}
		}

		
		
//		PQ_IntFace heap = new Heap4(1024);
//		Random random = new Random(new Date().getTime());
//		int[] values = new int[1024];
//		for(int i = 0; i < 1024; i++)
//		{
//			int rand = random.nextInt(9999);
//			values[i] = rand;
//			heap.insert(rand);
//		}
//		
//		Arrays.sort(values);
//		
//		int[] expected = values;
//		int[] actual = new int[1024];
//		for(int i = 0; i < 1024; i++)
//		{
//			actual[i] = heap.removeMin();
//		}
//		
		Assert.assertTrue(!err);
	}
	


}
