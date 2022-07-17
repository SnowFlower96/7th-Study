package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Study
 * @Author jhno96
 * @Date 2022. 07. 13
 */
public class Main_BJ_17070_파이프옮기기1 {

    static int N, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
//        dynamic();
        dfs(1, 2, 0);

        System.out.println(ans);
    }

    private static void dfs(int r, int c, int dir) {
        if (r == N && c == N) {
            ans++;
            return;
        }

        if (dir == 0) {
            if (c + 1 <= N && map[r][c + 1] == 0) dfs(r, c + 1, 0);
        } else if (dir == 1) {
            if (r + 1 <= N && map[r + 1][c] == 0) dfs(r + 1, c, 1);
        } else if (dir == 2) {
            if (c + 1 <= N && map[r][c + 1] == 0) dfs(r, c + 1, 0);
            if (r + 1 <= N && map[r + 1][c] == 0) dfs(r + 1, c, 1);
        }

        if (r + 1 <= N && c + 1 <= N && map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) dfs(r + 1, c + 1, 2);
    }

    private static void dynamic() {
        int[][][] dp = new int[N + 1][N + 1][3];
        dp[1][2][0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1) continue;

                dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
                dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
                if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                    dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        ans = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
    }

}
