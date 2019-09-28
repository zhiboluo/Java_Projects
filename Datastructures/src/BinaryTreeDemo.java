public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明：我们先手动创建二叉树，后面再学习递归方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);
        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

        //前序遍历查找
        //前序遍历次数：4
        System.out.println("前序遍历方式~~~");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no = %d name = %s \n", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄 \n", 5);
        }

        //中序遍历查找
        //中序遍历次数：3
        System.out.println("中序遍历方式~~~");
        HeroNode resNode2 = binaryTree.infixOrderSearch(5);
        if (resNode2 != null) {
            System.out.printf("找到了，信息为 no = %d name = %s \n", resNode2.getNo(), resNode2.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄 \n", 5);
        }

        //后序遍历查找
        //后序遍历次数：2
        System.out.println("后序遍历方式~~~");
        HeroNode resNode3 = binaryTree.postOrderSearch(5);
        if (resNode3 != null) {
            System.out.printf("找到了，信息为 no = %d name = %s \n", resNode3.getNo(), resNode3.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄 \n", 5);
        }

    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode [no = " + no + ", name = " + name + "]";
    }

    //前序遍历的方法
    public void preOrder() {
        System.out.println(this); //先输出父节点
        //递归左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历的方法
    public void infixOrder() {
        //先递归左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this); //输出父节点
        //递归右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历的方法
    public void postOrder() {
        //先递归左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this); //最后输出父节点
    }

    //前序遍历查找

    /**
     * @param no 查找编号
     * @return 找到返回该node, 否则返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历~");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        // 1. 否则判定当前节点的左子节点是否为空，不为空则递归前序查找
        // 2. 如果左递归前序查找，找到节点则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明左子树上找到
            return resNode;
        }
        //如果左递归前序查找未找到节点，则继续右递归查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判定当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {//如果找到则返回
            return resNode;
        }
        System.out.println("进入中序遍历~");
        //如果没找到，则和当前节点比较，是则返回
        if (this.no == no) {
            return this;
        }
        //否则继续右递归中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //判定当前节点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//说明在左子树找到
            return resNode;
        }
        //如果左子树没找到，则向右子树递归进行查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历~");
        //如果左右子树都没找到，就比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

}

//定义一个Binary Tree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}
