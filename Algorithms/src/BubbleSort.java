import java.util.Arrays;

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
        for (int i = 0; i < arr2.length - 1; i++) {
            for (int j = 0; j < arr2.length - 1 - i; j++) {
                //如果前面的数比后面的大，则交换
                if (arr2[j] > arr2[j + 1]) {
                    temp2 = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp2;
                }
            }
            System.out.println("第 " + (i + 1) + " 趟排序后的数组：");
            System.out.println(Arrays.toString(arr2));
        }

    }

}
