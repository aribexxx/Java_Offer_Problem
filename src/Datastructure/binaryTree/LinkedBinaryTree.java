package Datastructure.binaryTree;

//Linked Structure of Binary Tree
public class LinkedBinaryTree {
    BinaryTreeNode root;
    int size;

    LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    boolean isEmpty() {
        return root == null;
    }

    int size() {
        return size;
    }

    void addRoot(BinaryTreeNode root) {
        if (!isEmpty()) {
            this.root = root;
        }
    }

    void addLeft() {

    }

    void addRight() {

    }


}
