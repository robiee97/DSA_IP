import java.util.*;

public class July18 {
    public static void main(String[] args) {
        // int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1 };
        // Node root = construct(arr);
        // display(root);
        
        // rootToLeafPIR();
        // nodesAtOddLvl(root);
        // sumOfOddNEvenLvl(root);
        // kUPLeaf();
        // kDown();
        // kFar();
        // mirrorBT();
        // minDistbwBSTNodes();
        // minDepthBT();
        // minDepth_In_Pre();
        // maxNodesAtLvl();
        // sumNtoL();
        // sumLtoL();
        // sumNtoN();
        // LLtoBT();
        // BTtoBST();
        // longestConsecutiveInTree();
        // leftMostrightMost();
        // isBST();
        // largestBSTRoot();
        // largestBSTSize();
        // leafAtSameLvl();
        // leafUnderBudget();
        // kthLargest();
        // isSimilar();
        // isMirror();
        // isFoldable();
        // transformToLD();
        // transformFromLD();
        // clonningWithRandomPointer();
    }
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static Node construct(int[] arr) {
        Stack<Node> stack = new Stack<>();
        Node root = null;
        for (int val : arr) {
            if (val == -1) {
                stack.pop();
            } else {
                Node node = new Node();
                node.data = val;
                if (stack.isEmpty()) {
                    root = node;
                } else {
                    if (stack.peek().left == null) {
                        stack.peek().left = node;
                    } else {
                        stack.peek().right = node;
                    }
                }
                stack.push(node);
            }
        }
        return root;
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }
        String str = "";
        str += node.left != null ? node.left.data + "->" : ".->";
        str += node.data;
        str += node.right != null ? "<-" + node.right.data : "<-.";

        System.out.println(str);

        display(node.left);
        display(node.right);
    }


    public static void rootToLeafPIR(){


    }

    public static class Help{
        Node node;
        int lvl;
        public Help(Node node, int lvl){
            this.node=node;
            this.lvl=lvl;
        }
    }
    public static void nodesAtOddLvl(Node root){
        LinkedList<Help> q = new LinkedList<>();
        q.addLast(new Help(root,0));
        while(q.size()>0){
            Help rem = q.removeFirst();
            if(rem.lvl%2!=0){
                System.out.println(rem.node.data+" "+rem.lvl);
            }
            if(rem.node.left!=null){
                q.addLast(new Help(rem.node.left,rem.lvl+1));
            }
            if(rem.node.right!=null){
                q.addLast(new Help(rem.node.right,rem.lvl+1));
            }
        }
    }
    public static int esum=0;
    public static int osum=0;  
    public static void sumOfOddNEvenLvl(Node root){
        LinkedList<Help> q = new LinkedList<>();
        q.addLast(new Help(root,0));
        while(q.size()>0){
            Help rem = q.removeFirst();
            if(rem.lvl%2!=0){
                osum+=rem.node.data;
            }else{
                esum+=rem.node.data;
            }
            if(rem.node.left!=null){
                q.addLast(new Help(rem.node.left,rem.lvl+1));
            }
            if(rem.node.right!=null){
                q.addLast(new Help(rem.node.right,rem.lvl+1));
            }
        }
        System.out.println("oddsum: "+osum+" ,"+"evensum: "+esum);
    }   
}