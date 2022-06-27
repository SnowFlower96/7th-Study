package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-04
 */
public class Main_BJ_18429_근손실 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] kits = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(permutation(N, K, kits, new int[N], 0, 0, 0));
    }

    private static int permutation(int N, int K, int[] kits, int[] order, int flag, int cnt, int ans) {
        if(cnt == N) {
            int weight = 500;
            for (int i = 0; i < N; i++) {
                weight += (kits[order[i]] - K);
                if(weight < 500) return ans;
            }
            return ans + 1;
        }

        for (int i = 0; i < N; i++) {
            if((flag & (1 << i)) != 0) continue;

            order[cnt] = i;
            ans = permutation(N, K, kits, order, (flag | (1 << i)), cnt + 1, ans);
        }

        return ans;
    }

}
