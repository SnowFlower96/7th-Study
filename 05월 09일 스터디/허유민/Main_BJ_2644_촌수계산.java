package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2644_촌수계산 {

    static int N,M, Find1,Find2,answer;
    static int[][] list; // 인접행렬 리스트
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        N = Integer.parseInt(br.readLine());
        list = new int[N+1][N+1]; // 0번 사용을 하지 않기위해 1더해줌
        visited = new boolean[N+1];

        st = new StringTokenizer(br.readLine());

        Find1 = Integer.parseInt(st.nextToken());
        Find2 = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 1촌 관계 양방향 관계
            list[from][to] = 1;
            list[to][from] = 1;
        }
        answer = -1; // find2를 찾지못할경우 -1 출력
        dfs(Find1,0);
        System.out.println(answer);
    }

    private static void dfs(int find1, int depth) {
        if(find1 == Find2){
            answer = depth;
            return;
        }
        visited[find1] = true;
        for (int i = 1; i <= N; i++) {
            if(list[find1][i] != 1 || visited[i]){
                continue;
            }
            visited[i] = true;
            dfs(i,depth+1);
            visited[i] = false;
        }
    }
}
