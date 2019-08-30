public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);   //加入5个小孩节点
        circleSingleLinkedList.showBoy();

    }
}

//创建一个环形单向链表
class CircleSingleLinkedList {
    //创建first节点，默认null，没有编号
    private Boy first = new Boy(-1);

    //添加小孩节点，构建成环形链表
    public void addBoy(int nums) {
        //nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确！");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩，
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成一个环
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);//先指向新节点
                boy.setNext(first);//新节点指向第一节点形成环
                curBoy = boy;//curBoy后移
            }
        }
    }

    //遍历
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空，没有小孩~");
            return;
        }
        //因为first指针不能动，因此我们仍然使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }
}


//创建一个BOY类，表示一个节点
class Boy {
    private int no; //编号
    private Boy next; //指向下一个节点，默认null

    //构造函数
    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
