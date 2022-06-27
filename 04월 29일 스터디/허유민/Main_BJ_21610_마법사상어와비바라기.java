package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_21610_마법사상어와비바라기 {

    static int N,M;
    static int[][] map;
    static int[][] ds;
    static boolean[][] visited;
    static int[] dr = {0,0,-1,-1,-1,0,1,1,1};
    static int[] dc = {0,-1,-1,0,1,1,1,0,-1};
    static List<Cloud> list;

    static class Cloud{
        int r;
        int c;

        public Cloud(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        ds = new int[M][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ds[i][0] = Integer.parseInt(st.nextToken());
            ds[i][1] = Integer.parseInt(st.nextToken());
        }

        rain();
        // 비바라기 작업이 전부 끝나고
        // 바구니에 들어있는 물의 양의 합 구하기
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                answer += map[i][j];
            }
        }
        System.out.println(answer);

    }

    private static void rain() {
        // 최초 구름 위치 설정
        list = new ArrayList<Cloud>();
        list.add(new Cloud(N,1));
        list.add(new Cloud(N,2));
        list.add(new Cloud(N-1,1));
        list.add(new Cloud(N-1,2));

        // 구름을 M번 이동시킨다.
        for (int i = 0; i < M; i++) {
            int d = ds[i][0];
            int s = ds[i][1];
            visited = new boolean[N+1][N+1]; // 물복사 버그를 위해서 방문처리

            // 1. 모든 구름이 d방향으로 s칸 이동
            // 2. 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의양 1 증가
            for (Cloud cloud: list) {
                int nr = (cloud.r + dr[d]*s);
                int nc = (cloud.c + dc[d]*s);

                //N다음 1로되고 1에서 내려가면 N이된다.
                while(nr > N || nr <= 0){
                    if(nr>N){
                        nr = nr - N;
                    }else if(nr <= 0){
                        nr = nr + N;
                    }
                }
                while(nc <= 0 || nc > N){
                    if(nc>N){
                        nc = nc - N;
                    }else if(nc <= 0){
                        nc = nc + N;
                    }
                }

                // 위치한 지역의 바구니의 물의 양 1 증가 & 방문처리
                map[nr][nc]++;
                visited[nr][nc] = true;
                // 구름의 위치 값 수정
                cloud.r = nr;
                cloud.c = nc;
            }

            // 구름을 비우는 작업은 물복사 버그가 일어나는 칸에 좌표를 바로 쓰기위해 물복사 이후에 비운다.
            // 4.물이증가한 칸에 물복사버그 마법 시전
            // 대각선 방향에 있는 칸에 물이 있는 바구니의 수만큼 해당 바구니에 물의 양 증가
            for (Cloud cloud: list) {
                int r = cloud.r;
                int c = cloud.c;

                for (int k = 2; k <= 8; k+=2) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    //경계 체크 물이 없으면 증가하지 않음
                    if(nr <= 0 || nr > N || nc <= 0 || nc > N || map[nr][nc] <= 0){
                        continue;
                    }
                    map[r][c]++;
                }
            }

            // 3.구름이 모두 사라진다.
            list.clear();
            // 5.바구니에 저장된 물의 양이 2이상인 모든 칸에 구름이 생기고 물의양 -2
            // 구름이 생기는 칸은 구름이 사라진 칸이 아니여야 한다 -> 아까 방문체크 활용
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if(map[j][k] >= 2 && !visited[j][k]){
                        list.add(new Cloud(j,k));
                        map[j][k] -= 2;
                    }
                }
            }
        }
    }
}
