package study0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17086_아기상어2 {

	static class Shark {
		int r;
		int c;
		
		public Shark(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M, map[][], distance[][];
	//상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static Queue<Shark> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		distance = new int[N][M];
		
		//입력 받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					q.offer(new Shark(i, j));
				}
			}
		}
		
		int ans = Integer.MIN_VALUE;	//안전거리 최대를 구해야해서 작은값으로 초기화
		
		while(!q.isEmpty()) {
			Shark shark = q.poll();
			
			int r = shark.r;
			int c = shark.c;
			
			for(int d=0; d<8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue; 	//경계에 벗어난다면
				if(distance[nr][nc]!=0 || map[nr][nc]==1) continue;		//이미 방문했거나 상어라면 
				distance[nr][nc] = distance[r][c] + 1;
				ans = Math.max(ans, distance[nr][nc]);
				q.offer(new Shark(nr, nc));
			}
		}
		
		System.out.println(ans);
	
	}
	
	

}
