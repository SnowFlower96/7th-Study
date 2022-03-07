package study;

import java.util.Scanner;

public class bg_2798_블랙잭_허예강 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] card = new int[N];
		
		for(int i=0; i<N; i++) {
			card[i] = sc.nextInt();
		}
		
		int result = search(card, N, M);
		
		System.out.println(result);
	}
	
	static int search(int[] arr, int N, int M) {
		// 값 초기화
		int result = 0;
		
		// 0~3 3장
		for(int i=0; i<N-2; i++) {
			// 1~4 3장
			for(int j=i+1; j<N-1; j++) {
				// 2~5 3장
				for(int k=j+1; k<N; k++) {
					int temp = arr[i]+arr[j]+arr[k];
					
				// M==temp 종료
				if(M == temp) {
					return temp;
				}
				// 카드 값이 이전 카드 값 보다 작거나 M보다 작으면 계속 실행
				if(result < temp && temp<M) {
					result = temp;
					}
				}
			}
		}
		// 다 돌았으면 결과값 리턴
		return result;
	}
}
