import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_파티_1238 {

	static int N, M, X, maxTime;
	
	static public class Node implements Comparable<Node>{
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
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] list = new ArrayList[N+1];
		//시간줄이기 용
		ArrayList<Node>[] reverseList = new ArrayList[N+1];		
		
		for(int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
			reverseList[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to, time));
			reverseList[to].add(new Node(from, time));
		}
		
		
		int goHome[] = gohome(list);
		int goParty[] = gohome(reverseList);
		
		int maxTime = 0;
		for(int i = 1 ; i < N+1; i++) {
			int time = 0;
//			time += goParty(list, i);
			time += goParty[i];
			time += goHome[i];
			maxTime = Math.max(maxTime, time);
		}
		System.out.println(maxTime);
		

	}
	private static int[] gohome(ArrayList<Node>[] list) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] distance = new int[N+1];
		boolean[] visited = new boolean[N+1];

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;
		pq.offer(new Node(X, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.v]) continue;
			
			visited[cur.v] = true;
			
			for(Node next : list[cur.v]) {
				if(!visited[next.v] && 
						distance[next.v] > distance[cur.v] + next.dis ) {
					distance[next.v] = distance[cur.v] + next.dis;
					pq.offer(new Node(next.v, distance[next.v]));
				}					
			}
		}
		
		return distance;
		
	}
	
	private static int goParty(ArrayList<Node>[] list, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] distance = new int[N+1];
		boolean[] visited = new boolean[N+1];

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.v]) continue;
			
			visited[cur.v] = true;
			
			for(Node next : list[cur.v]) {
				if(!visited[next.v] && 
						distance[next.v] > distance[cur.v] + next.dis ) {
					distance[next.v] = distance[cur.v] + next.dis;
					pq.offer(new Node(next.v, distance[next.v]));
				}					
			}
		}
		
		return distance[X];
		
		
		
	}

}
