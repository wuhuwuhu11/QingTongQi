package 练习练习.栈队列堆;

import java.util.ArrayList;
import java.util.Collections;

//BM46 最小的K个数
public class BM46 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            list.add(input[i]);
        }
        Collections.sort(list);
        for (int i = 0; i < k ; i++) {
            list1.add(list.get(i));
        }

        return list1;
    }
}
