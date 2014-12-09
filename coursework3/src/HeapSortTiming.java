import java.util.Arrays;

/** DRAFT for illustration -- should be modified as needed
 */
public class HeapSortTiming
{
	/** Sorts the array into non-decreasing elements using heapsort. (note: access this method using Sorts.heapSort(array);)
	 *  @param array The array to sort
	 */
	 
	// public static final int size = 1000; // 20000000 is about the max feasible
	
	public static void main(String[] args) {
	
		int numRuns = 100;
		for (int size = 1000 ; size <= 100000 ; size *= 1.1 ) {
			int to = 4;
			long[] musSum = new long[to-1];
			long[] min = new long[to-1];
			long[] max = new long[to-1];
			for (int d = 2; d <= to; d++) {
				for (int run = 0 ; run < numRuns ; run++ ) {
					int[] values = new int[size];
					for (int i = 0 ; i < size ; i++ ) { values[i] = size-1-i; }
					long startTime = System.nanoTime();
					if ( d == 2 ) {
						Sorts.heapSort(values,new Heap2(1));
					} else {
						Sorts.heapSort(values,new HeapD(1,d));
					}
					long mus = (System.nanoTime() - startTime)/1000; // time in microseconds
					musSum[d-2] += mus;
					if (mus < min[d-2] || run == 0)
						min[d-2] = mus;
					if (max[d-2] < mus)
						max[d-2] = mus;
					// System.out.println(size + " " + mus);
					for (int i = 0 ; i < size ; i++ ) { // CHECK VALUES WERE CORRECTLY SORTED!
						if ( run == 0 && values[i] != i ) {
							System.out.println("ERROR i=" + i + " values[i]= " + values[i] + ", d: " + d ); 
						}
					}
				}
			}
			for (int i = 0; i < musSum.length; i++) {
				musSum[i] /= 100;
			}
			System.out.println(size + " " + Arrays.toString(musSum) + " " + Arrays.toString(min) + " " + Arrays.toString(max));
		}
	}
}
