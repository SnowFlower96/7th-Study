package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Study
 * @Date 2022-09-03
 * @Author jhno96
 */
public class Main_BJ_1774_우주신과의교감 {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int from, to;
        double w;

        public Edge(int from, int to, double w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }


        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Point[] points = new Point[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 좌표 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        // 이미 연결된 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(parent, from, to);
        }

        // 각 정점간 거리 계산
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double distance = getDistance(points[i], points[j]);
                pq.offer(new Edge(i, j, distance));
            }
        }

        double ans = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (findParent(parent, e.from) != findParent(parent, e.to)) {
                ans += e.w;
                union(parent, e.from, e.to);
            }
        }

        System.out.printf("%.2f%n", ans);
    }

    private static void union(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);

        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    private static int findParent(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return findParent(parent, parent[x]);
    }

    private static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }

}
