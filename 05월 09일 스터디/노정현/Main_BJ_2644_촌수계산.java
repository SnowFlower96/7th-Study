package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-05-07
 */
public class Main_BJ_2644_촌수계산 {

    static int n, m, from, to;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());  // 부모
            int b = Integer.parseInt(st.nextToken());  // 자식

            list[a].add(b);  // 1촌관계 추가
            list[b].add(a);  // 1촌관계 추가
        }

        dfs(from, new boolean[n + 1], 0);

        // dfs 에서 종류하지 않으면 -1 출력
        System.out.println(-1);
    }

    /**
     * dfs 를 이용한 촌수 계산
     * @param cur 현재 사람 인덱스
     * @param visited 방문여부
     * @param depth 촌수
     */
    private static void dfs(int cur, boolean[] visited, int depth) {
        // 도달하면 프로그램 종료
        if(cur == to){
            System.out.println(depth);
            System.exit(0);
            return;
        }

        // 인접리스트 활용
        for(int person : list[cur]){
            if(visited[person]) continue;

            visited[person] = true;
            dfs(person, visited, depth + 1);
        }
    }

}
