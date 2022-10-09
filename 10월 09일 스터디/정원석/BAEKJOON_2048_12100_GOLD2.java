import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BAEKJOON_2048_12100_GOLD2 {

    static int N;

    // 상-하-좌-우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int map[][] = new int[N][N];
        result = 0;
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N;j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        dfs(0, map);

        System.out.println(result);
    }
    public static void dfs(int idx, int[][] map){
        //5번 움직였을 경우
        if(idx == 5){
            int temp_result = 0;

            for(int i = 0; i < N;i++){
                for(int j = 0; j < N; j++){
                    temp_result = Math.max(temp_result, map[i][j]);
                }
            }

            result = Math.max(result, temp_result);

            return;
        }

        for (int i = 0; i < 4; i++){

            boolean[][] mergeCheck = new boolean[N][N];

            int[][] map_clone = new int[N][N];
            for(int t = 0; t < N; t++){
                map_clone[t] = map[t].clone();
            }

            // ^ <로 움직이는 경우
            if(i == 0 || i == 2){
                for(int j = 0; j < N; j++){
                    for(int k = 0; k < N; k++){
                        move(map_clone, mergeCheck, i, j, k);
                    }
                }
            }
            else if (i == 1 || i == 3){
                for(int j = N-1; j >= 0; j--){
                    for(int k = N-1; k >= 0; k--){
                        move(map_clone, mergeCheck, i, j, k);
                    }
                }
            }

            dfs(idx+1, map_clone);
        }


    }

    private static void move(int[][] map_clone, boolean[][] mergeCheck, int dir, int r, int c) {
        //현재 r, c
        int nr = r;
        int nc = c;

        //이동할 위치 계산
        int mr = nr + dr[dir];
        int mc = nc + dc[dir];

        if(mr >= N || mr < 0 || mc >= N || mc < 0) return;


        while(true){
            // 이동위치 값이 0 이면
            if(map_clone[mr][mc] == 0){
                map_clone[mr][mc] = map_clone[nr][nc];
                map_clone[nr][nc]=0;

                nr = mr;
                nc = mc;

                mr = nr + dr[dir];
                mc = nc + dc[dir];

                if(mr >= N || mr < 0 || mc >= N || mc < 0) break;
            }
//          이동위치 값이 같으면
            else if( map_clone[mr][mc] == map_clone[nr][nc]){
                if(!mergeCheck[mr][mc]){
                    map_clone[mr][mc] = map_clone[mr][mc] * 2;

                    map_clone[nr][nc] = 0;

                    mergeCheck[mr][mc] = true;
                }
                break;
            }
            // 이동 위치 값이 다르면
            else {
                break;
            }
        }
    }


}
