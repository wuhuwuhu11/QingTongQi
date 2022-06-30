package 练习练习.leecode;

import java.util.ArrayList;
import java.util.List;

public class 括号生成22 {

    public List<String> generateParenthesis(int n) {
        char[] arr = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(arr, 0, 0, n, ans);
        return ans;
    }

    public static List<String> process(char[] arr, int index, int leftNum, int leftRest, List<String> ans) {
        if (index == arr.length) {
            ans.add(String.valueOf(arr));
        } else {
            if (leftRest > 0) {
                arr[index] = '(';
                process(arr, index + 1, leftNum + 1, leftRest - 1, ans);
            }
            if (leftNum > 0) {
                arr[index] = ')';
                process(arr, index + 1, leftNum - 1, leftRest, ans);
            }
        }
        return ans;
    }
}
