package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석_허유민 {

	// N 극은 0 , S 극은 1
	static int K;
	static int[][] map;
	static int[][] rotation_n;
	static boolean[] visited;
	static int[] head;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			K = Integer.parseInt(br.readLine());
			
			map = new int[4][8];
			head = new int[4];

			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			rotation_n = new int[K][2];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				rotation_n[i][0] = Integer.parseInt(st.nextToken());
				rotation_n[i][1] = Integer.parseInt(st.nextToken());

				visited = new boolean[4];

				rotation(rotation_n[i][0] - 1, rotation_n[i][1]); // 0은 자석 번호 1 ~ 4, 1은 자석 회전방향 1 혹은 -1
			}
			
			int sum = 0;
			int mul = 1;
            for (int i = 0; i < 4; i++) {
                if (map[i][head[i]] == 1) {
                    sum += mul;
                }
                mul *= 2;
            }
            
            sb.append("#").append(testcase).append(" ").append(sum).append("\n");

		}
		System.out.println(sb);

	}

	public static void rotation(int n, int d) {

		if (visited[n]) {
			return;
		}
		visited[n] = true;

		if (n - 1 >= 0 && map[n - 1][(head[n - 1] + 2) % 8] != map[n][(head[n] + 6) % 8]) {
			rotation(n - 1, -d);
		}
		if (n + 1 < 4 && map[n][(head[n] + 2) % 8] != map[n + 1][(head[n + 1] + 6) % 8]) {
			rotation(n + 1, -d);
		}

		if (d == 1) {
			head[n] = (head[n] + 7) % 8;
		} else if (d == -1) {
			head[n] = (head[n] + 1) % 8;
		}

	}

}
