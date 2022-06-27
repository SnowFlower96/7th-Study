package study0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2512_예산 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int ans = 0;
		int start = 0;
		int end = arr[N-1];
		
		long budget = Integer.parseInt(br.readLine());	//총 예산
		
		while(start <= end) {	//start가 end보다 작아야하기 때문에
			int mid = (start+end)/2;
			long sum = 0;
			for(int i=0; i<N; i++) {
				if(arr[i] > mid) {	//요청한 값 줄 수 없음
					sum += mid;
				}
				else {	//요청한 값 줄 수 있음
					sum += arr[i];
				}
			}
			//범위 좁히기
			if(sum <= budget) start = mid+1;	//시작점 올리기
			else {
				end = mid - 1;	//끝나는 점 내리기
//				ans = Math.max(end, ans);	
			}
		}
		System.out.println(end);
		
		
	}

}
