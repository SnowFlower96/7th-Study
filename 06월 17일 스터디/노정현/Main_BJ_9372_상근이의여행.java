package silver.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-14
 */
public class Main_BJ_9372_상근이의여행 {

    static class Node {
        int to;

        public Node(int to) {
            this.to = to;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            ArrayList<Node>[] list = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list[from].add(new Node(to));
                list[to].add(new Node(from));
            }

            Queue<Node> queue = new LinkedList<>();
            boolean[] visit = new boolean[N + 1];
            int ans = 0;
            queue.offer(new Node(1));
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                if (visit[cur.to]) continue;
                visit[cur.to] = true;
                ans++;
                for (Node node : list[cur.to]) {
                    if (!visit[node.to]) queue.add(node);
                }
            }

            sb.append(ans - 1).append("\n");
        }
        System.out.println(sb);
    }

}
