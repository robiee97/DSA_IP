
import java.util.*;

public class July17 {
    public static void main(String[] args) {
        int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1 };
        // int[] arr = { 0, 1, 0, -1, 1, -1, -1, 1, 1, -1, 0, -1, -1};

        Node root = construct(arr);
        // display(root);

        // levelOrder
        // lvlOrderprevLvl(root);
        // lvlOrderdelimiter(root);
        // lvlOrder2Lists(root);
        // lvlOrder_count(root);
        // lvlOrderzigzag(root);
        // avgLvlBT(root);
        // BSTItr(root);
        // SOG(root);
        // vertexCover(root);
        // BTCameras(root);
        // deSerialization();
        // sumofKLvlBT(root,1);
        // reverseLvlOrder(root);
        // maxPathSum
        // maxPathSumNL(root);
        // maxPathSumLL(root);
        // maxPathSumNN(root);
        // rootToleafPath(root);
        // pathInRange(root);
        // BTPrune(root);
        // BTTilt(root);
        // rightSideView(root,0);
        // leftSideView(root,0);
        // BTdll(root);
        // BTcdll(root);
        // boundariesinBT(root);

        // topView(root,0);
        // bottomView(root,0);
        // System.out.println(map);
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

    public static class HelperClass {
        Node node;
        int level;

        public HelperClass() {

        }

