import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1197_최소스패닝트리_adjMatrix {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] adjMatrix = new int[V+1][V+1];
		int[] minEdge = new int[V+1]; // 타 정점에서 자신으로의 간선 비용중 최소 비용
		boolean[] visited = new boolean[V+1]; //신장트리에 선택된 여부
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjMatrix[to][from] = w;
			adjMatrix[from][to] = w;
			
			minEdge[i+1] = Integer.MAX_VALUE;// 충분히 큰값으로 최소값 초기화
		}
//		for(int i = 1; i <= V; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}
		
		int result = 0;//MST 비용
		minEdge[1] = 0;
		
		for(int c = 0; c < V; c++) { // N개의 정점을 모두 연결
			// 신장트리에 연결되지 않은 정점중 가장 유리한 비용의 정점을 선택
			int min = Integer.MAX_VALUE;
			int minVertex = 1;
			
			// 자신과 연결된 간선중 가장 작은 값을 min에 넣는다. 
			for (int i = 1; i <= V; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}	
			}
			
			//선택된 정점을 신장 트리에 포함시킴
			visited[minVertex] = true;
			result += min;
			
			// 선택된 정점 기준으로 신장트리에 포함되지 않은 다른 정점으로의 간선 비용을 따져보고 최소값 갱신
			for(int i = 1; i <= V; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] !=0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
			
		}
		System.out.println(result);

	}

}
