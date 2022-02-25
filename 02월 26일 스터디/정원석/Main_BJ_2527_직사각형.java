import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2527_직사각형 {

	static class Rectangle{
		int x1, y1, x2, y2;
		
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 4 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Rectangle first = null, second = null;
			
			for(int j = 0; j < 2 ; j++) {
				int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());
				if(j == 0) first = new Rectangle(x, y, p, q); 
				else second = new Rectangle(x, y, p, q);
				
			}			
			
			System.out.println(Rect_Judge(first, second));
			
		}		
	}
	
	public static char Rect_Judge(Rectangle f, Rectangle s) {
		
		//점에서 만나는 경우
		if((f.x1 == s.x2 && f.y1 == s.y2) || (f.x1 == s.x2 && f.y2 == s.y1) || (f.x2 == s.x1 && f.y2 == s.y1 ) 
				|| (f.x2 == s.x1 && f.y1 == s.y2)) {
			return 'c';
		}
		// 안만나는 경우
		else if(f.x2 < s.x1 || f.y2 < s.y1 || f.x1 > s.x2 || f.y1 > s.y2) {
			return 'd';
		}
		//선에서 만나는 경우
		else if(f.x2 == s.x1 || f.x1 == s.x2 || f.y2 == s.y1 || f.y1 == s.y2) {
			return 'b';
		}
		// 직사각형인 경우
		else {
			return 'a';
		}
		
		
	}

}
