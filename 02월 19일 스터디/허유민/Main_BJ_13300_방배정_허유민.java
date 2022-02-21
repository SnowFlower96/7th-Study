package personal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_13300_방배정_허유민 {

	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int room = 0;
		
		// 성별과 학년을 입력받으면 해당하는 학년의 성별의 인원 수 증가
		// 0행은 일부러 비워둠
		int[][] arr = new int[7][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int student_sex = Integer.parseInt(st.nextToken());
			int student_grade = Integer.parseInt(st.nextToken());
			arr[student_grade][student_sex]++;
		}
		
		// 방에 최대인원 K로 나눴을때 나머지가 0이면 몫으로 처리하고 0이 아니면 방 하나를 더 추가
		for (int i = 1; i <= 6; i++) {
			for (int j = 0; j < 2; j++) {
				if(arr[i][j] != 0 && arr[i][j] % K == 0) {
					room += arr[i][j] / K;
				}else if(arr[i][j] != 0 && arr[i][j] % K != 0) {
					room += arr[i][j] / K + 1;
				}
			}
		}
		System.out.println(room);

	}

}
