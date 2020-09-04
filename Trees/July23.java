import java.util.*;
public class July23{
    public static class Node {
        int data = 0;
        ArrayList<Node> child = new ArrayList<>();

        public Node(int data) {
            this.data = data;
        }
    }

    // ----------------gtree
    public static Node gTree(int[] arr) {

        LinkedList<Node> st = new LinkedList<>();
        Node root = null;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.removeFirst();
            } else {
                Node node = new Node(arr[i]);
                if (st.size() == 0) {
                    root = node;
                } else {
                    st.getFirst().child.add(node);
                }
                st.addFirst(node);
            }

        }
        return root;
    }
    public static void display(Node root) {
        System.out.print(root.data + " -> ");
        for (Node child : root.child) {
            System.out.print(child.data + " ");
        }
        System.out.println(".");

        for (Node child : root.child) {
            display(child);
        }
    }

    static int res=0;
    public static void immediateSmaller(Node root,int val){
        if(res<root.data && root.data<val){
            res=root.data;
        }
        for (Node children : root.child) {
            immediateSmaller(children, val);
        }
    }
    static int lar=0;
    static int secLar=0;
    public static void secLarNode(Node root){
        if(root.data>lar){
            secLar=lar;
            lar=root.data;
        }
        for(Node children:root.child){
            secLarNode(children);
        }
    }

    static int currSum=0;
    static int maxSum=0;
    static Node resNode=null;
    public static void maxSumImmChild(Node root){
        currSum=root.data;
        for (Node children : root.child) {
            currSum+=children.data;
            maxSumImmChild(children);
        }
        if(currSum>maxSum){
            maxSum=currSum;
            resNode=root;
        }
    }

    public static void countNGreaterchildN(Node root){
        int count=0;
        LinkedList<Node> q= new LinkedList<>();
        q.addLast(root);
        while(q.size()>0){
            Node rem=q.removeFirst();
            for(Node children:rem.child){
                if(children.child.size()>rem.child.size()){
                    count++;
                }
                q.addLast(children);
            }
        }
        System.out.println(count);
    }
    
    public static void main(String[] args) {
        int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
            -1 };

        Node root = gTree(arr);
        // display(root);
        // immediateSmaller(root, 50);
        // System.out.println(res);
        // secLarNode(root);
        // System.out.println(secLar);
        // maxSumImmChild(root);
        // System.out.println(maxSum);
        // System.out.println(resNode.data);
        // countNGreaterchildN(root);
    }
}