package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : Home
 * @Date : 2022-04-28
 */
public class Main_BJ_21610_마법사상어와비바라기 {

    static class Point {

        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" +
                    "" + r +
                    ", " + c +
                    ')';
        }
    }

    static int N, M;
    static int[][] basket;
    static List<Point> clouds;  // 기존의 비구름
    static List<Point> newClouds; // 이동한 비구름

    // 좌측부터 시계방향
    static final int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static final int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        basket = new int[N][N];

        // 비 구름 위치 입력
        clouds = new ArrayList<>();
        newClouds = new ArrayList<>();
        clouds.add(new Point(N - 1, 0));
        clouds.add(new Point(N - 1, 1));
        clouds.add(new Point(N - 2, 0));
        clouds.add(new Point(N - 2, 1));

        // 지도 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                basket[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            order(d, s);
        }

        // 물의 양 계산
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += basket[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void order(int d, int s) {
        boolean[][] wasCloud = new boolean[N][N];

        for (Point cloud : clouds) {
            // 1. 모든 구름이 d 방향으로 s칸 이동, 경계를 벗어나면 반대편으로 이동
            int nr = (cloud.r + N + dr[d - 1] * s % N) % N;
            int nc = (cloud.c + N + dc[d - 1] * s % N) % N;

            // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
            basket[nr][nc]++;
            // 이동한 구름위치 저장 및 표시
            newClouds.add(new Point(nr, nc));
            wasCloud[nr][nc] = true;
        }

        // 3. 구름이 모두 사라짐
        clouds.clear();

        // 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다.
        for (Point cloud : newClouds){
            int r = cloud.r;
            int c = cloud.c;
            // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
            int cnt = 0;
            // 대각선 방향
            for (int dir = 1; dir < 8; dir+=2) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                // 경계 체크 및 물이 있는지 확인
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && basket[nr][nc] > 0) cnt++;
            }

            // 물이 있는 바구니의 수만큼 물의 양 증가
            basket[r][c] += cnt;
        }

        // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고,
        // 물의 양이 2 줄어든다.
        // 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
        // 바구니에 저장된 물의 양이 2칸 미만이거나 구름이였던 곳이면 다음 칸 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(basket[i][j] >= 2 && !wasCloud[i][j]){
                    clouds.add(new Point(i, j));
                    basket[i][j] -= 2;
                }
            }
        }

        newClouds.clear();
    }

}
