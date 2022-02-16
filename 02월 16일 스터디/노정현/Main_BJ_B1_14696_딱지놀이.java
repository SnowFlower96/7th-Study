package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_B1_14696_딱지놀이 {

	static class Round {
		
		int star;
		int circle;
		int square;
		int triangle;
		
		public void increase(int val) {
			switch (val) {
			case 4:
				this.star++;
				break;

			case 3:
				this.circle++;
				break;

			case 2:
				this.square++;
				break;

			case 1:
				this.triangle++;
				break;

			default:
				break;
			}
		}
		
		// 승 : 1, 무승부 : 0, 패배 : -1
		public int getResult(Round R) {
			// 별이 다르다면
			if(this.star != R.star) {
				return this.star > R.star ? 1 : -1;
			}
			// 별이 같다면
			else {
				// 동그라미다 다르다면
				if(this.circle != R.circle) {
					return this.circle > R.circle ? 1 : -1;
				}
				// 동그라미가 같다면
				else {
					// 사각형이 다르다면
					if(this.square != R.square) {
						return this.square > R.square ? 1 : -1;
					}
					// 사각형이 같다면
					else {
						// 세모가 다르다면
						if(this.triangle != R.triangle) {
							return this.triangle > R.triangle ? 1 : -1;
						}
						// 세모도 같다면 무승부
						else {
							return 0;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 라운드 수
		for (int i = 0; i < N; i++) {
			// A의 딱지 입력
			Round A = new Round();
			st = new StringTokenizer(br.readLine());
			int num_A = Integer.parseInt(st.nextToken());
			for (int n = 0; n < num_A; n++) {
				int val = Integer.parseInt(st.nextToken());
				A.increase(val);
			}

			// B의 딱지 입력
			Round B = new Round();
			st = new StringTokenizer(br.readLine());
			int num_B = Integer.parseInt(st.nextToken());
			for (int n = 0; n < num_B; n++) {
				int val = Integer.parseInt(st.nextToken());
				B.increase(val);
			}
			
			int result = A.getResult(B);
			if(result == 1) sb.append("A").append("\n");
			else if(result == 0) sb.append("D").append("\n");
			else if(result == -1) sb.append("B").append("\n");
		}

		// 결과 출력
		System.out.println(sb.toString());

		br.close();
	}

}
