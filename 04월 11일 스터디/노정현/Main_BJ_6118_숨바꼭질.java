package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_6118_숨바꼭질 {

	static int N, M, ans;
	static ArrayList<Integer>[] list;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}

//		bfs(sb);
		dijkstra(sb, 1);
		
		System.out.println(sb);
	}

	@SuppressWarnings("unused")
	private static void bfs(StringBuilder sb) {
		Queue<Integer> queue = new LinkedList<>();
		int[] dists = new int[N + 1];
		boolean[] isVisited = new boolean[N + 1];

		queue.offer(1);
		isVisited[1] = true;

		int distance = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				int cur = queue.poll();
				dists[cur] = distance;

				for (int i = 0; i < list[cur].size(); i++) {
					if (isVisited[list[cur].get(i)])
						continue;

					isVisited[list[cur].get(i)] = true;
					queue.offer(list[cur].get(i));
				}
			}
			distance++;
		}

		int cnt = 0;
		int idx = 0;
		for(int i = 1; i <= N; i++) {
			if(dists[i] == distance - 1) {
				cnt++;
				if(idx == 0) idx = i;
			}
		}
		
		sb.append(idx).append(" ").append(distance - 1).append(" ").append(cnt);
	}

	@SuppressWarnings("unused")
	private static void dijkstra(StringBuilder sb, int start) {
		boolean[] isVisited = new boolean[N + 1];
		int[] dists = new int[N + 1];
		
		Arrays.fill(dists, Integer.MAX_VALUE);
		
		isVisited[start] = true;
		dists[start] = 0;
		
		for(int i = 0; i < list[start].size(); i++) {
			int to = list[start].get(i);
			if(!isVisited[to]) dists[to] = 1;
		}
		
		for(int a = 0; a < N - 1; a++) {
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			
			for(int i = 1; i <= N; i++) {
				if(!isVisited[i] && dists[i] != Integer.MAX_VALUE) {
					if(dists[i] < min) {
						min = dists[i];
						minIdx = i;
					}
				}
			}
			
			isVisited[minIdx] = true;
			
			for(int i = 0; i < list[minIdx].size(); i++) {
				int to = list[minIdx].get(i);
				if(!isVisited[to]) {
					if(dists[to] > dists[minIdx] + 1) dists[to] = dists[minIdx] + 1;
				}
			}
		}
		
		int cnt = 0;
		int max = 0;
		int idx = 0;
		for(int i = 1; i <= N; i++) {
			if(max < dists[i]) {
				cnt = 1;
				max = dists[i];
				idx = i;
			}
			else if(max == dists[i]) cnt++;
		}
		
		sb.append(idx).append(" ").append(max).append(" ").append(cnt);
	}
	
}
