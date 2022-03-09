package study0302;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_5585_거스름돈 {

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int money = Integer.parseInt(br.readLine());
		int change = 1000-money;
		int result = 0;
		
		int[] coins = {500, 100, 50, 10, 5, 1};
		
		for(int coin: coins) {
			
			if(coin > change) continue;
			result += change/coin;
			change = change % coin;
			
		}
		System.out.println(result);
		
	}

}
