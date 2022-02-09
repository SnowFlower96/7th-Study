package bronze.b2;

import java.util.Scanner;

public class Main_BJ_B2_10829_이진수변환 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 입력 범위 1 ≤ N ≤ 100,000,000,000,000
		Long input = sc.nextLong();
		System.out.println(getByte(input));
		
		sc.close();
		
	}
	
	public static String getByte(Long n) {
		if(n == 0) return "";
		
		return getByte(n / 2) + Long.toString(n % 2);
	}

}
