package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * Study
 * @author jhno96
 * @date 2022. 4. 17.
 */
public class Main_BJ_14225_부분수열의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N]; 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		boolean[] number = new boolean[20 * 100000 + 1];
		comb(arr, new boolean[N], number, 0);
		
		for (int i = 0; i < number.length; i++) {
			if(!number[i]) {
				System.out.println(i);
				return;
			}
		}
	}
	
	private static void comb(Integer[] arr, boolean[] selected, boolean[] number, int idx) {
		if(idx == arr.length) {
			int sum = 0;
			for(int i = 0; i < selected.length; i++) {
				if(!selected[i]) sum += arr[i];
			}
			number[sum] = true;
			return;
		}
		
		selected[idx] = true;
		comb(arr, selected, number, idx + 1);
		
		selected[idx] = false;
		comb(arr, selected, number, idx + 1);
	}

}
