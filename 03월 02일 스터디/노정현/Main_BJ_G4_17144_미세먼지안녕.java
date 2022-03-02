package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_G4_17144_미세먼지안녕 {

	static class Dust {
		int r, c, cnt;

		public Dust(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	
	// 상우하좌
	static final int[] clockDr = { -1, 0, 1, 0 };
	static final int[] clockDc = { 0, 1, 0, -1 };
	
	// 하우상좌
	static final int[] counterClockDr = { 1, 0, -1, 0 };
	static final int[] counterClockDc = { 0, 1, 0, -1 };
	
	static int R, C, T;
	static int dustCnt;
	static int[][] house;
	
	// 공기청정기 좌표(두번째 행)
	static int posR;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());  // 행
		C = Integer.parseInt(st.nextToken());  // 열
		T = Integer.parseInt(st.nextToken());  // 초
		house = new int[R][C];
				
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < C; j++) {
				int val = Integer.parseInt(st.nextToken());
				house[i][j] = val;
				
				// 공기청정기 좌표
				// 공기청정기는 항상 1열에 위치하고 두 칸의 행을 차지하므로 최종 값은 마지막행의 좌표
				if(val == -1) posR = i;
			}
		}
		
		System.out.println(solution());
		
		br.close();
	}

	private static int solution() {
		int result = 0;
		
		for(int time = 1; time <= T; time++) {
			spread();
			airCleaner();
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(house[i][j] > 0) result += house[i][j];
			}
		}
		
		return result;
	}
	
	/**
	 * 미세먼지 확산
	 * 원래 먼지가 있었던 장소에 대한 정보를 큐에 저장하고 poll
	 */
	private static void spread() {
		// 원래 먼지가 있었던 칸에 대한 정보를 저장하기 위한 큐
		Queue<Dust> queue = new LinkedList<>();
		
		// 먼지가 있었는지 확인
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				int val = house[i][j];
				
				if(val > 0) {
					queue.offer(new Dust(i, j, val));
				}
			}
		}
		
		// 큐를 비우면서 확인
		while(!queue.isEmpty()) {
			Dust cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;
			
			// 확산된 방향의 개수
			int dirCnt = 0;
			// 사방탐색
			for(int d = 0; d < 4; d++) {
				int nr = r + clockDr[d];
				int nc = c + clockDc[d];
				
				// 경계체크 및 공기청정기 위치 확인
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || house[nr][nc] == -1) continue;
				
				// 확산할 수 있으면 인접한 칸의 미세먼지 추가
				// 확산이 동시에 일어나기 때문에 먼저 저장된 먼지의 정보로 확인
				house[nr][nc] += cnt / 5;
				dirCnt++;
			}
			
			// 확산된 후 원래 칸의 미세먼지 계산
			house[r][c] -= cnt / 5 * dirCnt; 
		}
	}
	
	private static void airCleaner() {
		// 공기청정기 상단의 좌표
		int[] upP = new int[] { posR - 1, 0 };
		// 공기청정기 하단의 좌표
		int[] downP = new int[] { posR, 0};
		
		int dirUp = 0;
		// 공기청정기의 위쪽(반시계방향)
		// 위쪽부터 시계방향 탐색
		int[] curUp = upP.clone();
		curUp[0]--;
		while(true) {
			// 코너에 위치하면 방향 변경
			if((curUp[0] == 0 && curUp[1] == 0) || (curUp[0] == 0 && curUp[1] == C - 1) || (curUp[0] == upP[0] && curUp[1] == C - 1)) dirUp = (dirUp + 1) % 4;
			
			int nr = curUp[0] + clockDr[dirUp];
			int nc = curUp[1] + clockDc[dirUp];
			
			// 다음 위치에 공기청정기에 위치할 때
			if(nr == upP[0] && nc == upP[1]) {
				house[curUp[0]][curUp[1]] = 0;
				break;
			}
			
			// 먼지 이동
			house[curUp[0]][curUp[1]] = house[nr][nc];
			
			// 현재 위치 변경
			curUp[0] = nr;
			curUp[1] = nc;
			
		}		
		
		// 공기청정기의 아래쪽(시계방향)
		// 아래쪽부터 반시계방향 탐색
		int[] curDown = downP.clone();
		curDown[0]++;
		int dirDown = 0;
		while(true) {
			// 코너에 위치하면 방향 변경
			if((curDown[0] == R - 1 && curDown[1] == 0) || (curDown[0] == R - 1 && curDown[1] == C - 1) || (curDown[0] == downP[0] && curDown[1] == C - 1)) dirDown = (dirDown + 1) % 4;
			
			int nrUp = curDown[0] + counterClockDr[dirDown];
			int ncDown = curDown[1] + counterClockDc[dirDown];
			
			// 공기청정기에 위치할 때
			if(nrUp == downP[0] && ncDown == downP[1]) {
				house[curDown[0]][curDown[1]] = 0;
				break;
			}
			
			// 먼지 이동
			house[curDown[0]][curDown[1]] = house[nrUp][ncDown];
			
			// 현재 위치 변경
			curDown[0] = nrUp;
			curDown[1] = ncDown;
			
		}

		// 공기 청정기 위치에 있던 먼지 제거
		house[upP[0]][upP[1]] = -1;
		house[downP[0]][downP[1]] = -1;
	}

}
