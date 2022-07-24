package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022. 07. 24
 */
public class Main_BJ_17406_배열돌리기4 {

    static class RotateInfo {
        int sr, sc, er, ec, s;
        int length;
        int outerForCnt;

        public RotateInfo(int r, int c, int s) {
            this.sr = r - s;
            this.sc = c - s;
            this.er = r + s;
            this.ec = c + s;
            this.s = s;
            this.length = s * 2 + 1;
            this.outerForCnt = (this.er - this.sr) / 2;
        }
    }

    static int N, M, K, ans;
    static int[][] inputArr;
    static RotateInfo[] rotateInfoArr;
    static int[] order;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열의 크기, 회전 연산 수 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        inputArr = new int[N][M];
        rotateInfoArr = new RotateInfo[K];
        order = new int[K];
        visit = new boolean[K];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                inputArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 정보 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            rotateInfoArr[i] = new RotateInfo(r, c, s);
        }

        permute(0);
        System.out.println(ans);
    }

    // 회전할 경우의 수
    public static void permute(int cnt) {
        if (cnt == K) {
            ans = Math.min(ans, rotateArr());
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visit[i]) continue;

            order[cnt] = i;
            visit[i] = true;
            permute(cnt + 1);
            visit[i] = false;
        }
    }

    // 주어진 순서대로 배열 회전
    public static int rotateArr() {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = inputArr[i].clone();
        }

        for (int idx : order) {
            RotateInfo rotateInfo = rotateInfoArr[idx];
            for (int i = 0; i < rotateInfo.outerForCnt; i++) {
                int sr = rotateInfo.sr + i;
                int sc = rotateInfo.sc + i;
                int er = (sr + rotateInfo.length) - i * 2 - 1;
                int ec = (sc + rotateInfo.length) - i * 2 - 1;
                int buffer = copy[sr][sc];
                // left
                for (int r = sr; r < er; r++) {
                    copy[r][sc] = copy[r + 1][sc];
                }
                // bottom
                for (int c = sc; c < ec; c++) {
                    copy[er][c] = copy[er][c + 1];
                }
                // right
                for (int r = er; r > sr; r--) {
                    copy[r][ec] = copy[r - 1][ec];
                }
                // top
                for (int c = ec; c > sc; c--) {
                    copy[sr][c] = copy[sr][c - 1];
                }
                copy[sr][sc + 1] = buffer;
            }
        }

        return getArrValue(copy);
    }

    // 배열 값(행의 합 중에서 가장 작은 합) 구하기
    public static int getArrValue(int[][] arr) {
        int result = Integer.MAX_VALUE;
        for (int[] row : arr) {
            int sum = 0;
            for (int col : row) {
                sum += col;
            }

            result = Math.min(result, sum);
        }

        return result;
    }

    public static void print(int[][] arr) {
        System.out.println("-------------------");
        for (int[] row : arr) System.out.println(Arrays.toString(row));
        System.out.println("-------------------");
    }

}
