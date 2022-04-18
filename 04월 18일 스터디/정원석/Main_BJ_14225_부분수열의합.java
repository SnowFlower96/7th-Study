import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14225_부분수열의합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[N];
		visited = new boolean[N];
		
		for(int i = 0 ; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken()); 
			max += num[i];
		}
		checked = new boolean[max+2]; 
		
		Subset(0);

		for(int i = 1 ; i <= max+1; i++) {
			if(!checked[i]) {
				System.out.println(i);
				break;
			}		
		}
		
	}

	static int N, max;
	static boolean visited[], checked[];
	static int num[];
	
	//부분집합을 구하는 메서드
	public static void Subset(int cnt) {
		
		// 0 부터 2^(N+1)까지 반복문을 돌린다. (1이 있는 부분이 부분집합)
		for(int flag = 0, caseCount = 1<<N; flag< caseCount; flag++) {
			//반복문을 돌 때마다 sum을 초기화 시킨다. 
			int sum = 0;
			
			for(int i = 0; i< N; i++) {
				//And연산을 해서 1이 있으면 그 위치는 부분집합에 포함시킨다.
				if((flag & 1<<i) != 0) {
					sum += num[i];
				}
			}
			
			checked[sum] = true;
			
		}
		
	}
	
}
