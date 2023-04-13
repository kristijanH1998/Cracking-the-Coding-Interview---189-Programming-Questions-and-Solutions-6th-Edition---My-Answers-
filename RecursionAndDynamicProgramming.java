package recursionAndDynamicProgramming;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Stack;
import java.util.Arrays;

public class RecursionAndDynamicProgramming {
	public static int sum (ArrayList<Integer> list) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++)
		    sum += list.get(i);
		return sum;
	}
	//recursive tripleStep(problem 8.1) without memoization
	public static void tripleStep(int start, int n, ArrayList<ArrayList<Integer>> possiblePaths, ArrayList<Integer> path){
		if(start + 1 <= n) {
			start+=1;
			path.add(1);
			tripleStep(start, n, possiblePaths, path);
			start -= 1;
		}
		if(start + 2 <= n) {
			start+=2;
			path.add(2);
			tripleStep(start, n, possiblePaths, path);
			start -= 2;
		}
		if(start + 3 <= n) {
			start+=3;
			path.add(3);
			tripleStep(start, n, possiblePaths, path);
			start -= 3;
		}
		if(sum(path)==n && !possiblePaths.contains(path)) {
	        @SuppressWarnings("unchecked")
			ArrayList<Integer> temp = (ArrayList<Integer>)path.clone();
			possiblePaths.add(temp);
		}
		if(!path.isEmpty()) {
			path.remove(path.size() - 1);
		}
	}
	//creates a grid for RobotInGrid problem (8.2). All cells with a value 0 are inaccessible cells, 1s are accessible cells, and -1 
	//denotes cells that have already been visited.
	public static int[][] makeGrid(int r, int c){
		int[][] ar = new int[r][c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				ar[i][j] = 0;
			}
		}
		ar[0][0] = 1;
		ar[1][0] = 1;
		ar[2][0] = 1;
		ar[2][1] = 1;
		ar[3][1] = 1;
		ar[3][2] = 1;
		ar[3][2] = 1;
		ar[3][3] = 1;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(ar[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		return ar;
	}
	//The robot starts at the upper leftmost cell in the grid (grid[0][0]), so we set that cell to -1 first to 
	//signify that it has been visited. The goal is to reach the rightmost bottom cell (grid[3][3])
	public static void RobotInAGrid(ArrayList<Integer> path, int[][] grid, int row, int col) {
		path.add(grid[row][col]);
		String output = (row == (grid.length-1) && col == (grid[0].length - 1)) ? "(" + row + ", " + col + ")"
				: "(" + row + ", " + col + "), ";
		System.out.print(output);
		if(row == (grid.length-1) && col == (grid[0].length - 1)) {
			return;
		}
		grid[row][col] = -1;
		if(col + 1 < grid[0].length && grid[row][col+1] != 0 && grid[row][col+1] != -1) {
			RobotInAGrid(path, grid, row, ++col);
		} else if (row + 1 < grid.length && grid[row+1][col] != 0 && grid[row+1][col] != -1) {
			RobotInAGrid(path, grid, ++row, col);
		}
	}
	
	public static boolean sorted(int[] ar) {
		boolean result = true;
		for(int i = 0; i < ar.length - 1; i++) {
			result = (ar[i] <= ar[i + 1]) ? true : false;
			if(result == false) {
				return result;
			}
		}
		return result;
	}
	//returns the magical index in the array ar. Magical index i is when ar[i] = i.
	public static void magicIndex(int[] ar, int start, int end) {
		int mid = start + (end - start) / 2;
		if((start >= end) && (ar[mid] != mid)) {
			System.out.print("No magical index.");
			return;
		}
		if(ar[mid] == mid) {
			System.out.println("The magical index is: " + mid);
			return;
		} else {
			if(ar[mid] > mid) {
				magicIndex(ar, start, mid-1);
			} else {
				if(ar[mid] < mid) {
					magicIndex(ar, mid+1, end);
				}
			}
		}
		return;
	}
	public static ArrayList<Integer> toArray(Set<Integer> set){
		ArrayList<Integer> ar = new ArrayList<Integer>();
		for(int element: set) {
			ar.add(element);
		}
		return ar;
	}
	public static ArrayList<Set<Integer>> powerSet(Set<Integer> set, ArrayList<Set<Integer>> powerSets) {
		ArrayList<Integer> elements = toArray(set);
		for(int j = 0; j < elements.size(); j++) {
			Set<Integer> s = new HashSet<Integer>();		
			for(int i = j; i < elements.size(); i++) {
				s.add(elements.get(i));
			}
			powerSets.add(s);
		}
		set.remove(elements.get(elements.size() - 1));
		if(set.size() == 1) {
			powerSets.add(set);
	        Set<Integer> emptySet = Collections.<Integer>emptySet();  
			powerSets.add(emptySet);
			return null;
		} else {
			powerSet(set, powerSets);
		}
		return powerSets;
	}
	//problem 8.6 Towers of Hanoi
	public static Stack<Integer> hanoi(int n, Stack<Integer> current, Stack<Integer> aux, Stack<Integer> goal) {
		if(n == 1) {
			goal.push(current.pop());
			return goal;
		}
		hanoi(n-1,current, goal, aux);
		goal.push(current.pop());
		hanoi(n-1,aux, current, goal);
		return goal;	
	}
	//problem 8.7 Permutations without Dups. Prints all possible permutations of a string str.
	public static void findPermutations(String str){
		ArrayList<String> permutations = new ArrayList<String>();
		String fixed = str;
		String unfixed = "";
		int lastFixed = fixed.length()-1;
		permutations = permutations(fixed, unfixed, permutations, lastFixed);
		System.out.print("List of permutations of string " + str + ": ");
		System.out.println(permutations);
		System.out.println("Number of permutations of string " + str + ": " + permutations.size());
	}
	public static ArrayList<String> permutations(String fixed, String unfixed, ArrayList<String> permutations, int lastFixed){
		if(unfixed.length() == 0) {
			permutations.add(fixed + unfixed);
		}
		if(fixed.length() == 1) {
			return permutations;
		}
		unfixed = (fixed.charAt(lastFixed) + unfixed);
		fixed = fixed.substring(0, lastFixed);
		lastFixed--;
		ArrayList<String> newPerms = new ArrayList<String>();
		for(String perm: permutations) {
			int i = lastFixed + 1;
			while(i < perm.length()) {
				String temp = swap(perm, lastFixed, i);
				newPerms.add(temp);
				i++;
			}
		}
		for(String perm: newPerms) {
			permutations.add(perm);
		}
		newPerms.clear();
		permutations(fixed, unfixed, permutations, lastFixed);
		return permutations;
	}
	public static String swap (String str, int i, int j){
		ArrayList<String> list = new ArrayList<>(Arrays.asList(str.split("")));
		String temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		str = String.join("", list);
		return str;
	}
	public static void main(String[] args) {
//		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
//		ArrayList<Integer> path = new ArrayList<Integer>();
//		tripleStep(0, 5, paths, path);
//		for(ArrayList<Integer> p: paths) {
//			System.out.println(p);
//		}
//		int[][] grid = makeGrid(4,4);
//		ArrayList<Integer> path = new ArrayList<Integer>();
//		RobotInAGrid(path, grid, 0, 0);
//		for(int i: path) {
//			System.out.print(i + " ");
//		}
//		int[] ar = {10,10,10,10,10,10,10,10,10,10};
//		if(!sorted(ar)) {
//			System.out.println("Error. Unsorted Array."); 
//			return;
//		} 
//		magicIndex(ar, 0, 9);
//		Set<Integer> set = new HashSet<Integer>();
//		for(int i = 1; i <= 6; i++) {
//			set.add(i);
//		}
//		ArrayList<Set<Integer>> pwSets = new ArrayList<Set<Integer>>();
//		System.out.println(powerSet(set, pwSets));
//		Stack<Integer> tower1 = new Stack<Integer>();
//		Stack<Integer> tower2 = new Stack<Integer>();
//		Stack<Integer> tower3 = new Stack<Integer>();
//		tower1.push(5);
//		tower1.push(4);
//		tower1.push(3);
//		tower1.push(2);
//		tower1.push(1);
//		int numDisks = 5;
//		hanoi(numDisks, tower1, tower2, tower3);
//		System.out.println("Tower 1 is empty: " + tower1.isEmpty());
//		System.out.println("Tower 2 is empty: " + tower2.isEmpty());
//		System.out.print("tower 3 contents: ");
//		while(!tower3.isEmpty()) {
//			System.out.print(tower3.pop() + ", ");
//		}
		String str = "abcd";
		findPermutations(str);
	}
}