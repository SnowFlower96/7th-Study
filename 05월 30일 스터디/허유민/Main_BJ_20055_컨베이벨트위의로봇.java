package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_20055_컨베이벨트위의로봇 {

    static int N, K;
    static int map[];
    static ArrayList<Integer> list;
    static Queue<Integer> que;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        que = new LinkedList<>();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[2*N + 1];
        robot = new boolean[2*N];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2*N; i++) {
            //map[i] = Integer.parseInt(st.nextToken());
            list.add(Integer.parseInt(st.nextToken()));
        }

        int count = 1; // 몇번째 단계인지 while문의 횟수 카운트
        int zero = 0; // 내구도가 0이 된 칸의 개수 카운트
        while(true){
            // 1.벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            rotation();
            // 2.가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
            if(robot[N-1]) {
                robot[N-1] = false;
            }
            // 2-1로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            for (int i = N-1; i >= 0; i--) {
                int next = i+1;
                if(robot[i] && list.get(next) >= 1 && !robot[next]){
                    int a = list.get(next) - 1;
                    list.set(next,a);
                    robot[next] = true;
                    robot[i] = false;
                    if(list.get(next) == 0) zero++;
                }
                if(robot[N-1]) {
                    robot[N-1] = false;
                }
            }

            // 3.올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if(list.get(0) > 0 && !robot[0]){
                robot[0] = true;
                int a = list.get(0)-1;
                list.set(0,a);
                if(list.get(0) == 0) zero++;
            }

            // 4.내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
            if(zero >= K) {
                break;
            }

            count++;
        }

        System.out.println(count);


    }

    static public void rotation(){
        int temp = list.get(list.size()-1);
        list.remove(list.size()-1);
        list.add(0,temp);

        boolean bltemp = robot[2*N-1];
        for (int i = 2*N-1; i > 0; i--) {
            robot[i] = robot[i-1];
        }
        robot[0] = bltemp;

        // int temp = que.poll();
        // que.add(temp);
    }
}
