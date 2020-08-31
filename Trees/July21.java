import java.util.*;

public class July21 {
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

    static int count=0;
    public static boolean countUnivalueST(Node root) {
        if(root==null){
            return true;
        }

        boolean lans=countUnivalueST(root.left);
        boolean rans=countUnivalueST(root.right);

        if(lans && rans){
            if(root.left!=null &&root.left.data!=root.data){
                return false;
            }
            if(root.right!=null &&root.right.data!=root.data){
                return false;
            }
            count++;
            return true;
        }
        return false;
    }

    static int c = 0;

    public static void countNonLeaf(Node root) {
        if (root.left == null && root.right == null) {
            return;
        }
        c++;
        countNonLeaf(root.left);
        countNonLeaf(root.right);
    }

    static ArrayList<Integer> list = new ArrayList<>();

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root.data);
        inorder(root.right);
    }

    public static void kClosestBSTVal(Node root, int val, int k) {
        inorder(root);
        int i = 0;
        int j = k;
        while (Math.abs(list.get(i) - val) > Math.abs(list.get(j) - val)) {
            i++;
            j++;
        }
        while (i != j) {
            System.out.print(list.get(i) + " ");
            i++;
        }
    }

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void verticleWidth(Node root, int lvl) {
        if (root == null) {
            return;
        }
        min = Math.min(min, lvl);
        max = Math.max(max, lvl);
        verticleWidth(root.left, lvl - 1);
        verticleWidth(root.right, lvl + 1);
    }

    static TreeMap<Integer, Integer> map = new TreeMap<>();

    public static void verticleSum(Node root, int lvl) {
        if (root == null) {
            return;
        }
        map.put(lvl, map.containsKey(lvl) ? map.get(lvl) + root.data : root.data);
        verticleSum(root.left, lvl - 1);
        verticleSum(root.right, lvl + 1);
    }

    public static int uniqueBST(int n) {
        int[] strg= new int[n+1];
        strg[0]=strg[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                strg[i]+=strg[j]*strg[i-1-j];
            }
        }
        return strg[n];
    }

    public static class STPair {
        boolean ST;
        int sum;

        public STPair() {
        }

        public STPair(boolean ST, int sum) {
            this.ST = ST;
            this.sum = sum;
        }
    }

    public static STPair SumTree(Node root) {
        if (root.left == null && root.right == null) {
            return new STPair(true, root.data);
        }
        STPair lp = SumTree(root.left);
        STPair rp = SumTree(root.right);
        STPair mp = new STPair();
        mp.sum = lp.sum + rp.sum + root.data;
        mp.ST = lp.ST && rp.ST && lp.sum + rp.sum == root.data;
        return mp;
    }

    public static Node convertToSumTree(Node root) {
        if(root.left==null&& root.right==null){
            return root;
        }
        root.left=convertToSumTree(root.left);
        root.right=convertToSumTree(root.right);
        root.data=root.left.data+root.right.data;
        return root;
    }

    public static Node mergeTwoTrees(Node root1, Node root2) {
        if(root1==null ||root2==null){
            return null;
        }
        root1.left=mergeTwoTrees(root1.left, root2.left);
        root1.right=mergeTwoTrees(root1.right, root2.right);
        root1.data+=root2!=null?root2.data:0;
        return root1;
    }

    public static boolean isBSTaHeap(Node root) {
        // complete tree && heap order property
        if(root.left==null&&root.right==null){
            return true;
        }
        boolean lans=isBSTaHeap(root.left);
        boolean rans=isBSTaHeap(root.right);
        return lans && rans && 
        root.left.data < root.data && 
        root.right.data < root.data;
    }

    public static boolean isBSTaCT(Node root) {
        // level order -no node should come after null
        boolean flag = false;
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (queue.size() > 0) {
            Node rem = queue.removeFirst();
            if (rem == null) {
                flag = true;
                continue;
            } else {
                if (flag) {
                    System.out.println("Not complete");
                    return false;
                } else {
                    queue.addLast(rem.left);
                    queue.addLast(rem.right);
                }
            }
        }
        System.out.println("complete BT");
        return true;
    }

    public static int depth(Node root, int ele, int lvl) {
        if (root == null) {
            return 0;
        }
        if (root.data == ele) {
            return lvl;
        }
        int l = depth(root.left, ele, lvl + 1);
        if (l != 0) {
            return l;
        }
        int r = depth(root.right, ele, lvl + 1);
        return r;
    }

    public static Node LCABT(Node root, int data1, int data2) {
        if (root == null) {
            return null;
        }
        if (root.data == data1 || root.data == data2) {
            return root;
        }
        Node l = LCABT(root.left, data1, data2);
        Node r = LCABT(root.right, data1, data2);
        if (l != null && r != null) {
            return root;
        } else {
            return l != null ? l : r;
        }
    }

    public static void distBWtoNodes(Node root, int data1, int data2) {
        // depth(a)+depth(b)-2depth(lca)
        Node lca = LCABT(root, data1, data2);
        System.out.println(depth(root, data1, 0) + depth(root, data2, 0) - 2 * (depth(root, lca.data, 0)));
    }

    public static void clonningTree() {

    }

    public static void main(String[] args) {
    //     int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70,
    //     -1, -1, 87, -1, -1, -1 };
        // int[] arr = { 50, 25, -1, 75, -1, -1};
        // int[] arr = { 26, 10, 4, -1, 6, -1, -1, 3, 1, -1, 2, -1, -1, -1 };
        // int[] arr = { 5, 1, 5, -1, 5, -1, -1, 5, 5, -1, -1, -1};

        // Node root = construct(arr);
        // display(root);

        // countUnivalueST(root);
        // System.out.println(count);
        // countNonLeaf(root);
        // System.out.println(c);
        // kClosestBSTVal(root,50,3);
        // verticleWidth(root,0);
        // System.out.println(Math.abs(min-max)+1);
        // verticleSum(root,0);
        // System.out.println(map);
        // System.out.println(uniqueBST(4));
        // System.out.println(SumTree(root).ST);
        // convertToSumTree(root);
        // display(root);
        // display(mergeTwoTrees(root, root));
        // System.out.println(isBSTaHeap(root) && isBSTaCT(root));
        // isBSTaCT(root);
        // distBWtoNodes(root,25,62);
        // clonningTree();
    }
}