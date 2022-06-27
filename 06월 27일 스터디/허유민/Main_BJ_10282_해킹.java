package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_10282_해킹 {

    static int N, D, C;
    static ArrayList<ArrayList<int[]>> list;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            list = new ArrayList<ArrayList<int []>>();
            for (int j = 0; j <= N; j++) {
                list.add(new ArrayList<int []>());
            }

            // 의존성 (a,b)는 b가 감염되었을때 s초후에 a가 감염된다는 뜻
            // a : to, b : from, s : 가중치,비용
            for (int j = 0; j < D; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new int[]{a,s});
            }

            distance = new int[N+1];
            Arrays.fill(distance,Integer.MAX_VALUE);

            dijkstra();

            int cnt = 0;
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= N; j++) {
                // 방문 컴퓨터 개수와 가장 큰 시간이 모든 컴퓨터가 감염된 시간
                if(distance[j] != Integer.MAX_VALUE){
                    cnt++;
                    max = Math.max(max,distance[j]);
                }
            }
            sb.append(cnt).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    public static void dijkstra(){
        PriorityQueue<int []> pque = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));

        distance[C] = 0;
        pque.offer(new int[]{C,0});

        while(!pque.isEmpty()){
            int[] current = pque.poll();
            int b = current[0];
            int s = current[1];
            if(distance[b] < s){
                continue;
            }

            // 현재에서 이동가능한 컴퓨터 확인
            for (int j = 0; j < list.get(b).size(); j++) {
                int[] next = list.get(b).get(j);
                int a = next[0];
                int a_s = next[1];

                // 최소시간으로 갱신
                if(distance[a] > s+a_s){
                    distance[a] = s + a_s;
                    pque.offer(new int[]{a,s+a_s});
                }
            }
        }
    }
}
