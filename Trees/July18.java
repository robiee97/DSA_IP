import java.util.*;

public class July18 {
    public static void main(String[] args) {
        int[] arr = { 50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1 };
        Node root = construct(arr);
        // display(root);

        // nodesAtOddLvl(root);
        // sumOfOddNEvenLvl(root);
        // for(Node i:rootToNodePath(root, 60)){
        //     System.out.println(i.data);
        // }
        // kUPLeaf();
        // kDown(root,null,3);
        // kFar(root,75,2);
        // mirrorBT(root);
        // display(root);
        // minDistbwBSTNodes(root);
        // minDepthBT(root,0);System.out.println(depth);
        // minDepth_In_Pre();
        // maxNodesAtLvl(root);
        // sumNtoL(root);
        // sumLtoL(root);
        // sumNtoN(root);
        // LinkedList<Integer> list= new LinkedList<>();
        // for(int i=1;i<=7;i++){
        //     list.add(i);
        // }
        // LLtoBT(list);
        // BTtoBST(root);
        // longestConsecutiveInTree(root, new LinkedList<>());
        // System.out.println(best);
        // leftMostrightMost(root);
        // BSTPair ans= isBST(root);
        // System.out.println(ans.isBST);
        // BSTPair ans= largestBSTRoot(root);
        //     System.out.println(ans.lbstnode.data+" "+ans.lbstsize);
        // leafAtSameLvl(root,0);
        // leafUnderBudget(root,50);
        // kthLargest(root,4);
        // isSimilar();
        // isMirror();
        // isFoldable();
        // display(transformFromLD(transformToLD(root)));
        // clonningWithRandomPointer();
    }

    public static class Node implements Comparable<Node>{
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
        public int compareTo(Node o){
            return this.data-o.data;
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

    public static class Help {
        Node node;
        int lvl;

        public Help(Node node, int lvl) {
            this.node = node;
            this.lvl = lvl;
        }
    }

    public static void nodesAtOddLvl(Node root) {
        LinkedList<Help> q = new LinkedList<>();
        q.addLast(new Help(root, 0));
        while (q.size() > 0) {
            Help rem = q.removeFirst();
            if (rem.lvl % 2 != 0) {
                System.out.println(rem.node.data + " " + rem.lvl);
            }
            if (rem.node.left != null) {
                q.addLast(new Help(rem.node.left, rem.lvl + 1));
            }
            if (rem.node.right != null) {
                q.addLast(new Help(rem.node.right, rem.lvl + 1));
            }
        }
    }

    public static int esum = 0;
    public static int osum = 0;

    public static void sumOfOddNEvenLvl(Node root) {
        LinkedList<Help> q = new LinkedList<>();
        q.addLast(new Help(root, 0));
        while (q.size() > 0) {
            Help rem = q.removeFirst();
            if (rem.lvl % 2 != 0) {
                osum += rem.node.data;
            } else {
                esum += rem.node.data;
            }
            if (rem.node.left != null) {
                q.addLast(new Help(rem.node.left, rem.lvl + 1));
            }
            if (rem.node.right != null) {
                q.addLast(new Help(rem.node.right, rem.lvl + 1));
            }
        }
        System.out.println("oddsum: " + osum + " ," + "evensum: " + esum);
    }

    public static ArrayList<Node> rootToNodePath(Node root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.data == data) {
            ArrayList<Node> list = new ArrayList<>();
            list.add(root);
            return list;
        }
        ArrayList<Node> lans = rootToNodePath(root.left, data);
        if (lans.size() > 0) {
            lans.add(root);
            return lans;
        }
        ArrayList<Node> rans = rootToNodePath(root.right, data);
        if (rans.size() > 0) {
            rans.add(root);
            return rans;
        }
        return new ArrayList<>();
    }

    public static void kUPLeaf(Node root) {

    }

    public static void kDown(Node root, Node block, int k) {
        if (root == null || root == block || k < 0) {
            return;
        }
        if (k == 0) {
            System.out.print(root.data + " ");
        }
        kDown(root.left, block, k - 1);
        kDown(root.right, block, k - 1);
    }

