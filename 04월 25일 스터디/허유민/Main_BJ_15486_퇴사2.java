package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_15486_퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int D[] = new int[N+2];
        int T[] = new int[N+2];
        int P[] = new int[N+2];
        int max = 0;
        int day = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 상담을 진행한 당일이 아닌 상담이 끝나고 다음날에 돈을 받음
        for (int i = 1; i <= N+1; i++) {

            max = Math.max(max, D[i]); //i날짜의 최대값
            day = i+T[i]; //상담이 끝난 날

            //퇴사날에는 상담을 하지 못함, 퇴사날까지 상담이 끝나는 경우
            if(day <= N+1){
                D[day] = Math.max(max+P[i], D[day]);
            }
        }
        System.out.println(max);

    }
}
