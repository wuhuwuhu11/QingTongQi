package 练习练习.二分;

//BM21 旋转数组的最小数字
public class BM21 {

    public int minNumberInRotateArray(int [] array) {

        int left=0;
        int right=array.length-1;
        while (left<=right){
            int min = (left+right)/2;
            if (array[left]<array[min]){
                left=min+1;
            }else if(array[min]==array[right]){
                right--;
            }else {
                right=min;
            }
        }
        return array[0];
    }
}
