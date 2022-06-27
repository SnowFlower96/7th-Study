package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_12865_평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] w = new int[N+1]; // 무게
        int[] v = new int[N+1]; // 가치
        int[][] D = new int[N+1][W+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
                if(w[i] > j) { // 현재 무게보다 담을려는 무게가 크면 이전꺼를 그대로 사용
                    D[i][j] = D[i - 1][j]; // 이 물건 전까지 고려한 상황과 동일하다.
                }else{ // 전체 가치는 물건i의 가치 + 물건1~(i-1) 까지 고려하여 배낭의 용량이 (W-j)인 경우의 최대 가치
                    D[i][j] = Math.max(v[i] + D[i-1][j-w[i]],D[i-1][j]);
                }
            }
        }

        System.out.println(D[N][W]);

    }
}
