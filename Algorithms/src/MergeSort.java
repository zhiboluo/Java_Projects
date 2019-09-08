import java.text.SimpleDateFormat;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        System.out.println("归并排序时间复杂度测试~");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是：" + datestr);

        int[] temp = new int[arr.length]; //归并排序需要一个额外的空间

        mergeSort(arr, 0, arr.length - 1, temp);

//        System.out.println("归并排序后，arr = " + Arrays.toString(arr));

        Date date2 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + datestr2);

    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引

            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);

            //到合并时，
            merge(arr, left, mid, right, temp);
        }
    }


    //合并的方法  合并的次数是 arr.length-1 次

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边有序序列的初始索引
     * @param temp  中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //初始化i,左边有序序列的初始索引
        int j = mid + 1; //初始化j,右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引

        // 1. 先把左右两边（有序）的数据，按照规则填充到temp数组，
        //    直到左右两边的有序序列，有一边处理完毕为止
        // 2. 把剩余数据的一边的数据依次全部填充到temp
        // 3. 将temp数组的元素拷贝到arr

        // 1
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素，
            //  则将左边的当前元素拷贝到temp数组的 t 位置，然后 t，i 均后移
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }
            //反之，则将右边的当前元素拷贝到temp数组的 t 位置，然后 t，j 均后移
            else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // 2.
        while (i <= mid) { //说明左边的有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) { //说明右边的有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 3.
        t = 0;
        int tempLeft = left; //
        //第一次合并时，tempLeft = 0; right = 1.  第二次合并时，tempLeft = 2; right = 3. ...
        //   最后一次合并时，tempLeft = 0; right = 7
//        System.out.println("tempLeft=" + tempLeft + ", right=" + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
