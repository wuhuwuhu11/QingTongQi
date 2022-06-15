package 练习练习.哈希;

import java.util.HashMap;

//BM50 两数之和
public class BM50 {


    public int[] twoSum (int[] numbers, int target) {
        // write code here

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target-numbers[i])){
                return new int[]{map.get(target-numbers[i])+1,i+1};
            }else {
                map.put(numbers[i],i);
            }
        }
        throw new IllegalArgumentException("No solution");
    }
}
