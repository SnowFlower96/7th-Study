package personal;

public class Programmers_Solution_키패드누르기 {

	class Solution {
	    
	    static String[][] arr = {{"1","2","3"},
	                             {"4","5","6"},
	                             {"7","8","9"},
	                             {"*","0","#"}
	                            };
	    
	    int getNum(int a, int b, int c, int d){
	            return Math.abs(a-c) + Math.abs(b-d);
	        }
	    
	    public String solution(int[] numbers, String hand) {
	        String answer = "";
	        int lr = 3;
	        int lc = 0;
	        
	        int rr = 3;
	        int rc = 2;
	        for(int i = 0; i < numbers.length;i++){
	            switch(numbers[i]){
	                case 1:
	                lr = 0;
	                lc = 0;
	                answer += "L";
	                break;
	                    case 2:
	                    if(getNum(lr,lc,0,1) < getNum(rr,rc,0,1) ){
	                        lr = 0;
	                        lc = 1;
	                        answer += "L";
	                    } else if(getNum(lr,lc,0,1) > getNum(rr,rc,0,1)){
	                        rr = 0;
	                        rc = 1;
	                        answer += "R";
	                    } else if(getNum(lr,lc,0,1) == getNum(rr,rc,0,1)){
	                        if(hand.equals("left")){
	                            lr = 0;
	                        lc = 1;
	                        answer += "L";
	                        }else if(hand.equals("right")){
	                            rr = 0;
	                        rc = 1;
	                        answer += "R";
	                        }
	                    }
	                    break;
	                    case 3:
	                    rr = 0;
	                    rc = 2;
	                    answer += "R";
	                    break;
	                    case 4:
	                    lr = 1;
	                lc = 0;
	                answer += "L";
	                break;
	                    case 5:
	                    if(getNum(lr,lc,1,1) < getNum(rr,rc,1,1) ){
	                        lr = 1;
	                        lc = 1;
	                        answer += "L";
	                    } else if(getNum(lr,lc,1,1) > getNum(rr,rc,1,1)){
	                        rr = 1;
	                        rc = 1;
	                        answer += "R";
	                    } else if(getNum(lr,lc,1,1) == getNum(rr,rc,1,1)){
	                        if(hand.equals("left")){
	                            lr = 1;
	                        lc = 1;
	                        answer += "L";
	                        }else if(hand.equals("right")){
	                            rr = 1;
	                        rc = 1;
	                        answer += "R";
	                        }
	                    }
	                    break;
	                    case 6:
	                    rr = 1;
	                    rc = 2;
	                    answer += "R";
	                    break;
	                    case 7:
	                    lr = 2;
	                lc = 0;
	                answer += "L";
	                break;
	                    case 8:
	                    if(getNum(lr,lc,2,1) < getNum(rr,rc,2,1) ){
	                        lr = 2;
	                        lc = 1;
	                        answer += "L";
	                    } else if(getNum(lr,lc,2,1) > getNum(rr,rc,2,1)){
	                        rr = 2;
	                        rc = 1;
	                        answer += "R";
	                    } else if(getNum(lr,lc,2,1) == getNum(rr,rc,2,1)){
	                        if(hand.equals("left")){
	                            lr = 2;
	                        lc = 1;
	                        answer += "L";
	                        }else if(hand.equals("right")){
	                            rr = 2;
	                        rc = 1;
	                        answer += "R";
	                        }
	                    }
	                    break;
	                    case 9:
	                    rr = 2;
	                    rc = 2;
	                    answer += "R";
	                    break;
	                    case 0:
	                    if(getNum(lr,lc,3,1) < getNum(rr,rc,3,1) ){
	                        lr = 3;
	                        lc = 1;
	                        answer += "L";
	                    } else if(getNum(lr,lc,3,1) > getNum(rr,rc,3,1)){
	                        rr = 3;
	                        rc = 1;
	                        answer += "R";
	                    } else if(getNum(lr,lc,3,1) == getNum(rr,rc,3,1)){
	                        if(hand.equals("left")){
	                            lr = 3;
	                        lc = 1;
	                        answer += "L";
	                        }else if(hand.equals("right")){
	                            rr = 3;
	                        rc = 1;
	                        answer += "R";
	                        }
	                    }
	                    break;
	                default:
	                break;
	            }
	        }
	        
	        return answer;
	    }
	}

}
