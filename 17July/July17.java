
import java.util.*;

public class July17 {
    public static void main(String[] args) {
        // int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1 };
        // Node root = construct(arr);
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
        sumofKLvlBT(root,2);
        // reverseLvlOrder(root);
        // maxPathSum
        // maxPathSumNL(root);
        // maxPathSumLL(root);
        // maxPathSumNN(root);
        // rootToleafPath(root);
        // pathInRange(root);
        // BTPrune(root);
        // BTTilt(root);
        // rightSideView(root);
        // leftSideView(root);
        // BTdll(root);
        // BTcdll(root);
        // boundariesinBT(root);
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

    public static void sumofKLvlBT(Node root,int k) {

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

    }

    public static void BTTilt(Node root) {

    }

    public static void rightSideView(Node root) {

    }

    public static void leftSideView(Node root) {

    }

    public static void BTdll(Node root) {

    }

    public static void BTcdll(Node root) {

    }

    public static void boundariesinBT(Node root) {

    }
}