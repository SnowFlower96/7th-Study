package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Study
 * @author jhno96
 * @date 2022. 4. 14.
 */
public class Main_BJ_1446_지름길 {

	static class Node implements Comparable<Node>{
		
		int from, to, dist;

		public Node(int from, int to, int dist) {
			super();
			this.from = from;
			this.to = to;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", dist=" + dist + "]";
		}

		@Override
		public int compareTo(Node o) {
			if(this.from == o.from) return this.to - o.to;
			return this.from - o.from;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		List<Node> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if(to > D || to - from <= dist) continue;
			
			list.add(new Node(from, to, dist));
		}
		
		Collections.sort(list);

		System.out.println(solution(N, D, list));
	}

	private static int solution(int N, int D, List<Node> list) {		
		int[] dist = new int[D + 1];
		Arrays.fill(dist, Integer.MAX_VALUE / 2);
		dist[0] = 0;
		
		int cur = 0, idx = 0;
		while(cur < D) {
			if(idx < list.size()) {
				Node curPath = list.get(idx);
				if(cur == curPath.from) {
					dist[curPath.to] = Math.min(dist[cur] + curPath.dist, dist[curPath.to]);
					idx++;
				} else {
					dist[cur + 1] = Math.min(dist[cur + 1], dist[cur] + 1);
					cur++;
				}
			} else {
				dist[cur + 1] = Math.min(dist[cur + 1], dist[cur] + 1);
				cur++;
			}
		}
		
		return dist[D];
	}

}
