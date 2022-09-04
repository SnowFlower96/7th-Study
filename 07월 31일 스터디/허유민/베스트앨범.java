package programmers;
import java.util.*;

public class 베스트앨범 {

        public int[] solution(String[] genres, int[] plays) {
            ArrayList<Integer> answer = new ArrayList<>();

            // 장르별 재생횟수합 구하는 해쉬
            // 장르별 고유번호와 재생횟수 해쉬가 들어간 해쉬
            HashMap<String,Integer> map = new HashMap<String,Integer>();
            HashMap<String,HashMap<Integer,Integer>> map2 = new HashMap<String,HashMap<Integer,Integer>>();

            for(int i = 0; i < genres.length; i++){
                // 해당 장르가 없다면 넣기
                if(!map.containsKey(genres[i])){
                    HashMap<Integer,Integer> tempMap = new HashMap<Integer,Integer>();
                    tempMap.put(i,plays[i]);
                    map2.put(genres[i], tempMap);
                    map.put(genres[i],plays[i]);
                }
                // 있다면 해당 장르에 재생횟수 추가
                else {
                    map2.get(genres[i]).put(i,plays[i]);
                    int temp = map.get(genres[i]);
                    map.remove(genres[i]);
                    map.put(genres[i],plays[i]+temp);
                }
            }

            ArrayList<String> list = new ArrayList<String>();
            for(String k : map.keySet()){
                list.add(k);
            }

            // 장르별 재생횟수 순으로 내림차순 정렬
            Collections.sort(list, (o1, o2) -> map.get(o2) - (map.get(o1)));


            for(int i = 0; i < list.size(); i++){
                String k = list.get(i);
                HashMap<Integer, Integer> tempMap = map2.get(k);
                List<Integer> genre_key = new ArrayList();
                for(int s : tempMap.keySet()){
                    genre_key.add(s);
                }

                // 장르안에서 재생횟수 순으로 정렬
                Collections.sort(genre_key, (o1, o2) -> tempMap.get(o2) - (tempMap.get(o1)));

                answer.add(genre_key.get(0));
                if(genre_key.size() > 1)
                    answer.add(genre_key.get(1));
            }

            int array[] = new int[answer.size()];
            for(int i = 0; i < answer.size();i++){
                array[i] = answer.get(i);
            }

            return array;
        }

}
