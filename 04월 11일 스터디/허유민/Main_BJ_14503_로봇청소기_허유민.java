package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14503_로봇청소기_허유민 {

    static int N,M,r,c,d;
    static int[][] map;

    // d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
    // 왼쪽으로 계속 움직인다. 반시계방향
    static int dr[] = {-1,0,1,0}; // 북동남서
    static int dc[] = {0,1,0,-1};
    static int cnt;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        cnt = 1; // 처음 청소기가 놓여있을때부터
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r,c,d);
        System.out.println(cnt);

    }

    public static void dfs(int r, int c, int d){
        // 1. 현재 위치를 청소한다.
        visited[r][c] = true;

        // 2. 현재 위치에서 다음을 반복하면서 인접한 칸을 탐색한다.
        // a. 현재 위치의 바로 왼쪽에 아직 청소하지 않은 빈 공간이 존재한다면,
        //      왼쪽 방향으로 회전한 다음 한 칸을 전진하고 1번으로 돌아간다.
        //      그렇지 않을 경우, 왼쪽 방향으로 회전한다. 이때, 왼쪽은 현재 바라보는 방향을 기준으로 한다.
        for (int del = 0; del < 4; del++) {
            d = (d+3) % 4;
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(map[nr][nc] != 0 || visited[nr][nc]) continue;
            else {
                cnt++;
                dfs(nr, nc, d);
                return;
            }

        }
        // b. 1번으로 돌아가거나 후진하지 않고 2a번 단계가 연속으로 네 번 실행되었을 경우,
        // 바로 뒤쪽이 벽이라면 작동을 멈춘다. 그렇지 않다면 한 칸 후진한다.
        int br = r + (dr[d]*-1);
        int bc = c + (dc[d]*-1);

        if(map[br][bc] == 0){
            dfs(br,bc,d);
        }

    }
}
