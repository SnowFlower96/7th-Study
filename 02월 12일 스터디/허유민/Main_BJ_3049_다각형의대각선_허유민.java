package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_3049_다각형의대각선_허유민 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int mul = 1;
		
		for (int i = 0; i < 4; i++) {
			mul *= N-i;
		}
		int answer = mul / 24;
		
		// 조합 nC4 = nP4 / 4! = (n! / (n-4)!) 
		// int answer = N * (N-1) * (N-2) * (N-3) / 24;
		
		System.out.println(answer);
	}

}
