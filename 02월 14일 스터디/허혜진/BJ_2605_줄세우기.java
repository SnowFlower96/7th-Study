package study0214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2605_줄세우기 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		List<Integer> list = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine()); // 학생수 입력 받기
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) { //학생 수 만큼 돌기
			int input = Integer.parseInt(st.nextToken()); // 학생들이 뽑은 번호
			
			list.add(list.size()-input, i);
		}
		
		for(int n:list) {
			System.out.print(n+" ");
		}
	}

}
