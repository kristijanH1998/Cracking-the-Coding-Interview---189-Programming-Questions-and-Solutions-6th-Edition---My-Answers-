package sortingAndSearching;
import java.util.*;
import java.io.*;
import java.time.*;

class Cell{
	int row;
	int col;
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
}

//Listy data structure used for 10.4 Sorted Search, no Size problem
class Listy{
	int[] array = {};
	int lastIndex = 0;
	int size;
	public Listy(int size) {
		this.array = new int[size];
		this.size = size;
	}
	public void add(int x) {
		this.array[this.lastIndex++] = x; 
	}
	public int elementAt(int index) {
		if((index >= this.size) || (index < 0)) {
			return -1;
		}
		return this.array[index];
	}
}
public class SortingAndSearching {
	//10.1; assumption: both A and B are initially sorted
	public static void sortedMergeHelper() {
		int[] A = new int[8];
		A[0] = 3;
		A[1] = 3;
		A[2] = 3;
		A[3] = 3;
		A[4] = 3;
		int[] B = {1,2,3};
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
	//10.2 Group Anagrams
	public static void groupAnagramsHelper() {
		String[] ar = {"abcd", "rtou", "dcba", "qwer", "opui", "werq", "112p", "opui", "ipou", "ioup"};
		groupAnagrams(ar);
		for(String s: ar) {
			System.out.println(s);
		}
	}
	public static void swap(String[] ar, int i, int j) {
		String temp = ar[i];
		ar[i] = ar[j];
		ar[j] = temp;
	}
	public static boolean isAnagram(String str1, String str2) {
		if(str1.length() != str2.length()) {return false;}
		HashMap<Character,Integer> mapFirst = new HashMap<Character, Integer>();
		HashMap<Character,Integer> mapSecond = new HashMap<Character, Integer>();
		for(int i = 0; i < str1.length(); i++) {
			if(!mapFirst.containsKey(str1.charAt(i))) {
				mapFirst.put(str1.charAt(i), 1);
			} else {
				mapFirst.put(str1.charAt(i), mapFirst.get(str1.charAt(i)) + 1);
			}
		}
		for(int j = 0; j < str2.length(); j++) {
			if(!mapSecond.containsKey(str2.charAt(j))) {
				mapSecond.put(str2.charAt(j), 1);
			} else {
				mapSecond.put(str2.charAt(j), mapSecond.get(str2.charAt(j)) + 1);
			}
		}
		if(mapFirst.equals(mapSecond)) {
			return true;
		}
		return false;
	}
	public static String[] groupAnagrams(String[] ar) {
		for(int i = 0; i < ar.length; i++) {
			int groupAt = i + 1;
			for(int j = i + 1; j < ar.length; j++) {
				if(isAnagram(ar[j], ar[i])) {
					swap(ar, groupAt, j);
					groupAt++;
				}
			}
		}
		return ar;
	}
	//10.3 Search in Rotated Array
	public static int searchRotatedHelper() {
		int[] ar = {6,7,8,1,2,3,4,5};
		return(searchRotated(ar, 2));
	}
	public static int searchRotated(int[] ar, int x) {
		int rotpnt = 0;
		for(int i = 0; i < ar.length - 1; i++) {
			if(ar[i + 1] < ar[i]) {
				rotpnt = i + 1;
				break;
			}
		}
		int low;
		int high;
		if(rotpnt != 0) {
			if((x >= ar[rotpnt]) && (x < ar[ar.length - 1])) {
				low = rotpnt;
				high = ar.length - 1;
			} else {
				low = 0;
				high = rotpnt - 1;
			}
		} else {
			low = 0;
			high = ar.length - 1;
		}
		int mid;
		while(low <= high) {
			mid = (low + high) / 2;
			if(ar[mid] < x) {
				low = mid + 1;
			} else if(ar[mid] > x) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	//10.4 Sorted Search, no Size
	public static int sortedSearchNoSizeHelper() {
		Listy listy = new Listy(20000);
		for(int i = 1; i <= 20000; i++) {
			listy.add(i);
		}
		return sortedSearchNoSize(listy, 17882);
	}
	public static int sortedSearchNoSize(Listy listy, int x) {
		if(listy.elementAt(0) == -1) {
			System.out.println("Error. Listy size is 0.");
			return -1;
		}
		int exp = 1;
		int bound = exp;
		while(!(listy.elementAt(bound) == -1) ) {
			bound = (int) Math.pow(2, exp);
			exp++;
		}
		for(int i = 0; i < bound; i++) {
			if(listy.elementAt(i) == x) {
				return i;
			}
		}
		return -1;
	}
	//10.5 Sparse Search solution
	public static int sparseSearchHelper() {
		String[] strAr = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		return sparseSearch(strAr, "at");
	}
	public static int sparseSearch(String[] strAr, String x) {
		ArrayList<String> strArAux = new ArrayList<String>();
		HashMap<String, Integer> emptysBeforeStr = new HashMap<String, Integer>();
		int emptyStrings = 0;
		for(int i = 0; i < strAr.length; i++) {
			if(!strAr[i].equals("")) {
				strArAux.add(strAr[i]);
				emptysBeforeStr.put(strAr[i], emptyStrings);
			} else {
				emptyStrings++;
			}
		}
		int low = 0;
		int high = strArAux.size() - 1;
		int mid;
		while(low <= high) {
			mid = (low + high) / 2;
			if((strArAux.get(mid)).compareTo(x) < 0) {
				low = mid + 1;
			}else if((strArAux.get(mid)).compareTo(x) > 0) {
				high = mid - 1;
			}else {
				return (mid + emptysBeforeStr.get(strArAux.get(mid)));
			}
		}
		return -1;
	}
	//10.8 Find Duplicates
	public static void findDuplicatesHelper(){
		int numberOfInts = 32000;
		int numberOfDups = 4;
		int[] ar = new int[numberOfInts + numberOfDups];
		for(int i = 0; i < numberOfInts; i++) {
			ar[i] = i;
		}
		//We will add 4 duplicates at the end of the array. These should be stored in the result and output.
		ar[numberOfInts] = 4;
		ar[numberOfInts + 1] = 113;
		ar[numberOfInts + 2] = 11656;
		ar[numberOfInts + 3] = 567;
		for(int dup: findDuplicates(ar, numberOfInts, numberOfDups)) {
			System.out.print(dup + " ");
		};
	}
	public static ArrayList<Integer> findDuplicates(int[] ar, int numberOfInts, int numberOfDups){
		ArrayList<Integer> dups = new ArrayList<Integer>();
		//We have only 4 kilobytes of memory available, and a byte array of size 32000/8=4000 bytes should work.
		byte[] bitvector = new byte [(int) numberOfInts / 8];
		
		for(int i = 0; i < (numberOfInts + numberOfDups); i++) {
			int n = ar[i];
			bitvector[n / 8] ^= (1 << (n % 8));
		}
		for(int i = 0; i < bitvector.length; i++) {
			for(int j = 0; j < 8; j++) {
				if((bitvector[i] & (1 << j)) == 0) {
					dups.add(i * 8 + j);
				}
			}
		}
		return dups;
	}
	//10.9 Sorted Matrix Search naive solution
	public static void sortedMatrixSearchNaive(int element) {
		Instant start = Instant.now();
		int[][] matrix = {{1,7,11,13,23,33,45},
				  {2,8,12,16,24,60,100}};
		outerloop:
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == element) {
					System.out.println("Row: " + i + ", Col: " + j);
					break outerloop;
				}
			}
		}
		Instant finish = Instant.now();
		System.out.println(Duration.between(start,finish).toNanos());
	}
	//10.9 Sorted Matrix Search optimized solution
	public static void sortedMatrixSearchHelper(int element) {
		Instant start = Instant.now();
		int[][] matrix = {{1,7,11,13,23,33,45},
						  {2,8,12,16,24,60,100}};
		Cell cell = sortedMatrixSearch(matrix, element);
		if(cell != null) {
			System.out.println("Number " + element + " is at row " + cell.getRow() + " and column "
					+ cell.getCol() + ".");
		}
		Instant finish = Instant.now();
		System.out.println(Duration.between(start,finish).toNanos());
	}
	public static Cell searchColumnUp(int row, int col, int[][] matrix, int element) {
		while(row - 1 >= 0 && matrix[row-1][col] >= element) {
			row--;
			if(matrix[row][col] == element) {
				Cell cell = new Cell(row, col);
				return cell;
			}
		}
		return null; //element not found above matrix[row][col]
	}
	public static Cell searchRowLeft(int row, int col, int[][] matrix, int element) {
		while(col - 1 >= 0 && matrix[row][col-1] >= element) {
			col--;
			if(matrix[row][col] == element) {
				Cell cell = new Cell(row, col);
				return cell;
			}
		}
		return null; //element not found to the left of matrix[row][col]
	}
	public static Cell sortedMatrixSearch(int[][] matrix, int element) {
		int row = 0;
		int col = 0;
		boolean searchColumnsUpwards = true;
		boolean searchRowsToLeft = true;
		while(row < matrix.length && col < matrix[0].length && matrix[row][col] != element) {
			if(searchColumnsUpwards) {
				Cell cell = searchColumnUp(row,col,matrix,element); 
				if(cell != null) {	
					return cell;
				}
			}
			if(searchRowsToLeft) {
				Cell cell = searchRowLeft(row,col,matrix,element); 
				if(cell != null) {	
					return cell;
				}
			}
			if(row+1 < matrix.length && col+1 < matrix[0].length) {
				row++;
				col++;
			} else if (row+1 == matrix.length) {
				searchRowsToLeft = false;
				col++;
			} else if (col+1 == matrix[0].length) {
				searchColumnsUpwards = false;
				row++;
			} 
		}
		if(row < matrix.length && col < matrix[0].length && matrix[row][col] == element) {
			Cell cell = new Cell(row, col);
			return cell;
		} else {
			System.out.println("Error: the given number is not in the matrix.");
			return null; //The given number was not found in the matrix.
		}
	}
	public static void main(String[] args) {
	//	sortedMergeHelper();
	//	groupAnagramsHelper();
	//	System.out.println(searchRotatedHelper());
	//	System.out.println(sortedSearchNoSizeHelper());
	//	System.out.println(sparseSearchHelper());
	//	findDuplicatesHelper();
		sortedMatrixSearchHelper(100);
		sortedMatrixSearchNaive(100);
	}
}