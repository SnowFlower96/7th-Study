package study0530;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_20055_컨베이어벨트위의로봇 {

	static int N, K, belt[];
	static boolean robot[];
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[N*2];
		robot = new boolean[N];
		int count = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			rotate();
			count++;
			
			robotMove();
			if(isEnd()) {
				System.out.println(count);
				break;
			}
			
		}
	
	}

	private static boolean isEnd() {

		int cnt = 0;
		for(int i : belt) {
			if(i==0) cnt++;
		}
		
		if(cnt >= K) {
			return true;
		}else {
			
			return false;
		}
	}

	private static void robotMove() {

		//내리는 위치의 로봇 내리기
		robot[N-1] = false;
		
		//로봇 이동
		for(int i=N-2; i>=0; i--) {
			if(robot[i]) {	//로봇이 있다면				
				if(!robot[i+1] && belt[i+1] > 0) {
					robot[i+1] = robot[i];
					belt[i+1]--;
					robot[i] = false;
				}
			}
		}

		//올리는 위치에 로봇 올리기
		if(belt[0] > 0) {
			robot[0] = true;
			belt[0]--;
		}
		
	}

	
	private static void rotate() {

		//벨트 회전
		int end = belt[belt.length-1];
		
		for(int i=belt.length-2; i>=0; i--) {
			belt[i+1] = belt[i];
		}
		belt[0] = end;
		
		//로봇 회전
		for(int i=N-2; i>=0; i--) {
			robot[i+1] = robot[i];
		}
		robot[0] = false;
	}
	
	

	
	
	
	
}
