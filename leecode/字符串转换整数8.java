package 练习练习.leecode;

public class 字符串转换整数8 {
    public static int myAtoi(String s) {
        if (s == null || s.equals("")) return 0;
        s = removeHeadZero(s.trim());
        if (s.equals("")) return 0;
        char[] chars = s.toCharArray();
        if (!isValid(chars)) return 0;
        boolean posi = chars[0] == '-'?false:true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MAX_VALUE % 10;
        int res = 0;
        int cur = 0;
        for (int i = (chars[0] == '-' || chars[0] == '+') ? 1 : 0; i < chars.length; i++) {
            cur = '0' - chars[i];
            if ((res < minq) || (res == minq && cur < minr)) {
                return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + cur;
        }
        if (posi && res == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return posi ? -res : res;

    }


    private static String removeHeadZero(String str) {
        boolean r = (str.startsWith("+") || str.startsWith("-"));
        int s = r ? 1 : 0;
        for (; s < str.length(); s++) {
            if (str.charAt(s) != '0') {
                break;
            }
        }
        int e = -1;
        for (int i = str.length() - 1; i >= (r ? 1 : 0); i--) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                e = i;
            }
        }
        return (r ? String.valueOf(str.charAt(0)) : "") + str.substring(s, e == -1 ? str.length() : e);
    }

    private static boolean isValid(char[] chars) {
        if (chars[0] != '-' && chars[0] != '+' && (chars[0] < '0' || chars[0] > '9')) {
            return false;
        }
        if ((chars[0] == '-' || chars[0] == '+') && chars.length == 1) {
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        myAtoi("2147483646");
    }
}
