package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-12
 */
public class Main_BJ_2589_보물섬 {

    static int N, M, ans;
    static char[][] map;
    static Queue<int[]> queue;

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MIN_VALUE;
        map = new char[N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 'L'){
                    bfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    private static void bfs(int r, int c) {
        int[][] visit = new int[N][M];
        for(int[] row : visit) Arrays.fill(row, -1);

        queue.offer(new int[] { r, c });
        visit[r][c] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 'W' || visit[nr][nc] != -1) continue;

                queue.offer(new int[] { nr, nc });
                visit[nr][nc] = visit[cur[0]][cur[1]] + 1;
                ans = Math.max(ans, visit[nr][nc]);
            }
        }
    }

}
