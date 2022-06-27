package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @Author : jhno96
 * @Date : 2022-06-26
 */
public class Main_BJ_13549_숨바꼭질3 {

    static final int INF = Integer.MAX_VALUE;

    static class Point {
        int idx, time;

        public Point(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수빈이와 동생의 위치 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 수빈이의 위치
        int K = Integer.parseInt(st.nextToken());  // 동생의 위치

        // 큐와 방문 체크 배열 선언
        Queue<Point> queue = new LinkedList<>();
        boolean[] visit = new boolean[100001];

        // 시작점 입력
        queue.offer(new Point(N, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int idx = cur.idx;
            int time = cur.time;

            // 동생의 위치에 도달하면
            if (idx == K) {
                System.out.println(time);
                return;
            }

            // 방문 체크 확인
            if (visit[idx]) continue;
            visit[idx] = true;

            // 순간이동
            if (idx * 2 <= 100000 && !visit[idx * 2]) {
                queue.offer(new Point(idx * 2, time));
            }
            // 좌측 이동
            if (idx - 1 >= 0 && !visit[idx - 1]) {
                queue.offer(new Point(idx - 1, time + 1));
            }
            // 우측 이동
            if (idx + 1 <= 100000 && !visit[idx + 1]) {
                queue.offer(new Point(idx + 1, time + 1));
            }
        }
    }

}
