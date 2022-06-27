package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1197_최소스패닝트리 {
    static int V, E;
    static Node[] nodeList;
    static int[] parents;

    static class Node implements Comparable<Node>{
        int cost;
        int node1;
        int node2;

        Node(int node1, int node2, int cost){
            this.cost = cost;
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        nodeList = new Node[E];
        parents = new int[V+1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodeList[i] = new Node(node1,node2,cost);
        }
        int answer = 0;
        int res = 0, cnt = 0;

        Arrays.sort(nodeList); // 오름차순정렬
        make(); // 유니온-파인드에 사용될 간선그래프 생성

        // 주어진 간선을 이어보면서
        for (Node n : nodeList) {
            // 해당 가선들이 그래프에서 연결되어 있는지 확인
            if(union(n.node1, n.node2)) {
                // 해당 간선을 사용
                res += n.cost;
                // 간선의 개수가 정점의 개수 - 1 그래프가 전부 이어진 것
                if(++cnt == V - 1) {
                    answer = res;
                    break;
                }
            }
        }
        answer = res;
        sb.append(answer);

        System.out.println(sb);
    }

    public static int find(int node) {
        if(node == parents[node]) return node;
        return parents[node] = find(parents[node]);
    }

    public static boolean union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);

        if(root1 == root2) return false;
        parents[root1] = root2;
        return true;
    }

    public static void make() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }
}
