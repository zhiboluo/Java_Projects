public class BinarySearch {
    public static void main(String[] args) {
        //二分查找的数组必须是有序的
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int resIndex = binarySearch(arr, 0, arr.length - 1, 88);

        System.out.println("resIndex=" + resIndex);
    }

    //二分查找算法

    /**
     * @param arr
     * @param left    左索引
     * @param right   右索引
     * @param findVal
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) { //当left > right 时，说明递归整个数组都没找到
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
