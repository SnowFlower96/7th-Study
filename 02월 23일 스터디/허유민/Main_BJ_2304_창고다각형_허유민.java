package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Main_BJ_2304_창고다각형_허유민 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 2차원 배열
		int[][] map = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 정수 L기준으로 정렬
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		System.out.println(Arrays.deepToString(map));
		
		int maxH = 0;
		int maxi = 0;
		for (int i = 0; i < N; i++) {
			if(map[i][1] > maxH) {
				maxH = map[i][1];
				maxi = i;
			}
		}
		
		// 왼쪽에서 부터 최대 높이까지 더해나간다.
		int cnt = 0;
		for (int i = 0; i < maxi; i++) {
			for (int j = i+1; j <= maxi; j++) {
				if(map[i][1] <= map[j][1]) {
					cnt += (map[j][0] - map[i][0]) * map[i][1];
					i = j;
				}
			}
		}
		
		// 오른쪽에서 부터 최대 높이까지 더해나간다.
		for (int i = N-1; i > maxi; i--) {
			for (int j = i-1; j >= maxi; j--) {
				if(map[i][1] <= map[j][1]) {
					cnt += (map[i][0] - map[j][0]) * map[i][1];
					i = j;
				}
			}
		}
		// 최대 높이 기둥을 더한다.
		System.out.println(cnt+maxH);
	}

}
