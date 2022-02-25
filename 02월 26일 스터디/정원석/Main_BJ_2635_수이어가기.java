import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2635_수이어가기 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		StringBuilder max_sb = new StringBuilder();
		int max_cnt = -1;;
		
		for(int i = 1; i <= N; i++) {
			num_cnt = 2;
			sb.append(N + " " + i + " ");
			sub(N, i);
			if(max_cnt > num_cnt) {
				sb.setLength(0);
			}else {
				max_cnt = num_cnt;
				max_sb.setLength(0);
				max_sb.append(sb.toString());
				sb.setLength(0);
			}
		}
		System.out.println(max_cnt);
		System.out.println(max_sb);
		
	}
	
	static StringBuilder sb;
	static int num_cnt;
	public static void sub(int first, int second) {
		
		if(first - second < 0) {
			return;
		}
		num_cnt++;
		
		sb.append((first - second) + " ");
		sub(second, (first - second));
		
	}

}
