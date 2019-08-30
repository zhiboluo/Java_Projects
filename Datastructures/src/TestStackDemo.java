import java.util.Stack;

public class TestStackDemo {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();

        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //pop就是将栈顶取出
        }

    }
}
