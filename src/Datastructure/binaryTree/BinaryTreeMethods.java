package Datastructure.binaryTree;

import java.util.*;

//-----------q2-----TreeNode------
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    int getVal() {
        return this.val;
    }


}

//-------------------------------------
public class BinaryTreeMethods {


    //-----------------Q1----IN & PRE-------------------
    public static TreeNode buildTreePre_In(int[] pre, int[] in) {
        int pre_len = pre.length;
        int in_len = pre_len;
        return helper(pre, in, 0, pre_len - 1, 0, in_len - 1);

    }

    public static TreeNode helper(int[] pre, int[] in, int prestart, int pre_end, int instart, int in_end) {
// build in preorder way
//base case
        if (prestart > pre_end || instart > in_end) {
            return null;
        }

        TreeNode root = new TreeNode(pre[prestart]);
        int index = 0;

//find index in inorder
        for (int i = 0; i <= in_end; i++) {
            if (root.val == in[i]) {
                index = i;
                break;
            }
        }

        root.left = helper(pre, in, prestart + 1, prestart + index - instart, instart, index - 1);
        root.right = helper(pre, in, prestart + index - instart + 1, pre_end, index + 1, in_end);

        return root;
    }

//print pre_order way

    public static void print_Pre(TreeNode root) {
        TreeNode cur = root;
        if (cur == null) {
            return;
        }
        System.out.print(cur.val + ",");
        print_Pre(cur.left);
        print_Pre(cur.right);

    }


//-----------Q1 IMPLEMENTATION END------------------


