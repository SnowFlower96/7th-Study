package study0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_15486_퇴사2 {

	private static int[] T, P;
	private static int N;
	private static int max;
	private static int[] DP;


	public static void main(String[] args) throws IOException{

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
        N = Integer.parseInt(br.readLine()); // 일 수
        
        T = new int[N + 2];	//시간
        P = new int[N + 2];	//금액
        DP = new int[N + 2];
        //입력 받기
        for(int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	T[i] = Integer.parseInt(st.nextToken());
        	P[i] = Integer.parseInt(st.nextToken());
        }
        
        max = Integer.MIN_VALUE;
        
        for(int i=1; i<N+2; i++) {
        	max = Math.max(max, DP[i]);
        	
        	int day = i + T[i];
        	if(day < N+2) {
        		DP[day] = Math.max(max + P[i],DP[day]);
        	}
        }
        
        System.out.println(max);
	}

}
