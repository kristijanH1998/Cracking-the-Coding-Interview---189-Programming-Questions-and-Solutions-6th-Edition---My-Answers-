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
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		tripleStep(0, 5, paths, path);
		for(ArrayList<Integer> p: paths) {
			System.out.println(p);
		}
	}

}
