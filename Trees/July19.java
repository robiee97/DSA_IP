import java.util.*;

public class July19 {
    public static class Node implements Comparable<Node> {
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

        @Override
        public int compareTo(Node o) {
            return this.data - o.data;
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

    public static Node lkrm(Node root){
        Node leftRightMost=root.left;
        while(leftRightMost.right!=null && leftRightMost.right!=root){
            leftRightMost=leftRightMost.right;
        }
        return leftRightMost;
    }
    public static void morrisTravNLR(Node root) { //preorder
        while(root!=null){
            if(root.left==null){
                System.out.print(root.data+" ");
                root=root.right;
            }else{
                Node leftRightMost=lkrm(root);
                if(leftRightMost.right==null){
                    System.out.print(root.data+" ");
                    leftRightMost.right=root;
                    root=root.left;
                }else if(leftRightMost.right==root){
                    leftRightMost.right=null;
                    root=root.right;
                }
            }
        }
    }

    public static void morrisTravLNR(Node root) { //inorder
        while(root!=null){
            if(root.left==null){
                System.out.print(root.data+" ");
                root=root.right;
            }else{
                Node leftRightMost=lkrm(root);
                if(leftRightMost.right==null){
                    // System.out.print(root.data+" ");
                    leftRightMost.right=root;
                    root=root.left;
                }else if(leftRightMost.right==root){
                    System.out.print(root.data+" ");
                    leftRightMost.right=null;
                    root=root.right;
                }
            }
        }
    }

    public static void perfectBT() {
        //lvl order all leafs should come on same lvl
    }

    public static Node prein(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
        if (psi > pei || isi > iei) {
            return null;
        }
        Node node = new Node();
        node.data = pre[psi];
        int x = -1;
        for (x = isi; x <= iei; x++) {
            if (pre[psi] == in[x]) {
                break;
            }
        }
        int lhs = x - isi;
        node.left = prein(pre, psi + 1, psi + lhs, in, isi, x - 1);
        node.right = prein(pre, psi + lhs + 1, pei, in, x + 1, iei);

        return node;
    }

    public static Node postin(int[] pos, int psi, int pei, int[] in, int isi, int iei) {
        if (psi > pei || isi > iei) {
            return null;
        }
        Node node = new Node();
        node.data = pos[pei];
        int x = -1;
        for (x = isi; x <= iei; x++) {
            if (pos[pei] == in[x]) {
                break;
            }
        }
        int lhs = x - isi;
        node.left = postin(pos, psi, psi + lhs - 1, in, isi, x - 1);
        node.right = postin(pos, psi + lhs, pei - 1, in, x + 1, iei);
        return node;
    }

    public static Node prepost(int[] pre, int prsi, int prei, int[] pos, int posi, int poei) {
        if (prsi > prei || posi > poei) {
            return null;
        }
        Node node = new Node();
        node.data = pre[prsi];
        int x = -1;
        for (x = posi; x <= poei; x++) {
            if (pre[prsi + 1] == pos[x]) {
                break;
            }
        }
        int lhs = x - posi;
        node.left = prepost(pre, prsi + 1, prsi + lhs + 1, pos, posi, posi + x);
        node.right = prepost(pre, prsi + lhs + 2, prei, pos, posi + x + 1, poei - 1);
        return node;
    }

    public static void serializationArr(int[] arr) {
        Node root=new Node();
        HashMap<Integer,Node> map =new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]==-1){
                root.data=i;
                root.left=null;
                root.right=null;
                map.put(i, root);
            }else{
                Node node=map.get(arr[i]);
                if(node.left==null){
                    node.left=new Node(i, null, null);
                    map.put(i, node.left);
                }else{
                    node.right=new Node(i, null, null);
                    map.put(i,node.right);
                }
            }
        }
        display(root);
    }

    public static void predSuccMT(Node root, int ele) {
        ArrayList<Integer> list= morrisTraversalInorder(root);
        int i=list.indexOf(ele);
        System.out.println(i==0?"-1":list.get(i-1)+" predeccesor");
        System.out.println(i==list.size()-1?"-1":list.get(i+1)+" successor");
    }

    public static int[] nge(int[] arr){
        Stack<Integer> st= new Stack<>();
        int[] ans= new int[arr.length];
        for(int i=0;i<arr.length;i++){
            while(st.size()>0 && arr[st.peek()]<arr[i]){
                ans[st.peek()]=i;
                st.pop();
            }
            st.push(i);
        }
        while(st.size()>0){
            ans[st.peek()]=-1;
            st.pop();
        }
        return ans;
    }  
    public static void printPOSTusingPRE(int[] pre, int[] nge, int s, int e) {
        if(s>e){
            return;
        }
        int val=pre[s];
        int ngei=nge[s];
        if(ngei!=-1){
            printPOSTusingPRE(pre, nge, s+1, ngei-1);
            printPOSTusingPRE(pre, nge, ngei, e);
        }
        System.out.print(val+" ");
    }

    public static void printNodesBWRanges(Node root, int lo, int hi) {
        if (root == null) {
            return;
        }
        if (root.data >= lo && root.data <= hi) {
            System.out.println(root.data);
        }
        printNodesBWRanges(root.left, lo, hi);
        printNodesBWRanges(root.right, lo, hi);
    }

    public static ArrayList<Integer> morrisTraversalInorder(Node root){
        ArrayList<Integer> ans= new ArrayList<>();
        while(root!=null){
            if(root.left==null){
                ans.add(root.data);
                root=root.right;
            }else{
                Node lkarm= lkrm(root);
                if(lkarm.right==null){
                    lkarm.right=root;
                    root=root.left;
                }else if(lkarm.right==root){
                    ans.add(root.data);
                    lkarm.right=null;
                    root=root.right;
                }
            }
        }
        return ans;
    } 
    public static void commonEleIn2BST(Node root1, Node root2) {
        ArrayList<Integer> bst1=morrisTraversalInorder(root1);
        ArrayList<Integer> bst2=morrisTraversalInorder(root2);
        
        int i = 0;
        int j = 0;
        while (i < bst1.size() && j < bst2.size()) {
            if (bst1.get(i) < bst2.get(j)) {
                i++;
            } else if (bst1.get(i) > bst2.get(j)) {
                j++;
            } else {
                System.out.println(bst1.get(i) + " " + bst2.get(j));
                i++;
                j++;
            }
        }
    }

    public static void printLeafsUsingPRE(int[] pre, int[] nge, int s, int e) {
        if(s>e){
            return;
        }
        int val=pre[s];
        int ngei=nge[s];
        if(ngei!=-1){
            printLeafsUsingPRE(pre, nge, s+1, ngei-1);
            printLeafsUsingPRE(pre, nge, ngei, e);
        }
        if(s==e){
            System.out.print(val+" ");
        }
    }

    public static Node LCABT(Node root, int a,int b) {
        if(root==null){
            return null;
        }
        if(root.data==a||root.data==b){
            return root;
        }
        Node LS=LCABT(root.left, a, b);
        Node RS=LCABT(root.right, a, b);
        if(LS!=null && RS!=null){
            return root;
        }else{
            return LS!=null?LS:RS;
        }
    }

    public static void LCABST(Node root,int a,int b) {
        if(root==null){
            return;
        }
        if(a<root.data && b<root.data){
            LCABST(root.left, a, b);
        }
        if(a>root.data && b>root.data){
            LCABST(root.right, a, b);
        }else{
            System.out.println("LCA "+root.data);
        }
    }

    public static void pthLCA() {
        //root to node path count p from index both ele are same
    }

    public static void main(String[] args) {
        int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1 };
        Node root = construct(arr);
        // display(root);

        // morrisTravNLR(root);
        // morrisTravLNR(root);
        // perfectBT();
        // int[] pre   = { 50, 25, 12, 37, 30, 75, 62, 70, 87 };
        // int[] in    = { 12, 25, 30, 37, 50, 62, 70, 75, 87 };
        // int[] post  = { 12, 30, 37, 25, 70, 62, 87, 75, 50 };
        // prein(pre, 0, pre.length - 1, in, 0, in.length - 1);
        // postin(post, 0, post.length - 1, in, 0, in.length - 1);
        // prepost(pre, 0, pre.length - 1, post, 0, post.length - 1);
        // serializationArr(new int[]{-1,0,0,1,1,2,2,3,3,4,4});
        // predSuccMT(root,12);
        // int nge[]=nge(pre);
        // printPOSTusingPRE(pre, nge, 0, pre.length);
        // printNodesBWRanges(root, 30, 70);
        // commonEleIn2BST(root,root);
        // printLeafsUsingPRE(pre, nge, 0, pre.length);
        // System.out.println(LCABT(root,12,75).data);
        // LCABST(root,12,75);
        // pthLCA();
    }
}