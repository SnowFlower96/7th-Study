package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2512_예산 {

    static int N, answer;
    static int map[];
    static int budget;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
            sum += map[i];
        }
        answer = 0;
        budget = Integer.parseInt(br.readLine()); // 총 예산액
        Arrays.sort(map); // 배열 정렬

        // 모든 요청을 배정이 가능한 경우 배열의 마지막값으로 정답출력
        if(budget >= sum){
            answer = map[N-1];
        }else{ // 요청들의 합이 예산액보다 크면 이진탐색시작
            binarySearch(map,0,map[N-1]);
        }
        System.out.println(answer);
    }

    public static void binarySearch(int[] s,int min,int max) {

        if (min > max){
            answer = max;
        }else{
            int mid = (min+max) / 2; //상한액
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (s[i] > mid) {
                    sum += mid;
                } else {
                    sum += s[i];
                }
            }

            // 합계가 총예산액보다 크다면 최대값을 1낮춰서 탐색
            if(sum > budget){
                binarySearch(s,min,max-1);
            }else{
                answer = Math.max(answer,mid);
                binarySearch(s,min+1,max);
            }
        }
    }
}
