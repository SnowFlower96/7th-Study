package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2156_포도주시식_허유민 {

    /*
        1. 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
            -> 앞에 포도주를 먹더라도 순서에 변화가없다. 잔을 빼지 않기 때문에
        2. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다. -> 2잔까지 한번에 먹음

        포도주는 0개일때 1개일때 2개 일때 0 : 포도주가없으니 못마심
        1 : 포도주가 1잔 뿐이니 1잔만 마시면됨
        2 : 포도주가 2잔 뿐이니 2잔 다 먹으면 됨
        3 : 포도주는 3잔 첫번째와 두번째를 먹던가, 두번재와 세번째만를 먹을수있음
        4 : 첫번째 두번쨰 네번째, 두번째 세번째, 첫번째 세번째 네번째;
        5 : 첫번째 두번째 네번째 다섯번째, 두번째 세번째 다섯번째, 첫번째 세번째 네번째

        6 10 13 9 8

        D[N] = D[N-3] + arr[N-1] + arr[N];
        D[N] = D[N-2] + arr[N];
        D[N] = D[i-1];

     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        int[] D = new int[N+1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        D[1] = arr[1];
        if(N > 1){
            D[2] = arr[1]+arr[2];
        }
        for (int i = 3; i <= N; i++) {
            D[i] = Math.max(D[i-1],
                    Math.max(D[i-2] + arr[i],
                    D[i-3] + arr[i-1] + arr[i]));
        }

        System.out.println(D[N]);
    }
}
