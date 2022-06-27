package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14938_서강그라운드 {

    static int N, M, R;
    static int items[];
    static int[][] arrmatrix;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        items = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        arrmatrix = new int[N+1][N+1];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            arrmatrix[a][b] = l;
            arrmatrix[b][a] = l;
        }

        int answer = 0;
        // 출발지가 정해져있지 않으므로 N개의 출발지 전부 확인
        for (int i = 1; i <= N; i++) {
            distance = new int[N+1];
            Arrays.fill(distance,Integer.MAX_VALUE);
            dijkstra(i);
            int cnt = 0;
            // 최소비용이 M보다 작은 곳들의 아이템 수의 합을 구해 그 중에서 가장 큰 값이 정답
            for (int j = 1; j <= N; j++) {
                if(distance[j] <= M){
                    cnt += items[j];
                }
            }
            answer = Math.max(answer,cnt);
        }

        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        PriorityQueue<int []> pque = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));

        distance[start] = 0;
        pque.offer(new int[]{start,0});

        while(!pque.isEmpty()){
            int[] current = pque.poll();
            int a = current[0];
            int l = current[1];
            if(l > M){
                continue;
            }

            // 현재에서 이동가능한 지역확인
            for (int j = 1; j <= N; j++) {

                // 도로가 있고 최소바용을 갱신할수있는 경우
                if( arrmatrix[a][j] != 0 && distance[j] > l+arrmatrix[a][j]){
                    distance[j] = l + arrmatrix[a][j];
                    pque.offer(new int[]{j,l + arrmatrix[a][j]});
                }
            }
        }

    }
}
