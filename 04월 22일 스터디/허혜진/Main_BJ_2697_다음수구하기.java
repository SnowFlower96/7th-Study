package study0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BJ_2697_다음수구하기 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			List<Integer> list = new ArrayList<>();
			List<Integer> list2 = new ArrayList<>();
			
			String str = br.readLine();
			
			//입력 받기
			for(int i=0; i<str.length(); i++) {
				list.add(str.charAt(i)-'0');
			}

			int idx = 0;
			for(int i=list.size()-1; i>=0; i--) {
				if(i-1 < 0) {
					sb.append("BIGGEST");
					break;
				}
				int curNum = list.get(i);
				int frontNum = list.get(i-1);
				int n = 100;
				int nIdx = 100;
				if(frontNum < curNum) {
					idx = i-1;
					
					for(int j=0; j<idx; j++) {
						sb.append(list.get(j));
					}
					
					//새로운 list에 뒤에 부분 넣기
					for(int j=idx+1; j<list.size(); j++) {
						list2.add(list.get(j));
						if(list.get(idx) < list.get(j)) {
							n = Math.min(n, list.get(j)); 
							nIdx = list2.indexOf(n);
						}
					}
					list2.remove(nIdx);
					list2.add(list.get(idx));

					sb.append(n);
					Collections.sort(list2);
					for(int a : list2) {
						sb.append(a);
					}
					break;
				}
			}
			
			sb.append("\n");
		} // end tc
		
		System.out.println(sb);
	}

}
