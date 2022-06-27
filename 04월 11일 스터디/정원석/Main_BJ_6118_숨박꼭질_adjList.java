import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_6118_숨박꼭질_adjList {

	static int N, maxDistance, cnt, minNum;
	static Barn[] adjList;
	static Queue<Integer> maxBarn;
	
	
	//헛간을 연결하기 위해서 Barn이라는 class만듬
	static class Barn{
		int barn, distance;
		Barn link;
		
		
		public Barn(int barn, Barn link, int distance) {
			
			this.barn = barn;
			this.link = link;
			this.distance = distance;
		}


		@Override
		public String toString() {
			return "Barn [barn=" + barn + ", distance=" + distance + ", link=" + link + "]";
		}

		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adjList = new Barn[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			adjList[A] = new Barn(B, adjList[A], 0);
			adjList[B] = new Barn(A, adjList[B], 0);
		}
		maxBarn = new LinkedList<>();
		minNum = Integer.MAX_VALUE;
		bfs();
		
		for(Barn b : adjList) {
			for(Barn temp = b; temp != null; temp = temp.link) {
				//거리가 max인 헛간을 찾을때마다 cnt에 +1Tlr
				if(temp.distance==maxDistance) {
					cnt++;
					// 가장 번호가 작은 헛간 저장
					minNum = Math.min(minNum, temp.barn);
				}
			}
		}
		
		
		System.out.println(minNum + " " + maxDistance + " " + cnt);
	}

	private static void bfs() {
		
		Queue<Integer> queue = new LinkedList<>();
		
		boolean visited[] = new boolean[N+1];

		queue.offer(1);
		visited[1] = true;
		while (true) {
			
			int qSize = queue.size();
			//레벨별 탐색 진행
			for (int j = 0; j < qSize; j++) {
				int cur = queue.poll();
				for(Barn temp = adjList[cur]; temp != null; temp = temp.link) {

					if (visited[temp.barn]) continue;
					visited[temp.barn] = true;
					temp.distance = maxDistance + 1;
					queue.offer(temp.barn);
				}
			}
			
			if(queue.isEmpty()) break;
			// 레벨별 탐색이 끝날때마다 거리를 +1 씩 만듬 
			maxDistance++;
		}

	}

}
