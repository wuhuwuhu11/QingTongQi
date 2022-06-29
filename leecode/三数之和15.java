package 练习练习.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans =new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(i==0||nums[i-1]!=nums[i]){
                List<List<Integer>> next = twosum(nums,i+1,-nums[i]);
                for(List<Integer> cur : next){
                    cur.add(0,nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }
    public static List<List<Integer>> twosum(int[] nums, int i, int target){
        int left = i;
        int right = nums.length-1;
        List<List<Integer>> temp = new ArrayList<>();
        while (left<right){
            if(nums[left]+nums[right]>target){
                right--;
            }else if(nums[left]+nums[right]<target){
                left++;
            }else {
                if(left==i||nums[left-1]!=nums[left]){
                    List<Integer> dangqian = new ArrayList<>();
                    dangqian.add(nums[left]);
                    dangqian.add(nums[right]);
                    temp.add(dangqian);
                }
                left++;
            }
        }
        return temp;
    }


    public static int[] twoSum(int[] nums, int target) {
        int[] xin = new int[2];
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                if (left == 0 || nums[left - 1] != nums[left]) {
                    xin[0] = nums[left];
                    xin[1] = nums[right];
                }
                left++;
            }
        }
        return xin;
    }


}
