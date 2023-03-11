import java.util.HashMap;
public class my_class {

		public static boolean isUniqueChars(String str) {
			if (str.length() > 128) {
				return false;
			}
			boolean[] char_set = new boolean[128];
			for (int i = 0; i < str.length(); i++) {
				int val = str.charAt(i);
				if (char_set[val]) return false;
				char_set[val] = true;
			}
			return true;
		}
				
		public static boolean isPermutation(String str1, String str2) {
			
	        char[] ar1 = new char[str1.length()];
	        char[] ar2 = new char[str2.length()];
	        
	        if(str1.length() != str2.length()) {
	        	return false;
	        }
	        
	        for (int i = 0; i < str1.length(); i++) {
	            ar1[i] = str1.charAt(i);
	        }
	        
	        for (int i = 0; i < str2.length(); i++) {
	            ar2[i] = str2.charAt(i);
	        }
	        
	        for (int k = 0; k < str1.length(); k++) {
	        	for (int l = 0; l < str2.length(); l++) {
	        		if (ar1[k] == ar2[l]) {
	        			ar2[l] = ' ';
	        			break;
	        		}	
	        	}
	        }
	        
	        for (int i = 0; i < str1.length(); i++) {
	        	if (ar2[i] != ' ') {
	        		return false;
	        	}	
	        } 
			return true;
		}
		
