package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_1774_우주신과의교감 {

    static int N, M;
    static Node[] list;
    static int[] parents;
    static ArrayList<Edge> edges;

    // 우주신
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 우주신과 연결된 통로
    static class Edge implements Comparable<Edge> {
        int node1, node2;
        double cost;

        public Edge(int node1, int node2, double cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost < o.cost ? -1 : 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new Node[N + 1];
        parents = new int[N + 1];

        // 황선자씨와 우주신 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[i] = new Node(x, y);
        }

        make();

        // 이미 연결된 통로
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            union(n1, n2);
        }

        edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double cost = distance(list[i], list[j]);

                edges.add(new Edge(i, j, cost));
            }
        }
        Collections.sort(edges);

        double answer = 0;

        for (Edge e : edges) {

            if(find(e.node1) == find(e.node2)) continue;
            if(union(e.node1,e.node2)){
                answer += e.cost;
            }
        }
        System.out.println(String.format("%.2f",answer) + "\n");

    }

    public static int find(int node) {
        if (node == parents[node]) return node;
        return parents[node] = find(parents[node]);
    }

    public static boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if (root1 == root2) return false;
        parents[root1] = root2;
        return true;
    }

    public static void make() {
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    public static double distance(Node n1, Node n2) {
        int dx = n1.x - n2.x;
        int dy = n1.y - n2.y;

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}
