package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @Author : jhno96
 * @Date : 2022-06-01
 */
public class Main_BJ_1890_점프 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        long[][] dp = new long[N][N];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 목표지점 도달 시
                if(i == N - 1 && j == N - 1) break;

                // dp의 값이 0이면 갈 수 없는 칸
                if (dp[i][j] == 0) continue;

                int cur = arr[i][j];
                // 우측으로 갈 수 있을 때
                if (j + cur < N) {
                    dp[i][j + cur] += dp[i][j];
                }
                // 아래으로 갈 수 있을 때
                if (i + cur < N) {
                    dp[i + cur][j] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }

//    static final int[] dr = { 0, 1 };
//    static final int[] dc = { 1, 0 };
//    private static void solution(int N, int[][] arr, int[][] dp, int r, int c) {
//        dp[r][c]++;
//        if(r == N - 1 && c == N - 1) return;
//
//        int cur = arr[r][c];
//        for (int d = 0; d < 2; d++) {
//            int nr = r + dr[d] * cur;
//            int nc = c + dc[d] * cur;
//
//            if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
//
//            solution(N, arr, dp, nr, nc);
//        }
//    }

}
