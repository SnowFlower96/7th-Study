import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2644_촌수계산 {

	static int N, M, secondPer;
	static int[][] familyTree;
	static boolean visited[];
	static boolean complite;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		
		st = new StringTokenizer(br.readLine());
		int firstPer = Integer.parseInt(st.nextToken());
		secondPer = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		// 인접행렬을 구한다.
		familyTree = new int[N+1][N+1];
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int parent = Integer.parseInt(st.nextToken());
			int son = Integer.parseInt(st.nextToken());
			
			familyTree[son][parent] = 1;
			familyTree[parent][son] = 1;
		}
		// 1~ N까지 촌수를 체크했는지를 확인한다.
		visited = new boolean[N+1];
		
		
		//첫번째 사람은 항상 지나쳐가므로 체크한다.
		visited[firstPer] = true;
		dfs(firstPer, 0);
		//만약 여기까지 오면 촌수관계가 아니므로 -1을 출력한다.
		System.out.println(-1);
	}
	
	private static void dfs(int start, int cnt) {
		//만약 촌수를 구해아될 사람과 만나게되면 끝낸다.
		if(start == secondPer) {
			complite = true;
			System.out.println(cnt);
			System.exit(0);
		}
		
		for(int i = 1 ; i <= N; i++) {
			// 만약 방문하지 적이 없거나 부모자식 관계라면 dfs를 돌린다.
			if(!visited[i] && familyTree[start][i] == 1) {
				// 방문 처리
				visited[i] = true;
				
				dfs(i, cnt+1);
				
				// 방문 처리 초기화 
				visited[i] = false;
			}
		}
	}

}
