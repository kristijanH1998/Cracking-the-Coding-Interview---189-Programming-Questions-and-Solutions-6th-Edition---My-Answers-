package recursionAndDynamicProgramming;
import java.util.ArrayList;



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
	public static void main(String[] args) {
//		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
//		ArrayList<Integer> path = new ArrayList<Integer>();
//		tripleStep(0, 5, paths, path);
//		for(ArrayList<Integer> p: paths) {
//			System.out.println(p);
//		}
		int[][] grid = makeGrid(4,4);
		ArrayList<Integer> path = new ArrayList<Integer>();
		RobotInAGrid(path, grid, 0, 0);
//		for(int i: path) {
//			System.out.print(i + " ");
//		}
	}
}