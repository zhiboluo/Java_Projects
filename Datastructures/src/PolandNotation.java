import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //测试多位数(30+4)*5-6
        //4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        //为了方便，逆波兰表达式中的数字和符号用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 - ";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; //"30 4 + 5 * 6 - ";
        //思路
        // 1. 先将 "3 4 + 5 * 6 - " 放入ArrayList 中
        // 2. 将ArrayList 传递给一个方法，遍历ArrayList, 配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList = " + rpnList);

        int res = calculator(rpnList);
        System.out.println("计算的结果：" + res);
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
     * 1) 从左至右扫描，将3，4压入栈
     * 2）遇到+运算符，因此弹出4和3（4为栈顶，3为次顶）。计算出3+4=7，再将7压入栈
     * 3）将5压入栈
     * 4）遇到*运算符，因此弹出5和7，计算出7*5=35，压入栈
     * 5）将6压入栈
     * 6）遇到-运算符，弹出35和6，计算出35-6=29，压入栈。由此得到最终结果
     */
    public static int calculator(List<String> ls) {
        //创建栈。只需要一个栈
        Stack<String> stack = new Stack<String>();
        //遍历 ls
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数并运算（运算符为当前遍历到的item）,再将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误！！！");
                }
                //把res入栈
                stack.push(res + "");//将整型转换成字符串
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
