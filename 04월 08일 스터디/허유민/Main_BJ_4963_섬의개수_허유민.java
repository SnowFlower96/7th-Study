package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_4963_섬의개수_허유민 {
    static int w,h;
    static int[] dr = {-1,1,0,0,-1,-1,1,1};
    static int[] dc = {0,0,-1,1,-1,1,-1,1};
    static int[][] map;
    static boolean[][] visited;

    /*
        1인 땅을 찾아 dfs탐색을 시작 8방 탐색하여 방문처리를하고 섬의 개수를 증가
        전체 탐색이 끝나면 섬의 개수 출력
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0){
                break;
            }

            map = new int[h][w];
            visited = new boolean[h][w];


            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count =0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(map[i][j] == 1 && !visited[i][j]){
                        count++;
                        dfs(i,j);
                    }
                }
            }

            System.out.println(count);
        }
    }

    public static void dfs(int r, int c){
        visited[r][c] = true;

        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nr >= h || nc < 0 || nc >= w || visited[nr][nc] || map[nr][nc] == 0){
                continue;
            }

            dfs(nr,nc);

        }
    }
}