    //Q2:-------IN & LEVEL----------User HashMap to keep track of index--------------
    public static TreeNode buildBinaryTree(int in[], int level[]) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < level.length; i++) {
            map.put(level[i], i);
        }
        return constructTree(in, level, 0, in.length - 1, map);
    }

    //helper method for constructTree
    public static TreeNode constructTree(int in[], int level[], int instart, int inend, Map<Integer, Integer> map) {
        //base case
        if (instart > inend) {
            return null;
        }
        //start from the first ele in subtree to find the root,if can not find level index less than this ,then it is the root.
        int index = instart;
        //traverse through inorder [],compare each elements, find the same value in level[], that has the least index,that is root.
        for (int j = instart + 1; j < inend; j++) {
            if (map.get(in[j]) < map.get(in[index])) {
                index = j;
            }
        }
        TreeNode root = new TreeNode(in[index]);

        //System.out.println("node created "+in[index]);
        root.left = constructTree(in, level, instart, index - 1, map);
        root.right = constructTree(in, level, index + 1, inend, map);
        return root;

    }
    //Q2---END----IN & LEVEL----------

    public static void printPreOrder(TreeNode root, StringBuilder sb) {
        TreeNode cur = root;

        if (cur == null) {
            return;
        }

        /*Difference between String & StringBuilder */

/*   USING STRING:  This part of code will only output :
1,
1,2,
1,2,4,
1,2,5,
1,3,
1,3,6,
1,3,7,
        out+= Integer.toString(cur.val)+',';
        System.out.println(out);
  */
//USING STRING BUILDER WORKS!!
        sb.append(Integer.toString(cur.val) + ',');
        System.out.print(cur.val + ",");
        printPreOrder(root.left, sb);
        printPreOrder(root.right, sb);

    }

    //print PostOrder
    public static void printPostOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.print(root.val + ",");
    }


    /*Q3: --------------Symmetric binary tree------------*/

    static boolean isSymmetrical(TreeNode pRoot) {
        return Symmetry_helper(pRoot, pRoot);
    }

    static boolean Symmetry_helper(TreeNode pRootLR, TreeNode pRootRL) {
        /* WRONG： 这种思路会产生 NULLPTR EXCEPTION，
        因为如果一个OBJ为NULL 那么它也无法通过.val 取得数值。
         */
        if (pRootLR == null && pRootRL == null) {
            return true;
        }
        if (pRootLR == null || pRootRL == null) {
            return false;
        }
        if (pRootLR.val != pRootRL.val) {
            return false;
        }

        return Symmetry_helper(pRootLR.left, pRootRL.right) && Symmetry_helper(pRootLR.right, pRootRL.left);

    }
    /*Q3--------END--------- Symmetric binary tree*/

    //Q32-1：---------------从上到下打印二叉树_bfs-----------------------
    //use queue, FIFO,Loop not recursion
    public static String print_bfs_BinaryTree(TreeNode root) {
        String out = "";

        if (root == null) {
            return out;
        }
        ArrayDeque<TreeNode> dq = new ArrayDeque<>();
        dq.addLast(root);
        while (!dq.isEmpty()) {
            //pop the first element.And update "cur" to this first ele.
            TreeNode cur = dq.pollFirst();
            int output = cur.val;
            //这里有一个坑！！：
            // WHY THIS LINE PRINTS 50 this kind of number????-------------
            //System.out.println(output+',');    //this will treat the int as a character and print the unicode value of the char.
            //System.out.println("Direct int output: "+output+',');//this print correct string
            // System.out.println(Integer.toString(output)+',');    //correct
            //-------------------------------
            out += output + ",";
            // out.append(output+',');
            //
            if (cur.left != null) {
                dq.addLast(cur.left);
                //difference between addFirst VS push??? They does the same thing.
            }
            if (cur.right != null) {
                dq.addLast(cur.right);
            }
        }
        return out;
    }
    //-------END----------- Q32-1 从上到下打印二叉树_bfs------------------


    //Q32-2：从上到下打印二叉树-分层
    //Q32-3：S型 打印二叉树??????????????
    //重点：需要两个stack，交替保 & 分奇偶层。
    public static String print_S_Shape(TreeNode root) {
        String out = "";
        if (root == null) {
            return out;
        }
//put root into deque
        ArrayDeque<TreeNode> dq[] = new ArrayDeque[2];
        dq[0] = new ArrayDeque<>();
        dq[1] = new ArrayDeque<>();
        int current = 0;// dq[0]:for storing current nodes
        int next = 1;// dq[1]: storing the child nodes of the current node.
        System.out.println(dq[current] + "safdsaf");////////EMPTY DQ initialization
        dq[current].addLast(root);

        while (!dq[0].isEmpty() || !dq[1].isEmpty()) {
            TreeNode first;

            first = dq[current].pollLast();


            String first_str = Integer.toString(first.val);
            out += first_str + ",";


            if (current == 0) {

                if (first.left != null) {
                    dq[next].addLast(first.left);

                }
                if (first.right != null) {
                    dq[next].addLast(first.right);

                }
            } else {

                if (first.right != null) {
                    dq[next].addLast(first.right);
                }
                if (first.left != null) {
                    dq[next].addLast(first.left);
                }

            }


            if (dq[current].isEmpty()) {
                out += "\n";

                current = 1 - current;
                next = 1 - next;
            }


        }
        return out;
    }

    //Q37: -----------Serialization & Deserialization---------------------
    public static String Serialize(TreeNode root, StringBuilder sb) {
        //Pre order-Serialization

        if (root == null) {
            sb.append("#!");
            return sb.toString();
        } else {
            sb.append(Integer.toString(root.val) + '!');
        }
        Serialize(root.left, sb);
        Serialize(root.right, sb);
        return sb.toString();
    }

    public static TreeNode Deserialize(String str) {

        if (str.isEmpty()) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, "!");
        TreeNode root = deserializeHelper(st);
        return root;
    }

    public static TreeNode deserializeHelper(StringTokenizer st) {
        TreeNode root = null;
        if (st.hasMoreTokens()) {
            String next = st.nextToken();
            if (next.equals("#")) {
                return null;
            }
            int nextVal = Integer.parseInt(next);
            root = new TreeNode(nextVal);

            root.left = deserializeHelper(st);
            root.right = deserializeHelper(st);
        }
        return root;
    }

    //-------------------FIND K th smallest number in BST
    //Using FIFO queue.
    public static TreeNode KthNode(TreeNode pRoot, int k) {
        TreeNode out = null;
        if (pRoot == null || k <= 0) {
            return out;
        }
        //PreOrder traverse BST
        ArrayDeque<TreeNode> dq = new ArrayDeque<TreeNode>();
        InOrder(pRoot, dq);


        //for loop to pop elements till kth
        for (int i = 0; i < k - 1; i++) {
            dq.pollFirst();
        }
        out = dq.pollFirst();
        return out;
    }

    public static ArrayDeque InOrder(TreeNode root, ArrayDeque<TreeNode> dq) {

        if (root == null) {
            return dq;
        }
        InOrder(root.left, dq);
        dq.addLast(root);//push elements into dq
        InOrder(root.right, dq);
        return dq;
    }

    //----------------------End Kth smallest element in BST---------------
    public static void main(String[] argv) {


        //Q1:build tree ,given pre and in  .(TreeNode)------------------

        int[] pre = {1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15};
        int[] in = {8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15};
        TreeNode root = buildTreePre_In(pre, in);
        print_Pre(root);
        System.out.println("\n");
        //------------------END Build tree pre & In--------------------------

        //------------Deserialization-----&----Serialization----------

        StringBuilder sb1 = new StringBuilder();
        String out_serialize = Serialize(root, sb1);
        System.out.println("Serialize: " + out_serialize + "\n");

        TreeNode deserialTree = Deserialize(out_serialize);
        StringBuilder sb_des = new StringBuilder();
        System.out.println("De_Serialize: ");
        printPreOrder(deserialTree, sb_des);
        System.out.println("\n");

        //Deserialization
        //----------------End----------------------------------
        //-------------START Kth-----------------
        String serial = "5!3!2!#!#!4!#!#!7!6!#!#!8!#!#!";
        TreeNode bst = Deserialize(serial);
        TreeNode kth = KthNode(bst, 5);
        System.out.println("K th val of BST is : " + kth.getVal() + "\n");


        //------------end-----------------
        //Q2:build tree,given in and level traversal.(TreeNode)
        int[] in2 = {4, 2, 5, 1, 6, 3, 7};
        int[] level = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root2 = buildBinaryTree(in2, level);


        int[] in3 = {5, 6, 7, 8, 9, 10, 11};
        int[] level3 = {8, 6, 10, 5, 7, 9, 11};
        TreeNode root3 = buildBinaryTree(in3, level3);

        //---------Q32: 从上到下打印二叉树---------print by level--------------------
        //
        String bfs_out = print_bfs_BinaryTree(root3);
        System.out.println("Print tree, BFS top to bottom " + bfs_out);
        //---------------------END print tree by level-------------------------

        //-------------PreOrder & PostOrder print-------------------
        StringBuilder sb = new StringBuilder();
        System.out.println("Pre");
        printPreOrder(root2, sb);
        System.out.println("\nUsing string builder in PreOrder: " + sb);

        System.out.println("\nPost");
        printPostOrder(root2);

        //Q3----------Check if a tree is symmetry.------------
        int[] symTree = {3, 2, 4, 1, 4, 2, 3};
        int[] symTree_l = {1, 2, 2, 3, 4, 4, 3};

        int[] sym2Tree = {7, 7, 7, 7, 7, 7, 7};
        int[] sym2Tree_l = {7, 7, 7, 7, 7, 7, 7};
        TreeNode root4 = buildBinaryTree(sym2Tree, sym2Tree_l);
        System.out.println("\nSymmetry: " + isSymmetrical(root2));
        boolean b = isSymmetrical(root4);
        ;
        System.out.println("Symmetry: " + b);
        StringBuilder sb3 = new StringBuilder();
        printPreOrder(root4, sb3);
        System.out.println("\nSymmetriy tree");
        //----------Check if a tree is symmetry.--END----------

        //q32_3: S_shape print
        String out_s = print_S_Shape(root);
        System.out.println("s shape out: \n" + out_s);
//-----------


    }
}
