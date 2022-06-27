package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-25
 */
public class Main_BJ_1504_특정한최단경로 {

    static final int INF = 200000000;

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

        // 정점의 수, 간선의 수, 인접리스트 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 인접리스트 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to, w));
            list.get(to).add(new Node(from, w));
        }

        // 시작과 도착 입력
        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());

        int oneToTwo = 0;
        oneToTwo += Dijkstra(N, list, 1, V1);
        oneToTwo += Dijkstra(N, list, V1, V2);
        oneToTwo += Dijkstra(N, list, V2, N);

        int twoToOne = 0;
        twoToOne += Dijkstra(N, list, 1, V2);
        twoToOne += Dijkstra(N, list, V2, V1);
        twoToOne += Dijkstra(N, list, V1, N);

        if (oneToTwo <= INF && twoToOne <= INF)
            System.out.println(Math.min(oneToTwo, twoToOne));
        else System.out.println(-1);
    }

    // 다익스트라
    private static int Dijkstra(int N, ArrayList<ArrayList<Node>> list, int st, int ed) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];  // 방문 체크 배열
        int[] dist = new int[N + 1];  // 거리 저장 배열

        // 거리 저장 배열 초기화
        Arrays.fill(dist, INF);
        dist[st] = 0;  // 시작점

        pq.offer(new Node(st, 0));
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

        return dist[ed];
    }
}
