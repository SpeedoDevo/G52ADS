/** G52ADS 2014-15 C/W ONE  ------ DRAFT 2014-10-02 -- MIGHT CHANGE A LITTLE!! ---------
    Title:  Analysis of Algorithms
	Author: Andrew Parkes
	
	Parts of Main should be modified as you think necessary. 
	Submit the version of this code that you use for the main graph.
*/
import java.util.Arrays;
import java.util.Random;

public class Main {
	
	/// used for counting primitive operations
	static int c;
	static Random rn;
	static int smallMaxVal;
	static int bigMaxVal;
	static boolean rand;

	/* Main method:  runs the experiments and prints results.
	
		You can (and should) change this as needed.
		
		E.g. You should change the maxN and maxRuns to values of your choice.
		You may well also want to do more than just report the n,c from each run.
		e.g. to collect and print more 'statistics' such as worst, best, average at each value of n.
	*/
	public static void main(String[] a){
		
		int maxN = 100;   // CHANGE AS NEEDED
		int numRuns = 20;  // CHANGE AS NEEDED
		
		
		rn = new Random();

		for (int n = 1 ; n <= maxN ; n++ ) {
			int[] A = new int[n];
			int[] cls = new int[numRuns];
			int[] ces = new int[numRuns];
			for (int run = 0 ; run < numRuns ; run++ ) {
				// initialise A with randomised values
				randInit(A);
				//actualRandInit(A);
				// reset the counter, c, run f, and report the count
				c=0;
				//long start = System.nanoTime();
				/*int out = */p(A);
				//long end = System.nanoTime();
				//long time = end-start;
				//if (time >= 8000){
				//	time = 8000;
				//}
				if ( rand ) {
					ces[run] = c;
					//System.out.println(n + ";;" + c + ";" + bigMaxVal /*+ ";" + Arrays.toString(A)*/);
				} else {
					cls[run] = c;
					//System.out.println(n + ";" + c + ";;" + bigMaxVal /*+ ";" + Arrays.toString(A)*/);
				}
				
				// KEEP EXTRA STATISTICS AS NEEDED
			}
			System.out.print(n + ";");
			for(int i = 0; i<numRuns; i++){
				if (cls[i] == 0) {
					System.out.print(";");
				} else {
					System.out.print(cls[i] + ";");
				}				
			}
			System.out.print(";;;");
			for(int i = 0; i<numRuns; i++){
				if (ces[i] == 0) {
					System.out.print(";");
				} else {
					System.out.print(ces[i] + ";");
				}			
			}
			System.out.println();
			
			// KEEP EXTRA STATISTICS AS NEEDED
		}
		// PROCESS/PRINT EXTRA STATISTICS AS NEEDED
	}
	
	/* 	This is the function 'p' that needs to be analysed.
		It works on an integer array, 'A' with n elements.
		You can think of it as a piece of 'legacy code' you are given and it is suspected to 
		be causing trouble, such as making the application program to be going slow. 
		You need to analyse its scaling behaviour and make other comments.
		The "c += " fragments have been added to help, but are not part of the code itself.

		NOTE: Do _NOT_ take this as an example of how to write good code!
		Parts of it may be deliberately poor to illustrate useful points.

		DO NOT CHANGE THIS FUNCTION EXCEPT THE increments to the counter on the r.h.s. !!!
	*/
	static int p(int[] A) {
		int n = A.length;                   c += 2;
	    int[] B = new int[n];               c += n+1;

		int sum = 0;                        c += 1;
	    for (int p=0; p < n; p++) {         c += 6 ;
			int k = A[p];                   c += 3 ;
			int m = 0;                      c += 1 ;
			while ( k >= 2 ) {              c += 2 ;
				k /= 2;                     c += 3 ;
				m++;                        c += 3 ;
			}
		                                    c += 3; // for 'anything else'
			B[p] = m;                       c += 3;
			sum += m;                       c += 4;
		}
		                                    c += 3; // for 'anything else'
		A = B;                              c += 2;
											c += 1; // for the 'return'
		return sum;
	}

	
	/* Used to initialise the array A.
	   You are expected to report results using version exactly as below.
	   DO NOT CHANGE THIS FUNCTION!!!
	   (Unless you know what you are doing, e.g. for some "side experiments" 
	    used to help you understand.)
	*/
	static void randInit(int[] A) {
			int n = A.length;
			smallMaxVal = n;
			bigMaxVal=1;
			for (int p = 1 ; p < n/4 ; p++ ) {
				if ( bigMaxVal >= (1 << 30) ) {
					System.out.println("## bigMaxVal too BIG!!! with n= " + n + " Aborting!!!");
					System.exit(100);
				}
				bigMaxVal *= 2;
			}
			rand = (n%3 == 0) && (rn.nextInt(n) <= 10);
			if ( rand ) {
				for (int i = 0 ; i < n ; i++ ) {
					A[i] = n/4 + rn.nextInt( bigMaxVal );
				}
			}
			else {
				for (int i = 0 ; i < n ; i++ ) {
					A[i] = n + rn.nextInt( smallMaxVal ); 
				}
			}
		}
	static void actualRandInit(int[] A){
		int n = A.length;
		for (int i = 0 ; i < n ; i++ ) {
			A[i] = rn.nextInt( 1000 );
		}
	}
}
