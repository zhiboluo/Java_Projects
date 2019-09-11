import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index=" + fibSearch(arr, 134));
    }

    //斐波那契查找算法（必须是有序序列）
    //因为后面我们mid = low+F(k-1)-1,我们需要用到斐波那契数列

    //非递归方式得到斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法

    /**
     * @param arr 数组
     * @param key 待查找的值
     * @return 返回对应的下标，未找到返回-1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib(); //获取斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f【k】可能大于arr的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp
        //   不足的部分使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上需要使用a数组最后的数进行填充
        //  举例：temp  = {1, 8, 10, 89, 1000, 1234, 0, 0, 0} -->
        //        temp  = {1, 8, 10, 89, 1000, 1234, 1234, 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //使用while循环处理，找到我们的数key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //说明应该继续向数组的前面查找（左边）
                high = mid - 1;
                k--; //注意这里需要减1
            } else if (key > temp[mid]) { //说明应该继续向数组的后面查找（右边）
                low = mid + 1;
                k -= 2; //注意这里需要减2
            } else {
                //需要确定返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
