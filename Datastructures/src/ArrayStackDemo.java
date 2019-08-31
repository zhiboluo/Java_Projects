import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试ArrayStarck 是否正确
        //先创建一个ArrayStack对象
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈（入栈）");
            System.out.println("pop:表示从栈取出数据（出栈）");

            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("出栈的数据是 %d \n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

//定义一个ArrayStack 类
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈，数据放在该数组中
    private int top = -1; //栈顶，初始化为 -1

    //构造
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //判断是否栈满
        if (isFull()) {
            System.out.println("栈满，无法添加数据！");
            return;
        }
        // 入栈
        top++;
        stack[top] = value;
    }

    //出栈，即将栈顶数据返回
    public int pop() {
        //判断
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空~~~没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况（遍历）,遍历时，需要从栈顶开始显示数据
    public void list() {
        //判断
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }
}
