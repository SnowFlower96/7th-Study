package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-22
 */
public class Main_BJ_1916_최소비용구하기 {

    static class Node implements Comparable<Node> {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 수와 간선의 수 입력
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 인접리스트 초기화
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 인접리스트 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to, w));
        }

        // 출발지와 목적지 입력
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        // Dijkstra
        int[] dist = new int[N + 1];  // 각 정점으로의 최소 거리를 저장할 배열
        for (int i = 1; i <= N; i++) {
            // 초기화
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;  // 출발지는 거리가 0

        boolean[] visit = new boolean[N + 1];  // 정점 방문 체크 배열
        PriorityQueue<Node> pq = new PriorityQueue<>();  // 우선순위큐(가중치기준)
        pq.offer(new Node(start, 0));  // 시작점 추가
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 방문 확인 및 체크
            if (visit[cur.to]) continue;
            visit[cur.to] = true;

            // 연결되어있는 노드중에 확인
            for (Node n : list.get(cur.to)) {
                if (!visit[n.to] && dist[n.to] > (n.w + dist[cur.to])) {
                    dist[n.to] = n.w + dist[cur.to];
                    pq.offer(new Node(n.to, dist[n.to]));
                }
            }
        }

        // 결과 출력
        System.out.println(dist[dest]);
    }

}
