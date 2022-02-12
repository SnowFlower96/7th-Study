package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_B1_3049_다각형의대각선 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		// nC4
		System.out.println(n * (n - 1) * (n - 2) * (n - 3) / 24);

		br.close();
	}

}
