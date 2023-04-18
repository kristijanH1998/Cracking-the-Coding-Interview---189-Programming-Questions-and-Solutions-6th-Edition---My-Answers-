package recursionAndDynamicProgramming;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Stack;
import java.util.HashMap;
import java.util.Iterator;

import recursionAndDynamicProgramming.RecursionAndDynamicProgramming.Coin;

import java.util.Arrays;

class Box{
	String name;
	int height;
	int width;
	int depth;
	public Box(int h, int w, int d, String name) {
		this.name = name;
		this.height = h;
		this.width = w;
		this.depth = d;
	}
	public int getDepth() {
		return this.depth;
	}
	public int getHeight() {
		return this.height;
	}
	public int getWidth() {
		return this.width;
	}
	public String getName() {
		return this.name;
	}
}

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
	//Solution to 8.8 Permutations with Dups. String str may contain duplicate characters, but 
	//the final list of permutations may not contain duplicate strings.
	public static void findPermutationsDups(String str){
		ArrayList<String> permutations = new ArrayList<String>();
		String fixed = str;
		String unfixed = "";
		int lastFixed = fixed.length()-1;
		permutations = permutationsDups(fixed, unfixed, permutations, lastFixed);
		System.out.print("List of permutations of string " + str + ": ");
		System.out.println(permutations);
		System.out.println("Number of permutations of string " + str + ": " + permutations.size());
	}
	public static ArrayList<String> permutationsDups(String fixed, String unfixed, ArrayList<String> permutations, int lastFixed){
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
				if(newPerms.contains(temp) || permutations.contains(temp)) {
					i++;
					continue;
				}
				newPerms.add(temp);
				i++;
			}
		}
		for(String perm: newPerms) {
			permutations.add(perm);
		}
		newPerms.clear();
		permutationsDups(fixed, unfixed, permutations, lastFixed);
		return permutations;
	}
	public static ArrayList<String> parens(int n) {
		ArrayList<String> list = new ArrayList<String>();
		String comb = "";
		parensCombs(true, false, n, 1, 0, list, comb);
		return list;
	}
	public static ArrayList<String> parensCombs(boolean open, boolean closed, int n, int countOpen, int countClosed, 
			ArrayList<String> list, String comb){
		if(open) {
			comb += "(";
		} else {
			comb += ")";
		}
		if((countClosed == countOpen) && ((countOpen + countClosed) == n * 2)) {
			list.add(comb);
			comb = "";
			return list;
		}
		
		if(countOpen < n) {
			parensCombs(true, false, n, countOpen + 1, countClosed, list, comb);
		}
		if(countClosed < countOpen) {
			parensCombs(false, true, n, countOpen, countClosed + 1, list, comb);
		}
		return list;
	}
	
	public enum Coin{
		QUARTER(25),
		DIME(10), 
		NICKEL(5), 
		PENNY(1),
		ZERO(0);
		private final int centValue;
		Coin(int centValue){
			this.centValue = centValue;
		}
		public int getValue() {
			return this.centValue; 
		}
	}
	public static boolean alreadyInList(ArrayList<Coin> representation, ArrayList<ArrayList<Coin>> list) {
		for(ArrayList<Coin> comb: list) {
			if((Collections.frequency(comb, Coin.PENNY) == Collections.frequency(representation, Coin.PENNY)) &&
					Collections.frequency(comb, Coin.NICKEL) == Collections.frequency(representation, Coin.NICKEL) &&
					Collections.frequency(comb, Coin.DIME) == Collections.frequency(representation, Coin.DIME) &&
					Collections.frequency(comb, Coin.QUARTER) == Collections.frequency(representation, Coin.QUARTER)) {
				return true;
			}
		}
		return false;
	}
	public static ArrayList<ArrayList<Coin>> coins(int amount){
		ArrayList<ArrayList<Coin>> list = new ArrayList<ArrayList<Coin>>();
		ArrayList<Coin> representation = new ArrayList<Coin>();
		return coinsRepresent(list, representation, amount, 0, Coin.ZERO);
	}
	public static ArrayList<ArrayList<Coin>> coinsRepresent(ArrayList<ArrayList<Coin>> list, 
			ArrayList<Coin> representation, int amount, int currentTotal, Coin coin){
		if(coin.getValue() != 0) {
			representation.add(coin);
		} 
		if (currentTotal > amount){ //Error: Amount of money exceeded, return
			representation.remove(coin);
			return list;
		}
		if((currentTotal == amount) && !alreadyInList(representation, list)) {
			list.add(representation);
			return list;
		}
		coinsRepresent(list, (ArrayList<Coin>) representation.clone(), amount, 
				currentTotal + Coin.PENNY.getValue(), Coin.PENNY);
		coinsRepresent(list, (ArrayList<Coin>) representation.clone(), amount, 
				currentTotal + Coin.NICKEL.getValue(), Coin.NICKEL);
		coinsRepresent(list, (ArrayList<Coin>) representation.clone(), amount, 
				currentTotal + Coin.DIME.getValue(), Coin.DIME);
		coinsRepresent(list, (ArrayList<Coin>) representation.clone(), amount, 
				currentTotal + Coin.QUARTER.getValue(), Coin.QUARTER);
		return list;
	}
	//alreadyMapped, eightQueens, and eightQueensCombs are parts of the solution to 8.12 Eight Queens problem
