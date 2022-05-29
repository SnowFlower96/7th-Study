package study0316;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9093_단어뒤집기 {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String str = br.readLine();
			
			String[] strings = str.split(" ");
			
			for(String s : strings) {
				for(int i=s.length()-1; i>=0; i--) {
					sb.append(s.charAt(i));
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
