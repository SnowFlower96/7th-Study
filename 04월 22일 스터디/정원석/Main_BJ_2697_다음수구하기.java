import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main_BJ_2697_다음수구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			StringBuilder sb = new StringBuilder();
			String num = br.readLine();
			
			int[] number = new int[num.length()];
			for(int i = 0 ; i < num.length(); i++) {
				number[i] = num.charAt(i) - '0';
			}
//			System.out.println(Arrays.toString(number));
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			boolean BIGGEST = true;
			int idx = 0; 
			loop : for(int i = num.length()-1; i >= 0; i--) {
				list.add(number[i]);
				for(int j = 0; j < list.size(); j++) {
					// 만약 앞의 숫자가 뒤에 있던 숫자보다 작은 idx가 있으면 인덱스를 기록하고 for문을 탈출한다.
					if(number[i] < list.get(j) ) {
						idx = i;
						BIGGEST = false;
						break loop;
					}
				}
			}
			
			// 만약 바꿀 숫자가 없으면 BIGGEST를 출력한다.
			if(BIGGEST) System.out.println("BIGGEST");
			else {
				// 기록된 idx에 있는 값과 가장 작은 차이를 가진 경우를 저장할 변수를 만든다. 
				int minDis = 10;
				// 제거할 변수를 만든다.
				int check = 0;
				
				for(int i = 0 ; i < list.size(); i++) {
					// 차이가 가장 작은 idx를 반환한다.
					if(number[idx] < list.get(i))
						if(minDis > list.get(i)) {
							minDis = list.get(i);
							check = i;
						}
				}
				// 뒤에 있는 가장 차이가 적은 숫자를 제거한다.
				list.remove(check);
				// 가장 차이가 적은 숫자를 원래 있ㄴ던 idx에 넣는다.
				number[idx] = minDis;
				//뒤에 바꿀 숫자들 오름차순으로 정렬
				Collections.sort(list);
				
				//둘을 합한다.
				for(int i = 0; i <= idx; i++) {
					sb.append(number[i]);
				}
				for(int i = 0; i < list.size(); i++) {
					sb.append(list.get(i));
				}
				System.out.println(sb.toString());
			}
		}
		
		
	}
}
