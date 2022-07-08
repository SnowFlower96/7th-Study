package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11403_경로찾기 {
    static int N;
    static int arrmatrix[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arrmatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arrmatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 경유지 -> 출발지 -> 목적지로 3중 루프 돌린다.
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(arrmatrix[i][k] == 1 && arrmatrix[k][j] == 1){
                        arrmatrix[i][j] = 1;
                    }
                }
            }
            //print();
            //System.out.println();
        }

        print();
    }
    public static void print(){
        // 인접 행렬 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arrmatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
