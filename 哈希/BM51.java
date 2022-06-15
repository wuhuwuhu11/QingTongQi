package 练习练习.哈希;

import java.util.HashMap;

//给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
//
//数据范围：n \le 50000n≤50000，数组中元素的值 0 \le val \le 100000≤val≤10000
//要求：空间复杂度：O(1)O(1)，时间复杂度 O(n)O(n)
public class BM51 {
    public int MoreThanHalfNum_Solution(int [] array) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(array[i])){
                map.put(array[i],1);
            }else {
                map.put(array[i], map.get(array[i])+1);
            }
            if (map.get(array[i])>array.length/2){
                return array[i];
            }
        }
        return 0;
    }
}
