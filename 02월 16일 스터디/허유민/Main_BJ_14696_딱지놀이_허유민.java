package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14696_딱지놀이_허유민 {

	static StringBuilder sb =new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			// A의 딱지
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int[] A = new int[4];
			for (int j = 0; j < a; j++) {
				char c = st.nextToken().charAt(0);
				switch(c) {
				case '4':
					A[3]++;
					break;
				case '3':
					A[2]++;
					break;
				case '2':
					A[1]++;
					break;
				case '1':
					A[0]++;
					break;
				}
			}
			
			// B의 딱지
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int[] B = new int[4];
			for (int j = 0; j < b; j++) {
				char c = st.nextToken().charAt(0);
				switch(c) {
				case '4':
					B[3]++;
					break;
				case '3':
					B[2]++;
					break;
				case '2':
					B[1]++;
					break;
				case '1':
					B[0]++;
					break;
				}
			}
			Dakji(A,B); //딱지치기
		}
		System.out.println(sb);
		
	}
	
	// 딱지치기 
	public static void Dakji(int[] A, int[] B) {
		int count = 0; // 무승부 측정 카운트
		for (int i = 3; i >= 0; i--) {
			if(A[i] > B[i]) {
				sb.append("A" + "\n");
				break;
			}else if(B[i] > A[i]) {
				sb.append("B" + "\n");
				break;
			}else {
				count++;
			}
		}
		if(count == 4) {
			sb.append("D" + "\n");
			return;
		}
	}

}
