import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_24230_트리색칠하기 {
	
	public static class Node{
		ArrayList<Integer> to;
		int color;
		
		public Node(ArrayList<Integer> to, int color) {
			super();
			this.to = to;
			this.color = color;
		}
		
	}

	static Node[] treeColor;
	static boolean[] visited;
	static int N, changeCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//연결된 다음 트리[0]와 현재 트리의 색깔[1];
		treeColor = new Node[N+1];
		visited = new boolean[N+1];
		
		for(int i = 0; i <= N; i++ ) {
			treeColor[i]= new Node(new ArrayList<>(), 0);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			treeColor[i].color = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			//ArrayList에 추가한다. 
			treeColor[from].to.add(to);
			treeColor[to].to.add(from);
			
			/*
			if(from < to)
				treeColor[from].to.add(to);
			else
				treeColor[to].to.add(from);
			*/
		}
		
		//루트부터 색칠되어 있으면 cnt를 1부터 시작한다.
		if(treeColor[1].color == 0) {
			changeCnt = 0;
		}else {
			changeCnt = 1;
		}
		dfs(1);
		System.out.println(changeCnt);
		
	}

	//끝까지 먼저 탐색을 한다.
	private static void dfs(int cur) {
		
		visited[cur] = true;
		if(treeColor[cur].to.isEmpty()) return;
		
		int color = treeColor[cur].color;
		
		for(int n : treeColor[cur].to ) {
			if(!visited[n]) {
				
				if (treeColor[n].color != color) {
//				System.out.println(n);
					changeCnt++;
				}
				
			dfs(n);
			}
		}
		
	}

}
