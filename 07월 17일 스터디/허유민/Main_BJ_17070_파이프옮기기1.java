package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17070_파이프옮기기1 {

    static int N, answer;
    static int[][] map;
    // 0 : 가로, 1 : 세로, 2 : 대각
    static int[][] dr = {
            {0,1}, {1,1}, {0,1,1}
    };
    static int[][] dc = {
            {1,1}, {0,1}, {1,1,0}
    };
    static int[][] dhvd = {
            {0,2},{1,2,},{0,2,1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 도착해야 하는 n-1,n-1이 긁지말아야할 곳 이라면 갈 수 없으니까 0 출력
        if(map[N-1][N-1] == 1){
            System.out.println(0);
            return;
        }
        answer = 0;
        // 파이프는 0,0과 0,1에서 시작 앞쪽을 계속 이동하면서 모양을 기억하고있는다
        dfs(0,0,1);
        System.out.println(answer);
    }

    public static void dfs(int hvd, int r,int c){
        // 벽에 도착하거나 긁지말아야하는 경우
        if(r == N || c == N || map[r][c] == 1){
            return;
        }

        //
        if (hvd==2 && (map[r-1][c]==1 || map[r][c-1]==1)) {	// 대각
            return;
        }

        // 도착했을 때 정답 증가
        if(r == N-1 && c == N-1){
            answer++;
            return;
        }

        for (int i = 0; i < dr[hvd].length; i++) {
            int nr = r + dr[hvd][i];
            int nc = c + dc[hvd][i];
            dfs(dhvd[hvd][i],nr,nc);
        }
    }
}
