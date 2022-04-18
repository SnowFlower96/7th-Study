import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1446_지름길 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[N][3];
		int distance[] = new int[D+1];;
		int goal = D;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken()); // 지름길의 출발 지점
			map[i][1] = Integer.parseInt(st.nextToken()); // 지름길의 목적지
			map[i][2] = Integer.parseInt(st.nextToken()); // 지름길의 길이
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		Queue<Integer> Queue = new LinkedList<>();
		distance[0] = 0;
		// 첫 출발지를 Queue에 넣는다.
		Queue.offer(0);
		
		while(!Queue.isEmpty()) {
			int cur = Queue.poll();
			
			//지름길을 전부 반복문을 돌린다. 
			for(int d = 0; d < N; d++) {
				// 지름길을 반복문돌릴때 현재있는 위치와 지름길의 출발지가 같고 도착지가 목표지점보다 작거나 같고 다음 지점까지 거리 > 현재 거리 + 지름길 거리이면 
				// distance에 더 작은 거리를 저장하고 Queue에 다음 지점을 넣는다.
				if(map[d][1] <= goal && map[d][0] == cur && distance[map[d][1]] > distance[cur] + map[d][2]) {
					distance[map[d][1]] = distance[cur] + map[d][2];
					Queue.offer(map[d][1]);
				}
			}
			
			
			// 현재위치 바로 다음 위치가 goal보다 작고 다음위치 거리가 현재위치 거리 +1 보다 크면
			// distance에 더 작은 거리를 저장하고 Queue에 다음 지점(현재위치 + 1)을 넣는다.
			if( cur + 1<= goal && distance[cur+1] > distance[cur] + 1) {
				distance[cur+1] = distance[cur] + 1;
				Queue.offer(cur+1);
			}
		}
		
		System.out.println(distance[goal]);

	}

}
