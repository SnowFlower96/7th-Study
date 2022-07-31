package gold.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17136_색종이붙이기 {

    static int ans;
    static boolean[][] map;
    static int[] remainPaper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ans = Integer.MAX_VALUE;
        map = new boolean[10][10];
        remainPaper = new int[] { 0, 5, 5, 5, 5, 5 };
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = st.nextToken().equals("1");
            }
        }

        Solution(0, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void Solution(int r, int c, int cnt) {
        if (r >= 9 && c > 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (cnt >= ans) return;

        // 줄 바꾸기
        if (c > 9) {
            r = r + 1;
            c = 0;
        }

        // 색종이를 붙여야되는 곳이면
        if (map[r][c]) {
            for (int size = 5; size >= 1; size--) {
                // 해당 크기의 색종이를 붙일 수 없으면
                if (!isAvailable(r, c, size)) continue;

                // 색종이 붙이기
                work(true, r, c, size);
                Solution(r, c + 1, cnt + 1);
                // 색종이 떼기
                work(false, r, c, size);
            }
        }
        else Solution(r, c + 1, cnt);
    }

    // (r, c)위치에서 size 의 색종이를 붙일 수 있는지 반환하는 함수
    public static boolean isAvailable(int r, int c, int size) {
        // 해당 크기의 색종이가 없으면
        if (remainPaper[size] == 0) return false;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                // 경계를 벗어나면
                if (i < 0 || i >= 10 || j < 0 || j >= 10) return false;
                // 해당 칸에 색종이를 붙일 수 없으면
                if (!map[i][j]) return false;
            }
        }

        return true;
    }

    // (r, c)위치에 size * size 의 색종이 붙이기/떼기
    public static void work(boolean isAttach, int r, int c, int size) {
        // 색종이를 붙이는지에 따라 해당 남은 색종이 숫자 변경
        if (isAttach) remainPaper[size]--;
        else remainPaper[size]++;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                // isAttach 에 따라 변경
                map[i][j] = !isAttach;
            }
        }
    }

    public static void print() {
        System.out.println("-----------------------");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] ? 1 + " " : 0 + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

}
