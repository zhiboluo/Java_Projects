import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //完成一个中缀表达式转后缀表达式的功能
        //说明
        // 1. 1 + ( ( 2 + 3 ) * 4 ) - 5 => 转成1 2 3 + 4 * + 5 -
        // 2. 因为直接对一个字符串str操作不方便，因此，先将字符串转成中缀表达式对应的list
        //    即将"1+((2+3)*4)-5" ==> ArrayList[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        // 3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //    即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1,2,3,+,4,*,+,5,-]

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List:" + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List:" + suffixExpressionList);

        System.out.printf("expression = %d \n", calculator(suffixExpressionList));

        /*
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
        */
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

    //中缀表达式转后缀表达式的具体步骤：
    /* 1) 初始化两个栈：运算符栈s1和存储中间结果的栈s2
     * 2) 从左至右扫描中缀表达式
     * 3) 遇到操作数时，压入s2
     * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     *      1. 如果s1为空，或栈顶运算符为左括号"("，则直接入栈s1
     *      2. 否则，若优先级比s1栈顶运算符的高，也将其入栈s1
     *      3. 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到（4.1）与s1中新的栈顶运算符相比较
     * 5）遇到括号时：
     *      1. 如果是左括号"("，则直接入栈s1
     *      2. 如果是右括号")",则依次弹出s1栈顶运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤2）-5）直到表达式的最右边
     * 7) 将s1中剩余的运算符依次弹出并压入s2
     * 8) 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     *
     */
// 代码实现:
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 1) 定义两个栈s1,s2
        Stack<String> s1 = new Stack<String>(); //s1符号栈
        //说明：因为s2这个栈在整个转换过程中，没有pop操作，而且后面还需要逆序输出
        // 因此比较麻烦。这里我们就不用Stack<String>, 我们直接用List<String> s2
//        Stack<String> s2 = new Stack<String>();
        List<String> s2 = new ArrayList<String>();

        // 2) 遍历ls
        for (String item : ls) {
            // 3) 如果是一个数，则入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号")",则依次弹出s1栈顶运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //将"（"弹出s1栈，消除一对小括号
            } else {
                //当item 的优先级 小于等于 s1栈顶运算符的优先级，则:
                // 将s1栈顶的运算符弹出并压入到s2中，再次转到（4.1）与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入s1栈
                s1.push(item);
            }
        }
        // 7) 将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //注意：因为是存放到List中，因此顺序输出就是对应的逆波兰表达式
    }


    //将中缀表达式转换成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //先定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //用于遍历中缀表达式字符串s
        String str; // 对多位数的拼接
        char c; //每遍历到一个字符就存入c
        do {
            //如果c是一个非数字，我们就需要加入到ls中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else { //如果是一个数，需要考虑多位数问题
                str = ""; //先将str 置空    '0'【48】-> '9'【57】
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }
}

//编写一个类Operation可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}
