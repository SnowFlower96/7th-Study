import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14621_나만안되는연애 {

	static int N, M;
	
	public static class road implements Comparable<road>{
		int to, w;

		public road(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(road o) {
			return this.w - o.w;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 학교의 수
		N = Integer.parseInt(st.nextToken());
		// 학교를 연결하는 도로의 개수
		M = Integer.parseInt(st.nextToken());
		
		char[] gender = new char[N+1];
		
		ArrayList<road>[] roadList = new ArrayList[N+1];
		for(int i = 0 ; i < N+1; i++) {
			roadList[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i < N+1 ;i++) {
			gender[i] = st.nextToken().charAt(0);
		}
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			roadList[from].add(new road(to, w));
			roadList[to].add(new road(from, w));
		}
		
		PriorityQueue<road> pq = new PriorityQueue<>();
		pq.offer(new road(1, 0));
		
		boolean visited[] = new boolean[N+1];
		
		int result = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			road cur = pq.poll();
			
			
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			result += cur.w;
			
			if(++cnt == N) break;
			
			for(int i = 0; i < roadList[cur.to].size(); i++) {

				road next = roadList[cur.to].get(i);
				
				if(visited[next.to] || gender[next.to] == gender[cur.to]) continue;

				pq.offer(next);

			}
		}
		System.out.println(cnt != N ? -1 : result);
		
		
	}

}
