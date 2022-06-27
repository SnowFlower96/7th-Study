package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Study
 * @Author Home
 * @Date 2022. 5. 31.
 */
public class Main_BJ_2012_등수매기기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long ans = 0;
        // 첫 번째 풀이
        int[] arr = new int[500001];
        for (int i = 0; i < N; i++) arr[Integer.parseInt(br.readLine())]++;

        int cnt = 1;
        for (int i = 1; i <= 500000; i++) {
            if (cnt == N + 1) break;
            if (arr[i] > 0) {
                for (int j = 0; j < arr[i]; j++) {
                    ans += Math.abs(cnt - i);
                    cnt++;
                }
            }
        }

        // 두 번째 풀이
//        int[] expects = new int[N];
//        for (int i = 0; i < N; i++) {
//            expects[i] = Integer.parseInt(br.readLine());
//        }
//
//        // 예상 등수 오름차순 정렬
//        Arrays.sort(expects);
//
//        for (int i = 1; i <= N; i++) {
//            // 만족도 계산
//            ans += Math.abs(expects[i - 1] - i);
//        }

        System.out.println(ans);
    }

}
