public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建一个双向链表,顺序添加到尾部
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        //加入按照编号顺序
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);

        //输出
        System.out.println("双向链表测试：");
        doubleLinkedList.list();
        //修改
        HeroNode2 newheroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newheroNode);
        System.out.println("修改后的双向链表：");
        doubleLinkedList.list();
        //删除操作
        doubleLinkedList.del(3);
        System.out.println("删除后的双向链表：");
        doubleLinkedList.list();
    }
}


//创建一个双向链表的类
class DoubleLinkedList {
    //先初始化头结点，头结点不要动，不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表，显示链表【遍历】
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历链表
        HeroNode2 temp = head.next;
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

    //添加节点到双向链表的最后
    //思路：当不考虑编号顺序时，找到当前链表的最后节点，将最后节点的next指向新的节点
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，我们需要一个辅助节点temp
        HeroNode2 temp = head;
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
        heroNode.pre = temp;//形成双向链表
    }

    //双向链表的第二中节点添加方式
    //第二种方式添加英雄时，根据排名将英雄插入到指定位置。如果有这个排名则添加失败并给出提示
    public void addByOrder(HeroNode2 heroNode) {
        //因为头结点不能动，因此我们任然通过一个辅助节点来帮助找到添加的位置
        //因为双向链表，因此我们找的temp是位于添加位置的前一个节点，否则无法插入
        HeroNode2 temp = head;
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
//            heroNode.next = temp.next; //单链表的插入操作
//            temp.next = heroNode;
            heroNode.next = temp.next;
            temp.next = heroNode;
            if (temp.next != null)
                temp.next.pre = heroNode;
            heroNode.pre = temp;
        }
    }


    //修改思路和单向链表一致
    //修改节点的信息，根据no编号来修改，即no编号不能变
    //说明：根据newHeroNode的no来修改
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no 编号来找
        //先定义辅助变量
        HeroNode2 temp = head.next;
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

    //从双向链表中删除一个节点
    //说明
    //1.对于双向链表，可直接找到要删除的节点，如temp
    //2.找到后，自我删除即可. temp.pre.next = temp.next
    //3. temp.next.pre = temp.pre
    public void del(int no) {
        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
        }
        HeroNode2 temp = head.next;
        boolean flag = false; //标识是否找到待删除节点
        while (true) {
            if (temp == null) {//已到链表最后
//                System.out.println();
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) {
            //找到，可删除
//            temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //有风险，如删除节点刚好是最后一个，就无需执行下一句，否则出现空指正异常
            if (temp.next != null)
                temp.next.pre = temp.pre;
        } else {
            System.out.printf("要删除的 %d 节点不存在!\n", no);
        }
    }

}

//定义一个HeroNode2,每个HeroNode2对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; //指向前一个节点

    //构造
    public HeroNode2(int hNo, String hName, String hNickname) {
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
