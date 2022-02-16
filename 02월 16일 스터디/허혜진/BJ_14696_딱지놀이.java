package study0216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14696_딱지놀이 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int t=0; t<N; t++) {
			
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int[] a = new int[num];
			
			int fourCnt=0, threeCnt=0, twoCnt=0, oneCnt = 0;
			
			for(int i=0; i<num; i++) {		//어린이 A 정보
				a[i] = Integer.parseInt(st.nextToken()); 
				if(a[i] == 4) fourCnt++;
				else if(a[i] == 3) threeCnt++;
				else if(a[i] == 2) twoCnt++;
				else oneCnt++;
			}
			
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			
			int[] b = new int[num];
			for(int i=0; i<num; i++) {		//어린이 B 정보
				b[i] = Integer.parseInt(st.nextToken()); 
				if(b[i] == 4) fourCnt--;
				else if(b[i] == 3) threeCnt--;
				else if(b[i] == 2) twoCnt--;
				else oneCnt--;
			}
			

			if(fourCnt > 0) sb.append("A");
			else if(fourCnt == 0) {
				if(threeCnt>0) sb.append("A");
				else if(threeCnt ==0) {
					if(twoCnt > 0) sb.append("A");
					else if(twoCnt==0) {
						if(oneCnt>0) {
							sb.append("A");
						}else if(oneCnt==0) sb.append("D");
						else sb.append("B");
					}else sb.append("B");
				}else sb.append("B");
			}else sb.append("B");
			
		}
		
	}

}
