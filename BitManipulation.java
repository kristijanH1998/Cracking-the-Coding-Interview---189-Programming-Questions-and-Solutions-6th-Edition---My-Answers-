package bitManipulation;

import java.util.ArrayList;
import java.util.Collections;

public class BitManipulation {
	public static String convertIntegerToBinary(int n) {
	    if (n == 0) {
	        return "0";
	    }
	    StringBuilder binaryNumber = new StringBuilder();
	    while (n > 0) {
	        int remainder = n % 2;
	        binaryNumber.append(remainder);
	        n /= 2;
	    }
	    binaryNumber = binaryNumber.reverse();
	    return binaryNumber.toString();
	}
	
	public static boolean getBit(int num, int i) {
		return((num & (1 << i)) != 0);
	}
	
	public static int setBit(int num, int i) {
		return num | (1 << i);
	}
	
	public static int clearBit(int num, int i) {
		int mask = ~(1 << i);
		return num & mask;
	}
	
	public static int clearBitMSBThroughI(int num, int i) {
		int mask = (1 << i) - 1;
		return num & mask;
	}
	
	public static int clearBitsIThrough0(int num, int i) {
		int mask = (-1 << (i + 1));
		return num & mask;
		
	}
	
	public static int updateBit(int num, int i, boolean bitIs1) {
		int value = bitIs1 ? 1 : 0;
		int mask = ~(1 << i);
		value <<= i;
		return (num & mask) | value;
	}
	
	public static int insertion(int M, int N, int i, int j) {
		if((j - i) + 1 < (convertIntegerToBinary(M).length())) {
			System.out.println("Not enough space for M in N");
			return -1;
		}
		int temp = i;
		while(i <= j) {
			N = clearBit(N, i);
			i++;
		}
		i = temp;
		int iter = 0;
		while(i <= j) {
			if (getBit(M, iter) == true) {
				N = setBit(N, i);
			}
			i++;
			iter++;
		}
		return N;
	}
	
	public static String BinaryToString (double num, char[] chars) {
		chars = String.valueOf(num).toCharArray();
		String beforePoint = new String();
		String afterPoint = new String();
		int power = 0;
		int i = 0;
		while(chars[i] != '.') {
			beforePoint = beforePoint + chars[i];
			i++;
		}
		i++;
		while(i < chars.length) {
			afterPoint = afterPoint + chars[i];
			power++;
			i++;
		}
		System.out.println(beforePoint);
		System.out.println(afterPoint);
		double decimals = Integer.parseInt(afterPoint) / Math.pow(10, power);
		afterPoint = "";
		while(decimals != 0) {
			decimals *= 2;
			if(decimals < 1) {
				afterPoint += "0";
			} else {
				afterPoint += "1";
				decimals -= 1;
			}
		}
		beforePoint = convertIntegerToBinary(Integer.parseInt(beforePoint)) + "." + afterPoint;
		System.out.println(beforePoint.length() - 1);
		if((beforePoint.length() - 1) > 32) {
			System.out.println("ERROR");
			return null;
		}
		return beforePoint;
	}
	
	public static int flipBitToWin(int num) throws Exception {
		if(num == 0) {
			return 1;
		}
		if(num == -1) {
			System.out.println("No zeros in the input.");
			throw new Exception("Error");
		}
		StringBuilder str = new StringBuilder();
		str.append(convertIntegerToBinary(num));
		ArrayList<Integer> zeros = new ArrayList<Integer>();
		int index = 0;
		while(index < str.length()) {
			if(getBit(num, index) == false) {
				zeros.add(index);
			}
			index++;
		}
		index = 0;
		ArrayList<Integer> onesList = new ArrayList<Integer>();
		int ones = 0;
		int longest1sequence = 0;
		for(int zero: zeros) {
			num = setBit(num, zero);
			while(index < str.length()) {
				if(getBit(num, index) == true) {
					while(getBit(num, index) == true) {
						ones++;
						index++;
					} 
					onesList.add(ones);
				} else {
					ones = 0;
					index++;
				}
			}
			longest1sequence = (longest1sequence < Collections.max(onesList)) 
					? Collections.max(onesList) : longest1sequence;
			num = clearBit(num, zero);
			ones = 0;
			index = 0;
			onesList.clear();
		}
		return longest1sequence;
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(getBit(4, 0));
//		System.out.println(setBit(3, 2));
//		System.out.println(clearBit(5, 2));
//		System.out.println(clearBitMSBThroughI(6, 2));
//		System.out.println(clearBitsIThrough0(7, 2));
//		System.out.println(updateBit(9, 1, true));
//		System.out.println(insertion(19, 1024, 2, 6));
//		char[] chars = new char[40];
//		System.out.println(BinaryToString(20.375, chars));
		System.out.println(flipBitToWin(39999202));
		
	}
}
