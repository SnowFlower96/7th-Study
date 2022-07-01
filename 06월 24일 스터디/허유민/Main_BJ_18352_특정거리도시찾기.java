package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_18352_특정거리도시찾기 {

    static int N,M,K,X;
    static ArrayList<Integer> answer;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> arrmatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        arrmatrix = new ArrayList<ArrayList<Integer>>(); // 도로를 담을 list
        for (int i = 0; i <= N; i++) {
            arrmatrix.add(new ArrayList<Integer>());
        }
        visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arrmatrix.get(from).add(to);
        }
        distance = new int[N+1];
        Arrays.fill(distance,-1); // 거리배열을 -1로 채운다

        answer = new ArrayList<>();
        dijkstra();

        // 최단 거리가 K인 도시 answer에 추가
        for (int i = 1; i <= N; i++) {
            if(distance[i] == K){
                answer.add(i);
            }
        }

        // answer가 비어있으면 -1 출력
        if(answer.isEmpty()){
            System.out.println(-1);
        }else {
            Collections.sort(answer); // 오름차순 정렬
            for (int i = 0; i < answer.size(); i++) {
                System.out.println(answer.get(i));
            }
        }
    }

    public static void dijkstra(){
        Queue<Integer> que = new LinkedList<Integer>();

        distance[X] = 0;
        que.offer(X);

        while(!que.isEmpty()){
            int current = que.poll();

            // 현재에서 이동가능한 도시 확인
            for (int j = 0; j < arrmatrix.get(current).size(); j++) {
                int next = arrmatrix.get(current).get(j);

                // 방문하지 않았다면 최단 거리 수정
                if(distance[next] == -1){
                    distance[next] = distance[current] + 1;
                    que.offer(next);
                }
            }
        }
    }
}
