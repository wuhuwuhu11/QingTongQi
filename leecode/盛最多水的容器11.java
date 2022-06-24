package 练习练习.leecode;

public class 盛最多水的容器11 {
    public static int maxArea(int[] h){
        int max=0;
        int l=0;
        int r=h.length-1;
        while (l<r){
            max=Math.max(max,Math.min(h[l],h[r])*(r-l));
            if (h[l]>h[r]){
                r--;
            }else {
                l++;
            }
        }
        return max;
    }
}
