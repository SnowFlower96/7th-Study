package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_1916_최소비용구하기 {

    static int N,M,start,end;
    static final int INF = Integer.MAX_VALUE-1;

    static int[][] arr;
    static boolean[] visited;
    static long[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i==j) {
                    arr[i][j] = 0;
                    continue;
                }
                arr[i][j] = Integer.MAX_VALUE-1; // infinite
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 같은 경로인데 더 낮은 비용을 쓰는것만 사용
            if(arr[from][to] == -1){
                arr[from][to] = cost;
            }
            else if(arr[from][to] > cost){
                arr[from][to] = cost;
            }
            //list.add(new Node(from,to,distance));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        distance = new long[N+1];
        for (int i = 1; i <= N; i++) {
            distance[i] = arr[start][i];
        }

        visited[start] = true;

        for (int i = 0; i < N-1; i++) {
            long MIN = Integer.MAX_VALUE;
            int current = 0;
            // 단계 1 : 최소비용이 확정되지 않은 정점중 최소비용의 정점 선택
            for (int j = 1; j <= N; j++) {
                if(!visited[j] && MIN > distance[j]){
                    MIN = distance[j];
                    current = j;
                }
            }
            visited[current] = true;

            // 단계 2 : 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른정점의 최소비용을 고려
            for (int j = 1; j <= N; j++) {
                if(!visited[j] &&
                distance[j] > distance[current] + arr[current][j]){
                    distance[j] = distance[current] + arr[current][j];
                }
            }
        }
        System.out.println(distance[end]);
    }

}
