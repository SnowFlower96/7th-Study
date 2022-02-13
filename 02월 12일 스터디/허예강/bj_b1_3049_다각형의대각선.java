import java.util.Scanner;

public class bj_b1_3049_다각형의대각선 {

	public static void main(String[] args) {
		/*
		 * 조합 nCr
		 * */
		//1. N입력 받기
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		//2. N각형에서 대각선의 교차점을 세는데 필요한 공식 nC4
		// nC4 = n*(n-1)*(n-2)*(n-3)/24
		int M = N*(N-1)*(N-2)*(N-3)/24;
		
		//3. 출력
		System.out.println(M);
	}

}
