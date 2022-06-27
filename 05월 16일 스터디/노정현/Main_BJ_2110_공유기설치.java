package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-15
 */
public class Main_BJ_2110_공유기설치 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int left = 1;
        int right = arr[N - 1] - arr[0];
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int start = arr[0];
            int cnt = 1;

            for (int i = 1; i < N; i++) {
                int d = arr[i] - start;
                if (mid <= d) {
                    cnt++;
                    start = arr[i];
                }
            }

            if(cnt >= C) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

}
