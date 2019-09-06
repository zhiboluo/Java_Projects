import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
//        int arr[] = {101, 34, 119, 1};

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        System.out.println("插入排序时间复杂度测试~");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是：" + datestr);

        insertSort(arr);

        Date date2 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + datestr2);
    }

    //插入排序
    public static void insertSort(int[] arr) {

     /*
        //使用逐步推导的方式，便于理解

        //第一轮 {101,34,119,1} => {34,101,119,1}

        //{101,34,119,1} => {101,101,119,1}
        //定义待插入的数
        int insetVal = arr[1];
        int insetIndex = 1 - 1; //即arr[1]前面这个数的下标

        //给insertVal找到插入的位置
        //1. insertIndex > 0保证在给insertVal找插入位置时，不越界
        //2. insetVal < arr[insetIndex 待插入的数还没有找到合适位置
        //3. 就需要将insertIndex后移
        while (insetIndex >= 0 && insetVal < arr[insetIndex]) {
            arr[insetIndex + 1] = arr[insetIndex];
            insetIndex--;
        }
        //当退出循环时，说明插入的位置找到为：  insertIndex+1
        //
        arr[insetIndex + 1] = insetVal;

        System.out.println("第一轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第二轮 {34,101,119,1} => {34,101,119,1}
        insetVal = arr[2];
        insetIndex = 2 - 1;
        while (insetIndex >= 0 && insetVal < arr[insetIndex]) {
            arr[insetIndex + 1] = arr[insetIndex];
            insetIndex--;
        }
        arr[insetIndex + 1] = insetVal;

        System.out.println("第二轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第三轮 {34,101,119,1} => {1，34,101,119}
        insetVal = arr[3];
        insetIndex = 3 - 1;
        while (insetIndex >= 0 && insetVal < arr[insetIndex]) {
            arr[insetIndex + 1] = arr[insetIndex];
            insetIndex--;
        }
        arr[insetIndex + 1] = insetVal;

        System.out.println("第三轮插入后：");
        System.out.println(Arrays.toString(arr)); */

        //使用for循环来简化代码
        for (int i = 1; i < arr.length; i++) {
            int insetVal = arr[i];
            int insetIndex = i - 1; //即arr[1]前面这个数的下标

            //给insertVal找到插入的位置
            //1. insertIndex > 0保证在给insertVal找插入位置时，不越界
            //2. insetVal < arr[insetIndex 待插入的数还没有找到合适位置
            //3. 就需要将insertIndex后移
            // 若要从大到小排列，只需将 < 改成 > 即可
            while (insetIndex >= 0 && insetVal < arr[insetIndex]) {
                arr[insetIndex + 1] = arr[insetIndex];
                insetIndex--;
            }
            //当退出循环时，说明插入的位置找到为：  insertIndex+1
            //这里判断下是否需要赋值
            if (insetIndex + 1 != i)
                arr[insetIndex + 1] = insetVal;

//            System.out.println("第 " + i + " 轮插入后：");
//            System.out.println(Arrays.toString(arr));
        }

    }
}