    public static void kFar(Node root, int data, int k) {
        ArrayList<Node> r2np = rootToNodePath(root, data);
        kDown(r2np.get(0), null, k);
        for (int i = 1; i < r2np.size(); i++) {
            kDown(r2np.get(i), r2np.get(i - 1), k - i);
            System.out.println();
        }
    }

    public static void mirrorBT(Node root) {
        if(root==null){
            return;
        }
        Node temp=root.left;
        root.left=root.right;
        root.right=temp;

        mirrorBT(root.left);
        mirrorBT(root.right);
    }

    public static void minDistbwBSTNodes(Node root) {
        //inorder bst and then two pointer take static to store min dis
    }

    public static int depth=Integer.MAX_VALUE;
    public static void minDepthBT(Node root,int d) {
        if(root.left==null && root.right==null){
            depth=Math.min(depth, d);
            return;
        }
        minDepthBT(root.left, d+1);
        minDepthBT(root.right, d+1);
    }

    public static void minDepth_In_Pre(Node root) {

    }

    public static void maxNodesAtLvl(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        int width=0;
        while (queue.size() > 0) {
            int c = queue.size();
            width=Math.max(width, c);
            while (c-- > 0) {
                Node rem = queue.removeFirst();
                if (rem.left != null) {
                    queue.addLast(rem.left);
                }
                if (rem.right != null) {
                    queue.addLast(rem.right);
                }
            }
        }
        System.out.println(width);
    }

    public static void sumNtoL(Node root) {

    }

    public static void sumLtoL(Node root) {

    }

    public static void sumNtoN(Node root) {

    }

    public static void LLtoBT(LinkedList<Node> list) {
        LinkedList<Node> q= new LinkedList<>();
        Node root=new Node(list.getFirst().data,null,null);
        q.add(root);
        int i=1;
        while(i<list.size()){
            Node rem=q.removeFirst();
            int l=list.get(i).data;
            int r=list.get(i+1).data;
            rem.left=new Node(l, null, null);
            q.add(rem.left);
            rem.right=new Node(r, null, null);
            q.add(rem.right);        
            i+=2;
        }
        display(root);    
    }

