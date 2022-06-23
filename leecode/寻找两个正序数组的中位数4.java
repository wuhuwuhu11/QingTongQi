package 练习练习.leecode;

public class 寻找两个正序数组的中位数4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //这里就主要处理 两个为空 一个为空 全不为空的三个情况
        int size = nums1.length + nums2.length;
        boolean e = (size & 1) == 0;
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        } else if (nums1.length == 0) {
            if (!e) {
                return nums2[size / 2];
            } else {
                return (double) (nums2[size / 2 - 1] + nums2[size / 2]) / 2;
            }
        } else if (nums2.length == 0) {
            if (!e) {
                return nums1[size / 2];
            } else {
                return (double) (nums1[size / 2 - 1] + nums1[size / 2]) / 2;
            }
        } else {
            if (!e) {
                return findKthNum(nums1, nums2, size / 2 + 1);
            } else {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            }
        }
    }

    //原型
    public static int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            //mid
            mid1 = (e1 + s1) >> 1;
            mid2 = (e2 + s2) >> 1;
            if (arr1[mid1] == arr2[mid2]) return arr1[mid1];
            //奇数
            if (((e1 - s1 + 1) & 1) == 1) {
                if (arr1[mid1] < arr2[mid2]) {
                    if (arr1[mid1] >= arr2[mid2 - 1]) return arr1[mid1];
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                } else {
                    if (arr2[mid2] >= arr1[mid1 - 1]) return arr2[mid2];
                    s2 = mid2 + 1;
                    e1 = mid1 - 1;
                }
            }
            //偶数
            else {
                if (arr1[mid1] > arr2[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        //最后跳出循环 都只剩一个数
        return Math.min(arr1[s1], arr2[s2]);
    }

    //处理长度不同 返回Kth的情况
    public static int findKthNum(int[] arr1, int[] arr2, int k) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        //1
        if (k <= s) {
            return getUpMedian(longs, 0, k - 1, shorts, 0, k - 1);
        }
        //2
        if (k > l) {
            if (longs[k - s - 1] >= shorts[s - 1]) {
                return longs[k - s - 1];
            }
            if (shorts[k - l - 1] >= longs[l - 1]) {
                return shorts[k - l - 1];
            }
            return getUpMedian(longs, k - s, l - 1, shorts, k - l, s - 1);
        }
        //3
        if (longs[k - s - 1] >= shorts[s - 1]) {
            return longs[k - s - 1];
        }
        return getUpMedian(longs, k - s, k - 1, shorts, 0, s - 1);
    }
}

