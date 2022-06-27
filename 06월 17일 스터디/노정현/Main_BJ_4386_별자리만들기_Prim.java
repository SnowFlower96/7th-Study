package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Study
 * Prim 알고리즘 사용
 * @Author : jhno96
 * @Date : 2022-06-15
 */
public class Main_BJ_4386_별자리만들기_Prim {

    static class Point{
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Node implements Comparable<Node>{
        int to;
        double dist;

        public Node(int to, double dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            points[i] = new Point(x, y);
        }

        ArrayList<Node>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = getDist(points[i], points[j]);
                list[i].add(new Node(j, dist));
                list[j].add(new Node(i, dist));
            }
        }

        double ans = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[n];
        pq.add(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visit[cur.to]) continue;
            visit[cur.to] = true;
            ans += cur.dist;
            for (Node node : list[cur.to]) {
                if (!visit[node.to]) pq.add(node);
            }
        }
        
        System.out.println(ans);
    }

    private static double getDist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

}
