import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {

//        int[] arr = {101, 34, 119, 1, -1, 90, 123};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

//        System.out.println("原始数组：");
//        System.out.println(Arrays.toString(arr));

        System.out.println("选择排序时间复杂度测试~");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是：" + datestr);

        selectSort(arr);

        Date date2 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + datestr2);
//        System.out.println("选择排序后：");
//        System.out.println(Arrays.toString(arr));

    }

    //选择排序
    public static void selectSort(int[] arr) {
        //使用逐步推导的方式进行思路分析
        //  --原始数组：101，34，119，1
        //  第一轮排序：1，34，119，101

        //时间复杂度为O(n^2) 因为是双重for循环

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定最小值不是最小值，重置最小值及其下标
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将找到的最小值放到arr[0]的位置，而将arr[0]放到arr[minIndex]
            //优化：如果未发生位置改变，则不交换 if (minIndex != 0)
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println("第" + (i + 1) + "轮选择排序：");
//            System.out.println(Arrays.toString(arr));
        }
        /*
        //第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 1; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定最小值不是最小值，重置最小值及其下标
                min = arr[j];
                minIndex = j;
            }
        }
        //将找到的最小值放到arr[0]的位置，而将arr[0]放到arr[minIndex]
        //优化：如果未发生位置改变，则不交换 if (minIndex != 0)
        arr[minIndex] = arr[0];
        arr[0] = min;

        System.out.println("第一轮选择排序：");
        System.out.println(Arrays.toString(arr));

        //第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 2; j < arr.length; j++) {
            if (min > arr[j]) {//说明假定最小值不是最小值，重置最小值及其下标
                min = arr[j];
                minIndex = j;
            }
        }
        //将找到的最小值放到arr[0]的位置，而将arr[0]放到arr[minIndex]
        arr[minIndex] = arr[1];
        arr[1] = min;

        System.out.println("第二轮选择排序：");
        System.out.println(Arrays.toString(arr)); */
    }
}
