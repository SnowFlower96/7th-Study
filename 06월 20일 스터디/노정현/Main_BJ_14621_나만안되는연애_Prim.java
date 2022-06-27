package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Study
 * Prim 알고지름
 * @Author : jhno96
 * @Date : 2022-06-20
 */
public class Main_BJ_14621_나만안되는연애_Prim {

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[] isMan = new boolean[N + 1];
        ArrayList<Node>[] nodeList = new ArrayList[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            // 정점리스트 초기화
            nodeList[i] = new ArrayList<>();
            // 각 정점의 유형 저장
            isMan[i] = st.nextToken().equals("M");
        }

        // 정점리스트배열 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // 정점의 유형이 서로 다를 때만
            if(isMan[from] != isMan[to]) {
                nodeList[from].add(new Node(to, w));
                nodeList[to].add(new Node(from, w));
            }
        }

        int ans = 0;  // 경로의 길이
        int cnt = 0;  // MST 를 구성하는 정점의 수
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N + 1];  // 정점 방문체크
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visit[cur.to]) continue;
            visit[cur.to] = true;
            ans += cur.w;
            cnt++;
            for (Node n : nodeList[cur.to]) {
                if (!visit[n.to]) {
                    pq.offer(n);
                }
            }
        }

        // MST 를 구성하는 정점의 수를 확인하여 모든 학교를 연결하는 경로가 있는지 판단
        System.out.println(cnt == N ? ans : -1);
    }

}
