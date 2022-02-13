package personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Main_BJ_2667_단지번호붙이기_허유민 {

	static int count, N;
	// up right down left
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] map;
	static boolean[][] visited;
	static Stack<Integer> stack = new Stack<Integer>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<Integer>();

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine(); // 한줄받아오기
			String[] arr = s.split(""); // s를 하나씩 잘라서 String배열에 저장
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(arr[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					count =1; //탐색 시작 이기 때문에 1로 초기화
					dfs(i, j);
					list.add(count); // 단지내에 아파트 수를 리스트에 넣는다.
				}
			}
		}

		Collections.sort(list); // 컬렉션의 정렬을 활용하여 오름차순 정렬
		System.out.println(list.size()); // 사이즈를 활용해서 단지 개수 체크
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)); // 정렬된 아파트 수를 출력
		}

	}

	public static void dfs(int r, int c) {
		visited[r][c] = true;

		// 사방탐색해서 방문을 체크하면서 간다 결국 다 탐색을 계속하다 보면 시작좌표에서
		// 이어져있는 곳들을 방문을 트루로 바꾸고 끝나고나면 list에 단지를 입력한다.
		for (int i = 0; i < 4; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];
			if (nr < N && nr >= 0 && nc < N && nc >= 0 && map[nr][nc] == 1 && !visited[nr][nc]) {
				count++;
				dfs(nr, nc);
			}
		}

	}

}
