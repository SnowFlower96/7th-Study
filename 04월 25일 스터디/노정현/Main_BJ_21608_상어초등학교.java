package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 스터디
 * @author jhno96
 * @date 2022. 4. 23.
 */
public class Main_BJ_21608_상어초등학교 {

	static class Point implements Comparable<Point> {
		int r, c, cnt, blank;

		public Point(int r, int c, int cnt, int blank) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.blank = blank;
		}

		@Override
		public int compareTo(Point o) {
			// 1. 좋아하는 학생이 많은 순으로 정렬
			if (this.cnt != o.cnt) {
				return o.cnt - this.cnt;
			} else {
				// 2. 좋아하는 학생이 같으면 비어있는 칸을 기준으로 정렬
				if(this.blank != o.blank) return o.blank - this.blank;
				// 3. 빈칸의 수도 같으면 행 -> 열로 정렬
				else return this.r != o.r ? this.r - o.r : this.c - o.c;
			}
		}

		@Override
		public String toString() {
			return "[(r=" + r + ", c=" + c + "), " + cnt + ", " + blank + "]";
		}

	}

	static int N;
	static int[][] map;
	static int[][] favor;
	static List<Point> list;

	// 시계방향
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		favor = new int[N * N + 1][4];
		list = new ArrayList<>();

		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());

			for (int j = 0; j < 4; j++) {
				favor[student][j] = Integer.parseInt(st.nextToken());
			}
			
			// 첫 번째 학생은 무조건 (1, 1)에 배치
			if(i == 0) {
				map[1][1] = student;
				continue;
			}
			
			list.clear();
			getSeat(student);
		}
		
		System.out.println(getScore());
	}

	private static void getSeat(int student) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 학생이 배치가 된 곳이면 다음 위치 탐색
				if(map[i][j] != 0) continue;
				
				Point p = getFavorBlank(i, j, student);
				
				list.add(p);
			}
		}
		
		// 문제의 조건에 따라 정렬
		Collections.sort(list);
		
		Point p = list.get(0);
		map[p.r][p.c] = student;
	}

	// 학생들의 만족도 계산
	private static int getScore() {
		int score = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int student = map[i][j];

				Point p = getFavorBlank(i, j, student);

				if (p.cnt == 0)
					score += 0;
				else if (p.cnt == 1)
					score += 1;
				else if (p.cnt == 2)
					score += 10;
				else if (p.cnt == 3)
					score += 100;
				else if (p.cnt == 4)
					score += 1000;
			}
		}

		return score;
	}

	// 해당 칸과 학생을 기준으로 좋아하는 학생의 수와 빈칸의 수를 저장한 객체 반환 
	private static Point getFavorBlank(int r, int c, int student) {
		int cnt = 0;
		int blank = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!checkBorder(nr, nc))
				continue;

			// 빈칸 수 계산
			if(map[nr][nc] == 0) blank++;
			
			// 좋아하는 학생의 수 계산
			for (int s : favor[student]) {
				if (map[nr][nc] == s)
					cnt++;
			}
		}
		return new Point(r, c, cnt, blank);
	}

	// 경계 체크
	private static boolean checkBorder(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N ? true : false;
	}

}
