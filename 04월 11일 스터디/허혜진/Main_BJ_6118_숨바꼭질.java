package study0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_6118_숨바꼭질 {

	static int N, M;
	static ArrayList<Integer>[] list;
	static int destination, distance, sameDis;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		list = new ArrayList[N+1]; // 1은 dummy
		
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}

		bfs(1); // 1번 정점부터 시작
		System.out.println(destination+" "+distance+" "+sameDis);
	}

	private static void bfs(int num) {
		
		Queue<Integer> q = new LinkedList<>();
		int count[] = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		q.offer(num);
		visited[num] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int i : list[v]) {
				if(!visited[i]) {
					q.offer(i);
					visited[i] = true;
					count[i] = count[v] + 1;
				}
			}
		}
		
		distance = -1;
		for(int i=0; i<=N; i++) {
			if(count[i] > distance) {
				distance = count[i];
				destination = i;
				sameDis = 1;
			}
			else if(count[i] == distance) {
				sameDis++;
			}
		}
	}
}
