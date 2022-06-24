package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-21
 */
public class Main_BJ_18352_특정거리의도시찾기 {

    static class Node implements Comparable<Node>{
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

        // 문제 크기 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 인접리스트 초기화
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 연결 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to, 1));
        }

        // 다익스트라
        boolean[] visit = new boolean[N + 1];  // 방문 체크
        int[] dist = new int[N + 1];  // 해당 정점까지의 최소 거리를 저장할 배열

        // 초기화
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[X] = 0;  // 출발지는 0으로 초기화

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visit[cur.to]) continue;
            visit[cur.to] = true;

            for (Node n : list.get(cur.to)) {
                if (!visit[n.to] && dist[n.to] > (n.w + dist[cur.to])) {
                    dist[n.to] = n.w + dist[cur.to];
                    pq.offer(new Node(n.to, dist[n.to]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) sb.append(i).append("\n");
        }

        System.out.println(sb.length() == 0 ? -1 : sb.toString().trim());
    }

}
