

package sortingAndSearching;

public class SortingAndSearching {
	//10.1; assumption: both A and B are initially sorted
	public static void sortedMergeHelper() {
		int[] A = new int[10];
		A[0] = 2;
		A[1] = 4;
		A[2] = 5;
		A[3] = 7;
		A[4] = 8;
		A[5] = 13;
		int[] B = {1,6,7,11};
		for(int i: sortedMerge(A,B)) {
			System.out.print(i + " ");
		}
	}
	public static int[] sortedMerge(int[] A, int[] B) {
		int lastInA = A.length - 1;
		while(lastInA >= 0 && A[lastInA] == 0) {
			lastInA--;
		}
		int temp = lastInA;
		int j = 0;
		while(j < B.length) {
			for(int i = 0; i <= lastInA; i++) {
				if(B[j] <= A[i]) {
					while(lastInA >= i) {
						A[lastInA + 1] = A[lastInA];
						lastInA--;
					}
					A[i] = B[j];
					break;
				}
			}
			lastInA = ++temp;
			j++;
		}	
		return A;
	}
	public static void main(String[] args) {
		sortedMergeHelper();
	}
}
