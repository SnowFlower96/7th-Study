package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 추석트래픽 {

    static String[] input = {"2016-09-15 01:00:04.002 2.0s",
            "2016-09-15 01:00:07.000 2s"};

    public static void main(String[] args) {
        System.out.println(solution(input));
    }

    public static int solution(String[] lines){
        int answer = 0;
        List<int []> list = new ArrayList<>();

        for (String data : lines ) {
            String[] temp = data.split("\\s");
            String time = temp[1];
            int t = cal(time);
            String dur = temp[2];
            int d = cal2(dur);
            list.add(new int[] {t-d,0});
            list.add(new int[] {t+1000,1});
        }

        Collections.sort(list,(a,b)->(a[0]-b[0]));
        int cnt = 0;

        for(int[] data:list){
            if(data[1]==0){
                cnt++;
            }
            else{
                cnt--;
            }
            answer=Math.max(answer,cnt);
        }

        return answer;
    }

    public static int cal(String time){
        String[] temp = time.split(":");
        int sum=0;
        int hour=Integer.parseInt(temp[0]);
        int min=Integer.parseInt(temp[1]);
        int sec=(int)(Double.parseDouble(temp[2])*1000);

        sum+=3600*hour*1000+min*60*1000+sec;
        return sum;
    }
    public static int cal2(String dur){
        dur=dur.substring(0,dur.length()-1);
        int time=(int)(Double.parseDouble(dur)*1000);
        return time-1;
    }
}
