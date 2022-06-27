package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2589_보물섬 {
    static int N,M;
    static char[][] map;
    static boolean[][] visited;

    static int dr[] = {-1,1,0,0};
    static int dc[] = {0,0,-1,1};

    static class Node{
        int r;
        int c;
        int time;

        Node(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int bomul = 0;
                if(map[i][j] == 'L'){
                    bomul = bfs(i,j);
                }
                answer = Math.max(answer, bomul);
            }
        }
        System.out.println(answer);

    }

    public static int bfs(int r, int c){
        visited = new boolean[N][M];

        Queue<Node> queue = new LinkedList<Node>();

        queue.add(new Node(r,c,0));
        int cnt = 0;

        while (!queue.isEmpty()){
            Node node = queue.poll();
            int nr = node.r;
            int nc = node.c;
            visited[nr][nc] = true;

            // 사방탐색을 통해 육지를 찾아서 시간을늘려간다.
            for (int d = 0; d < 4; d++) {
                int nnr = nr + dr[d];
                int nnc = nc + dc[d];

                if(nnr < 0 || nnr >= N || nnc < 0 || nnc >= M || map[nnr][nnc] == 'W' || visited[nnr][nnc]){
                    continue;
                }
                queue.add(new Node(nnr,nnc,node.time+1));
                cnt = Math.max(cnt,node.time+1);
                visited[nnr][nnc] = true;
            }
        }
        return cnt;
    }
}
