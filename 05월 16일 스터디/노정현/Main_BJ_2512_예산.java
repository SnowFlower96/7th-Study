package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-14
 */
public class Main_BJ_2512_예산 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] regions = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            regions[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());

        Arrays.sort(regions);
        int left = 0;
        int right = regions[N - 1];
        while (left <= right) {
            int mid = (left + right) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.min(regions[i], mid);
            }

            if (sum <= M) left = mid + 1;
            else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }


}
