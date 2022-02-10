package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Baekjoon_1769_3의배수 {

	static int count = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader ( new InputStreamReader (System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		Scanner sc = new Scanner(System.in);
		//int x = Integer.parseInt(st.nextToken());
		//Long x = Long.parseLong(sc.next());
		String x = sc.next();
		N3(x);
		System.out.println(sb.toString());
	}

	public static void N3(String x) {
		
		int y = 0;
		String s = "";
		// x가 한자리인 경우에 3,6,9 중에 하나인지 확인후 리턴
		if(x.length() < 2) {
			sb.append(count + "\n");
			// 문제 2. "Y는 3의 배수인가?"
			if(x.equals("3") || x.equals("6") || x.equals("9")) {
				sb.append("YES \n");
			}else {
				sb.append("NO \n");
			}
			return;
		}else {
			// x의 자릿수들을 더한다.
			for (int i = 0; i < x.length(); i++) {
				char b = x.charAt(i);
				int a = b - '0';
				y += a;
			}
			s = String.valueOf(y);
		}
		count++; // 몇번 돌았는지 카운트
		N3(s);
	}

}
