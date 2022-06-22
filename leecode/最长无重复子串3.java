package 练习练习.leecode;

public class 最长无重复子串3 {
    public int lengthOfLongestSubstring(String s) {
        if(s==null||s.equals("")) return 0;
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i]=-1;
        }
        int len = 0;
        int ShangYGe = -1;
        int DangQian = 0;
        for (int i = 0; i < str.length; i++) {
            ShangYGe = Math.max(ShangYGe,map[str[i]]);
            DangQian = i-ShangYGe;
            len = Math.max(len,DangQian);
            map[str[i]]=i;
        }
        return len;
    }
}
