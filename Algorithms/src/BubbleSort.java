import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};

        System.out.println("原始的数组：");
        System.out.println(Arrays.toString(arr));
        //冒泡排序演变过程
        // 1. 第一趟排序就是将最大的数排在最后（总共四次）
        int temp = 0; //临时变量，用于交换
        for (int j = 0; j < arr.length - 1; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第一趟排序后的数组：");
        System.out.println(Arrays.toString(arr));

        // 2. 第二趟排序将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组：");
        System.out.println(Arrays.toString(arr));

        // 3. 第三趟排序将第三大的数排在倒数第三位
        for (int j = 0; j < arr.length - 1 - 2; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组：");
        System.out.println(Arrays.toString(arr));

        // 4. 第四趟排序将第二大的数排在倒数第四位
        for (int j = 0; j < arr.length - 1 - 3; j++) {
            //如果前面的数比后面的大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组：");
        System.out.println(Arrays.toString(arr));

        // 总结：for循环可以包起来。 最终的冒泡排序算法为
        // 双重循环，故时间复杂度为O(n^2)
        int arr2[] = {3, 9, -1, 10, -2};
        int temp2 = 0;
        boolean flag = false; //标识是否进行过交换
        for (int i = 0; i < arr2.length - 1; i++) {
            for (int j = 0; j < arr2.length - 1 - i; j++) {
                //如果前面的数比后面的大，则交换
                if (arr2[j] > arr2[j + 1]) {
                    flag = true;
                    temp2 = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp2;
                }
            }
            System.out.println("第 " + (i + 1) + " 趟排序后的数组：");
            System.out.println(Arrays.toString(arr2));

            if (flag == false) { // 说明在一趟排序中，一次交换都没发生过则表示排序已完成
                break;
            } else {
                flag = false; //重置flag, 进行下一趟判断是否发生过交换
            }
        }

        //测试封装后的排序算法
        int[] arr3 = {3, 9, -1, 10, -2};
        System.out.println("封装测试排序前：");
        System.out.println(Arrays.toString(arr3));
        bubbleSort(arr3);
        System.out.println("封装测试排序后：");
        System.out.println(Arrays.toString(arr3));

        //测试下冒泡排序的速度O(n^2),给80000个数据测试
        System.out.println("冒泡排序时间复杂度测试~");
        //生成80000个随机数
        int arr4[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr4[i] = (int) (Math.random() * 800000);//生成[0, 800000]
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是：" + datestr);

        bubbleSort(arr4);

        Date date2 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + datestr2);
    }

    //将前面的冒泡排序算法封装成一个方法
    public static void bubbleSort(int[] arr2) {
        int temp2 = 0;
        boolean flag = false; //标识是否进行过交换
        for (int i = 0; i < arr2.length - 1; i++) {
            for (int j = 0; j < arr2.length - 1 - i; j++) {
                //如果前面的数比后面的大，则交换
                if (arr2[j] > arr2[j + 1]) {
                    flag = true;
                    temp2 = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp2;
                }
            }
//            System.out.println("第 " + (i + 1) + " 趟排序后的数组：");
//            System.out.println(Arrays.toString(arr2));

            if (flag == false) { // 说明在一趟排序中，一次交换都没发生过则表示排序已完成
                break;
            } else {
                flag = false; //重置flag, 进行下一趟判断是否发生过交换
            }
        }
    }


}
