import java.io.*;

public class bj_s5_1769_3의배수 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 큰 자연수 X 입력 받기
		String X = br.readLine();
		
		//3. 카운트 초기화
		int count = 0;
		
		//2. 변환 과정
		while(true) {
			//X가 1자리일 경우 멈춤
			if(X.length() == 1) break;
		
			//X가 2자리이상일 경우 각 자리의 수를 모두 더함
			long sum = 0;
			for(int i=0; i<X.length(); i++) {
				sum += Integer.parseInt(String.valueOf(X.charAt(i)));
			}
			count++;
			X = String.valueOf(sum);
		}
		
		//3. 3의 배수 y/n
		System.out.println(count);
//		삼항 연산자
//		System.out.println(Integer.parseInt(X) % 3 == 0 ? "YES" : "NO");
		
		if(Integer.parseInt(String.valueOf(X)) %3 ==0) {
			System.out.printf("YES");
		}
		else {
			System.out.printf("NO");
		}
		
	}

}
