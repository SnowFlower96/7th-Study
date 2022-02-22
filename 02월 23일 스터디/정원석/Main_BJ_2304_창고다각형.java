import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BJ_2304_창고다각형 {

	static class XY implements Comparable<XY>{
		int x, y;
		
		public XY(int x, int y) {
			this.x = x;
			this.y = y;
		}

		//내림차순으로 정렬
		@Override
		public int compareTo(XY o) {
			return this.x - o.x;
		}
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		XY[] arr = new XY[N];
		int max_y = -1;
		
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			max_y = Math.max(max_y, y);
			arr[i] = new XY(x,y);
		}
		
		Arrays.sort(arr);
		ArrayList<XY> max_front = new ArrayList<>();
		ArrayList<XY> max = new ArrayList<>();
		ArrayList<XY> max_after = new ArrayList<>();
		int max_x = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			if(arr[i].y == max_y) {
				max.add(arr[i]);
				max_after.clear();
				max_x = arr[i].x;
			}else if(arr[i].x < max_x) {
				max_front.add(arr[i]);
			}else if(arr[i].x > max_x) {
				max_after.add(arr[i]);
			}
		}
		
//		for(XY a: max_front)
//			System.out.print(a.x + " " + a.y+ " / ");
//		System.out.println();
//		for(XY a: max)
//			System.out.print(a.x + " " + a.y + " / ");
//		System.out.println();
//		for(XY a: max_after)
//			System.out.print(a.x + " " + a.y + " / ");
//		System.out.println();
		int sum = 0;
		if(!max_front.isEmpty()) {
			XY high = max_front.get(0);
			for(int i = 0; i < max_front.size(); i++) {
				XY temp = max_front.get(i);
				if(temp.y > high.y ) {
					sum += high.y * (temp.x - high.x); 
					high = temp;
				}
			}
			sum += high.y * (max.get(0).x - high.x);
		}
		
		
//		System.out.println(sum);
//		System.out.println("======================================");
		if(max.size() != 1)
			sum += max.get(0).y * (max.get(max.size()-1).x - max.get(0).x + 1);
		else sum += max.get(0).y;
//		
//		System.out.println(sum);
		
		Collections.reverse(max_after);
		
		if(!max_after.isEmpty()) {
			XY high = max_after.get(0);
			for(int i = 0; i < max_after.size(); i++) {
				XY temp = max_after.get(i);
				if(temp.y > high.y ) {
					sum += high.y * (high.x - temp.x); 
					high = temp;
				}
			}
			sum += high.y * (high.x - max.get(max.size()-1).x);
		}
		
		
		System.out.println(sum);
	}

}
