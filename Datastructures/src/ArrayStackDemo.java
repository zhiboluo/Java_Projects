public class ArrayStackDemo {
    public static void main(String[] args) {

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
