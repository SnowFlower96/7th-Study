package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_S1_2527_직사각형 {

	static class Square {
		int x, y, p, q;

		public Square(int x, int y, int p, int q) {
			super();
			this.x = x;
			this.y = y;
			this.p = p;
			this.q = q;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine());
			Square s1 = new Square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Square s2 = new Square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// 공통부분이 없을 때
			if ((s1.x > s2.p) || (s1.p < s2.x) || (s1.y > s2.q) || (s1.q < s2.y))
				sb.append("d\n");

			// 겹치는 부분이 점일 경우
			else if ((s1.x == s2.p && s1.y == s2.q) || (s1.p == s2.x && s1.q == s2.y) || (s1.x == s2.p && s1.q == s2.y)
					|| (s1.p == s2.x && s1.y == s2.q))
				sb.append("c\n");

			// 겹치는 부분이 선분일 경우
			else if ((s1.x == s2.p) || (s1.p == s2.x) || (s1.y == s2.q) || (s1.q == s2.y))
				sb.append("b\n");

			// 겹치는 부분이 직사각형인 경우
			else
				sb.append("a\n");
		}

		System.out.println(sb);

		br.close();
	}

}
