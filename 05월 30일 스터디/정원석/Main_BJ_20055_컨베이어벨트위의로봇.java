import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_20055_컨베이어벨트위의로봇 {

	static int N, K, cnt;
	static int[][] container;
//	static Queue<Integer> robot;
	
	//## 첫번째로 풀 때 queue를 사용했다 -> 엄청나게 시간과 메모리를 잡아먹었다. 메모리 246568KB 시간 588MB
	//## 두번쨰로 풀 때 queue를 사용해서 찾던 로봇의 위치를 반복문으로 찾아서 집어 넣었다. -> 시간과 메모리가 매우 많이 줄어든것을 보았다.
	//													메모리 16504KB 시간 328MB
	//## 마지막으로 풀 떄 내구도 0 개수처리를 발생할때마다 처리하는것으로 바꾸었다. -> 시간과 메모리가 조금 줄어들었다. 
	// 													메모리 13856KB 시간 232MB
	
	//로봇은 '올리는 위치'에서만 올릴 수 있다.(index = 0)
	//index = N-1가 되면 무조건 내린다.

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N -> 컨베이어 벨트 길이
		// K -> 내구도 0인 기기의 최대개수
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//벨트 로봇이 있는 위치와 내구도를 표시할 배열
		container = new int[2][N*2];
		
		//로봇이 올려질때마다 Queue에 로봇의 위치를 저장한다.
//		robot = new LinkedList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0 ; i < 2*N; i++) {
			container[1][i] = Integer.parseInt(st.nextToken());
		}
		//벨트가 각 칸 위에 있는 로봇과 함께 한칸 회전한다.
		
		// 진행되는 단계 수			
		int level = 0;
		//내구도가 0인 개수
		cnt=0;
		while(cnt < K) {
			beltMove();
			
			putRobot();
			
//			System.out.println("로봇 " + Arrays.toString(container[0]));
//			System.out.println("내구도 " + Arrays.toString(container[1]));
//			for(int i = 0 ; i < 2*N;i++){
//				if(container[1][i] == 0) cnt++;
//			}
			level++;
			
//			if(cnt >= K) break;
		}
		
		System.out.println(level);
		
	}
	
	private static void putRobot() {
		if(container[1][0] != 0) {
			container[0][0] = 1;
			container[1][0]--;
			if(container[1][0] == 0) cnt++;
		}
		
	}

	//로봇을 움직인다.
	private static void robotMove(int cur) {
//		int size = robot.size();
//		for(int i = 0 ; i < size; i++) {
//			int cur = robot.poll();
		
			// 로봇의 다음위치에 로봇이 없고, 
			// 로봇의 다음위치의 내구도가 1 이상이면 로봇을 옮긴다.
			if(container[0][cur+1] != 1 && container[1][cur+1] >= 1) {
				// 로봇 이동
				container[0][cur+1] = 1;
				container[0][cur] = 0;
				container[1][cur+1]--;
				if(container[1][cur+1] == 0) cnt++;
				
				//만약 로봇이 다음으로 가는 위치가 내리는 위치이면 내린다.
				if(cur+1 == N-1) container[0][cur+1] = 0;
			}
//		}
	}

	//벨트 한칸씩 이동
	private static void beltMove() {
		//0인부분 => 로봇위치 변경
		//1인부분 => 벨트 내구도 변경
		int temp_ro = container[0][0];
		int temp_du = container[1][0];
		for(int i = 2*N-1 ; i > 0; i--) {
			if(i != 2*N-1 && i != 1 ) {
				container[1][i] = container[1][i-1];
				container[0][i] = container[0][i-1];
			}else if(i == 1){
				container[1][1] = temp_du;
				container[0][1] = temp_ro;				
			}else if(i == 2*N-1){
				container[1][0] = container[1][2*N-1];
				container[0][0] = container[0][2*N-1];
				container[1][i] = container[1][i-1];
				container[0][i] = container[0][i-1];
			}
		}		
		//로봇이 내리는 곳에 있으면 내린다.
		if(container[0][N-1] == 1) container[0][N-1] = 0;
		//로봇위치를 전부 재설정한다.
//		robot.clear();
		for(int i = N-1 ; i >= 0; i--) {
			if(container[0][i] == 1) {
//				robot.offer(i);
				robotMove(i);
			}
		}
	}

}
