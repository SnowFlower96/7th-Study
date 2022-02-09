package algorithm_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Beakjoon_1769_3의배수 {

	static String str;
	static int cnt;
	static int total;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine();
		
		func(str);
	}

	private static void func(String str) {
		int[] arr = new int[str.length()];
		total=0;

		if(str.length() == 1) {
			int num = str.charAt(0) -'0';
			if(num%3 == 0) {
				System.out.println(cnt);
				System.out.println("YES");
				return;
			} else {
				System.out.println(cnt);
				System.out.println("NO");
				return;
			}
			
		}
		for(int i=0; i<str.length(); i++) {
			arr[i] = str.charAt(i) -'0';
			total += arr[i];
		}
		str = Integer.toString(total);
		cnt++;
		
		if(str.length() == 1) {
			System.out.println(cnt);
			if(total%3 == 0) System.out.println("YES");
			else System.out.println("NO");
			return;
		}else func(str); 
		
	}

}
