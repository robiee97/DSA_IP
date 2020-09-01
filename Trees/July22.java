import java.util.*;
public class July22{
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

    public static class countPair{
        int sum;
        int count;
    }
    public static countPair countSubTreeSumX(Node root,int x){
        if(root==null){
            countPair bp=new countPair();
            bp.sum=0;
            bp.count=0;
            return bp;
        }    
        countPair l=countSubTreeSumX(root.left,x);
        countPair r=countSubTreeSumX(root.right,x);
        countPair m=new countPair();
        m.sum=l.sum+r.sum+root.data;
        m.count=m.sum==x?l.count+r.count+1:l.count+r.count;
        return m;
    }

    public static class visP{
        int data;
        boolean isv;
        public visP(int data,boolean isv){
            this.data=data;
            this.isv=isv;
        }
        // @Override
        // public String toString() {
        //     return "[d=" + data + ", isv=" + isv + "]";
        // }  
    }
    public static TreeMap<Integer,HashMap<Integer,ArrayList<visP>>> map=new TreeMap<>();
    public static void diagnolTraversal(Node root,int width,int lvl){
        if(root==null){
            return;
        }
        if(map.containsKey(lvl)){
            HashMap<Integer,ArrayList<visP>> hm=map.get(lvl);
            if(hm.containsKey(width)){
                ArrayList<visP> al= hm.get(width);
                al.add(new visP(root.data,false));
                hm.put(width, al);
            }else{
                ArrayList<visP> nal=new ArrayList<>();
                nal.add(new visP(root.data, false));
                hm.put(width,nal);
            }
            map.put(lvl, hm);
        }else{
            HashMap<Integer,ArrayList<visP>> nhm= new HashMap<>();
            ArrayList<visP>nal= new ArrayList<>();
            nal.add(new visP(root.data, false));
            nhm.put(width, nal);
            map.put(lvl, nhm);
        }
        diagnolTraversal(root.left, width-1, lvl+1);
        diagnolTraversal(root.right, width+1, lvl+1);
    }
    // public static void printDia(){
        
    // }

    public static void constructSpecial(int[] data, char[] prop){
        Node root=null;
        Stack<Node> st= new Stack<>();
            for(int i=0;i<data.length;i++){
                Node nn= new Node(data[i], null, null);
                if(i==0){
                    root=nn;
                    st.push(nn);
                    continue;
                }
                if(prop[i]=='N'){
                    if(st.peek().left==null){
                        st.peek().left=nn;
                    }else{
                        st.peek().right=nn;
                    }
                    st.push(nn);
                }else{
                    if(st.peek().left!=null&&st.peek().right!=null){
                        st.pop();
                    }
                    if(st.peek().left==null){
                        st.peek().left=nn;
                    }else{
                        st.peek().right=nn;
                    }
                }
            }
        display(root);
    }
    public static int[][] mat;
    public static void ancestorMat(Node root,String asf){
        if(root.left==null && root.right==null){
            asf+=root.data;
            for(int i=0;i<asf.length()-1;i++){
                int row=asf.charAt(i)-48;
                int col=asf.charAt(asf.length()-1)-48;
                mat[row][col]=1;
            }
            asf=asf.substring(0,asf.length()-1);    
            return;
        }
        asf+=root.data;
        ancestorMat(root.left,asf);
        ancestorMat(root.right,asf);
        asf=asf.substring(0,asf.length()-1);
    }

    public static void constructBTusingParentArr(int[] arr){
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(map.containsKey(arr[i])){
                ArrayList<Integer> al= map.get(arr[i]);
                al.add(i);
                map.put(arr[i],al);
            }else{
                ArrayList<Integer> al= new ArrayList<>();
                al.add(i);
                map.put(arr[i],al);
            }
        }
    }

    public static void dlltoTritree(LinkedList<Integer> list){

    }
    public static int bTtoSumOfLeftSubtree(Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return root.data;
        }
        int l=bTtoSumOfLeftSubtree(root.left);
        int r=bTtoSumOfLeftSubtree(root.right);
        root.data+=l;
        return root.data+r;
    }
    public static void main(String[] args) {
        int[] arr = { 5, -10, 9, -1, 8, -1, -1, 3, -4, -1, 7, -1, -1, -1 };
        // int[] arr = { 5, 1, 0, -1, 4, -1, -1, 2, 3, -1,6,-1, -1, -1 };
        Node root = construct(arr);
        // display(root);`
        // System.out.println(countSubTreeSumX(root,7).count);
        // diagnolTraversal(root,0,0);
        // printDia();
        // int pre[] = {10, 30, 20, 5, 15};
        // char preLN[] = {'N', 'N', 'L', 'L', 'L'};
        // constructSpecial(pre, preLN);
 
        // mat=new int[7][7];
        // ancestorMat(root,"");
        // for (int i = 0; i < mat.length; i++) {
        //     for (int j = 0; j < mat.length; j++) {
        //         System.out.print(mat[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // constructBTusingParentArr(new int[]{1, 5, 5, 2, 2, -1, 3});
        // bTtoSumOfLeftSubtree(root);
        // display(root);
    }
}