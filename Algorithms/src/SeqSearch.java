public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89}; //无序数组
        int index = seqSearch(arr, 11);

        if (index == -1) {
            System.out.println("没有找到~~~");
        } else {
            System.out.println("找到，下标为：" + index);
        }
    }

    //这里实现的线性查找是，找到一个就返回，若需要返回所有的值，只需新加一个数组，
    //  将所有满足条件的值对应的下标存入即可，最后返回该数组

    /**
     * @param arr   要查找的数组
     * @param value 要查找的值
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        //线性查找是逐一比对，发现有相同值时，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
