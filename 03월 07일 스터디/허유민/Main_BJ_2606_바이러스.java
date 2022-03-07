package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2606_바이러스 {

	static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
	}

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 컴퓨터수 (정점)
		N = Integer.parseInt(br.readLine());

		// 컴퓨터 수 만큼 생성
		Node[] list = new Node[N + 1];

		// 연결된 컴퓨터쌍(간선)
		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			list[from] = new Node(to, list[from]);
			// list[to] = new Node(from, list[to]);
		}

		System.out.println(bfs(list, 1));

	}

	public static int bfs(Node[] list, int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			// current 정점의 인접정점 처리(단, 방문하지 않은 인접정점만)
			for (Node temp = list[current]; temp != null; temp = temp.link) {
				if (!visited[temp.vertex]) {
					queue.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
		
		// 1번을 제외한 방문한곳을 카운트
		int cnt = 0;
		for (int i = 2; i < visited.length; i++) {
			if (visited[i]) {
				cnt++;
			}
		}
		return cnt;
	}

}
