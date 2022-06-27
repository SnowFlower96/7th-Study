package study0509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1644_촌수계산 {

	static int p1, p2;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static int[] visited;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());	//전체 사람 수
		
		
		st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());	//person1
		p2 = Integer.parseInt(st.nextToken());	//person2

		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		visited = new int[N+1];
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			//양방향
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
//		System.out.println(list);
		System.out.println(bfs(p1));
	}

	private static int bfs(int p1) {
		
//		visited[p1] = 1;	
		Queue<Integer> queue = new LinkedList<>();
		queue.add(p1);
		
		while(!queue.isEmpty()) {
			int out = queue.poll();
			for(int i=0; i<list.get(out).size(); i++) {
				if(visited[list.get(out).get(i)] == 0) {
					visited[list.get(out).get(i)] = visited[out] + 1;
					queue.add(list.get(out).get(i));
				}
			}
		}
		
		if(visited[p2] == 0) return -1;
		else return visited[p2];
	}


}
