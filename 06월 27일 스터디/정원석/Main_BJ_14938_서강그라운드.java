import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14938_서강그라운드 {
	
	public static class Node implements Comparable<Node>{
		int v, dis;

		public Node(int v, int dis) {
			super();
			this.v = v;
			this.dis = dis;
		}

		@Override
		public String toString() {
			return "Node [v=" + v + ", dis=" + dis + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.dis - o.dis;
		}
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] item = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1 ; i <= n; i++ ) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Node>[] list = new ArrayList[n+1];
		
		for(int i = 0 ; i < n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, distance));
			list[to].add(new Node(from, distance));
		}
		
		int maxItem = 0;
		
		for(int i = 1; i <= n; i++) {
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int[] distance = new int[n+1];
			boolean[] visited = new boolean[n+1];
			
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[i] = 0;
			pq.offer(new Node(i, distance[0]));
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if(visited[cur.v]) continue;
				
				visited[cur.v] = true; 
				
				for(Node next : list[cur.v]) {
					if(!visited[next.v] && 
							distance[next.v] > distance[cur.v] + next.dis) {
						distance[next.v] = distance[cur.v] + next.dis;
						pq.offer(new Node(next.v, distance[next.v]));
					}
				}
			}
			
			int itemNum = 0;
			
			for(int j = 1; j <= n; j++) {
				if(distance[j] <= m) {
					itemNum += item[j];
				}
			}
//			System.out.println(i + " " + Arrays.toString(distance));
			maxItem = Math.max(itemNum, maxItem);
		}
		
		System.out.println(maxItem);

	}

}
