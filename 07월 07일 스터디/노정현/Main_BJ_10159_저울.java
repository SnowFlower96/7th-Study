package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 * @Author jhno96
 * @Date 2022-07-07
 */
public class Main_BJ_10159_저울 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 문제 입력
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int heavier = Integer.parseInt(st.nextToken());
            int lighter = Integer.parseInt(st.nextToken());
            map[heavier][lighter] = 1;  // 무거운거 -> 가벼운거 : 1
            map[lighter][heavier] = -1; // 가벼운거 -> 무거운거 : -1
        }

        for (int k = 1; k <= N; k++) {  // 중간 비교
            for (int i = 1; i <= N; i++) {  // 출발지
                if (i == k) continue;  // 중간과 출발지가 같다면
                for (int j = 1; j <= N; j++) {  // 도착지
                    if (i == j || j == k) continue;  // 출발과 도착이 같거나 도착이 중간과 같다면
                    // 물건 비교 결과가 없을 때
                    if (map[i][j] == 0) {
                        // i -> k와 k -> j가 1이면 i -> j는 무거운거 -> 가벼운거인 1
                        if (map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                        // i -> k와 k -> j가 -1이면 i -> j는 가벼운거 -> 무거운거인 -1
                        else if (map[i][k] == -1 && map[k][j] == -1) map[i][j] = -1;
                    }
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                // 출발 -> 도착이 아니고 비교 결과를 알 수 없을 때 카운트
                if (i != j && map[i][j] == 0) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString().trim());
    }

}
