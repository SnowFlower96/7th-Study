package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BJ_2210_숫자판점프 {

	// 상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static HashSet<String> answer;
	//static ArrayList<String> list;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 5X5 크기 고정
		answer = new HashSet<String>();
		//list = new ArrayList<String>();
		map = new int[5][5];
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				dfs(i,j,0,"");
			}
		}
		System.out.println(answer.size()); // 15
		//System.out.println(list.size()); // 37332

	}
	
	public static void dfs(int r, int c, int current, String s) {
		
		if(current == 6) { 
			answer.add(s);
			//list.add(s);
			return;
		}
		
		// 이 숫자판의 임의의 위치에서 시작해서, 인접해 있는 네 방향으로 다섯 번 이동하면서, 각 칸에 적혀있는 숫자를 차례로 붙이면 6자리의 수가 된다.
		// 자기자신을 포함해서 상하좌우 그리고 한번더 해서 총 6자리의 수 만들어진 수로 무엇을 하는게 아니라 단순히 hashset에 넣어 개수만 세면 되는 문제
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 경계체크
			if (nr < 0 || nr >= 5 || nc <0 || nc >= 5) {
				continue;
			}
			dfs(nr,nc,current+1,s+map[r][c]);
		}
	}

}
