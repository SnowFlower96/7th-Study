package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14889_스타트와링크_허유민 {

    // 인접리스트로 변경을 해야 된다.
    // 인정행렬 사용
    static int N, answer;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N];
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(answer);
    }

    public static void dfs(int depth,int start){
        if(depth == N/2){
            int Steam = 0;
            int Lteam = 0;

            for (int i = 0; i < N - 1; i++)
                for (int j = i+1; j < N; j++) {
                    if(visited[i] && visited[j]){
                        Steam += map[i][j] + map[j][i];
                    }else if(!visited[i] && !visited[j]){
                        Lteam += map[i][j] + map[j][i];
                    }
                }

            int result = Math.abs(Steam - Lteam);

            if(result == 0){
                System.out.println(result);
                System.exit(0);
            }

            answer = Math.min(answer, result);

            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, start + 1);
                visited[i] = false;
            }
        }
    }
}
