package Datastructure.binaryTree;

public class BinaryTreeNode {
    int data;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode root;

    BinaryTreeNode(int val) {
        data = val;
        left = null;
        right = null;
        root = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }


}
