package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17136_색종이붙이기 {

    static int[][] map;
    static int[] paperarray = {0, 5, 5, 5, 5, 5}; // 색종이 개수 저장
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        map = new int[10][10];
        boolean[][] visited = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        dfs(0, 0, paperarray, 0);

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);

    }

    public static void dfs(int r, int c, int[] paper, int cnt) {
        if (r == 9 && c == 10) {
            min = Math.min(min, cnt);
//            for (int i = 1; i <= 5; i++) {
//                System.out.println(paper[i]);
//            }
            return;
        }

        if (c == 10) {
            dfs(r + 1, 0, paper, cnt);
            return;
        }

        if (cnt >= min) {
            return;
        }

        if (map[r][c] == 1) {

            for (int k = 5; k >= 1; k--) {
                if (isinput(r, c, k) && paper[k] > 0) {
                    doAttach(r, c, k);
                    paper[k]--;
                    dfs(r, c + 1, paper, cnt + 1);
                    notAttach(r, c, k);
                    paper[k]++;
                }
            }

        } else {
            dfs(r, c + 1, paper, cnt);
        }

    }

    public static boolean isinput(int r, int c, int size) {

        int nr = r;

        for (int i = 0; i < size; i++) {
            int nc = c;
            if(nr >= 10 || nc >= 10 || map[nr][nc] != 1){
                return false;
            }
            for (int j = 0; j < size - 1; j++) {
                nc++;
                if (nr >= 10 || nc >= 10 || map[nr][nc] != 1) {
                    return false;
                }
            }
            nr++;
        }

        return true;
    }

    public static void doAttach(int r, int c, int size) {
        for (int i = 0; i < size; i++) {
            int nc = c;
            map[r][nc] = 2;
            for (int j = 0; j < size - 1; j++) {
                nc++;
                map[r][nc] = 2;
            }
            r++;
        }
    }

    public static void notAttach(int r, int c, int size) {
        for (int i = 0; i < size; i++) {
            int nc = c;
            map[r][nc] = 1;
            for (int j = 0; j < size - 1; j++) {
                nc++;
                map[r][nc] = 1;
            }
            r++;
        }
    }
}
