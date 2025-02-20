
import java.util.*;

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> answer = new ArrayList<String>();
        
        // 1ms answer
        /**
        for(int i = 1; i <= n; i++){
            if (i % 5 == 0 && i % 3 == 0){
                answer.add("FizzBuzz");
            } else if (i % 5 == 0) {
                answer.add("Buzz");
            } else if (i % 3 == 0){
                answer.add("Fizz");
            } else {
                answer.add(String.valueOf(i));
            }
        } */

        // 2ms answer
        for(int i = 1; i <= n; i++){
            answer.add("");
            if (i % 3 == 0){
                answer.set(i-1, answer.get(i-1).concat("Fizz"));
            }
            
            if (i % 5 == 0) {
                //answer.append("Buzz");
                answer.set(i-1, answer.get(i-1).concat("Buzz"));
            }
            if (i % 3 != 0 && i % 5 != 0) {
                answer.set(i-1, answer.get(i-1).concat(String.valueOf(i)));
                //answer.add(String.valueOf(i));
            }
        }
        
        return answer;
    }
}