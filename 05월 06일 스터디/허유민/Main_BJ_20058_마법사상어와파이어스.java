package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_20058_마법사상어와파이어스 {

    static int N, Q,mapN, bigice;
    static int[][] map;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        mapN = (int) Math.pow(2,N);
        map = new int[mapN][mapN];

        for (int i = 0; i < mapN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mapN; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            map = rotation(L);
            melts();
        }

        int max = 0;
        int sum = 0;
        // 남아있는 얼음들의 합
        for (int i = 0; i < mapN; i++) {
            for (int j = 0; j < mapN; j++) {
                sum += map[i][j];
            }
        }
        // 가장 큰 덩어리를 찾기 위한 dfs
        visited = new boolean[mapN][mapN];
        for (int i = 0; i < mapN; i++) {
            for (int j = 0; j < mapN; j++) {
                if(!visited[i][j] && map[i][j] != 0){
                    bigice = 1;
                    dfs(i,j);
                    max = Math.max(max,bigice);
                }
            }
        }

        System.out.println(sum);
        System.out.println(max);
    }

    //가장 큰 얼음 덩어리를 찾기 위해 dfs 사용
    public static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr >= mapN || nr < 0|| nc >= mapN || nc < 0 || map[nr][nc] == 0 || visited[nr][nc]){
                continue;
            }
            dfs(nr,nc);
            bigice++;
        }
    }

    // 얼음을 녹이기 위해 칸 주변에 얼음이 3칸있는지 확인
    private static void melts() {
        visited = new boolean[mapN][mapN];

        for (int i = 0; i < mapN; i++) {
            for (int j = 0; j < mapN; j++) {
                if(melt(i,j)){
                    visited[i][j] = true;
                }
            }
        }

        // 탐색 후 얼음 한번에 녹이기
        for (int i = 0; i < mapN; i++) {
            for (int j = 0; j < mapN; j++) {
                if(!visited[i][j] && map[i][j] > 0) map[i][j]--;
            }
        }
    }

    // 얼음 칸 주변에 얼음이 3칸 이상인 경우 체크
    private static boolean melt(int r, int c) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr >= mapN || nr < 0|| nc >= mapN || nc < 0 || map[nr][nc] == 0){
                continue;
            }
            cnt++;
        }
        if(cnt >= 3){
            return true;
        }
        else{
            return false;
        }
    }

    // 2^L 크기 마다 90도 회전 시키기 위한 좌표생성
    private static int[][] rotation(int l) {
        int[][] temp = new int[mapN][mapN];
        l = (int) Math.pow(2,l);

        for (int r = 0; r < mapN; r += l) {
            for (int c = 0; c < mapN; c+=l) {
                role(r,c,l,temp);
            }
        }
        return temp;
    }

    // 시계방향으로 90도 회전
    private static void role(int r,int c, int l,int[][] temp){
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                temp[r+j][c+i] = map[r+l-i-1][c+j];
            }
        }
    }


}
