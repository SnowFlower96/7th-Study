package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-10
 */
public class Main_BJ_20056_마법사상어와파이어볼 {

    static final int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static final int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

    static class MyMap {

        int size;
        ArrayList<FireBall>[][] map;
        List<FireBall> list;

        public MyMap(int N) {
            this.size = N;
            this.map = new ArrayList[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    this.map[i][j] = new ArrayList<FireBall>();
                }
            }
            this.list = new ArrayList<>();
        }

        public int getAnswer() {
            int sum = 0;
            for (FireBall fb : this.list) {
                sum += fb.mass;
            }
            return sum;
        }

        public void addFireBall(FireBall fb) {
            this.list.add(fb);
        }

        public void moveFireBall() {
            for (FireBall fb : this.list) {
                fb.move(this.size);
                this.map[fb.r][fb.c].add(fb);
            }
        }

        public void afterMove() {
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    int cnt = this.map[i][j].size();
                    if (cnt == 1) this.map[i][j].clear();
                    if (cnt < 2) {
                        this.map[i][j].clear();
                        continue;
                    }

                    // 2 개 이상의 파이어볼이 있으면
                    int mass = 0;  // 총 질략
                    int speed = 0;  // 총 속력
                    boolean isEven = this.map[i][j].get(0).dir % 2 == 0;  // 첫 번째 파이어볼의 방향이 짝수인지
                    boolean isAllSame = true;  // 모든 파이어볼의 방향이 모두 홀수이거나 모두 짝수인지
                    for (FireBall fb : this.map[i][j]) {
                        // list 에서 제거
                        this.list.remove(fb);

                        mass += fb.mass;
                        speed += fb.speed;
                        if (isAllSame && (isEven != (fb.dir % 2 == 0))) isAllSame = false;
                    }

                    // 계산 후 해당 map 초기화
                    this.map[i][j].clear();

                    if (mass / 5 == 0) continue;  // 나뉘어진 파이어볼의 질량이 0 이면
                    int newMass = mass / 5;
                    int newSpeed = speed / cnt;
                    for (int dir = isAllSame ? 0 : 1; dir < 8; dir += 2) {
                        this.list.add(new FireBall(i, j, newMass, newSpeed, dir));
                    }
                }
            }
        }

    }

    static class FireBall {

        int r, c;
        int mass, speed, dir;

        public FireBall(int r, int c, int mass, int speed, int dir) {
            this.r = r;
            this.c = c;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }

        public void move(int maxSize) {
            this.r = (this.r + maxSize + dr[dir] * (this.speed % maxSize)) % maxSize;
            this.c = (this.c + maxSize + dc[dir] * (this.speed % maxSize)) % maxSize;
        }

    }

    static int N, M, K;
    static MyMap map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new MyMap(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map.addFireBall(new FireBall(r, c, m, s, d));
        }

        // 마법사 상어가 이동을 K번 명령
        for (int k = 0; k < K; k++) {
            // 1. 모든 파이어볼 이동
            map.moveFireBall();

            // 2. 이동이 모두 끝난 뒤
            map.afterMove();
        }

        // 남아있는 파이어볼 질량의 합
        System.out.println(map.getAnswer());
    }

}
