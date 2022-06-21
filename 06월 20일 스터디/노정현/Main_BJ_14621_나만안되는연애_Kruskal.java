package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * Kruskal 알고리즘
 * @Author : jhno96
 * @Date : 2022-06-20
 */
public class Main_BJ_14621_나만안되는연애_Kruskal {

    static class Edge implements Comparable<Edge>{
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> edgeList = new ArrayList<>();
        boolean[] isMan = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            // 각 정점의 유형 저장
            isMan[i] = st.nextToken().equals("M");
        }

        // 간선리스트 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // 정점의 유형이 서로 다를 때만
            if(isMan[from] != isMan[to]) {
                edgeList.add(new Edge(from, to, w));
            }
        }

        // 각 정점의 부모 초기화
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 간선리스트를 가중치 기준으로 정렬
        Collections.sort(edgeList);

        int ans = 0;  // 경로의 길이
        int cnt = 0;  // MST 를 구성하는 간선의 수
        for (Edge e : edgeList) {
            // 각 정점의 부모가 같지 않으면(= 사이클이 발생하지 않으면)
            if (findParent(parent, e.from) != findParent(parent, e.to)) {
                union(parent, e.from, e.to);
                ans += e.w;
                cnt++;
            }
        }

        // 간선의 수가 (정점의 수 - 1)이 아니면 MST 가 아니므로 모든 학교를 연결하는 경로가 없다.
        System.out.println(cnt == N - 1 ? ans : -1);
    }

    private static int findParent(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return findParent(parent, parent[x]);
    }

    private static void union(int[] parent, int x, int y) {
        x = findParent(parent, x);
        y = findParent(parent, y);

        if (x > y) parent[x] = y;
        else parent[y] = x;
    }

}
