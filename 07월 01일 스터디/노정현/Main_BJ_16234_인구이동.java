package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-30
 */
public class Main_BJ_16234_인구이동 {

    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    static class Point {
        int r, c, population;

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", population=" + population +
                    '}';
        }

        public Point(int r, int c, int population) {
            this.r = r;
            this.c = c;
            this.population = population;
        }
    }

    static int N, L, R;
    static int[][] map;
    static Queue<Point> queue = new LinkedList<>();
    static int[][] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문제 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 지도 입력
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }

    private static int solution() {
        int ans = 0;

        while (true) {
            ans++;
            group = new int[N][N];
            int num = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (group[i][j] == 0) {
                        setGroupAndDivide(new Point(i, j, map[i][j]), num++);
                    }
                }
            }
            if (num == N * N + 1) break;
        }

        return ans - 1;
    }

    private static void setGroupAndDivide(Point st, int num) {
        int sum = 0;
        int cnt = 0;

        queue.offer(st);
        group[st.r][st.c] = num;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            cnt++;
            sum += cur.population;

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || group[nr][nc] != 0) continue;

                int diff = Math.abs(cur.population - map[nr][nc]);
                if (diff >= L && diff <= R) {
                    group[nr][nc] = num;
                    queue.offer(new Point(nr, nc, map[nr][nc]));
                }
            }
        }

        int avg = sum / cnt;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (group[i][j] == num) map[i][j] = avg;
            }
        }
    }

}
