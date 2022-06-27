import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_1197_최소스패닝트리 {

	public static class Node implements Comparable<Node>{
		
		int to, w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public String toString() {
			return "V [ to=" + to + ", w=" + w + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + to;
			result = prime * result + w;
			return result;
		}

		//가중치를 기준으로 삼는다.
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}

		
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[V+1]; //신장트리에 선택된 여부
		
		// 정점에 연결된 정점들을 Node로 저장한다.
		ArrayList<Node>[] list = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			// from, to 에서 시작해서 to, from으로
			list[from].add(new Node(to, w));
			list[to].add(new Node(from, w));
		}
		
		//우선순위큐를 사용하여 가장 w가 작은 간선을 가장 앞의 인덱스로 넣는다.
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		int result = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			
			//가장 작은 w를 가지고 있는 정점이 방문한 정점인지 아닌지 확인.
			if(visited[cur.to]) continue;
			
			//result에 가중치 저장
			result += cur.w;
			//방문처리
			visited[cur.to] = true;
			
			//만약 정점을 다 돌면 while문에서 탈출
			if(++cnt == V) break;
			
			for (int i = 0; i < list[cur.to].size(); i++) {
				// 다음으로 방문할 정점 선택(방문하지 않는 정점 중에서)
				Node next = list[cur.to].get(i);
				if(visited[next.to]) continue;
				
				pq.offer(next);
			}
			
		}
		
	
		System.out.println(result);

	}

}
