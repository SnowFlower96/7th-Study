import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_BJ_17413_단어뒤집기2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder reverse = new StringBuilder();
		char[] word = br.readLine().toCharArray();
		boolean open = false;
		for(int i = 0 ; i <= word.length; i++) {
			
			//끝났을때 뒤집어서 넣음
			if(i==word.length) {
				reverse.append(sb.reverse());
				break;
			}
			
			if(word[i] == '<' || word[i] == ' ') {
				if(word[i]=='<') open = !open;
				reverse.append(sb.reverse());
				reverse.append(word[i]);
				sb.setLength(0); 
				continue;
			}
			if(word[i] == '>' ){
				open = !open;
				reverse.append(word[i]);
				continue;
			}
			if(open) {
				reverse.append(word[i]);
				continue;
			}
			
			sb.append(word[i]);
		}
		
		System.out.println(reverse.toString());
	}

}
