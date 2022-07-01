package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_14621_나만안되는연애 {

    static int N, M;
    static char[] MW;
    static Node[] list;
    static int[] parents;

    static class Node implements Comparable<Node>{
        int node1;
        int node2;
        int cost;

        Node(int node1, int node2, int cost){
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MW = new char[N+1]; // 대학교 순서대로 남초인지 여초인지 MANWOMAN
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            MW[i] = st.nextToken().charAt(0);
        }

        list = new Node[M];
        parents = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[i] = new Node(node1,node2,cost);
        }

        int answer = -1;
        int res = 0, cnt = 0;

        Arrays.sort(list); // 오름차순정렬
        make(); // 유니온-파인드에 사용될 간선그래프 생성

        // 주어진 간선을 이어보면서
        for (Node n : list) {
            // 해당 간선들이 그래프에서 연결되어 있는지 확인
            if(MW[n.node1] == MW[n.node2]) continue;
            if(union(n.node1, n.node2)) {
                // 해당 간선을 사용
                res += n.cost;
                // 간선의 개수가 정점의 개수 - 1 그래프가 전부 이어진 것
                if(++cnt == N - 1) {
                    answer = res;
                    break;
                }
            }
        }

        System.out.println(answer);
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
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }
}