        public HelperClass(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public static void lvlOrderprevLvl(Node root) {
        LinkedList<HelperClass> queue = new LinkedList<>();
        queue.addLast(new HelperClass(root, 0));
        int pl = 0;
        while (queue.size() > 0) {
            HelperClass rem = queue.removeFirst();
            if (pl != rem.level) {
                System.out.println();
                pl = rem.level;
            }
            System.out.print(rem.node.data + " ");
            if (rem.node.left != null) {
                queue.addLast(new HelperClass(rem.node.left, rem.level + 1));
            }
            if (rem.node.right != null) {
                queue.addLast(new HelperClass(rem.node.right, rem.level + 1));
            }
        }
    }

    public static void lvlOrderdelimiter(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        queue.add(null);
        while (queue.size() > 1) {
            Node rem = queue.removeFirst();
            if (rem != null) {
                System.out.print(rem.data + " ");
                if (rem.left != null) {
                    queue.addLast(rem.left);
                }
                if (rem.right != null) {
                    queue.addLast(rem.right);
                }
            } else {
                System.out.println();
                queue.addLast(null);
            }
        }
    }

    public static void lvlOrder2Lists(Node root) {
        LinkedList<Node> cl = new LinkedList<>();
        LinkedList<Node> nl = new LinkedList<>();
        cl.addLast(root);
        while (cl.size() > 0) {
            Node rem = cl.removeFirst();
            System.out.print(rem.data + " ");
            if (rem.left != null) {
                nl.addLast(rem.left);
            }
            if (rem.right != null) {
                nl.addLast(rem.right);
            }
            if (cl.size() == 0) {
                System.out.println();
                cl = nl;
                nl = new LinkedList<>();
            }
        }
    }

    public static void lvlOrder_count(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (queue.size() > 0) {
            int c = queue.size();
            while (c-- > 0) {
                Node rem = queue.removeFirst();
                System.out.print(rem.data + " ");
                if (rem.left != null) {
                    queue.addLast(rem.left);
                }
                if (rem.right != null) {
                    queue.addLast(rem.right);
                }
            }
            System.out.println();
        }
    }

    public static void lvlOrderzigzag(Node root) {
        LinkedList<Node> cl = new LinkedList<>();
        LinkedList<Node> nl = new LinkedList<>();
        cl.addFirst(root);
        boolean flag = true;
        while (cl.size() > 0) {
            Node rem = cl.removeFirst();
            System.out.print(rem.data + " ");
            if (flag) {
                if (rem.left != null) {
                    nl.addFirst(rem.left);
                }
                if (rem.right != null) {
                    nl.addFirst(rem.right);
                }
            } else {
                if (rem.right != null) {
                    nl.addFirst(rem.right);
                }
                if (rem.left != null) {
                    nl.addFirst(rem.left);
                }
            }
            if (cl.size() == 0) {
                System.out.println();
                cl = nl;
                nl = new LinkedList<>();
                flag = !flag;
            }
        }
    }

    public static void avgLvlBT(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (queue.size() > 0) {
            int c = queue.size();
            int ele = c;
            int sum = 0;
            while (c-- > 0) {
                Node rem = queue.removeFirst();
                sum += rem.data;
                if (rem.left != null) {
                    queue.addLast(rem.left);
                }
                if (rem.right != null) {
                    queue.addLast(rem.right);
                }
            }
            System.out.println(sum / ele);
        }
    }

    public static void BSTItr(Node root) {

    }

    public static int SOGSUM = 0;

    public static void SOG(Node root) {
        if (root == null) {
            return;
        }
        SOG(root.right);
        int temp = root.data;
        root.data += SOGSUM;
        SOGSUM += temp;
        SOG(root.left);
    }

    public static void vertexCover(Node root) {

    }

    public static void BTCameras(Node root) {

    }

    public static class DeSerHelper {
        Node node;
        int level;
        int state;

        public DeSerHelper() {
        }

        public DeSerHelper(Node node, int level, int state) {
            this.node = node;
            this.level = level;
            this.state = state;
        }
    }

    public static void deSerialization() {
        String str = "(5(2(1()())(3()()))(7(6()())(8()())))"; // (NODE(LST)(RST))
        LinkedList<DeSerHelper> stack = new LinkedList<>();
        Node root = null;
        int s = 0;
        int e = 1;
        while (e < str.length()) {
            if (str.charAt(s) == '(') {
                if (Character.isDigit(str.charAt(e))) {
                    Node node = new Node((int) (str.charAt(e) - '0'), null, null);
                    if (stack.size() == 0) {// 1st level
                        DeSerHelper freshPair = new DeSerHelper(node, 1, 0);
                        stack.addFirst(freshPair);
                        root = node;
                    } else {
                        DeSerHelper topPair = stack.removeFirst();
                        if (topPair.state == 0) {
                            topPair.node.left = node;
                        } else if (topPair.state == 1) {
                            topPair.node.right = node;
                        }
                        topPair.state++;
                        stack.addFirst(topPair);
                        stack.addFirst(new DeSerHelper(node, topPair.level + 1, 0));
                    }
                } else {
                    // state increases with no values
                    DeSerHelper topPair = stack.removeFirst();
                    topPair.state++;
                    stack.addFirst(topPair);
                }
                e += 2;
                s += 2;
            } else if (str.charAt(s) == ')') {
                stack.removeFirst();
                s++;
                e++;
            }
        }
        display(root);
    }

    public static void sumofKLvlBT(Node root, int k) {
        LinkedList<HelperClass> q = new LinkedList<>();
        q.addLast(new HelperClass(root, 0));
        int sum = 0;
        while (q.size() > 0) {
            HelperClass rem = q.removeFirst();
            if (rem.level == k) {
                sum += rem.node.data;
            }
            if (rem.node.left != null) {
                q.addLast(new HelperClass(rem.node.left, rem.level + 1));
            }
            if (rem.node.right != null) {
                q.addLast(new HelperClass(rem.node.right, rem.level + 1));
            }
        }
        System.out.println(sum);
    }

    public static void reverseLvlOrder(Node root) {

    }

    public static void maxPathSumNL(Node root) {

    }

    public static void maxPathSumLL(Node root) {

    }

    public static void maxPathSumNN(Node root) {

    }

    public static void rootToleafPath(Node root) {

    }

    public static void pathInRange(Node root) {

    }

    public static void BTPrune(Node root) {
        Node ans = BTPruneSlave(root);
        display(ans);
    }

    public static Node BTPruneSlave(Node root) {
        if (root.left == null && root.right == null && root.data == 0) {
            return null;
        }
        if (root.left == null && root.right == null && root.data == 1) {
            return root;
        }
        if (root.left != null) {
            Node la = BTPruneSlave(root.left);
            root.left = la;
        }
        if (root.right != null) {
            Node ra = BTPruneSlave(root.right);
            root.right = ra;
        }
        return root;
    }

    public static int tilt = 0;

    public static void BTTilt(Node root) {
        int ans = BTTiltSlave(root);
        System.out.println(tilt);
    }

    public static int BTTiltSlave(Node root) {
        if (root == null) {
            return 0;
        }
        int ls = BTTiltSlave(root.left);
        int rs = BTTiltSlave(root.right);
        int rv = ls + rs + root.data;
        tilt += Math.abs(ls - rs);
        return rv;
    }

    public static int ml = -1;

    public static void rightSideView(Node root, int lvl) {
        if (lvl > ml) {
            System.out.print(root.data + " ");
            ml = lvl;
        }
        if (root.right != null) {
            rightSideView(root.right, lvl + 1);
        }
        if (root.left != null) {
            rightSideView(root.left, lvl + 1);
        }
    }

    public static void leftSideView(Node root,int lvl) {
        if (lvl > ml) {
            System.out.print(root.data + " ");
            ml = lvl;
        }
        if (root.left != null) {
            leftSideView(root.left, lvl + 1);
        }
        if (root.right != null) {
            leftSideView(root.right, lvl + 1);
        }
    }

    public static void BTdll(Node root) {
        LinkedList<Node> ans=BTdllSlave(root);
        for (Node i : ans) {
            System.out.print(i.data+ " ");
        }
    }

    public static LinkedList<Node> BTdllSlave(Node root){
        if(root.left==null && root.right==null){
            LinkedList<Node>base= new LinkedList<>();
            base.addLast(root);
            return base;
        }
        LinkedList<Node> ll=BTdllSlave(root.left);
        LinkedList<Node> rl=BTdllSlave(root.right);
        LinkedList<Node> ml=new LinkedList<>();
        for (Node node : ll) {
            ml.addLast(node);
        }
        ml.add(root);
        for (Node node : rl) {
            ml.add(node);
        }
        return ml;
    }

    public static class Block{
        int data;
        Block next;
        public Block(int data, Block next){
            this.data=data;
            this.next=next;
        }
    }
    public static class CLL{
        public Block head;
        public Block tail;
        public CLL(){
            head=null;
            tail=null;
        }
        void addLast(int data){
            Block b=new Block(data,null);
            if(head==null){
                head=tail=b;
            }else{
                tail.next=b;
                tail=b;
            }
        }
    }

    public static void BTcdll(Node root) {
        CLL cll=BTcdllSlave(root);
        // cll.tail.next=cll.head;
        while(cll.head!=null){
            System.out.print(cll.head.data+"->");
            cll.head=cll.head.next;
        }
    }

    public static CLL BTcdllSlave(Node root){
        if(root.left==null && root.right==null){
            CLL baseCLL=new CLL();
            baseCLL.addLast(root.data);
            return baseCLL;
        }
        CLL ll=BTcdllSlave(root.left);
        CLL rl=BTcdllSlave(root.right);
        CLL ml=new CLL();
        ml.addLast(root.data);
        if(ll.head!=null){
            ll.tail.next=ml.head;
            ml.head=ll.head;
        }
        if(rl.head!=null){
            ml.tail.next=rl.head;
            ml.tail=rl.tail;
        }
        return ml;
    }

    public static void boundariesinBT(Node root) {

    }
    
    public static TreeMap<Integer,Integer> map = new TreeMap<>();
    public static void topView(Node root,int timeLine){
        if(root==null){
            return;
        }
        if(!map.containsKey(timeLine)){
            map.put(timeLine,root.data);
        }
        topView(root.left,timeLine-1);
        topView(root.right,timeLine+1);
    }
    public static void bottomView(Node root,int timeLine){
        if(root==null){
            return;
        }
        map.put(timeLine,root.data);
        bottomView(root.left,timeLine-1);
        bottomView(root.right,timeLine+1);
    }
}