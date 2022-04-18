package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_13023_ABCDE_허유민 {

    static class Node {
        Integer data;
        ArrayList<Node> adj;

        Node(Integer data){
            this.data = data;
            this.adj = new ArrayList<>();
        }
    }

    static Node[] Nodelist;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Nodelist = new Node[N];
        for (int i = 0; i < N; i++) {
            Nodelist[i] = new Node(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            Nodelist[n1].adj.add(Nodelist[n2]);
            Nodelist[n2].adj.add(Nodelist[n1]);
        }

        answer = 0;

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i,0);
            if(answer == 1){
                System.out.println(answer);
                return;
            }
        }
        System.out.println(answer);
    }

    public static void dfs(int current,int depth){
        if(depth == 4) {
            answer = 1;
            return;
        }

        for (Node temp : Nodelist[current].adj) {
            if (!visited[(int) temp.data]){
                visited[(int) temp.data] = true;
                dfs((int) temp.data, depth + 1);
                visited[(int) temp.data] = false;
            }
        }
    }
}
