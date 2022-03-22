package study_03_23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16928_뱀과사다리게임 {

	static class FromTo{
		int from, to;
		
		public FromTo(int from, int to) {
			
			this.from = from;
			this.to = to;
		}


		@Override
		public boolean equals(Object obj) {
			
			FromTo other = (FromTo) obj;
			
			if (from == other.from)
				return true;
			
			return false;
		}
		
		
	}
	
	static class Count{
		int cur;
		int count;
		
		public Count(int cur, int count) {
			this.cur = cur;
			this.count = count;
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int LadderNum = Integer.parseInt(st.nextToken()); // 사다리수
		int SnakeNum = Integer.parseInt(st.nextToken()); // 뱀의 수
		int[] map = new int[101]; // 1 ~ 100 까지 0은 제외
		
		
		ladder = new ArrayList<>(); 
		for(int i = 0; i < LadderNum+SnakeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			ladder.add(new FromTo(r, c)); //사다리 정보
			
		}
		
		
		bfs(map);
		
		System.out.println(result);

	}
	
	static ArrayList<FromTo> ladder;
	static int result = 0;
	
	// bfs를 주사위 눈의 개수인 6만큼 돌리고 만약 뱀이 걸리게 되면 queue에 저장하지 않는다.
	static void bfs(int[] map) {
		
		
		Queue<Count> que = new LinkedList<Count>();
		boolean visited[] = new boolean[101];
		
		que.offer(new Count(1 , 0));
		
		while(!que.isEmpty()) {
			
			int cur = que.peek().cur;
			int cnt = que.poll().count;
			
			for(int i = 1 ; i <= 6; i++) {
				FromTo MoveCur = new FromTo(cur + i, 0);
				
				if( visited[MoveCur.from]) {
					continue;
				}
				
				if( ladder.contains(MoveCur) ) {
					for(int j = 0; j < ladder.size(); j++) {
						if(ladder.get(j).from == MoveCur.from && ladder.get(j).to == 100) {
							result = cnt  + 1;
							return;
						}else if(ladder.get(j).from == MoveCur.from) {
							que.offer(new Count(ladder.get(j).to, cnt + 1 ));
							visited[ladder.get(j).to] = true;
							break;
						}
					}
				}else if(MoveCur.from == 100) {
					result = cnt + 1;
					return;
				}else {
					visited[MoveCur.from] = true;
					que.offer(new Count(MoveCur.from, cnt + 1));
					
				}
			}
		}
	}

}
