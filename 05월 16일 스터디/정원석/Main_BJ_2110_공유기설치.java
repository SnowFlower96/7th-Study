import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_2110_공유기설치 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int C = Integer.parseInt(st.nextToken());
		
		
		int house[] = new int[N];
		for(int i = 0 ; i < N;i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		//공유기 설치 거리 최솟값
		int lowerDis = 1;
		//공유기 설치할 수 있는 최대값
		int highDis = house[N-1] - house[0] + 1;
		
		// 최솟값이 최대값을 역전할경우 그만둔다.
		while(lowerDis < highDis) {
			
			// 공유기 설치거리를 지정 거리를 2로 나눈다(2분탐색)
			int midDis = (lowerDis + highDis) / 2;
			
			//공유기 개수가 C에 못미치면 hi를 줄인다. (거리를 줄여야함)
			if(install(midDis, house) < C) {
				highDis = midDis;
			}
			// 공유기 개수가 C보다 크거나 같으면 거리를 늘리면서 최대값을 찾아낸다.
			else {
				lowerDis = midDis + 1;
			}
			
		}
		
		System.out.println(lowerDis - 1);
	}

	// 설치 가능한 공유기 개수를 찾는 메소드
	private static int install(int mid, int[] house) {
		//첫 번째 집은 무조건 설치 
		int cnt = 1;
		//마지막 공유기 설치 위치
		int lastLocate = house[0];
		
		for(int i = 1; i < house.length; i++) {
			int curLocate = house[i];
			
			//둘의 차이가 mid보다 크거나 같으면 공유기 개수를 늘리고 마지막 설치 위치 갱신한다.
			if(curLocate - lastLocate >= mid) {
				cnt++;
				lastLocate = curLocate;
			}
		}
		
		return cnt;
	}

}
