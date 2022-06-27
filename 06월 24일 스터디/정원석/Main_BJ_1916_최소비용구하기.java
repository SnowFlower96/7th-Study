import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_BJ_1916_최소비용구하기 {

		public static class Node implements Comparable<Node>{
			int v, minDis;

			public Node(int v, int minDis) {
				super();
				this.v = v;
				this.minDis = minDis;
			}

			@Override
			public int compareTo(Node o) {
				return this.minDis - o.minDis;
			}
			
			 
		}
		static int N, M;
		
		public static void main(String[] args) throws NumberFormatException, IOException {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			ArrayList<Node>[] list = new ArrayList[N+1];
			for(int i = 0 ; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i = 0 ; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dis = Integer.parseInt(st.nextToken());
				
				list[from].add(new Node(to, dis));
				
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int last = Integer.parseInt(st.nextToken());

			int[] distance = new int[N+1];
			boolean[] visited = new boolean[N+1];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[start] = 0;
			pq.offer(new Node(start, distance[start]));
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if(visited[cur.v]) continue;
				
				visited[cur.v] = true;
				
				for(Node next : list[cur.v]) {
					
					if(!visited[next.v] && distance[next.v] > distance[cur.v] + next.minDis) {
						distance[next.v] = distance[cur.v] + next.minDis;
						if(next.v != last)
							pq.offer(new Node(next.v, distance[next.v]));
					}
				}
			}
			
			System.out.println(distance[last]);
			

	}

}
