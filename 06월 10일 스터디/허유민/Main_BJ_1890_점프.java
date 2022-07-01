package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1890_점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        long[][] D = new long[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        D[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i==N-1&&j==N-1) continue;
                int next = map[i][j];
                if(i + next < N){
                    D[i+next][j] += D[i][j];
                }
                if(j + next < N){
                    D[i][j+next] += D[i][j];
                }
            }
        }
        System.out.println(D[N-1][N-1]);

    }
}
