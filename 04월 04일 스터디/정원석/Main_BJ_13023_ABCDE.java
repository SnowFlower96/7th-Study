import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_13023_ABCDE {
	
	static class Connect {
		int V;
		Connect link;
		public Connect(int V, Connect link) {
			this.V = V;
			this.link = link;
		}
		@Override
		public String toString() {
			return "Connect [V=" + V + ", link=" + link + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());//관계수
		
		connection = new Connect[N];
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			connection[from] = new Connect(to, connection[from]);
			connection[to] = new Connect(from, connection[to]);
		}
		
		for(Connect c : connection) {
			System.out.println(c);
		}

		connect = 0;
		visited = new boolean[N]; 
		for(int i = 0 ; i < N; i++) {
			dfs(i,0);
			if(connect==1) {
				break;
			}
		}
		System.out.println(connect);
		
	}

	static boolean visited[];
	static Connect[] connection;
	static int connect; 
	
	static public void dfs(int current, int cnt ) {
			if(cnt == 4) {
				connect = 1;
				return;
			}
			visited[current] = true;
			
			for(Connect temp = connection[current]; temp != null; temp = temp.link) {
				if(!visited[temp.V]){
					dfs(temp.V, cnt+1);
				}
			}
			
			visited[current] = false;
		
	}

}
