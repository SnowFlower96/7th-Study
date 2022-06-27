package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_20057_마법사상어와토네이도 {

    static class Point {

        int r, c, sand;

        public Point(int r, int c, int sand) {
            this.r = r;
            this.c = c;
            this.sand = sand;
        }

        public boolean checkBorder(int N) {
            return this.r >= 0 && this.r < N && this.c >= 0 && this.c < N;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", sand=" + sand +
                    '}';
        }
    }

    static final int[] dr = { 0, 1, 0, -1 };
    static final int[] dc = { -1, 0, 1, 0 };

    static int N, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moveStorm();
        System.out.println(ans);
    }

    private static void moveStorm() {
        Point cur = new Point(N / 2, N / 2, 0);
        int dir = 0;
        int curMax = 1;
        while(true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < curMax; j++) {
                    cur.r += dr[dir];
                    cur.c += dc[dir];
                    cur.sand = map[cur.r][cur.c];
                    spread(dir, cur);
                    if (cur.r == 0 && cur.c == 0) {
                        return;
                    }
                }
                dir = (dir + 1) % 4;
            }
            curMax++;
        }

    }

    private static void spread(int dir, Point cur) {
        if (cur.sand == 0) return;

        map[cur.r][cur.c] = 0;
        int sand = cur.sand;
        Point[] target = {
                // 5%
                new Point(cur.r + dr[dir] * 2, cur.c + dc[dir] * 2, (int) (sand * 0.05)),
                // 7%
                new Point(cur.r + dr[(dir + 3) % 4], cur.c + dc[(dir + 3) % 4], (int) (sand * 0.07)),
                new Point(cur.r + dr[(dir + 1) % 4], cur.c + dc[(dir + 1) % 4], (int) (sand * 0.07)),
                // 2%
                new Point(cur.r + dr[(dir + 3) % 4] * 2, cur.c + dc[(dir + 3) % 4] * 2, (int) (sand * 0.02)),
                new Point(cur.r + dr[(dir + 1) % 4] * 2, cur.c + dc[(dir + 1) % 4] * 2, (int) (sand * 0.02)),
                // 1%
                new Point(cur.r + dr[(dir + 3) % 4] + dr[(dir + 2) % 4], cur.c + dc[(dir + 3) % 4] + dc[(dir + 2) % 4], (int) (sand * 0.01)),
                new Point(cur.r + dr[(dir + 1) % 4] + dr[(dir + 2) % 4], cur.c + dc[(dir + 1) % 4] + dc[(dir + 2) % 4], (int) (sand * 0.01)),
                // 10%
                new Point(cur.r + dr[(dir + 3) % 4] + dr[dir], cur.c + dc[(dir + 3) % 4] + dc[dir], (int) (sand * 0.1)),
                new Point(cur.r + dr[(dir + 1) % 4] + dr[dir], cur.c + dc[(dir + 1) % 4] + dc[dir], (int) (sand * 0.1)),
        };

        // 모래 확산
        for (Point p : target) {
            cur.sand -= p.sand;
            // 범위 안이면
            if (p.checkBorder(N)) {
                map[p.r][p.c] += p.sand;
            }
            // 범위 밖이면
            else {
                ans += p.sand;
            }
        }

        // 확산되고 남은 모래
        Point a = new Point(cur.r + dr[dir], cur.c + dc[dir], cur.sand);
        // 범위 안이면
        if (a.checkBorder(N)) map[a.r][a.c] += a.sand;
        else ans += a.sand;
    }

    private static void print() {
        for (int[] row : map) System.out.println(Arrays.toString(row));
        System.out.println(ans);
    }

}
