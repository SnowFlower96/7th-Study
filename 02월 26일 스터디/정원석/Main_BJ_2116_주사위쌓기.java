import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2116_주사위쌓기 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dice = new int[N][6];
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 6; j++) {
				// 이렇게 저장하는 이유 이렇게 하면 앞의 3 숫자는 전면으로 뒤의 세숫자는 후면으로 이어질 수 있다.
				// 예를들어 index 0 1 2 3 4 5 가 있고 밑의 방법으로 다시 재배열을 하면
				// 0 1 2 5 3 4가 되고 0-3이 서로를 마주보고 있고 1-4 가 서로를 마주보고 있고 2-5가 서로를 마주보고 있게된다.
				// 따라서 내가 원하는 숫자의 (인덱스 + 3) % 6 을 하면 마주보고 있는 주사위 면이 나온다. 
				if(j > 2 && j < 5) {
					dice[i][j+1] = Integer.parseInt(st.nextToken());
				}else if(j == 5) {
					dice[i][3] = Integer.parseInt(st.nextToken()); 
				}else {
					dice[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		
		//주사위의 6면을 전부 확인하는 반복문
		for (int i = 0; i < 6; i++) {
			//가장 아랫 주사위를 먼저 확인한다.
			
			// 주사위의 앞면, 뒷면을 저장할 변수
			int bottom = dice[0][i];
			int top = dice[0][(i + 3) % 6];
			// 주사위의 옆면중에서 가장 큰값을 저장할 변수
			int max = MAX_search(i, 0, dice);
			// 주사위 옆면의 합을 저장할 변수
			int sum_max = max;
			
			
			for(int j = 1; j < N ; j++) {
				for(int t = 0; t < 6; t++) {
					//주사위의 면이 밑의 주사위의 윗면과 같을 경우
					if(dice[j][t] == top) {
						//앞면과 뒷면을 지금의 주시위의 아랫면과 윗면으로 저장하고
						bottom = dice[j][t];
						top = dice[j][(t+3) % 6];
						
						//주사위 옆면중 가장 큰값을 저장시킴
						max = MAX_search(t, j, dice);
						break;
					}
				}
				//가장 큰값을 옆면의 합에 더함
				sum_max += max;
			}
			// 그렇게 나온 옆면의 합이 현재 있는 결과 보다 크면 바꿔줌 
			result = Math.max(sum_max, result);
			System.out.println(result);
		}
		
	}
	
	//나머지 4개의 면중 최대값을 저장
	// n은 n번째 주사위를 뜻함 
	public static int MAX_search(int i, int n, int[][] dice) {
		int max = 0;
		for(int j = 0 ; j< 6; j++) {
			if(j != i && j != (i+3) % 6 ) {
				max = Math.max(max, dice[n][j]);
			}
		}
		return max;

	}	

}
