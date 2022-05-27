package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_13565_침투 {

    static int R, C;
    static boolean[][] map;

    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j) == '1';
            }
        }

        List<int[]> start = new ArrayList<>();
        for (int j = 0; j < C; j++) {
            if(!map[0][j] && !map[1][j]) start.add(new int[] { 1, j });
            map[0][j] = true;
        }

        String msg = null;
        for (int[] point :
                start) {
            if (dfs(point[0], point[1], false)) {
                msg = "YES";
                break;
            }
        }

        if (bfs(start)) {
            msg = "YES";
        }

        System.out.println(msg != null ? msg : "NO");
    }

    private static boolean dfs(int r, int c, boolean flag) {
        if(r == R - 1) return true;
        if(flag) return true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc]) continue;

            map[nr][nc] = true;
            flag = dfs(nr, nc, flag);
        }

        return flag;
    }

    private static boolean bfs(List<int[]> start) {
        Queue<int[]> queue = new LinkedList<>();
        for(int[] point : start) {
            int r = point[0];
            int c = point[1];
            queue.offer(new int[] { r, c });
            map[r][c] = true;
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if(cur[0] == R - 1) return true;

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc]) continue;

                map[nr][nc] = true;
                queue.offer(new int[] { nr, nc });
            }
        }

        return false;
    }

}
