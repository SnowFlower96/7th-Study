import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2512_예산 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int max = 0;
		int totalSum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] numList = new int[N];
		
		for(int i = 0 ; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			totalSum += num;
			numList[i] = num;
		}
		Arrays.sort(numList);
		System.out.println(Arrays.toString(numList));
		
		int M = Integer.parseInt(br.readLine());

		if(totalSum <= M) {
			System.out.println(numList[N-1]);
			System.exit(0);
		}
		
		int maximum = 0;
		for(int i = 0 ; i < N; i++) {
			int check = numList[i]*(N-i);
			//만약 남은 값들의 합이 M의 값보다 크게되면 상한 값이 필요하게된다.
			if(max + check >= M) {
				//이경우 남은 값들을 남은 숫자로 나누게 되면 그 값이된다.
				maximum = (M - max) / (N-i);
				break;
			}
			else {
				max += numList[i];
			}
		}
		
		System.out.println(maximum);
		
		
		
	}

}
