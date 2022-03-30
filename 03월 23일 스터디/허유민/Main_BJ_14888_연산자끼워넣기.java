package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14888_연산자끼워넣기 {

	static int N, MAX, MIN;
	static int[] arr;
	static int[] op;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		op = new int[4];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		// MAX, MIN 초기화
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;

		permutation(1, arr[0]);
		System.out.println(MAX);
		System.out.println(MIN);
		
	}

	public static void permutation(int cnt, int num) {
		// 종료조건 cnt를 1부터 시작하는건 첫번째 숫자를 넣고 시작하기 때문이다. num에 arr[0]을 입력으로 시작
		// 재귀를 반복할때마다 cnt값이 증가하여 N과 같으면 입력받은 N개의 숫자를 모두 연산한것이기 때문에 최대값과 최소값을 찾는다.
		if (cnt == N) {
			MAX = Math.max(num, MAX);
			MIN = Math.min(num, MIN);
			return;
		}

		// 연산자에 맞게 더하거나 빼거나 곱하거나 나누기를 하며 재귀호출
		for (int i = 0; i < 4; i++) {
			if(op[i] > 0 && i==0) {
				op[i]--; // 재귀 호출할때는 연산자를 빼기해서 감소시켰다가
				permutation(cnt + 1, num + arr[cnt]);
				op[i]++; // 재귀가 끝난 뒤에는 다시 증가시켜 준다.
			}else if (op[i] > 0 && i==1) {
				op[i]--;
				permutation(cnt + 1, num - arr[cnt]);
				op[i]++;
			}else if (op[i] > 0 && i==2) {
				op[i]--;
				permutation(cnt + 1, num * arr[cnt]);
				op[i]++;
			}else if (op[i] > 0 && i==3) {
				op[i]--;
				permutation(cnt + 1, num / arr[cnt]);
				op[i]++;
			}
			
		}
	}

}
