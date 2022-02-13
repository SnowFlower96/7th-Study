package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2491_수열_허유민 {
    static int N, MAX, count,count2;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        MAX = 1;
        permutation();
        permutation2();
        System.out.println(MAX);
    }

    // 점점 커지거나 같은것
    public static void permutation(){
        count = 1;
        for (int i = 0; i < N-1; i++) {
            if(arr[i] <= arr[i+1]){
                count++;
            }else count = 1;
            MAX = Math.max(MAX,count);
        }
    }
    // 점점 작아지거나 같은것
    public static void permutation2(){
        count2 = 1;
        for (int i = 0; i < N-1; i++) {
            if(arr[i] >= arr[i+1]){
                count2++;
            }else count2 = 1;
            MAX = Math.max(MAX,count2);
        }
    }
}
