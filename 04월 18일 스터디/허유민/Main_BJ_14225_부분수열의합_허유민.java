package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_BJ_14225_부분수열의합_허유민 {

	static int N;
	static int[] arr;
	static HashSet<Integer> set;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		set = new HashSet<Integer>();
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max += arr[i];
		}
		boolean[] number = new boolean[max+2];
		
		subset_bitmask();

		ArrayList<Integer> list = new ArrayList<>(set);
		
		Iterator iter = set.iterator();
		for (int i = 0; i < set.size(); i++) {
			int a = (int) iter.next();
			number[a] = true;
		}

		for (int i = 1; i < max+2; i++) {
			if( !number[i]) {
				System.out.println(i);
				break;
			}
		}
//		for (int i = 0; i < list.size(); i++) {
//			System.out.print(list.get(i) + " ");
//		}
		
//		int i;
//		for (i = 1; i <= max; i++) {
//			//System.out.print(list.get(i));
//			if( i != list.get(i)) {
//				System.out.println(i);
//				break;
//			}
//		}
//		//System.out.println();
//		if(i > max){
//			System.out.println(i);
//		}

	}
	
	public static void subset_bitmask() {
		for (int flag = 0, caseCount = 1<<N; flag < caseCount; flag++) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if((flag & 1<<i) != 0) {
					sum += arr[i];
				}
			}
			set.add(sum);
		}
	}

}
