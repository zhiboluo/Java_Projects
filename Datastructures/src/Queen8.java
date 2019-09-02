public class Queen8 {
    // 八皇后问题
    /**
     * 问题描述： 在8x8格的棋盘上摆放8个皇后，使其不能相互攻击，即任意两个皇后都不能处于同一行，
     * 同一列或同一斜线上。求共有多少中摆法
     */

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义一个array,保存皇后的位置。如array = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0; //static 变量用于统计总共有多少中解法
    static int judgeCount = 0; //判断judge了多少次

    public static void main(String[] args) {
        //测试。八皇后问题求解是否正确
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有 %d 种解法 \n", count);
        System.out.printf("一共有 %d 次判断冲突 \n", judgeCount);
    }

    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，都会有for (int i = 0; i < max; i++)的循环，因此有回溯

    /**
     * 1. 第一个皇后先放第1行第1列
     * 2. 第二个皇后放在第2行第1列，然后判断是否OK。如果不OK，继续放在第2列，第3列，依次把所有列都放完直至找到一个合适位置
     * 3. 继续第三个皇后，还是第1列，第2列，第3列。。。直到第八个皇后也能放到一个合适位置
     * 4. 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后放在第1列的所有正确解全部得到
     * 5. 然后回头继续第一个皇后放第2列，后面继续循环执行1,2,3,4的步骤
     */
    private void check(int n) {
        //
        if (n == max) { // n =8时，放第九个皇后，即表示前面八个皇后已经放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后n，放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到第i列时，是否冲突
            if (judge(n)) {//如果不冲突
                //接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            // else // 如果冲突，就继续执行 i++; array[n] = i 即将第n个皇后放置在本行后一个位置
        }
    }


    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //说明
            // 1. array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            // 2. Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后
            //    是否在同一斜线
            // 3. 判断是否在同一行没有必要。因为n每次都在变
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;// 冲突
            }
        }
        return true;//不冲突
    }

    //写一个方法可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
