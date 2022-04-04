package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 스터디
 * @author jhno96
 * @date 2022. 4. 3.
 */

public class Main_BJ_13023_ABCDE {

	static int N, M, ans, cnt;
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {		
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list.get(from).add(to);
			list.get(to).add(from);
		}
		
		for(int i = 0 ; i< N; i++) {
			if(!list.get(i).isEmpty()) {
				isVisited = new boolean[N];
				dfs(i, 1);
				if(ans == 1) break;
			}
		}
		
		System.out.println(ans);
	}

	private static void dfs(int idx, int depth) {
		if(depth >= 5) {
			ans = 1;
			return;
		}
		
		isVisited[idx] = true;
		int size = list.get(idx).size();
		
		for(int i = 0; i < size; i++) {
			if(isVisited[list.get(idx).get(i)]) continue;
			dfs(list.get(idx).get(i), depth + 1);
			isVisited[list.get(idx).get(i)] = false;  // 중요
		}
		
	}

}
