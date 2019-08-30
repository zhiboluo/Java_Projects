public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        int nums_of_Boy = 5;
        circleSingleLinkedList.addBoy(nums_of_Boy);   //加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(1, 2, nums_of_Boy); // 当为5个小孩时，对应的顺序应为2,4,1,5,3
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

    //根据用户输入，计算出出圈的顺序

    /**
     * @param startNo  表示从第一个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入！");
            return;
        }
        //先创建辅助指针，帮助小孩出圈
        Boy helper = first;
        //需要事先指向环形链表的最后节点
        while (true) {
            if (helper.getNext() == first) {
                //说明helper最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper 移动 startNo-1 次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //小孩报数时，让first和helper同时移动countNum-1次，然后出圈
        //这是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                //说明圈中只有一人
                break;
            }
            //让first和helper同时移动countNum-1次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是要出圈的小孩节点
            System.out.printf("小孩 %d 出圈\n", first.getNo());
            //这时将first指向的节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        //退出时，圈中只有一个节点
        System.out.printf("最后留在圈中的小孩编号是 %d \n", first.getNo());
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
