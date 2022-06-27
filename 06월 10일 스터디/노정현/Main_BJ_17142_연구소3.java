package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-06
 */
public class Main_BJ_17142_연구소3 {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    static int N, M, blankCnt, ans;
    static int[][] map;
    static List<Point> virus;
    static Point[] active;

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        virus = new LinkedList<>();
        active = new Point[M];
        blankCnt = 0;
        ans = Integer.MAX_VALUE;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) blankCnt++;
                else if (map[i][j] == 2) virus.add(new Point( i, j ));
            }
        }

        if (blankCnt == 0) System.out.println(0);
        else {
            comb(0, 0);
            System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
        }
    }

    private static void comb(int start, int cnt) {
        if (cnt == M) {
            spread(blankCnt);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            active[cnt] = virus.get(i);
            comb(i + 1, cnt + 1);
        }
    }

    private static void spread(int blank) {
        int time = 0;
        boolean[][] visit = new boolean[N][N];

        Queue<Point> queue = new LinkedList<>();
        for (Point p : active) {
            queue.offer(p);
            visit[p.r][p.c] = true;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();

                if (map[cur.r][cur.c] == 0) blank--;
                if (blank == 0) {
                    ans = Math.min(ans, time);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || visit[nr][nc] || map[nr][nc] == 1) continue;

                    queue.offer(new Point(nr, nc));
                    visit[nr][nc] = true;
                }
            }
            time++;
        }

    }

    private static void print(int[][] arr) {
        for (int[] row : arr) System.out.println(Arrays.toString(row));
        System.out.println();
    }

}
