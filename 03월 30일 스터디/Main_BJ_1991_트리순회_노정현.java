package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1991_트리순회 {

	static class Node {

		int left, right;

		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}

		public boolean isLeef() {
			return (left < 0 && right < 0) ? true : false;
		}

		@Override
		public String toString() {
			return "Node [left=" + left + ", right=" + right + "]";
		}

	}

	static int N;
	static Node[] nodeArr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		nodeArr = new Node[N + 1];
		for (int i = 1; i < N + 1; i++) {
			char[] line = br.readLine().replace(" ", "").toCharArray();
			int parent = line[0] - 'A' + 1;
			int left = line[1] - 'A' + 1;
			int right = line[2] - 'A' + 1;
			nodeArr[parent] = new Node(left, right);
		}

		preorder(1);
		sb.append("\n");

		visited = new boolean[N];
		inorder(1);
		sb.append("\n");

		visited = new boolean[N];
		postorder(1);

		System.out.println(sb);
	}

	private static void preorder(int idx) {
		// 현재 노드가 .(음수)일 때
		if (idx < 0)
			return;

		int left = nodeArr[idx].left;
		int right = nodeArr[idx].right;

		// 방문기록
		sb.append(String.valueOf((char) (idx + 'A' - 1)));

		if (left > 0)
			preorder(left);

		if (right > 0)
			preorder(right);
	}

	private static void inorder(int idx) {
		if (idx < 0)
			return;

		int left = nodeArr[idx].left;
		int right = nodeArr[idx].right;

		if (left > 0)
			inorder(left);
		
		// 방문기록
		sb.append(String.valueOf((char) (idx + 'A' - 1)));
		
		if (right > 0)
			inorder(right);

	}

	private static void postorder(int idx) {
		if (idx < 0)
			return;
		
		int left = nodeArr[idx].left;
		int right = nodeArr[idx].right;
		
		if (left > 0)
			postorder(left);
				
		if (right > 0)
			postorder(right);
		
		// 방문기록
		sb.append(String.valueOf((char) (idx + 'A' - 1)));
	}

}
