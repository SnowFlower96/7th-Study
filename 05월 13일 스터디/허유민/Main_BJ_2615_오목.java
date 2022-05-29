package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2615_오목 {

    static int start_r,start_c;
    static int[][] map;
    // 0 down 1 up 2 right 3 left
    // 4 우측하단 5 좌측상단 6 좌측하단 7 우측상단
    static int[] dr = {1,-1,0,0,1,-1,1,-1};
    static int[] dc = {0,0,1,-1,1,-1,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = 19;
        int answer = 0;

        map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        loopOut:
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j] == 0){
                    continue;
                }
                int r = i;
                int c = j;
                start_r = r;
                start_c = c;

                for (int d = 0; d < 8; d+=2) {
                    int nr = r;
                    int nc = c;
                    int cnt = 1;

                    // 무한반복으로 탐색
                    while(true) {
                        nr += dr[d];
                        nc += dc[d];

                        if (nr < 0 || nr > N || nc < 0 || nc > N || map[nr][nc] != map[r][c]) {
                            break;
                        }
                        cnt++;
                        // 가장왼쪽에 있는 돌을 찾는 코드
                        if(start_c > nc){
                            start_r = nr;
                            start_c = nc;
                        }
                    } // end of while
                    //System.out.println(r + " " + c + " " + cnt);

                    // 육목 체크
                    if(cnt == 5){
                        nr = r + dr[d] * -1;
                        nc = c + dc[d] * -1;
                        // 인덱스 값을 벗어나지 않으면서 map[nr][nc] 값이 1이면 육목이니까 정답을 2로 넣고 계속 탐색
                        if(nr > 0 && nr <= N && nc > 0 && nc <= N && map[nr][nc] == map[r][c]){
                            answer = 0;
                        }
                        // 인덱스값을 벗어나거나 좌표값이 시작위치 바둑돌이 아니라면 오목 완성으로 반복문 탈출
                        else if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] != map[r][c]){
                            answer = map[r][c];
                            break loopOut;
                        }
                    }

                }// end of d

            } // end of j
        } // end of i

        System.out.println(answer);
        if(answer > 0){
            System.out.println(start_r + " " + start_c);
        }


    }
}
