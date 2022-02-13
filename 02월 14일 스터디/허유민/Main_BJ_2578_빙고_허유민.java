package personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2578_빙고_허유민 {

	static int answer;
	static boolean[][] bingo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		bingo = new boolean[5][5];
		int[][] map = new int[5][5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] arr = new int[25];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				arr[index++] = Integer.parseInt(st.nextToken());
			}
		}
		index = 0;
		int x = 0;
		int y = 0;
		while (index < 25) {
			// 사회자가 부른 값을 찾아서 빙고배열에 좌표로 트루로 바꿔줌
			if (arr[index] == map[x][y]) {
				index++;
				bingo[x][y] = true;
			}

			// 최소 12개는 불러야 3줄이 가능
			if (index > 10) {
				rowcheck(); // 행체크
				columcheck(); // 열체크
				leftcrosscheck(); // 00부터 대각선 체크
				rightcrosscheck(); // 04부터 대각선 체크
			}
			
			if(answer >= 3) {
				System.out.println(index);
				break;
			}

			if (++y == 5) {
				y = 0;
				x++;
			}
			if (x == 5) {
				x = 0;
			}
			answer = 0;
		}

	}

	public static void rowcheck() {
		// 트루이면 count 증가해서 count가5가되면 한줄증가
		for (int i = 0; i < 5; i++) {
			int count = 0;
			for (int j = 0; j < 5; j++) {
				if(bingo[i][j]) {
					count++;
				}
			}
			if(count == 5) {
				answer++;
			}
		}
	}

	public static void columcheck() {
		for (int i = 0; i < 5; i++) {
			int count = 0;
			for (int j = 0; j < 5; j++) {
				if(bingo[j][i]) {
					count++;
				}
			}
			if(count == 5) {
				answer++;
			}
		}
	}

	public static void leftcrosscheck() {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if(bingo[i][i]) {
				count++;
			}
		}
		if(count == 5) {
			answer++;
		}
	}

	public static void rightcrosscheck() {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if(bingo[i][4-i]) {
				count++;
			}
		}
		if(count == 5) {
			answer++;
		}
	}
}
