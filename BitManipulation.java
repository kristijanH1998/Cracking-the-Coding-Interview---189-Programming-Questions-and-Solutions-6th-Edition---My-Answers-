package bitManipulation;




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
	
	public static void main(String[] args) {
//		System.out.println(getBit(4, 0));
//		System.out.println(setBit(3, 2));
//		System.out.println(clearBit(5, 2));
//		System.out.println(clearBitMSBThroughI(6, 2));
//		System.out.println(clearBitsIThrough0(7, 2));
//		System.out.println(updateBit(9, 1, true));
		System.out.println(insertion(19, 1024, 2, 6));
	}
}
