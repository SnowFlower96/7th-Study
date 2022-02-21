package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2559_수열 {

	static int N, K, MAX;
	static int[] input;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new int[N];
		numbers = new int[K];
		MAX = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		// 매번 K개의 합을 구하다보니 시간이 높게나옴
//		for (int i = 0; i <= N-K; i++) {
//			int sum = 0;
//			for (int j = i; j < i+K; j++) {
//				sum += input[j];
//			}
//			MAX = Math.max(MAX, sum);
//		}

		//permutation(0, 0);
		//System.out.println(MAX);
		twopointer();
	}

	// 수열을 만들어서 풀었는데 시간 초과 발생
	public static void permutation(int cnt, int flag) {
		if (cnt == K) {
			int sum = 0;
			for (int i = 0; i < K; i++) {
				sum += numbers[i];
			}
			MAX = Math.max(MAX, sum);
			return;
		}
		
		// 입력받은 모든 수를 현재 자리에 넣어보기
		for (int i = 0; i < N; i++) {
			// 기존자리의 수들과 중복되는지 체크
			if ((flag & 1 << i) != 0)
				continue;
			numbers[cnt] = input[i];
			// 다음수 뽑으러가기
			permutation(cnt + 1, flag | 1 << i);
		}
	}
	
	// 처음에 K개의 합을 구하고 구간의 시작의 시작은빼고 다음 숫자를 더하는
	// 한칸씩 이동하며 연산을 하므로 시간을 줄일수있다.
	public static void twopointer() {
		int sum = 0;
		for (int i = 0; i < K; i++) {
			sum += input[i];
		}
		int max = sum;
		for (int i = K; i < N; i++) {
			sum = sum - input[i-K] + input[i];
			max = Math.max(sum, max);
		}
		System.out.println(max);
	}

}