		public static void replace_string (String s) {
			int spaces = 0;
			char[] s_array = s.toCharArray();
			for(char c : s_array) {
				if (c == ' '){
					spaces++;
				}
			}
			System.out.println(spaces);
			char[] ar2 = new char[s.length() + (spaces * 3)];
			int j = 0;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == ' '){
					ar2[j++] = '%';
					ar2[j++] = '2';
					ar2[j++] = '0';
				} else {
					ar2[j++] = s.charAt(i);
				}
			}
			
			System.out.println(ar2);
			
		}

		public static boolean palindrome_permutation(String s) {
		    HashMap<Character, Integer> chSet = new HashMap<Character, Integer>();
		    
		    
		    for (int i = 0; i < s.length(); i++) {
		    	if((chSet.get(s.charAt(i)) != null) && (s.charAt(i) != ' ') && (s.charAt(i) != '	')) {
			    	chSet.put(s.charAt(i),chSet.get(s.charAt(i)) + 1);
		    	} else {
		    		if ((s.charAt(i) == ' ') || (s.charAt(i) == '	')) {
		    			continue;
		    		}
			    	chSet.put(s.charAt(i), 1);
		    	}
		    }
			
		    System.out.println(chSet);
		    int count_odds = 0;
		    for (Integer i : chSet.values()) {
		        if (i % 2 != 0) {
		        	count_odds++;
		        	if (count_odds > 1) {
		        		return false;
		        	}
		        }
		    }  
		  
		    return true;
		}
		
		public static boolean oneAway (String s, String t) {
			
			if ((s.equals(t))){
				return true;
			} else if (Math.abs(s.length() - t.length()) <= 1) {
				char[] ar1 = s.toCharArray();
				char[] ar2 = t.toCharArray();
				int j = 0;
				int diff = 0;
	
				for (int i = 0; i < (Math.max(s.length(), t.length()) - 1); i++) {
					

					if (ar1[i] == ar2[j]) {
						j++;
					} else {
						diff++;
					}
					
					if (diff > 1) {
						System.out.println(diff);
						return false;
					}
					
				}
			} else {
				return false;
			}
			
			return true;
			
		}
		
		public static String string_compression(String str) {
			char[] ar = str.toCharArray();
			int index = 0;
			int countCh = 1;
			
		    HashMap<Character, Integer> chSet = new HashMap<Character, Integer>();
		    String new_str = "";
			
			while (index < (str.length()-1)){
				
				if (ar[index] == ar[++index] && (ar[index] != '\n')) {
					countCh++;
					
				} else {
					chSet.put(str.charAt(index - 1), countCh);
					countCh = 1;
					if(chSet.containsKey(str.charAt(index-1))) {
						
						for (Character ch : chSet.keySet()) {
							new_str = new_str + ch + chSet.get(ch);
						}
						chSet.clear();
						chSet.put(str.charAt(index), 1);
					}
				}
	
			}
			
			chSet.put(str.charAt(index - 1), countCh);
			new_str = new_str + str.charAt(index) + chSet.get(str.charAt(index));
			 
			if(new_str.length() >= str.length()) {
				return(str);
			}
			
			System.out.println(chSet);
			System.out.println(str.length());
			return new_str;

		}
		
		public static int[][] zero_matrix (int[][] matrix){
		
			int row = 0;
			int col = 0;
			
			while((matrix[row][col] != 0)) {
				if (col == (matrix[0].length - 1)){
					row++;
					col = -1;
				}
				col++;
			}
			
			if (matrix[row][col] == 0) {
				int r = 0;
				int c = 0;
				while(r <= (matrix.length - 1)) {
					matrix[r][col] = 0;
					r++;
				}
				
				while(c <= (matrix[0].length - 1)) {
					matrix[row][c] = 0;
					c++;
				}
			}
			
			System.out.println(matrix.length);
		
			return matrix;
		}
		
		public static boolean is_substring(String s, String t) {
			char[] shorter = new char[Math.min(s.length(), t.length())];
			char[] longer = new char[Math.max(s.length(), t.length())];
			
			if(shorter.length == s.length() || (s.length() == t.length())) {
				shorter = s.toCharArray();
				longer = t.toCharArray();
			} else  {
				shorter = t.toCharArray();
				longer = s.toCharArray();
			}
		
			int j = 0;
			int similarity = 0;
			
			for(int i = 0; (i < longer.length) && (j < shorter.length); i++) {
				if(shorter[j] == longer[i]) {	
					similarity++;
					j++;
			
				} else {
					similarity = 0;
					j = 0;
				}
			}
			
			System.out.println(shorter);
			System.out.println(longer);		
			
			if(similarity == shorter.length) {
				return true;
			}
			
			return false;
			
		}
		
		public static boolean is_rotation (String s1, String s2) {
			
			if (s1.length() != s2.length()) {
				return false;
			}
			
			StringBuilder sb1=new StringBuilder(s1); 
			StringBuilder sb2=new StringBuilder(s2); 
			
			int j = s2.length() - 1;
			String temp1 = "";
			String temp2 = "";
			StringBuilder sb_temp = new StringBuilder(temp2);
			String temp3 = "";
			
			for(int i = 0; i < s1.length(); i++) {
				temp1 = temp1 + sb1.charAt(i);
				temp2 = temp2 + sb2.charAt(j);
				sb_temp.setLength(0);
				sb_temp.append(temp2);
				sb_temp = sb_temp.reverse();
				temp3 = sb_temp.toString();
				
				if ((temp1.equals(temp3)) && (sb1.substring(i + 1, sb1.length())).equals((sb2.substring(0, j)))) {
					return true;
				}
				j--;
			}
			
			return false;
		}		

		public static void main(String[] args) {
//			String[] words = {"abcde", "hello", "apple", "kite", "padle", "uniq", "unique"};
//			for (String word : words) {
//				System.out.println(word + ": " + isUniqueChars(word));
//			}
			
			//System.out.println(isPermutation("neroanq", "raoennu"));
			//replace_string("  This is some string . ");
			//System.out.println(palindrome_permutation("abfcccccba"));
			//System.out.println(oneAway("bale", "pale"));
			//System.out.println(string_compression("abccccc"));
	
//			int[][] matrix = {{1, 2, 3},
//							  {4, 5, 6},
//							  {7, 6, 9},
//							  {0, 11, 12}};
//			
//			matrix = zero_matrix(matrix);
//			for (int i = 0; i < matrix.length; i++) {
//				 
//	            for (int j = 0; j < matrix[i].length; j++) {
//	                System.out.print(matrix[i][j] + " ");
//	            }
//	            System.out.println();
//			}
			
			//System.out.println(is_substring("bottle", "erbottlewat"));
			System.out.println(is_rotation("abcd", "cdab"));

		}

}
