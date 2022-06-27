package study0530;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1138_한줄로서기 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		int[] result = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			int cnt = 0;
			for(int j=0; j<N; j++) {
				if(result[j] == 0) cnt++;
				
				if(input[i]+1 == cnt) {
					result[j] = i+1;
					break;
				}
				
			}
		}
		
		for(int a : result) {
			sb.append(a+" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
		
	}

}
