package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-29
 */
public class Main_BJ_20055_컨베이어벨트위의로봇 {

    static class Belt {
        int d;
        boolean isRobot;

        public Belt(int d, boolean isRobot) {
            this.d = d;
            this.isRobot = isRobot;
        }

        public void unload() {
            if(this.isRobot) {
                this.isRobot = false;
            }
        }

        public void setBelt(Belt belt) {
            this.d = belt.d;
            this.isRobot = belt.isRobot;
        }

        @Override
        public String toString() {
            return "{" +
                    "" + d +
                    ", " + isRobot +
                    '}';
        }

    }

    static int N, K;
    static Belt[] belt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new Belt[N * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            belt[i] = new Belt(Integer.parseInt(st.nextToken()), false);
        }

        System.out.println(solution());
    }

    private static int solution() {
        int cnt = 0;

        while (cnt++ >= 0){
            // 1. 벨트가 각 칸위에 있는 로봇과 함께 한 칸 회전
            Belt tempBelt = new Belt(0, false);
            tempBelt.setBelt(belt[N * 2 - 1]);
            for (int i = N * 2 - 1; i > 0; i--) {
                belt[i].setBelt(belt[i - 1]);
            }
            belt[0].setBelt(tempBelt);
            belt[N - 1].unload();  // 내리는칸에 로봇이 있으면 내린다

            // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
            for (int i = N - 1; i > 0; i--) {
                if(belt[i].isRobot && !belt[i+1].isRobot && belt[i + 1].d > 0) {
                    belt[i].isRobot = false;
                    belt[i + 1].isRobot = true;
                    belt[i + 1].d--;
                }
                belt[N - 1].unload();  // 내리는칸에 로봇이 있으면 내린다
            }

            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니라면 올리는 위치에 로봇을 올림
            if(belt[0].d > 0) {
                belt[0].d--;
                belt[0].isRobot = true;
            }

            // 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
            int k = 0;
            for (int i = 0; i < N * 2; i++) {
                if(belt[i].d == 0) {
                    k++;
                }
            }

            if (k >= K) break;
        }

        return cnt;
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            System.out.print(belt[i] + " ");
        }
        System.out.println();
        for (int i = N * 2 - 1; i >= N; i--) {
            System.out.print(belt[i] + " ");
        }
        System.out.println();
        System.out.println();
    }

}
