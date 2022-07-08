package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16234_인구이동 {

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(move());
    }

    // 인구이동
    public static int move() {

        int count = 0;
        while(true) {
            boolean flag = false;
            visited = new boolean[N][N]; // 방문체크를 위한 배열
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        int sum = bfs(i,j);
                        if(list.size() > 1) {
                            int avg = sum /list.size();
                            for (Node n : list) {
                                map[n.x][n.y] = avg;
                            }
                            flag = true;
                        }
                    }
                }
            }

            // 인구가 이동되어 각 연합들의 나라에 인구가 나눠지면
            if(!flag) return count;
            count++;
        }
    }

    // bfs 탐색을하면서
    public static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<Node>();
        list = new ArrayList<>();

        queue.offer(new Node(x,y));
        list.add(new Node(x,y));
        visited[x][y] = true;

        int sum = map[x][y];
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // current 정점의 인접정점 처리(단, 방문하지 않은 인접정점만)
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                // 범위를 벗어나지 않는지 방문을 하지않은 곳
                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    int d = Math.abs(map[current.x][current.y] - map[nx][ny]);
                    // 인구차이가 L명이상 , R명 이하 다음 나라를 넣고 국경이 연결된 나라의 인구수 합계를 구한다. 방문체크
                    if(L <= d && d <= R) {
                        queue.offer(new Node(nx, ny));
                        list.add(new Node(nx, ny));
                        sum += map[nx][ny];
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return sum;
    }

}
