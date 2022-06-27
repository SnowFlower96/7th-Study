import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_18352_특정거리의도시찾기 {

	public static class Node implements Comparable<Node>{
		int v, dis;

		public Node(int v, int dis) {
			super();
			this.v = v;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return this.dis -o.dis;
		}
		
		
	}
	
	static int N, M, K, X;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] list = new ArrayList[N+1];
		for(int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from].add( new Node(to, 1) );
		}
		
		int start = X;
		
		int dis[] = new int[N+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		boolean[] visited =new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dis[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.v]) continue;
			
			visited[cur.v] = true;
			
			for(Node next : list[cur.v]) {
				if(!visited[next.v] && 
						dis[next.v] > dis[cur.v] + next.dis){
					dis[next.v] = dis[cur.v] + next.dis;
					pq.offer(new Node(next.v, dis[next.v]));
				}
			}
		}
		
		boolean isChecked = false;
		for(int i = 1; i < N+1; i++) {
			if(dis[i] == K) {
				System.out.println(i);
				isChecked = true;
			}
		}
		if(!isChecked) System.out.println(-1);

	}

}
