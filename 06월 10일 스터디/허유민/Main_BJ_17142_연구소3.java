package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17142_연구소3 {
    static int N,M,blank,min;
    static int[][] map;

    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};

    static ArrayList<Virus> list;
    static boolean[][] visited_bfs;
    static boolean[] visited;

    static class Virus{
        int r, c;
        int time;

        public Virus(int r, int c,int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        list = new ArrayList<Virus>(); // 바이러스들의 좌표를 가진 리스트
        blank = 0; // 빈칸의 수
        min = Integer.MAX_VALUE; // 최소 시간
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                // 입력값이 2 일때 바이러스 이므로 바이러스 추가 0일때는 빈칸 증가
                if(n == 2){
                    list.add(new Virus(i,j,1));
                }else if(n == 0){
                    blank++;
                }
            }
        }

        if(blank == 0){
            System.out.println(0);
            return;
        }

        visited = new boolean[list.size()];

        combination(0,0);

        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    public static void combination(int count,int start){
        // M개를 골랐을때 bfs실행
        if(count == M){
//            for (int i = 0; i < list.size(); i++) {
//                if(visited[i]){
//                    System.out.print(i);
//                }
//            }
//            System.out.println();
            bfs();
            return;
        }

        for (int i = start; i < list.size(); i++) {
            if(!visited[i]){
                visited[i] = true;
                combination(count+1,i+1);
                visited[i] = false;
            }
        }
    }

    public static void bfs(){
        Queue<Virus> queue = new LinkedList<>();
        visited_bfs = new boolean[N][N];

        for (int i = 0; i < list.size(); i++) {
            // 방문체크된 바이러스만 큐에 추가
            if(visited[i]){
                Virus virus = list.get(i);
                visited_bfs[virus.r][virus.c] = true;
                queue.add(virus);
            }
        }

        int cnt = 0; // 바이러스가 퍼진 칸의 개수
        int time  = 0; // 바이러스가 퍼지는 시간
        while(!queue.isEmpty()){
            Virus current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                time = current.time;

                if( nr >= N || nr < 0 || nc >= N || nc < 0 || visited_bfs[nr][nc] || map[nr][nc] == 1){
                    continue;
                }
                if(map[nr][nc] == 0){
                    visited_bfs[nr][nc] = true;
                    cnt++;
                    queue.add(new Virus(nr,nc,current.time+1));
                }

                if(map[nr][nc] == 2){
                    visited_bfs[nr][nc] = true;
                    queue.add(new Virus(nr,nc,current.time+1));
                }

            }

            // 바이러스가 전부 퍼졌다면 브레이크
            if(cnt == blank){
                break;
            }
        }
        // 바이러스가 전부 펴지지 못했다면 종료
        if(cnt != blank){
            return;
        }
        min = Math.min(min,time);
    }
}
