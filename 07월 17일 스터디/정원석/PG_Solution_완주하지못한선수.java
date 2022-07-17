package study_07_17;

import java.util.*;

public class PG_Solution_완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };
		String result = solution(participant, completion);
		
		System.out.println(result);

	}

	public static String solution(String[] participant, String[] completion) {
		Arrays.sort(participant);
		Arrays.sort(completion);
		for (int i = 0; i < participant.length - 1; i++) {
			if (!participant[i].equals(completion[i]))
				return participant[i];
		}
		return participant[participant.length - 1];
	}
}
