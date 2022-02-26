import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main_BJ_2628_종이자르기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> width = new ArrayList<>();
		ArrayList<Integer> height = new ArrayList<>();
		int w = 0;
		int h = 0;
		for(int i = 0 ; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			int line = Integer.parseInt(st.nextToken()); // 0이면 가로 1이면 세로
			int num = Integer.parseInt(st.nextToken()); // 잘라야 하는 라인의 인덱스
			if(line == 0) {
				height.add(num);
			}else {
				width.add(num);
			}
		}
		Collections.sort(width);
		Collections.sort(height);
		
		
		int max_width = 0;
		int max_height = 0;
		int num_cnt = 0;
		int cnt = 0;
		for(int i = 1; i <= C; i++) {
			num_cnt++;
			if(cnt < width.size() && i == width.get(cnt)) {
				max_width = Math.max(max_width, num_cnt);
				cnt++;
				num_cnt = 0;
			}
		}
		max_width = Math.max(max_width, num_cnt);
		cnt = 0; num_cnt = 0;
		for(int i = 1; i <= R; i++) {
			num_cnt++;
			if(cnt < height.size() && i == height.get(cnt)) {
				max_height = Math.max(max_height, num_cnt);
				cnt++;
				num_cnt = 0;
			}
		}
		max_height = Math.max(max_height, num_cnt);
		System.out.println(max_height * max_width);
	}
}
//		방법 2.
//		int min_r = 0;
//		int max_r = R; 
//		int min_c = 0;
//		int max_c = C; 
//		
//		for(int i = 0 ; i< N; i++) {
//			st = new StringTokenizer(br.readLine());
//			int line = Integer.parseInt(st.nextToken()); // 0이면 가로 1이면 세로
//			int num = Integer.parseInt(st.nextToken()); // 잘라야 하는 라인의 인덱스
//			if(line == 0) {
//				if(num < min_r || num > max_r ) continue;
//				
//				if((max_r - min_r) / 2 > num) {
//					min_r = num;
//				}else {
//					max_r = num;
//				}
//			}else {
//				if((max_c - min_c) / 2 > num) {
//					min_c = num;
//				}else {
//					max_c = num;
//				}
//			}
//		}
//		
//		int result = (max_r - min_r) * (max_c - min_c);
//		
//		System.out.println(result);
//	}
//	
//	
//}
