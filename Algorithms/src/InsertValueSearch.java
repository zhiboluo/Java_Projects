public class InsertValueSearch {
    public static void main(String[] args) {
//        int[] arr = new int[100];
//        for (int i = 0; i < 100; i++) {
//            arr[i] = i + 1;
//        }
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        System.out.println("index = " + index);

    }

    //插值查找算法(只返回一个值)
    //说明：插值查找算法也要求数组是有序的

    /**
     * @param arr
     * @param left    左索引
     * @param right   右索引
     * @param findVal 待查找的值
     * @return 如果找到就返回对应的下标，未找到返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("查找次数~");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {//说明应向右递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//说明应向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
