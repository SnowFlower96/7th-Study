package study0219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13300_방배정 {

	static int K, N, S, Y, rooms, male[], female[];
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 총 학생수
		K = Integer.parseInt(st.nextToken()); // 한 방 최대 인원
		
		male = new int[7];
		female = new int[7];
		
		for(int i=0; i<N; i++) { // 배열 입력받기
			
			st = new StringTokenizer(br.readLine());
			
			S = Integer.parseInt(st.nextToken()); // 성별
			Y = Integer.parseInt(st.nextToken()); // 학년
			
			if(S == 0) female[Y]++;		// 여
			if(S == 1) male[Y]++;		// 남 
			
		}
			count(female);
			count(male);
		
		System.out.println(rooms);
		
	}

	private static void count(int[] arr) {
		
		for(int i=1; i<7; i++) {
			if(arr[i] != 0) {
				if(arr[i] % K != 0) {
					rooms += arr[i]/K +1;
				}else rooms += arr[i]/K;
			} else continue;
		}
		
	}
}
