import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main_BJ_13300_방배정_정원석 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int max_n = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> Room = new HashMap<>(); 
		int result = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			
			if(Room.get(gender+""+grade) == null) {
				++result;
				Room.put(gender+""+grade, 0);
			}
			
			if(Room.get(gender+""+grade) < max_n) {
				Room.put(gender+""+grade, Room.get(gender+""+grade)+1);
			}
			else if(Room.get(gender+""+grade) == max_n){
				Room.put(gender+""+grade, 1);
				++result;
			}
		}
		
		System.out.println(result);
	}

}
