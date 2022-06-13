package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-11
 */
public class Main_BJ_1197_최소스패닝트리_Kruskal {

    static class Node implements Comparable<Node>{
        int from, to, w;

        public Node(int from, int to, int w) {
            this.from = from;
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<Node> list = new ArrayList<>();
        int[] parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.add(new Node(from, to, w));
        }

        Collections.sort(list);

        int sum = 0;
        for (int i = 0; i < E; i++) {
            Node cur = list.get(i);
            if (findParent(parent, cur.from) != findParent(parent, cur.to)) {
                union(parent, cur.from, cur.to);
                sum += cur.w;
            }
        }

        System.out.println(sum);
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

}
