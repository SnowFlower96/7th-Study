package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-11
 */
public class Main_BJ_1197_최소스패닝트리_Prim {

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

    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, w));
            list[to].add(new Node(from, w));
        }

        int sum = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[V + 1];
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visit[cur.to]) continue;
            visit[cur.to] = true;
            sum += cur.w;
            for (Node n : list[cur.to]) {
                if (!visit[n.to]) {
                    pq.add(n);
                }
            }
        }

        System.out.println(sum);
    }

}
