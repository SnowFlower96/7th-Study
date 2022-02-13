package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main_BJ_2605_줄세우기_허유민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        ArrayList<Integer> list = new ArrayList<>();
        //Random random = new Random();

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        //학생 1부터 N까지 리스트에 넣는데 인덱스크기에서 입력받은 숫자를 뺀 자리에 넣는다.
        // 리스트는 알아서 순서를 밀어준다.
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            list.add(list.size()-num, i);
        }

        // 출력
        for (int i = 0; i < N; i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
