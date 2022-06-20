package 练习练习;

public class erfenchazhao {

    public int search (int[] nums, int target) {
        if (nums==null||nums.length==0) return -1;
        int left = 0;
        int right = nums.length-1;
        int biaoji=-1;
        while (left<=right){
            int mid = left - (left- right)/2;
            if (nums[mid]>=target){
                biaoji = mid;
                right = mid - 1;
            }else {
                left=mid + 1;
            }
        }
        return biaoji;
    }
}
