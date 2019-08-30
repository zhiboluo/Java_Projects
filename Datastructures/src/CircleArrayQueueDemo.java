import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("测试数组模拟环形队列的案例~~~");
        //创建一个环形队列
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4); // 这里设置为4，则队列的最大有效数字为3
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); //接收一个字符

            switch (key) {
                case 's': //显示队列所有数据
                    arrayQueue.showQueue();
                    break;
                case 'a': //添加数据
                    System.out.println("请输入一个数:");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g': //取数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArrayQueue {
    private int maxSize; // 表示数组最大容量
    private int front; // 队列头 初始值 = 0
    private int rear; // 队列尾 初始值 = 0
    private int[] arr; // 该数据用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; // 指向队列头，注意front指向队列头的当前位置
        rear = 0;  // 指向队列尾
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据！");
            return;
        }
//        rear++; //让rear 后移
//        arr[rear] = n;
        //直接将数据加入
        arr[rear] = n;
        //将rear后移
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            //通过抛出异常， 不能return -1;
            throw new RuntimeException("队列空，不能取数据");
        }
//        front++; //front后移
//        return arr[front];
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移. 考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("队列为空，没有数据！");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素?
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        // rear = 1, front = 0, maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            //System.out.println("队列为空，没有数据~");
            throw new RuntimeException("队列为空，没有数据~");
        }
        return arr[front];
    }
}
