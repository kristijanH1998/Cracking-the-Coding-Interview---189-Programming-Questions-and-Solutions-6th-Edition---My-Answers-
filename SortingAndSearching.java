package sortingAndSearching;
import java.util.*;

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
	public static void main(String[] args) {
	//	sortedMergeHelper();
		groupAnagramsHelper();
	}
}