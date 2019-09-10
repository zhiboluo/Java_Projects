import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        //二分查找的数组必须是有序的
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

        int resIndex = binarySearch(arr, 0, arr.length - 1, 88);

        System.out.println("resIndex=" + resIndex);

        System.out.println("测试多个相同值");

        List<Integer> resIndexList = binarySearchPro(arr, 0, arr.length - 1, 1000);

        System.out.println("resIndexList=" + resIndexList);
    }

    //二分查找算法

    /**
     * @param arr
     * @param left    左索引
     * @param right   右索引
     * @param findVal
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) { //当left > right 时，说明递归整个数组都没找到
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    //当数组中有多个相同元素时，输出所有满足条件的数值并输出
    //思路：
    //  1.在找到mid值时，不要马上输出
    //  2.向mid索引值的左边扫描，将满足findVal的下标加入到集合ArrayList
    //  3.向mid索引值的右边扫描，将满足findVal的下标加入到集合ArrayList
    //  4.将ArrayList返回

    public static ArrayList<Integer> binarySearchPro(int[] arr, int left, int right, int findVal) {
        if (left > right) { //当left > right 时，说明递归整个数组都没找到
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { //向右递归
            return binarySearchPro(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //向左递归
            return binarySearchPro(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            //2.向mid索引值的左边扫描，将满足findVal的下标加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则将temp放入集合中
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);

            //3.向mid索引值的右边扫描，将满足findVal的下标加入到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                //否则将temp放入集合中
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
