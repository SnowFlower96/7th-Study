package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-08
 */
public class Main_BJ_14500_테트로미노 {

    static int N, M, ans;
    static int[][] map;
    static boolean[][] visit;

    // 시계방향
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 저장
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 칸을 시작으로 테트로미노 놓기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 4가지 방법 dfs 로 확인
                visit[i][j] = true;  // 방문체크
                dfs(i, j, 1, map[i][j]);
                visit[i][j] = false;  // 방문체크 해제
                
                // T자 확인
                checkT(i, j);
            }
        }

        System.out.println(ans);
    }

    /**
     * T자를 제외한 테트로미노는 dfs 로 확인 가능
     * @param r r좌표
     * @param c c좌표
     * @param cnt 놓은 테트로미노의 수
     * @param sum 테트로미노가 놓인 칸에 쓰여 있는 수들의 합
     */
    private static void dfs(int r, int c, int cnt, int sum) {
        // 모든 테트로미노를 놓으면
        if(cnt == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        // 사방으로 탐색
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 경계 체크
            if(nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc]) continue;

            // 다른 모양도 체크하기 위해 방문체크 후 해제 
            visit[nr][nc] = true;
            dfs(nr, nc,cnt + 1, sum + map[nr][nc]);
            visit[nr][nc] = false;
        }
    }

    /**
     * T자 테트로미노 확인
     * +자에서 하나 없애서 확인
     * @param r 현재 r 좌표
     * @param c 현재 c 좌표
     */
    private static void checkT(int r, int c) {
        int sum = map[r][c];
        int min = Integer.MAX_VALUE;
        int cnt = 0;  // 놓을 수 없는 칸의 수
        
        for (int d = 0; d < 4; d++) {
            // 놓을 수 없는 칸이 두칸 이상이면 T자 테트로미노를 놓을 수 없음
            if (cnt > 1) return;

            int nr = r + dr[d];
            int nc = c + dc[d];

            // 경계 체크
            if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
                cnt++;  // 놓을 수 없는 칸 증가
                continue;
            }

            sum += map[nr][nc];
            // 주변의 칸 중 가장 작은 칸 저장
            min = Math.min(min, map[nr][nc]);
        }

        if(cnt == 0) sum -= min;  // 놓을 수 없는 칸이 0이다 => 십자 모양이면 가장 작은 칸 빼기
        ans = Math.max(ans, sum);
    }

}
