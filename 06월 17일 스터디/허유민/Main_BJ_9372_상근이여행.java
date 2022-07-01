package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_9372_상근이여행 {

    static int N, M;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 국가수 (노드)
            M = Integer.parseInt(st.nextToken()); // 비행기 종류 (간선)
            matrix = new int[N+1][N+1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());

                matrix[node1][node2] = 1;
                matrix[node2][node1] = 1;
            }
            sb.append(N-1).append("\n");
        }
        System.out.println(sb);
    }
}
