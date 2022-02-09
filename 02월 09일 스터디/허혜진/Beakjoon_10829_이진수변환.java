package algorithm_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Beakjoon_10829_이진수변환 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());
		
		ArrayList<Long> arr = new  ArrayList<>();
		
		while(true) {
			if(0>=N) break;
			
			long num = N %2;
			N /=2;
			arr.add(num);
		}

		Collections.reverse(arr);
		
		for(long num:arr) {
			System.out.print(num);
		}
	}

}