//	public static boolean alreadyMapped(String str, HashMap<String,char[][]> combs) {
//		for(String s: combs.keySet()) {
//			if(s.equals(str)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	public static boolean diagonalsBlocked(char[][] board, int row, int col) {
//		int r = row;
//		int c = col;
//		while(r-1 >= 0 && c-1 >= 0) {
//			r -= 1;
//			c -= 1;
//			if(board[r][c] == 'Q') {
//				return true;
//			}
//		}
//		r = row;
//		c = col;
//		while(r-1 >= 0 && c+1 < board[0].length) {
//			r -= 1;
//			c += 1;
//			if(board[r][c] == 'Q') {
//				return true;
//			}
//		}
//		r = row;
//		c = col;
//		while(r+1 < board.length && c-1 >= 0) {
//			r += 1;
//			c -= 1;
//			if(board[r][c] == 'Q') {
//				return true;
//			}
//		}
//		r = row;
//		c = col;
//		while(r+1 < board.length && c+1 < board[0].length) {
//			r += 1;
//			c += 1;
//			if(board[r][c] == 'Q') {
//				return true;
//			}
//		}
//		return false;
//	}
	
//	public static HashMap<String,char[][]> eightQueens(){
//		HashMap<String,char[][]> combs = new HashMap<String,char[][]>();
//		int queensLeft = 8;
//		int row = 0;
//		int col = 0;
//		char[][] board = new char[8][8];
//		for(int i = 0; i < board.length; i++) {
//			for(int j = 0; j < board[0].length; j++) {
//				board[i][j] = '-';
//			}
//		}
//		String coordinates = "";
//		ArrayList<Integer> validColumns = new ArrayList<Integer>();
//		for(int i = 0; i < board.length; i++) {
//			validColumns.add(i);
//		}
//		eightQueensCombs(row, col, validColumns, coordinates, queensLeft, board, combs);
//		return(combs);
//	}
	
//	public static void eightQueensHelper() {
//		HashMap<String,char[][]> combs = new HashMap<String,char[][]>();
//		for(int r = 0; r <= 7; r++) {
//			for(int c = 0; c <= 7; c++) {
//				eightQueens(r,c,combs);
//			}
//		}
//		
//	}
	
