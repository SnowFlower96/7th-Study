import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_BJ_9372_상근이의여행 {

	//현재 국가에서 다른 국가로 가는 것을 표현
	public static class Node implements Comparable<Node>{
		int to, w;

		public Node(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {		
			return this.w - o.w;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		
		for(int tc = 0 ; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			ArrayList<Node>[] list = new ArrayList[N+1];

			for(int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			
			
			
			for(int i = 0; i < M; i ++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				//비행기에 다른 가중치값이 없으므로 가중치를 1로 둔다.
				list[to].add(new Node(from, 1));
				list[from].add(new Node(to, 1));
			}
			
			//우선 순위 큐를 활용하여 Prim알고리즘을 사용한다.
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(1, 0));
			
			int result = 0;
			int cnt = 0;
			
			boolean visited[] = new boolean[N+1];
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				//가장 작은 가중치를 가지고 있는 국가가 방문한 국가인지 아닌지 확인.
				if(visited[cur.to]) continue;
				
				//result에 가중치 저장
				result += cur.w;
				
				//방문처리
				visited[cur.to] = true;
				
				//만약 국가를 다 돌면 while문에서 탈출
				if(++cnt == N) break;
				
				for (int i = 0; i < list[cur.to].size(); i++) {
					// 다음으로 방문할 국가 선택(방문하지 않는 국가 중에서)
					Node next = list[cur.to].get(i);
					if(visited[next.to]) continue;
					
					pq.offer(next);
				}
			}
			
			System.out.println(result);
			
		}

	}

}
