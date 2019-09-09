import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};

        //需要的内存空间为：80000000*11*4/1024/1024/1024 = 3.3 G
        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        System.out.println("基数排序时间复杂度测试~");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是：" + datestr);

//        radixSortTest(arr);
        radixSort(arr);

//        System.out.println("基数排序后 arr = " + Arrays.toString(arr));

        Date date2 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr2 = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + datestr2);
    }

    //基数排序方法思路测试
    public static void radixSortTest(int[] arr) {

        //定义一个二维数组，表示10个数，每个通就是一个一维数组
        //   说明：1.二维数组包含十个一维数组；2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶）大小定为arr.length
        //        3. 因此，基数排序就是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
        // 如  bucketElementCounts[0] 记录的就是 bucket[0] 桶的放入的数据个数
        int[] bucketElementCounts = new int[10];


        //第一轮排序（针对每个元素的个位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitofElement = arr[j] / 1 % 10;
            //放入到对应的桶
            bucket[digitofElement][bucketElementCounts[digitofElement]] = arr[j];
            bucketElementCounts[digitofElement]++;
        }
        //按照桶的顺序（一维数组的下标），依次取出数据，放入原来数组
        int index = 0;
        //遍历每一个桶，并将桶中数据，放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入到原数组中
            if (bucketElementCounts[k] != 0) {//说明第k个桶中有数据，for循环放入原数组即可
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入原数组
                    arr[index] = bucket[k][l];
                    index++;
                }
                //bucketElementCounts[k] 置零,以便下一轮继续处理
                bucketElementCounts[k] = 0;
            }
        }

        System.out.println("第一轮，对应的排序处理 arr = " + Arrays.toString(arr));

        //第二轮排序（针对每个元素的十位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位的值
            int digitofElement = arr[j] / 10 % 10;
            //放入到对应的桶
            bucket[digitofElement][bucketElementCounts[digitofElement]] = arr[j];
            bucketElementCounts[digitofElement]++;
        }
        //按照桶的顺序（一维数组的下标），依次取出数据，放入原来数组
        index = 0;
        //遍历每一个桶，并将桶中数据，放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入到原数组中
            if (bucketElementCounts[k] != 0) {//说明第k个桶中有数据，for循环放入原数组即可
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入原数组
                    arr[index] = bucket[k][l];
                    index++;
                }
                //bucketElementCounts[k] 置零,以便下一轮继续处理
                bucketElementCounts[k] = 0;
            }
        }

        System.out.println("第二轮，对应的排序处理 arr = " + Arrays.toString(arr));

        //第三轮排序（针对每个元素的百位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的百位的值
            int digitofElement = arr[j] / 100 % 10;
            //放入到对应的桶
            bucket[digitofElement][bucketElementCounts[digitofElement]] = arr[j];
            bucketElementCounts[digitofElement]++;
        }
        //按照桶的顺序（一维数组的下标），依次取出数据，放入原来数组
        index = 0;
        //遍历每一个桶，并将桶中数据，放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入到原数组中
            if (bucketElementCounts[k] != 0) {//说明第k个桶中有数据，for循环放入原数组即可
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入原数组
                    arr[index] = bucket[k][l];
                    index++;
                }
                //bucketElementCounts[k] 置零,以便下一轮继续处理
                bucketElementCounts[k] = 0;
            }
        }

        System.out.println("第三轮，对应的排序处理 arr = " + Arrays.toString(arr));
    }


    //最终基数排序方法

    /**
     * @param arr
     */
    public static void radixSort(int[] arr) {

        //根据前面的推导过程
        //1. 先得到数组中最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //2. 得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个数，每个通就是一个一维数组
        //   说明：1.二维数组包含十个一维数组；2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶）大小定为arr.length
        //        3. 因此，基数排序就是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
        // 如  bucketElementCounts[0] 记录的就是 bucket[0] 桶的放入的数据个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循环处理每一位
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //第 i 轮排序
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应的 i 位的值
                int digitofElement = arr[j] / n % 10;
                //放入到对应的桶
                bucket[digitofElement][bucketElementCounts[digitofElement]] = arr[j];
                bucketElementCounts[digitofElement]++;
            }
            //按照桶的顺序（一维数组的下标），依次取出数据，放入原来数组
            int index = 0;
            //遍历每一个桶，并将桶中数据，放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组中
                if (bucketElementCounts[k] != 0) {//说明第k个桶中有数据，for循环放入原数组即可
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入原数组
                        arr[index] = bucket[k][l];
                        index++;
                    }
                    //bucketElementCounts[k] 置零,以便下一轮继续处理
                    bucketElementCounts[k] = 0;
                }
            }

//            System.out.println("第" + (i + 1) + "轮，对应的排序处理 arr = " + Arrays.toString(arr));

        }
    }
}