    public static LinkedList<Node> BTdll(Node root){
        if(root.left==null && root.right==null){
            LinkedList<Node>base= new LinkedList<>();
            base.addLast(root);
            return base;
        }
        LinkedList<Node> ll=BTdll(root.left);
        LinkedList<Node> rl=BTdll(root.right);
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
    public static void BTtoBST(Node root) {
        //BT to LL
        LinkedList<Node> ll = BTdll(root);
        //LL to SLL (merge sort)
        Collections.sort(ll);
        //SLL to BT
        LLtoBT(ll);
    }

    public static int best=0;
    public static int sum(LinkedList<Integer> list){
        int sum=0;
        for(int i:list){
            sum+=i;
        }
        return sum;
    }
    public static void longestConsecutiveInTree(Node root, LinkedList<Integer>list) {
        if(root==null){
            return;
        }
        best=Math.max(sum(list),best);
        if(list.size()>0){
            if(root.data>list.getLast()){
                list.addLast(root.data);
                longestConsecutiveInTree(root.left, list);
                longestConsecutiveInTree(root.right, list);
                list.removeLast();
            }
        }else{
            LinkedList<Integer> temp=list;
            list.addLast(root.data);
            longestConsecutiveInTree(root.left, list);
            longestConsecutiveInTree(root.right, list);
            list.removeLast();
            list=temp;
        }
    }

    public static void leftMostrightMost(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (queue.size() > 0) {
            int c = queue.size();
            System.out.print(queue.getFirst().data!=queue.getLast().data?
                            queue.getFirst().data+","+queue.getLast().data:
                            queue.getFirst().data);
            while (c-- > 0) {
                Node rem = queue.removeFirst();
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

    public static class BSTPair{
        int min;
        int max;
        boolean isBST;
        Node lbstnode;
        int lbstsize;
    }
    public static BSTPair isBST(Node root) {
        if(root==null){
            BSTPair bp= new BSTPair();
            bp.min=Integer.MAX_VALUE;
            bp.max=Integer.MIN_VALUE;
            bp.isBST=true;
            return bp;
        }
        BSTPair lp=isBST(root.left);
        BSTPair rp=isBST(root.right);
        BSTPair mp=new BSTPair();

        mp.max= Math.max(root.data, Math.max(lp.max, rp.max));
        mp.min= Math.min(root.data, Math.min(lp.min, rp.min));
        mp.isBST= lp.isBST && rp.isBST && root.data>lp.max && root.data<rp.min;
        
        return mp;
    }

    public static BSTPair largestBSTRoot(Node root) {
        if(root==null){
            BSTPair bp= new BSTPair();
            bp.min=Integer.MAX_VALUE;
            bp.max=Integer.MIN_VALUE;
            bp.isBST=true;
            bp.lbstnode=null;
            bp.lbstsize=0;
            return bp;
        }
        BSTPair lp=isBST(root.left);
        BSTPair rp=isBST(root.right);
        BSTPair mp=new BSTPair();

        mp.max= Math.max(root.data, Math.max(lp.max, rp.max));
        mp.min= Math.min(root.data, Math.min(lp.min, rp.min));
        mp.isBST= lp.isBST && rp.isBST && root.data>lp.max && root.data<rp.min;
        
        if(mp.isBST){
            mp.lbstnode=root;
            mp.lbstsize=lp.lbstsize+rp.lbstsize+1;
        }else{
            if(lp.lbstsize>rp.lbstsize){
                mp.lbstnode=lp.lbstnode;
                mp.lbstsize=lp.lbstsize;
            }else{
                mp.lbstnode=rp.lbstnode;
                mp.lbstsize=rp.lbstsize;    
            }
        }
        return mp;
        
    }

    public static void leafAtSameLvl(Node root,int lvl) {
        if(root.left==null && root.right==null){
            System.out.println(root.data+" at "+lvl);
            return;
        }
        leafAtSameLvl(root.left, lvl+1);
        leafAtSameLvl(root.right, lvl+1);
    }

    public static void leafUnderBudget(Node root,int budget) {
        if(root.left==null && root.right==null){
            if(root.data<=budget){
                System.out.println(root.data);
            }
            return;
        }
        leafUnderBudget(root.left, budget);
        leafUnderBudget(root.right, budget);
    }

    public static void kthLargest(Node root ,int k){
        LinkedList<Integer> inorderList=new  LinkedList<>();
        kthLargestSlave(root,inorderList);
        System.out.println(inorderList.get(inorderList.size()-k-1));
    }
    public static void kthLargestSlave(Node root,LinkedList<Integer> list) {
        if(root==null){
            return;
        }
        kthLargestSlave(root.left,list);
        list.add(root.data);
        kthLargestSlave(root.right,list);
    }

    public static boolean isSymmetric(Node root) {
        return isMirror(root, root);
    }

    public static boolean isMirror(Node root1,Node root2) {
        if(root1==null && root2==null){
            return true;
        }if(root1==null || root2==null){
            return false;
        }
        return root1.data==root2.data && isMirror(root1.left, root2.right) &&isMirror(root1.right, root2.left);
    }

    public static void isFoldable(Node root) {

    }

    public static Node transformToLD(Node root) {
        if(root==null){
            return null;
        }
        root.left=transformToLD(root.left);
        root.right=transformToLD(root.right);
        root.left=new Node(root.data,root.left,null);
        return root;
    }

    public static Node transformFromLD(Node root) {
        if(root==null){
            return null;
        }
        root.left=transformFromLD(root.left.left);
        root.right=transformFromLD(root.right);
        return root;
    }

    public static void clonningWithRandomPointer(Node root) {

    }
}