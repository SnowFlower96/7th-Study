import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_BJ_4386_별자리만들기 {

	//다음으로 탐색할 별의 인덱스와 그 별과의 거리를 나타내는 클래스
	public static class Node implements Comparable<Node>{
		int to;
		double dis;

		public Node(int to, double dis) {
			super();
			this.to = to;
			this.dis = dis;
		}
		
		
		@Override
		public int compareTo(Node o) {
			return (int) (this.dis*100 - o.dis*100);
		}
		
		
	}

	
	// star에서는 현재 별의 위치를 표시
	public static class Star {
		double r, c;
		public Star(double r, double c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Star> star = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double r = Double.parseDouble(st.nextToken());
			double c = Double.parseDouble(st.nextToken());

			star.add(new Star(r, c));
		}

		ArrayList<Node>[] list = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 별에서 부터 별까지의 거리를 구하여 ArrayList에 저장한다.
		// 인덱스를 활용하여 구한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) {
					double fromR = star.get(i).r;
					double fromC = star.get(i).c;
					double toR = star.get(j).r;
					double toC = star.get(j).c;
					list[i].add(new Node(j, getDis(fromR, fromC, toR, toC)));
				}
			}
		}
		
		//PRIM알고리즘
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean visited[] = new boolean[N];

		pq.offer(new Node(0, 0));
		double result = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.to]) continue;
			
			result += cur.dis;
			
			visited[cur.to] = true;
			if(cnt++ == N) break;
			
			for(int i = 0 ; i < list[cur.to].size(); i++) {
				// 다음으로 방문할 정점 선택(방문하지 않는 정점 중에서)
				Node next = list[cur.to].get(i);
				if(visited[next.to]) continue;
				
				pq.offer(next);
			}
			
			
		}
		
		System.out.println(result);
	}
	
	private static double getDis(double fromR, double fromC, double toR, double toC) {
		double dis = Math.round(Math.sqrt(Math.pow(fromR - toR, 2) + Math.pow(fromC - toC, 2))  * 100 ) /100.0;
		return dis;
	}
}
