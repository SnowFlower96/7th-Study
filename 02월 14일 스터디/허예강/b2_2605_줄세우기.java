import java.util.*;

public class b2_2605_줄세우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Integer> list = new LinkedList<Integer>();
		int N = sc.nextInt();
		
		for(int i=0; i<N; i++) {
			int order = sc.nextInt();
			list.add(i-order, i+1);
		}
		//값을 통째로 가져옴
		for(int i : list) {
			System.out.print(i+" ");
		}
		
		//index 값으로 가져옴
//		for(int i=0; i<list.size(); i++) {
//			list.get(i);
//		}
	}

}
