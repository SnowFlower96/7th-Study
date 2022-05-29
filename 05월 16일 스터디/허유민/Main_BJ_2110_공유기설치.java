package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2110_공유기설치 {

    static int N, C,answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] map = new int[N];

        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(map);

        binarySearch(map,1,map[N-1]-map[0]);

        System.out.println(answer);
    }

    public static void binarySearch(int[] s,int min,int max) {

        if (min > max){
            return;
        }else{
            int mid = (min+max) / 2;
            int cnt = 1;
            int start = s[0];
            int distance = 0;
            for (int i = 1; i < N; i++) {
                distance = s[i] - start;
                if (distance >= mid) {
                    cnt++;
                    start = s[i];
                }
            }

            if(cnt >= C){
                answer = mid;
                min = mid+1;
            }else{
                max = mid -1;
            }
            binarySearch(s,min,max);
        }
    }
}
