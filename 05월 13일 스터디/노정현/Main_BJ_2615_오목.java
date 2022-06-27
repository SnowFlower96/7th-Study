package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 *
 * @Author : jhno96
 * @Date : 2022-05-10
 */
public class Main_BJ_2615_오목 {

    static final int size = 19;
    static int[][] map;
    static boolean[][] visit;

    // 우, 우하, 하, 좌하
    static final int[] dr = {0, 1, 1, 1};
    static final int[] dc = {1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[size][size];
        visit = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int color = map[i][j];

                if (color == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = i;
                    int nc = j;
                    // 해당 방향으로 끝까지 가기
                    for (int k = 1; ; k++) {
                        nr += dr[d];
                        nc += dc[d];

                        if (nr < 0 || nr >= size || nc < 0 || nc >= size || map[nr][nc] != color) {
                            nr -= dr[d];
                            nc -= dc[d];
                            break;
                        }
                    }
                    // 다시 반대 방향 끝까지 가기
                    int sr = nr;
                    int sc = nc;
                    int cnt;
                    for (cnt = 1; ; cnt++) {
                        sr -= dr[d];
                        sc -= dc[d];
                        if (sr < 0 || sr >= size || sc < 0 || sc >= size || map[sr][sc] != color) {
                            sr += dr[d];
                            sc += dc[d];
                            break;
                        }
                    }

                    if (cnt == 5) {
                        System.out.println(color);
                        if(d == 3) {
                            System.out.println((nr + 1) + " " + (nc + 1));
                        }
                        else {
                            System.out.println((sr + 1) + " " + (sc + 1));
                        }
                        System.exit(0);
                    }
                }
            }
        }
        System.out.println(0);
    }

}
