package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17406_배열돌리기4 {

    static int N,M,K,answer;
    static int[][] map;
    static int[][] rcs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        rcs = new int[K][3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rcs[i][0] = Integer.parseInt(st.nextToken());
            rcs[i][1] = Integer.parseInt(st.nextToken());
            rcs[i][2] = Integer.parseInt(st.nextToken());
        }
        answer = Integer.MAX_VALUE; // 정답 초기화
        int arr[][] = new int[K][3];
        boolean[] visited = new boolean[K];

        permutation(0,arr,visited);
        System.out.println(answer);


    }

    // 순열 생성
    public static void permutation(int depth, int arr[][],boolean[] visited){
        if (depth == K){
            int copy[][] = new int[map.length][];
            for (int i = 0; i <= N; i++) {
                copy[i] = map[i].clone();
            }

            for (int i = 0; i < K; i++) {
                copy = rotation(copy,arr[i][0],arr[i][1],arr[i][2]);
                //System.out.println(arr[i][0] +" " + arr[i][1] + " " + arr[i][2]);
            }
            answer = Math.min(answer,arrayMin(copy));
            return;
        }
        for (int i = 0; i < K; i++) {
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = rcs[i].clone();
                permutation(depth+1,arr,visited);
                visited[i] = false;
            }
        }
    }

    // 회전 연산
    public static int[][] rotation(int[][] array,int r,int c,int s){
        int[][] copy = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }

        for (int i = 1; i <= s; i++) {
            int lr = r - i; // 좌측 상단
            int lc = c - i; // 좌측 상단

            int rr = r + i; // 우측 하단
            int rc = c + i; // 우측 하단

            // 오른쪽으로
            for (int k = 1; k < i*2+1; k++) {
                int nc = lc + k;
                copy[lr][nc] = array[lr][nc-1];
            }
            // 아래쪽
            for (int k = 1; k < i*2+1; k++) {
                int nr = lr + k;
                copy[nr][rc] = array[nr-1][rc];
            }
            // 왼쪽으로
            for (int k = 1; k < i*2+1; k++) {
                int nc = rc - k;
                copy[rr][nc] = array[rr][nc+1];
            }
            // 위쪽으로
            for (int k = 1; k < i*2+1; k++) {
                int nr = rr - k;
                copy[nr][lc] = array[nr+1][lc];
            }
        }

        return copy;
    }

    // 배열 A에 대한 최솟값 구하기
    public static int arrayMin(int[][] A){
        int min = Integer.MAX_VALUE;
        // 각 행의 합이 가장 작은 값을 찾는다.
        for (int i = 1; i < A.length; i++) {
            int sum = 0;
            for (int j = 1; j < A[i].length; j++) {
                sum += A[i][j];
            }
            min = Math.min(min,sum);
        }
        return min;
    }
}
