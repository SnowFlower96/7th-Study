package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
그 지역에 많은 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사하려고 한다.
이때, 1~100 까지 강수량,

위의 예와 같은 지역에서 내리는 비의 양에 따른 모든 경우를 다 조사해 보면
물에 잠기지 않는 안전한 영역의 개수 중에서 최대인 경우는 5임을 알 수 있다.
0,0 부터 N-1,N-1

강수량 1의 영역갯수를 체크하고 LIST에 영역의 개 수를 넣어주고
강수량100까지 전부 구해서
LIST안에 있는 Integer 값중에서 가장 큰값 그걸 출력
 */

public class Main_BJ_2468_안전영역_허유민 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<Integer>();

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 강수레벨의 모든 경우의 수 확인해서 그 중에서 가장 큰 안전 영역
        for (int level = 0; level <= 100; level++) {
            int count = 0;
            // 0,0부터 N,N까지 반복
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if(map[j][k] > level && !visited[j][k]){
                        count++;
                        dfs(j,k, level);
                    }
                }
            }
            visited = new boolean[N][N]; // 방문처리 초기화
            list.add(count);
        }
        int max = Collections.max(list);
        System.out.println(max);


    }

    // r,c 배열의 좌표, level은 장마의 레벨? 강수량?
    public static void dfs(int r, int c,int level){
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]){
                continue;
            }
            if(map[nr][nc] > level){
                dfs(nr,nc,level);
            }
        }
    }
}
