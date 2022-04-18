import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Bj_1446_지름길_PQ {
	
	static class Road implements Comparable<Road>{ 
		int current, distance;
		
		public Road(int current, int distance) {
			this.current = current;
			this.distance = distance;
		}

		@Override
		public int compareTo(Road o) {
			return this.distance - o.distance;
		}	
	}

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
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Road> pQueue = new PriorityQueue<>();
		distance[0] = 0;
		pQueue.offer(new Road(0, 0));
		
		
		while(!pQueue.isEmpty()) {
			Road cur = pQueue.poll();
			
			if(cur.current == goal) break;
			
			for(int d = 0; d < N; d++) {
				if(map[d][1] <= goal && map[d][0] == cur.current && distance[map[d][1]] > distance[cur.current] + map[d][2]) {
					distance[map[d][1]] = distance[cur.current] + map[d][2];
					pQueue.offer(new Road(map[d][1], distance[map[d][1]]));
				}
			}
			
			if( cur.current + 1<= goal && distance[cur.current+1] > distance[cur.current] + 1) {
				distance[cur.current+1] = distance[cur.current] + 1;
				pQueue.offer(new Road(cur.current+1, distance[cur.current+1]));
			}
		}
		
		System.out.println(distance[goal]);

	}
}
