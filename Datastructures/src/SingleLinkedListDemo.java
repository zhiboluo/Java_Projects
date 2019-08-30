import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //直接加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //加入按照编号顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero3);

        //测试修改节点的代码
        //修改前显示
        singleLinkedList.list();
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        //修改后显示
        System.out.println("修改后的链表情况~");
        singleLinkedList.list();

        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);
        System.out.println("删除后的链表情况~");
        singleLinkedList.list();

        //测试下求单链表中有效节点的个数
        System.out.println("有效节点个数 = " + getLength(singleLinkedList.getHead()));

        //测试下是否能得到倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.println("res = " + res);

        //测试链表反转
        System.out.println("反转前的链表情况~");
        singleLinkedList.list();
        System.out.println("反转后的链表情况~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        //测试逆序打印链表节点
        System.out.println("逆序打印链表~");
        reversePrint(singleLinkedList.getHead());//注意，链表的结构未变化
    }

    //使用栈来逆序打印链表
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return; //空链表，无法打印
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移，继续压入下一个
        }
        //将栈中的节点打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());// stack的特点是先进后出
        }

    }

    //将单链表进行反转
    /* 思路
    1. 先定义一个节点reversehead;
    2. 从头到尾遍历原链表每个节点，取出并放在新链表的最前端
    3. 原链表的head.next = reversehead.next
     */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //1. 先定义辅助指针
        HeroNode cur = head.next;
        HeroNode next = null; // 指向当前节点（cur）的下一个节点
        HeroNode reversehead = new HeroNode(0, "", "");
        //2. 遍历原链表。从头到尾遍历原链表每个节点，取出并放在新链表的最前端
        while (cur != null) {
            next = cur.next; //暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reversehead.next; //将cur的下一个节点指向新链表的最前端
            reversehead.next = cur;
            cur = next; //让cur后移
        }
        //3. 原链表的head.next = reversehead.next,实现反转
        head.next = reversehead.next;
    }

    //查找单链表中的倒数第k个节点【sina】
    //思路： 1. 编写一个方法接收head节点，同时接收一个index
    //      2. index 表示是倒数第index个节点
    //      3. 先把链表从头到尾遍历， 得到链表总长度getLength
    //      4. 得到size后，从链表的第一个开始遍历（size - index）个，就得到 倒数第k个节点
    //      5. 如果找到则返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历 size - index 位置就是要找的倒数第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量cur, for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //获取单链表的节点个数（如果带头节点，需不统计头节点）
    //输入链表的头节点，输出有限节点个数
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助变量,注意这里未统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

//创建singleLinkedList 来管理我们的英雄
class SingleLinkedList {
    //先初始化头结点，头结点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路：当不考虑编号顺序时，找到当前链表的最后节点，将最后节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，我们需要一个辅助节点temp
        HeroNode temp = head;
        //遍历链表，找到最后节点
        while (true) {
            //找到链表最后
            if (temp.next == null) {
                break;
            }
            //如果没找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向了链表的最后
        temp.next = heroNode;
    }

    //第二种方式添加英雄时，根据排名将英雄插入到指定位置。如果有这个排名则添加失败并给出提示
    public void addByOrder(HeroNode heroNode) {
        //因为头结点不能动，因此我们任然通过一个辅助节点来帮助找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则无法插入
        HeroNode temp = head;
        boolean flag = false; //标识添加的编号是否存在，默认false
        while (true) {
            if (temp.next == null) {
                //已到链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到，就在temp的后面
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明编号已存在
                flag = true;
                break;
            }
            temp = temp.next; //后移，遍历当前链表

        }
        //判断flag的值
        if (flag) {
            //不能添加，说明编号已存在
            System.out.printf("准备插入的英雄编号 %d 已存在，不能加入!\n", heroNode.no);
        } else {
            //加入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能变
    //说明：根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no 编号来找
        //先定义辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //已到链表尾部
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到已修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找到编号 %d 的节点，不能修改！\n", newHeroNode.no);
        }

    }

    //删除节点
    //思路：1. head节点不能动，因此我们需要一个temp节点来找到待删除节点的前一个节点
    //   2.我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; //标识是否找到待删除节点
        while (true) {
            if (temp.next == null) {//已到链表最后
//                System.out.println();
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {
            //找到，可删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在!\n", no);
        }
    }

    //显示链表【遍历】
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历链表
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }
    }
}

//定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造
    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //为了显示方便，我们重写toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
