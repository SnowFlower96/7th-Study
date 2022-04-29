import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BJ_21608_상어초등학교 {

	static int N, sum;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		Map<Integer, int[]> favor = new HashMap<>();
		sum = 0;
		for(int i = 0 ; i < N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			int favorite[] = new int[4]; 
			for(int j = 0 ; j < 4; j++) {
				favorite[j] = Integer.parseInt(st.nextToken());
			}
			
			favor.put(student, favorite);
			
			choice(student, favorite);
		}
		for(int i = 0;  i < N; i++) {
			for(int j = 0; j < N; j++) {
				int check = stuNum(i, j, map[i][j], favor.get(map[i][j]));
//				System.out.println(check);
				if (check == 0)
					sum += 0;
				else if (check == 1)
					sum += 1;
				else if (check == 2)
					sum += 10;
				else if (check == 3)
					sum += 100;
				else
					sum += 1000;
			}
		}
		System.out.println(sum);
	}

	private static void choice(int student, int[] favorite) {
		// 선호 학생 주변에 얼마나 있는지 카운트
		int maxNum = 0;
		// 주변에 빈칸 카운트
		int maxBla = 0;
		// 들어올 값의 좌표 저장할 변수
		int r = 0;
		int c = 0;
		boolean check = false;
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if(map[i][j] == 0) {
					int stuNumber = stuNum(i, j, student, favorite);
//					System.out.println(stuNumber);
					// 만약 현재 위치에서 선호학생이 max보다 많으면 다음 진행
					if (maxNum < stuNumber) {
						maxBla = BlankNum(i,j,student,favorite);
						maxNum = stuNumber;
						r = i;
						c = j;
						check = true;
					}
					// 만약 선호학생이 max와 같으면 다음진행
					else if (maxNum == stuNumber) {
						// maxBla보다 많거나 같으면 저장
						int blaNum = BlankNum(i,j,student,favorite);
//						System.out.println("bla " + blaNum);
						if(maxBla < blaNum) {
							maxBla = blaNum;
							r = i;
							c = j;
							check = true;
						}
					}
				}else continue;
			}
		}
		if(check)
			map[r][c] = student;
		else {
			//주변에 선호하는 학생도 없고 0인 자리도 없으면 가장 0이 빨리 오는 곳에 저장
			for(int j = 0 ; j < N; j++) {
				for(int i = 0 ; i < N; i++) {
					if(map[i][j] == 0) {
						map[i][j] = student;
						break;
					}
				}
			}
		}
		
	}

	private static int stuNum(int r, int c, int student, int[] favorite) {
		int cnt = 0;
		for(int i = 0 ; i < 4; i++) {
			int sr = r + dr[i];
			int sc = c + dc[i];
			for(int j = 0; j < favorite.length; j++) {
				if(sr >= 0 && sr < N && sc >= 0&& sc < N && map[sr][sc] == favorite[j]) cnt++;
			}
		}
		return cnt;
	}
	
	private static int BlankNum(int r, int c, int student, int[] favorite) {
		int cnt = 0;
		for(int i = 0 ; i < 4; i++) {
			int sr = r + dr[i];
			int sc = c + dc[i];
			if(sr >= 0 && sr < N && sc >= 0 && sc < N && map[sr][sc] == 0) cnt++;
		}
		return cnt;
	}

}
