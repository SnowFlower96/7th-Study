package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14499_주사위굴리기_허유민 {

    static int N,M,X,Y,K;
    static int[][] map;
    static int[] deltas;
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        deltas = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            deltas[i] = Integer.parseInt(st.nextToken());
        }

        int[] dice = new int[7];
        for (int i = 0; i < K; i++) {
            int d = deltas[i];
            int nx = X + dx[d];
            int ny = Y + dy[d];

            // 주사위가 지도 바깥으로 이동하려는 경우 해당 명령어 무시, 출력 X
            if(nx < 0 || nx >= N || ny < 0 || ny >= M){
                continue;
            }

            int [] copydice = dice.clone();
            switch (d){
                case 1: // 동쪽
                    dice[1] = copydice[4];
                    dice[3] = copydice[1];
                    dice[4] = copydice[6];
                    dice[6] = copydice[3];
                    break;
                case 2: // 서쪽
                    dice[1] = copydice[3];
                    dice[3] = copydice[6];
                    dice[4] = copydice[1];
                    dice[6] = copydice[4];
                    break;
                case 3: // 남쪽
                    dice[1] = copydice[5];
                    dice[2] = copydice[1];
                    dice[5] = copydice[6];
                    dice[6] = copydice[2];
                    break;
                case 4: // 북쪽
                    dice[1] = copydice[2];
                    dice[2] = copydice[6];
                    dice[5] = copydice[1];
                    dice[6] = copydice[5];
                    break;
            }

            // 이동한 칸의 쓰여 있는 수가 0이면 주사위 주사위의 바닥면에 쓰여있는
            // 수를 칸에 복사
            if(map[nx][ny] == 0){
                map[nx][ny] = dice[6];
            }
            // 아닌 경우 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
            // 칸에 쓰여 있는 수는 0이 된다.
            else {
                dice[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            X = nx;
            Y = ny;
            sb.append(dice[1] + "\n");

        }
        System.out.println(sb);

    }
}
