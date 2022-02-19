import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10163_색종이_정원석 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int paper[] = new int[N];
		
		int map[][] = new int[1001][1001];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()); // x좌표
			int r = Integer.parseInt(st.nextToken()); // y좌표
			int w = Integer.parseInt(st.nextToken()); // 너비
			int h = Integer.parseInt(st.nextToken()); // 높이
			for(int dr = r; dr < r + h; dr++ ) {
				for(int dc = c; dc < c + w; dc++) {
					map[dr][dc] = i;
				}
			}
		}
		
		for(int t = 1 ; t <= N; t++) {
			for(int i = 0 ; i < 30; i++) {
				for(int j = 0; j < 30; j++) {
					if(map[i][j] == t)
						paper[t-1]++;
				}
			}
			sb.append(paper[t-1] + "\n");
		}
		System.out.println(sb);
	}

}
