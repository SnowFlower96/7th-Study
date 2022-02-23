package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_S2_2304_창고다각형 {

	static class Bar implements Comparable<Bar> {

		int x; // 좌표
		int h; // 높이

		public Bar(int x, int h) {
			super();
			this.x = x;
			this.h = h;
		}

		// 좌표 기준으로 정렬
		@Override
		public int compareTo(Bar o) {
			return this.x - o.x;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", h=" + h + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 값 입력
		int N = Integer.parseInt(br.readLine());
		Bar[] bars = new Bar[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			bars[i] = new Bar(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		// 좌표를 기준으로 오름차순 정렬
		Arrays.sort(bars);
		
		System.out.println(solution(N, bars));

		br.close();
	}

	private static int solution(int N, Bar[] bars) {
		int result = 0;
		int max_idx = 0;
		
		// 왼쪽에서부터
		Bar maxBarL = bars[0];
		for(int i = 1; i < N; i++) {
			if(bars[i].h > maxBarL.h) {
				result += maxBarL.h * (bars[i].x - maxBarL.x);
				maxBarL = bars[i];
				max_idx = i;
			}
		}
		
		// 오른쪽에서부터
		Bar maxBarR = bars[N - 1];
		for(int i = N - 2; i >= max_idx; i--) {
			if(bars[i].h > maxBarR.h) {
				result += maxBarR.h * (maxBarR.x - bars[i].x);
				maxBarR = bars[i];
			}
		}
		
		// 가운데 추가
		result += maxBarL.h * (maxBarR.x - maxBarL.x + 1);
		
		return result;
	}

}
