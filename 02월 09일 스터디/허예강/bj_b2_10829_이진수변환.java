import java.util.*;

public class bj_b2_10829_이진수변환 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextLong();
		
		recursive(N);
		
	}

	private static void recursive(long N) {
		if(N <= 1) {
			System.out.print(N);
			return;
		}
		recursive(N/2);
		System.out.print(N%2);
	}

}
