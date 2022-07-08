package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_7569_토마토 {

    static int M, N, H,day;
    static int[][][] farm;

    static boolean[][][] visited;
    static int[] dr = {-1,1,0,0,0,0};
    static int[] dc = {0,0,-1,1,0,0};
    static int[] dh = {0,0,0,0,-1,1};

    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        farm = new int[H][N][M];
        queue = new LinkedList<int[]>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int temp = Integer.parseInt(st.nextToken());
                    farm[i][j][k] = temp;
                    if (temp == 1){
                        queue.add(new int[]{i,j,k});
                    }
                }
            }
        }

        bfs();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(farm[i][j][k] == 0){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        if(day == 0) {
            System.out.println(0);
        }else {
            System.out.println(day-1);
        }

    }

    public static void bfs(){
        day = 0;

        while(!queue.isEmpty()){
            int current[] = queue.poll();
            int r = current[1];
            int c = current[2];
            int h = current[0];

            for (int d = 0; d < 6; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nh = h + dh[d];

                if(nr >= N || nr < 0 || nc >= M || nc < 0 || nh >= H || nh < 0){
                    continue;
                }
                if(farm[nh][nr][nc] != 0){
                    continue;
                }
                queue.add(new int[]{nh,nr,nc});
                farm[nh][nr][nc] = farm[h][r][c] + 1;
                day = Math.max(farm[nh][nr][nc],day);
            }
        }
    }
}
