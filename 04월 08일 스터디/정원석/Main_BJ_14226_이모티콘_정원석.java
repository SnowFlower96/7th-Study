import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BJ_14226_이모티콘_정원석 {

	static class Emoji implements Comparable<Emoji> {
		int emojiNum, time, clipBoard;

		public Emoji(int emojiNum, int time, int clipBoard) {
			this.emojiNum = emojiNum;
			this.time = time;
			this.clipBoard = clipBoard;
		}

		@Override
		public int compareTo(Emoji o) {
			return this.time - o.time;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new boolean[2001][2001];

		System.out.println(BFS());
	}

	static int N;
	static boolean visited[][];

	static int BFS() {
		Queue<Emoji> pQueue = new LinkedList<>();
		pQueue.offer(new Emoji(1, 0, 0));

		while (!pQueue.isEmpty()) {
			Emoji cur1 = pQueue.poll();
			Emoji cur2 = new Emoji(cur1.emojiNum, cur1.time, cur1.clipBoard);
			Emoji cur3 = new Emoji(cur1.emojiNum, cur1.time, cur1.clipBoard);


			// 복사하기
			if (cur1.emojiNum != cur1.clipBoard && !visited[cur2.emojiNum][cur2.emojiNum]) {
				cur1.clipBoard = cur1.emojiNum;
				visited[cur2.emojiNum][cur2.emojiNum] = true; 
				cur1.time++;
				if (cur1.emojiNum == N)
					return cur1.time;
//				System.out.println(cur1.emojiNum + " " + cur1.clipBoard + " " + cur1.time);
				pQueue.offer(new Emoji(cur1.emojiNum, cur1.time, cur1.clipBoard));
			}

			// 클립보드에 있는 것만 화면에 더해주기
			if (cur2.clipBoard != 0 && cur2.emojiNum + cur2.clipBoard <= N && !visited[cur2.emojiNum + cur2.clipBoard][cur2.clipBoard]) {
				cur2.emojiNum += cur2.clipBoard;
				cur2.time++;
				if (cur2.emojiNum == N)
					return cur2.time;
//				System.out.println(cur2.emojiNum + " " + cur2.clipBoard + " " + cur2.time);
				visited[cur2.emojiNum][cur2.clipBoard] = true;
				pQueue.offer(new Emoji(cur2.emojiNum, cur2.time, cur2.clipBoard));
			}

			// 화면에 있는 이모지 삭제하기
			if (cur3.emojiNum >= 1 && !visited[cur2.emojiNum-1][cur2.clipBoard]) {
				cur3.emojiNum--;
				cur3.time++;
				if (cur3.emojiNum == N)
					return cur3.time;
//				System.out.println(cur3.emojiNum + " " + cur3.clipBoard + " " + cur3.time);
				visited[cur3.emojiNum][cur2.clipBoard] = true;
				pQueue.offer(new Emoji(cur3.emojiNum, cur3.time, cur3.clipBoard));
			}

		}

		return -1;
	}
}
