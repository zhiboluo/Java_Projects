import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    //
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right;  //右下标
        int pivot = arr[(left + right) / 2]; //中轴
        int temp = 0; //用于交换时使用

        //while循环的目的是让比pivot小的放左边，比pivot大的放右边
        while (l < r) {

            while (arr[l] < pivot) {//在pivot左边一直找，找到大于等于pivot的数，才退出
                l += 1;
            }
            while (arr[r] > pivot) {//在pivot右边一直找，找到小于等于pivot的数，才退出
                r -= 1;
            }

            if (l >= r) { //说明pivot左右两边的值已经按照左边全部是小于等于pivot的值，而右边全部是大于等于pivot的值
                break;
            }
            //找到后交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后发现arr[l] == pivot，就让r--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后发现arr[r] == pivot，就让l++,后移
            if (arr[l] == pivot) {
                l += 1;
            }
        }

        //递归前，如果l==r,必须l++,r--,否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
