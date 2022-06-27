import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BJ_2156_포도주시식 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int D[] = new int[n + 1];
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		
		
		D[0] = 0;
		for (int i = 1; i <= n; i++) {
			
			if (i == 1) {
				D[1] = arr[0];
			}
			else if (i == 2) {
				D[2] = arr[0] + arr[1];
			} 
			else {
				int case1 = D[i - 3] + arr[i - 2] + arr[i - 1];
				int case2 = D[i - 2] + arr[i - 1];
				int case3 = D[i - 1];
				D[i] = Math.max(Math.max(case1, case2), case3);
			}
		}
		System.out.println(D[n]);
	}

}
