package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * Kruskal 알고리즘 사용
 * @Author : jhno96
 * @Date : 2022-06-15
 */
public class Main_BJ_4386_별자리만들기_Kruskal {

    static class Point{
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Edge implements Comparable<Edge>{
        int from, to;
        double dist;

        public Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 문제 입력
        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            points[i] = new Point(x, y);
        }

        // 간선리스트 계산
        ArrayList<Edge> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = getDist(points[i], points[j]);
                list.add(new Edge(i, j, dist));
            }
        }
        // 간선 리스트 정렬
        Collections.sort(list);
        // 각 정점의 부모 초기화
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        double ans = 0;
        // Kruskal
        for (Edge e : list) {
            if (find(parent, e.from) != find(parent, e.to)) {
                union(parent, e.from, e.to);
                ans += e.dist;
            }
        }
        
        System.out.println(ans);
    }

    private static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return find(parent, parent[x]);
    }

    private static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    private static double getDist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

}
