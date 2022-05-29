package study0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_18429_근손실 {

	static int N, K, ans, weight, kits[], choice[], weights[];
	static boolean isSelected[];
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//운동키트 개수
		K = Integer.parseInt(st.nextToken());	//감소하는 중량
		kits = new int[N];
		choice = new int[N];
		isSelected = new boolean[N];
		weights = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			kits[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = 0;
		
		permutation(0);
		
		System.out.println(ans);
	}

	private static void permutation(int cnt) {

		//기저조건
		if(cnt==N) {
			weight = 500;
			weights[0] = weight;
			for(int i=1; i<N+1; i++) {
				weights[i] = weights[i-1] - K + choice[i-1];
				
				if(weights[i] < 500 ) return;
			}
			ans ++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			
			if(isSelected[i]) continue;
			
			choice[cnt] = kits[i];
			isSelected[i] = true;
			
			permutation(cnt+1);
			
			isSelected[i] = false;
		}
		
	}
	
	
}
