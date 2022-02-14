package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_B2_2605_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		List<Integer> line = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int student = 1; student <= N; student++) {
			int work = Integer.parseInt(st.nextToken());
			line.add(student - work - 1, student);
		}
		
		for(int n : line) {
			sb.append(n).append(" ");
		}
			
		System.out.println(sb.toString());
	}

}
