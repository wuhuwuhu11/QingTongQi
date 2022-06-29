package 练习练习.算法技巧;

import java.util.Arrays;

//快速排序
public class 快速排序 {
    public static void sort(int[] array, int low, int high){
        if (low<high){
            int part = partition(array,low,high);
            sort(array, low, part-1);
            sort(array,part + 1,high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int benchmark = array[low];
        while (low<high){
            while ((low<high) && array[high]>=benchmark){
                high--;
            }
            if (low<high){
                array[low]=array[high];
            }
            while ((low<high)&& (array[low]<=benchmark)){
                low++;
            }
            if (low<high){
                array[high]=array[low];
            }
        }
        array[low] =benchmark;
        return low;
    }

    public static void main(String[] args) {
        int[] array = new int[]{46,36,96,26,86,16,76,-17};
        int low = 0;//初始低位索引
        int high = array.length-1;//初始高位索引
        System.out.print("排序前：");
        System.out.println(Arrays.toString(array));
        //使用快速排序算法对数组排序
        快速排序.sort(array,low,high);
        System.out.print("排序后：");
        System.out.println(Arrays.toString(array));
    }

}
