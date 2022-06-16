package 练习练习.栈队列堆;

//BM44 有效括号序列

import java.util.Stack;

public class BM44 {
    public boolean isValid (String s) {
        Stack<Character> stack= new Stack<>();
//        StringBuilder str= new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='('){
                stack.push(')');
            }else if (s.charAt(i)==('[')){
                stack.push(']');
            }else if (s.charAt(i)=='{'){
                stack.push('}');
            }else if (stack.isEmpty()||stack.pop()!=s.charAt(i)){
                return false;
            }
        }
        return stack.isEmpty();
    }
}
