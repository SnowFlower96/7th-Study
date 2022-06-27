import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_1138_한줄로서기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] person = new int[N+1];
		ArrayList<Integer> arr = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		//키큰 순서에 입력되어 있는 인덱스로 ArrayList에 추가한다.
		for(int i = N; i > 0; i--) {
			arr.add(person[i], i);
		}
		
		for(int ans : arr) {
			System.out.print(ans + " ");
		}

		
	}
	

}
