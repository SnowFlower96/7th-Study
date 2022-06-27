import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_14889_스타트와링크 {
	
	static int N, teamSum, min;
	static int team[][]; 
	static int input[];
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		team = new int[N+1][N+1];
		input = new int[N/2];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j<= N; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		Combination(0, 1);
		
		System.out.println(min);
		
	}

	
	public static void Combination(int cnt, int start) {
		
		if(cnt == N/2) {
			int[] input2= new int[N/2];
			int c = 0;
			// team1에 포함되지 않는 팀을 만든다.
			loop: for(int i = 1 ; i <= N; i++) {
				for(int j : input) if( j == i) continue loop;
				input2[c++] = i;
			}
			
			boolean[] visited = new boolean[N/2];
			int[] teamInput = new int[2];
			
			teamSum = 0;
			permutation(0, visited, input, teamInput);
			int team1Sum = teamSum;
			
			teamSum = 0;
			permutation(0, visited, input2, teamInput);
			int team2Sum = teamSum;
			min = Math.min(min, Math.abs(team2Sum - team1Sum));
			return;
		}
		
		for(int i = start; i <= N; i++) {
			input[cnt] = i;
			Combination(cnt+1, i+1);
		}
	}
	
	public static void permutation(int cnt, boolean visited[], int[] direct, int[] team_1) {
		
		if(cnt == 2){
			teamSum += team[team_1[0]][team_1[1]];
			return;
		}
		
		for(int i = 0; i < direct.length; i++) {
			if(visited[i]) continue;
			
			team_1[cnt] = direct[i]; 
			visited[i] = true;
			permutation(cnt + 1, visited, direct, team_1);
			visited[i] = false;
		}
		
	}
}