//	public static boolean eightQueensCombs(int row, int col, ArrayList<Integer> validColumns, 
//			String coordinates, int queensRemaining, char[][] board, HashMap<String,char[][]> combs) {	
//		ArrayList<Integer> colsTried = new ArrayList<Integer>();
//		if(col < board[0].length && !diagonalsBlocked(board,row,col)) {
//			board[row][col] = 'Q';
//			coordinates += (Integer.toString(row) + Integer.toString(col));
//			queensRemaining--;
//			validColumns.remove(validColumns.indexOf(col));
//			colsTried.add(col);
//		} else {
//			return false;
//		}
//		if(row == (board.length - 1) && !alreadyMapped(coordinates, combs) && queensRemaining == 0) {
//			printBoard(board);
//			combs.put(coordinates, board);
//			return true;
//		} 
//		char[][] boardCopy = new char[8][8];
//		boardCopy = board;
//		if(row+1 < board.length) {
//			int i = 0;
//			int c = validColumns.get(i);
//			boolean triedNextRow = eightQueensCombs(row+1, c, (ArrayList<Integer>) validColumns.clone(), coordinates, 
//					queensRemaining, boardCopy, combs);
//			while(!triedNextRow && i < validColumns.size()) {
//				i++;
//				c = validColumns.get(i);
//				triedNextRow = eightQueensCombs(row+1, c, (ArrayList<Integer>) validColumns.clone(), coordinates, 
//						queensRemaining, boardCopy, combs);
//				if(i == validColumns.size() - 1 && !triedNextRow) {
//					board[row][col] = '-';
//					coordinates = coordinates.substring(0, coordinates.length() - 2);
//					validColumns.add(col);
//					col = 0;
//					while(!colsTried.contains(col) && validColumns.contains(col) && col < board[0].length) {
//						col++;
//					}
//					board[row][col] = 'Q';
//					coordinates += (Integer.toString(row) + Integer.toString(col));
//					validColumns.remove(validColumns.indexOf(col));
//					colsTried.add(col);
//					i = 0;
//					if(colsTried.size() == board[0].length) {
//						return false;
//					} 
//					continue;
//				}
//			}
//		} 
//		return true;
//	}
	
	//New attempt at Eight Queens Problem (8.12), 
	public static void eightQueensNewHelper() {
		char[][] board = new char[8][8];
		int row = 0;
		int col = 0;
		for(int i = 0; i < board.length; i++) {
			eightQueensNew(board, row, i);
			for(int k = 0; k < board.length; k++) {
				for(int j = 0; j < board[0].length; j++) {
					board[k][j] = '-';
				}
			}
		}
		System.out.println(combsFound);
		System.out.print(validsFound);
	}
	public static int combsFound = 0;
	public static int validsFound = 0;
	public static void eightQueensNew(char[][] board, int row, int col) {
		board[row][col] = 'Q';
		if(row == board.length - 1){
			combsFound++;
			if(checkCols(board) && validDiagonals(board)) {
				System.out.println("Solution " + (validsFound - 1));
				printBoard(board);
				System.out.println();
				validsFound++;
			}
			return;
		}
		for(int i = 0; i < board[0].length; i++) {
			eightQueensNew(matrixCopy(board), row + 1, i);
		}
		return;
	}
	public static char[][] matrixCopy(char[][] board){
		char[][] boardCopy = new char[8][8];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				boardCopy[i][j] = board[i][j];
			}
		}
		return boardCopy;
	}
	public static boolean checkCols(char[][] board) {
		int queensCount;
		for(int i = 0; i < board[0].length; i++) {
			queensCount = 0;
			for(int j = 0; j < board.length; j++) {
				if(board[j][i] == 'Q') {
					queensCount++;
				}
				if (queensCount > 1){
					return false;
				}
			}
		}
		return true;
	}
	public static boolean validDiagonals(char[][] board) {
		int queensCount = 0;
		int r;
		int c;
		for(int i = 0; i < board.length; i++) {
			c = 0;
			r = i;
			while(r < board.length && c < board[0].length) {
				if(board[r][c] == 'Q') {
					queensCount++;
				}
				r += 1;
				c += 1;
			}
			if(queensCount > 1) {
				return false;
			}
			queensCount = 0;
		}
		queensCount = 0;
		for(int i = 0; i < board[0].length; i++) {
			c = i;
			r = 0;
			while(r < board.length && c < board[0].length) {
				if(board[r][c] == 'Q') {
					queensCount++;
				}
				r += 1;
				c += 1;
			}
			if(queensCount > 1) {
				return false;
			}
			queensCount = 0;
		}
		queensCount = 0;
		for(int i = board.length - 1; i >= 0; i--) {
			c = i;
			r = 0;
			while(r < board.length && c >= 0) {
				if(board[r][c] == 'Q') {
					queensCount++;
				}
				r += 1;
				c -= 1;
			}
			if(queensCount > 1) {
				return false;
			}
			queensCount = 0;
		}
		queensCount = 0;
		for(int i = 0; i < board.length; i++) {
			c = board[0].length - 1;
			r = i;
			while(r < board.length && c >= 0) {
				if(board[r][c] == 'Q') {
					queensCount++;
				}
				r += 1;
				c -= 1;
			}
			if(queensCount > 1) {
				return false;
			}
			queensCount = 0;
		}
		return true;
	}
	public static void printBoard(char[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static HashMap<Integer, ArrayList<Box>> stackOfBoxes(ArrayList<Box> boxes, HashMap<Integer, ArrayList<Box>>
		stackSizes, Box currentBox) {
		ArrayList<Box> stack = new ArrayList<Box>();
		boolean fits = true;
		stack.add(currentBox);
		outerLoop:
		for(Box b: boxes) {
			if(b == currentBox) {
				continue outerLoop;
			}
			innerLoop:
			for(Box stackedBox: stack) {
				if(!((b.getHeight() < stackedBox.getHeight() && b.getWidth() < stackedBox.getWidth() && 
						b.getDepth() < stackedBox.getDepth()) || 
						(b.getHeight() > stackedBox.getHeight() && b.getWidth() > stackedBox.getWidth() && 
						b.getDepth() > stackedBox.getDepth()))) {
					fits = false;
					break innerLoop;
				} 
			}
			if(fits) {
				stack.add(b);
			} else {
				fits = true;
				continue outerLoop;
			}
		}
		for(Box b: stack) {
			System.out.println(b.getName());
		}
		stackSizes.put(stack.size(), stack);
		stackOfBoxes(boxes, stackSizes, boxes.get(boxes.indexOf(currentBox) + 1));
		return stackSizes;
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
//		String str = "abcdefgh";
//		findPermutations(str);
//		String str2 = "crocodil";
//		findPermutationsDups(str2);
		
//		ArrayList<String> list = new ArrayList<String>();
//		list = parens(4);
//		System.out.println(list);
//		System.out.println("List size: " + list.size());
		
//		System.out.println(coins(8));
		
//		eightQueensNewHelper();
		Box b1 = new Box(4, 5, 4, "b1");
		Box b2 = new Box(3, 3, 3, "b2");
		Box b3 = new Box(1, 2, 1, "b3");
		Box b4 = new Box(7, 8, 8, "b4");
		Box b5 = new Box(11, 10, 14, "b5");
		Box b6 = new Box(4, 15, 32, "b6");
		Box b7 = new Box(11, 45, 23, "b7");
		ArrayList<Box> boxes = new ArrayList<Box>();
		boxes.add(b1);
		boxes.add(b2);
		boxes.add(b3);
		boxes.add(b4);
		boxes.add(b5);
		boxes.add(b6);
		boxes.add(b7);
		HashMap<Integer, ArrayList<Box>> stackSizes = new HashMap<Integer, ArrayList<Box>>();
		stackOfBoxes(boxes, stackSizes, b1);
	}
}