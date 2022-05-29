package study0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2615_오목 {
	
	//상 우상 우 우하 하 좌하 좌 좌상
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] map;
	static int size = 19;
	static int nr, nc;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[size][size];
		
		//입력받기
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		loop:for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j] != 0) {	//빈칸이 아니면
					if(check(i, j, map[i][j])) {	//오목일 경우
						if(nc == j) {	//세로로 이어진 오목인 경우
							System.out.println(map[i][j]);
							System.out.print(i-3+" ");
							System.out.print(j+1);
							break loop;
						}else {	//그 외 경우
							System.out.println(map[i][j]);
							System.out.print(i+1+" ");
							System.out.print(j+1);
							break loop;
							
						}
					}
					
				}
				if(i==size-1 && j==size-1) System.out.println(0); 	//끝까지 찾았는데 오목 없는 경우
			}
		}
	}

	private static boolean check(int i, int j, int color) {
		
		for(int d=0; d<4; d++) {
			int cnt = 1;
		
			int r = i;
			int c = j;
			
			int beforeR = i + dr[d+4];
			int beforeC = j + dc[d+4];
			
			while(true) {
				
				nr = r + dr[d];
				nc = c + dc[d];
				
				if( isBoundaryOut(nr, nc)|| map[nr][nc] != color) break;
				cnt++;
				
				r = nr;
				c = nc;

				if(cnt==5) {
					int frontNr = nr + dr[d];
					int frontNc = nc + dc[d];
					if(!isBoundaryOut(frontNr, frontNc)) {
						if(map[frontNr][frontNc] != color) {
							if(isBoundaryOut(beforeR, beforeC) || map[beforeR][beforeC] != color) {
								return true;
							}
						}
					}else {
						
						if(isBoundaryOut(beforeR, beforeC) || map[beforeR][beforeC] != color) {
							return true;
						}
					}
					
				}
			}
			
		}
		return false;
	}

	//경계 체크
	private static boolean isBoundaryOut(int r, int c) {
		if(r<0 || r>=size || c<0 || c>=size) return true;
		else return false;
		
	}
}
