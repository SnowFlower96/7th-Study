package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-28
 */
public class Main_BJ_1138_한줄로서기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if(cnt == input && result[j] == 0) {
                    result[j] = i + 1;
                    break;
                }
                else if(result[j] == 0) cnt++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int num : result) sb.append(num).append(" ");
        System.out.println(sb.toString().trim());
    }

}
