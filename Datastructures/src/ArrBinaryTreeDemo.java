public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        //创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("顺序存储二叉树，前序遍历~");
        arrBinaryTree.preOrder(); //根节点开始,输出为: 1，2，4，5，3，6，7
        System.out.println("顺序存储二叉树，中序遍历~");
        arrBinaryTree.infixOrder(); //根节点开始,输出为: 4,2,5,1,6,3,7
        System.out.println("顺序存储二叉树，后序遍历~");
        arrBinaryTree.postOrder(); //根节点开始,输出为: 4,5,2,6,7,3,1
    }
}

//编写一个ArrBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr; //存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     * @param index 表示数组的下标
     */
    public void preOrder(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归前序遍历
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归前序遍历
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0); //根节点开始
    }

    //顺序存储二叉树的中序遍历
    public void infixOrder(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归中序遍历
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向右递归中序遍历
        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //重载infixOrder
    public void infixOrder() {
        this.infixOrder(0); //根节点开始
    }

    //顺序存储二叉树的后序遍历
    public void postOrder(int index) {
        //如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归后序遍历
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归后序遍历
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前元素
        System.out.println(arr[index]);
    }

    //重载postOrder
    public void postOrder() {
        this.postOrder(0); //根节点开始
    }
}
