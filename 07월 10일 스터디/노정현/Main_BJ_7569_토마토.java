package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-07-06
 */
public class Main_BJ_7569_토마토 {

    // 상단, 위, 우, 하, 좌, 하단
    static final int[] dr = { 0, -1, 0, 1, 0, 0 };
    static final int[] dc = { 0, 0, 1, 0, -1, 0};
    static final int[] dh = { -1, 0, 0, 0, 0, 1 };

    static class Point {
        int r, c, h;

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", h=" + h +
                    '}';
        }

        public Point(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }

    }

    static int M, N, H, rawCnt, ripeCnt, day;
    static int[][][] box;
    static Queue<Point> queue;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 크기 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];

        rawCnt = 0;
        ripeCnt = 0;
        day = 0;
        queue = new LinkedList<>();
        // 박스 정보 입력
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    box[h][i][j] = input;
                    if (input == 0) rawCnt++;
                    else if (input == 1) {
                        ripeCnt++;
                        queue.offer(new Point(i, j, h));
                    }
                }
            }
        }

        // 이미 다 익어있으면 종료
        if (rawCnt == 0) {
            System.out.println(0);
            return;
        }
        while (!queue.isEmpty()) {
            day++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                int r = cur.r;
                int c = cur.c;
                int h = cur.h;


                for (int d = 0; d < 6; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    int nh = h + dh[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || nh < 0 || nh >= H || box[nh][nr][nc] != 0) continue;

                    queue.offer(new Point(nr, nc, nh));
                    box[nh][nr][nc] = 1;
                    rawCnt--;
                    ripeCnt++;
                }
            }
        }

        System.out.println(rawCnt == 0 ? day - 1 : -1);
    }

    private static void print() {
        System.out.println("Print()");
        System.out.println(rawCnt + " " + ripeCnt);
        for(int[][] rc : box) {
            for (int[] r : rc) {
                System.out.println(Arrays.toString(r));
            }
        }
        System.out.println();
    }

}
