package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_13565_침투 {

    static int M, N;
    static int[][] map;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            String[] sArray = s.split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sArray[j]);
            }
        }

        visited = new boolean[M][N];

        for (int i = 0; i < N; i++) {
            if(!visited[0][i] && map[0][i] == 0){
                //DFS(0,i);
                BFS(0,i);
            }
        }
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            if(visited[M-1][i]){
                flag = true;
            }
        }

        if(flag){
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
    }

    static public void DFS(int r,int c){
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr >= M || nr < 0 || nc >= N || nc < 0 || visited[nr][nc] || map[nr][nc] == 1){
                continue;
            }
            DFS(nr,nc);
        }
    }

    static public void BFS(int r,int c){
        Queue<int[]> que = new LinkedList<int[]>();

        que.add(new int[]{r,c});
        visited[r][c] = true;

        while (!que.isEmpty()){
            int[] current = que.poll();
            int x = current[0];
            int y = current[1];

            for (int d = 0; d < 4; d++) {
                int nr = x + dr[d];
                int nc = y + dc[d];
                if(nr >= M || nr < 0 || nc >= N || nc < 0 || visited[nr][nc] || map[nr][nc] == 1){
                    continue;
                }
                visited[nr][nc] = true;
                que.add(new int[]{nr,nc});
            }
        }
    }
}
