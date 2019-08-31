public class Calculator {
    public static void main(String[] args) {
        //根据分析思路，完成表达式运算
        String expression = "7+2*6-4";
        //先创建两个栈：数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量，index用于索引表达式中的字符
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  //将每次扫描得到的char保存到ch
        //开始while循环扫描expression
        while (true) {
            //依次得到expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0); //
            //判断ch 是什么，然后做相应处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //符号栈不为空，就进行比较。
                    // 1.如果当前操作符的优先级小于等于栈中操作符，则
                    //   从数栈中pop出两个数，再从符号栈中pop出一个操作符进行运算，运算结果存入数栈，
                    //    然后将当前扫描得到的操作符入符号栈
                    // 2.如果当前操作符的优先级大于符号栈中操作符，就将当前操作符直接入符号栈

                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //条件-1
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        //把扫描到的运算符入符号栈
                        operStack.push(ch);
                    } else {
                        //条件-2
                        //把扫描到的运算符直接入符号栈
                        operStack.push(ch);
                    }

                } else {
                    //如果为空则直接入符号栈
                    operStack.push(ch);
                }


            } else {
                //如果是数，则直接入数栈
                numStack.push(ch - 48); //注意数字的asica码与数字本身相差 48
            }
            //让index + 1,并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数字和符号，并运算
        while (true) {
            //如果符号栈为空，则计算结束，得到最后结果。此时，数栈中只有一个数字（即最终结果）
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //把运算结果入数栈
            numStack.push(res);
        }
        //将数栈的栈顶pop出，显示结果
        System.out.printf("表达式 %s = %d \n", expression, numStack.pop());

    }
}

//先创建栈类,直接使用前面创建好的
//定义一个ArrayStack2 类,相比于之前的栈类，需扩展功能
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈，数据放在该数组中
    private int top = -1; //栈顶，初始化为 -1

    //构造
    public ArrayStack2(int maxSize) {
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

    //返回运算符的优先级，优先级是程序员确定的
    //      优先级使用数字表示，数字越大，则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return 0;  //假定目前的表达式只有 +, -, *, /
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; //res 用于存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //增加一个方法，可以返回当前栈顶的值，但不是真正出栈
    public int peek() {
        return stack[top];
    }
}
