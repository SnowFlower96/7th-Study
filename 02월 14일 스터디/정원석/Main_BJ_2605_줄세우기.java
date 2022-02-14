package com.ssafy.BJ01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BJ_2605_줄세우기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer>  line = new LinkedList<Integer>();
				
		for(int i = 0; i < T; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			if( cnt != 0) {
				line.add(i-cnt, i+1);
			}
			else line.add(i+1);
		}
		
		for(int i = 0; i< line.size(); i++) {			
			sb.append(line.get(i) + " ");
		}
		
		sb.setLength(sb.length()-1);
		
		System.out.println(sb.toString());
	}

}
