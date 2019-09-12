import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id:");
                    int id = scanner.nextInt();
                    System.out.println("输入name:");
                    String name = scanner.next();
                    //创建一个雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    //
}

//创建HashTab，管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化 empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //注意：这时不要忘了分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据雇员id，得到该员工应当添加到哪条链表
        int emmLinkedListNO = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[emmLinkedListNO].add(emp);
    }

    //遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
        return;
    }

    //根据输入的id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数，确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) { //找到
            System.out.printf("在第%d条链表中找到雇员 id = %d \n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中没有找到该雇员~");
        }
    }

    //编写一个散列函数，使用简单的取模法
    public int hashFun(int id) {
        return id % size;
    }
}


//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; //默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList 表示链表
class EmpLinkedList {
    //头指针，指向第一个雇员
    private Emp head = null; //默认为空

    //添加雇员到链表
    //说明
    //  1.当添加雇员时，id是自增长，即id的分配总是从小到大，因此将新雇员加入到链表最后
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是添加第一个，则使用一个辅助指针帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next; //后移
        }
        //退出时直接将emp加入到链表最后
        curEmp.next = emp;
    }

    //遍历链表雇员信息
    public void list(int no) {
        if (head == null) {//空链表
            System.out.println("第 " + (no + 1) + " 链表为空！");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id = %d name = %s \t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;//后移遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    // 如果查找到就返回id，没找到返回null
    public Emp findEmpById(int id) {
        //先判断是否为空
        if (head == null) {
            System.out.println("链表为空~~~");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break; //这时curEmp指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}
