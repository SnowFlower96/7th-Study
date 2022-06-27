package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_11726_2xn타일링_허유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] D = new int[n+2];

        D[0] = 1;
        D[1] = 1;

        // 상향식(DP)
        for (int i = 2; i <= n; i++) {
            D[i] = (D[i-1] + D[i-2]) % 10007;
        }
        System.out.println(D[n]);
    }
}
