import java.util.*;

public class Stacks {

	public static boolean validParenthesis(String exp) {
		Stack<Character> st = new Stack<Character>();

		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i) == '[' || exp.charAt(i) == '{' || exp.charAt(i) == '(') {
				st.push(exp.charAt(i));
			} else if (exp.charAt(i) == ']') {
				if (st.size() == 0 || st.peek() != '[') {
					return false;
				} else {
					st.pop();
				}
			} else if (exp.charAt(i) == '}') {
				if (st.size() == 0 || st.peek() != '{') {
					return false;
				} else {
					st.pop();
				}
			} else if (exp.charAt(i) == ')') {
				if (st.size() == 0 || st.peek() != '(') {
					return false;
				} else {
					st.pop();
				}
			}
		}
		if (st.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static int[] nextGreaterEle(int[] arr) {
		int[] ans = new int[arr.length];
		Stack<Integer> st = new Stack<Integer>();
		for (int i = 0; i < arr.length; i++) {
			while (st.size() > 0 && arr[st.peek()] < arr[i]) {
				ans[st.peek()] = arr[i];
				st.pop();
			}
			st.push(i);
		}
		while (st.size() > 0) {
			ans[st.peek()] = -1;
			st.pop();
		}
		return ans;
	}

	public static int largestAreaHistogram(int[] arr) {
		int[] rb = new int[arr.length];
		int[] lb = new int[arr.length];

		Stack<Integer> st = new Stack<Integer>();

		for (int i = 0; i < arr.length; i++) {
			while (st.size() > 0 && arr[st.peek()] > arr[i]) {
				rb[st.peek()] = i;
				st.pop();
			}
			st.push(i);
		}
		while (st.size() > 0) {
			rb[st.peek()] = arr.length;
			st.pop();
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			while (st.size() > 0 && arr[st.peek()] > arr[i]) {
				lb[st.peek()] = i;
				st.pop();
			}
			st.push(i);
		}
		while (st.size() > 0) {
			lb[st.peek()] = arr.length;
			st.pop();
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int width = lb[i] - rb[i] - 1;
			int area = arr[i] * width;

			max = Math.max(max, area);
		}
		return max;
	}

	public static int[] maxInSW(int[] arr, int k) {
		int[] nge = new int[arr.length];
		Stack<Integer> st = new Stack<Integer>();
		for (int i = 0; i < arr.length; i++) {
			while (st.size() > 0 && arr[st.peek()] < arr[i]) {
				nge[st.peek()] = i;
				st.pop();
			}
			st.push(i);
		}
		while (st.size() > 0) {
			nge[st.peek()] = arr.length;
			st.pop();
		}

		int i = 0;
		int j = 0;

		int[] res = new int[arr.length - k + 1];

		while (i < res.length) {
			if (i > j) {
				j = i;
			}

			while (nge[j] < i + k) {
				j = nge[j];
			}
			res[i] = arr[j];
			i++;
		}
		return res;

	}

	public static void dididi(String exp) {
		int val = 1;
		Stack<Integer> st = new Stack<Integer>();
		for (int i = 0; i < exp.length(); i++) {
			st.push(val++);
			if (st.size() == 0 || exp.charAt(i) == 'i') {
				while (st.size() > 0) {
					System.out.print(st.peek() + " ");
					st.pop();
				}
			}
		}
		while (st.size() > 0) {
			System.out.print(st.peek());
			st.pop();
		}
	}

	public static class Inv implements Comparable<Inv> {
		int start;
		int end;

		public Inv(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Inv o) {
			return this.start - o.start;
		}
	}

	public static void mergeOverLapInt(int[] starts, int[] ends) {
		Inv[] intervals = new Inv[starts.length];
		for (int i = 0; i < intervals.length; i++) {
			intervals[i] = new Inv(starts[i], ends[i]);
		}
		Arrays.sort(intervals);
		Stack<Inv> st = new Stack<>();
		for (int i = 0; i < intervals.length; i++) {
			if (st.size() == 0) {
				st.push(intervals[i]);
			} else if (st.peek().end > intervals[i].start) {
				Inv rem = st.peek();
				st.pop();
				rem.end = Math.max(rem.end, intervals[i].end);
				st.push(rem);
			} else {
				st.push(intervals[i]);
			}
		}
		while (st.size() > 0) {
			System.out.println(st.peek().start + "-" + st.peek().end);
			st.pop();
		}
	}

	// infix
	public static int getValue(int v1, int v2, char op) {
		switch (op) {
		case '+':
			return v1+v2;
		case '-':
			return v1-v2;
		case '*':
			return v1*v2;
		
		case '/':
			return v1/v2;
		
		case '%':
			return v1%v2;
		
		case '^':
			return (int) Math.pow(v1,v2);
		
		default: return -1;

		}
	}
	public static int getPriority(char op) {
		switch (op) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':	
		case '%':
			return 2;
		case '^':
			return 3;
		default: return -1;
		}		
	}
	public static void infix123(String exp) {
		Stack<Integer> vs = new Stack<Integer>();
		Stack<Character> os = new Stack<Character>();

		Stack<String> pre= new Stack<String>();
		Stack<String> post= new Stack<String>();
		
		for (int i = 0; i < exp.length(); i++) {
			char ch = exp.charAt(i);
			if (ch >= '0' && ch <= '9') {
				vs.push(ch-48);
				
				
				pre.push(Integer.toString(ch-48));
				post.push(Integer.toString(ch-48));
			
			}else if(ch=='(') {
				os.push(ch);
			}else if(ch==')') {
				while(os.peek()!='(') {
					int v2=vs.peek();vs.pop();
					int v1=vs.peek();vs.pop();
					char op=os.peek();os.pop();
					int res=getValue(v1,v2,op);
					vs.push(res);
			
					String pre2=pre.peek();pre.pop();
					String pre1=pre.peek();pre.pop();
					pre.push(op+pre1+pre2);
					
					String post2=post.peek();post.pop();
					String post1=post.peek();post.pop();
					post.push(post1+post2+op);
					
				}
				os.pop();
			}else {
				while(os.size()>0 && os.peek()!='(' && getPriority(os.peek())>=getPriority(ch)) {
					int v2=vs.peek();vs.pop();
					int v1=vs.peek();vs.pop();
					char op=os.peek();os.pop();
					int res=getValue(v1,v2,op);
					vs.push(res);
					
					String pre2=pre.peek();pre.pop();
					String pre1=pre.peek();pre.pop();
					pre.push(op+pre1+pre2);
					
					String post2=post.peek();post.pop();
					String post1=post.peek();post.pop();
					post.push(post1+post2+op);
					
				}
				os.push(ch);
			}
		}
		while(os.size()!=0) {
			int v2=vs.peek();vs.pop();
			int v1=vs.peek();vs.pop();
			char op=os.peek();os.pop();
			int res=getValue(v1,v2,op);
			vs.push(res);
			
			String pre2=pre.peek();pre.pop();
			String pre1=pre.peek();pre.pop();
			pre.push(op+pre1+pre2);
			
			String post2=post.peek();post.pop();
			String post1=post.peek();post.pop();
			post.push(post1+post2+op);
			
		}

		System.out.println("preorder : "+pre.peek());
		System.out.println("postorder : "+post.peek());
		System.out.println(vs.peek());
	}

	// prefix
	public static void prefix123(String exp) {
		Stack<Integer> vs = new Stack<Integer>();

		Stack<String> post= new Stack<String>();
		Stack<String> in= new Stack<String>();
		
		for (int i = exp.length()-1; i >=0 ; i--) {
			char ch = exp.charAt(i);
			if (ch >= '0' && ch <= '9') {
				vs.push(ch-48);
				
				in.push(Integer.toString(ch-48));
				post.push(Integer.toString(ch-48));
			
			}else {
				int v2=vs.peek();vs.pop();
				int v1=vs.peek();vs.pop();
				int res=getValue(v2,v1,ch);
				vs.push(res);
				
				String in2=in.peek();in.pop();
				String in1=in.peek();in.pop();
				in.push(in2+ch+in1);
				
				String post2=post.peek();post.pop();
				String post1=post.peek();post.pop();
				post.push(post2+post1+ch);
			}
		}
		System.out.println("inorder : "+in.peek());
		System.out.println("postorder : "+post.peek());
		System.out.println(vs.peek());
	}

	// postfix
	public static void postfix123(String exp) {
		Stack<Integer> vs = new Stack<Integer>();

		Stack<String> pre= new Stack<String>();
		Stack<String> in= new Stack<String>();
		
		for (int i = 0; i <exp.length() ; i++) {
			char ch = exp.charAt(i);
			if (ch >= '0' && ch <= '9') {
				vs.push(ch-48);
				
				in.push(Integer.toString(ch-48));
				pre.push(Integer.toString(ch-48));
			
			}else {
				int v2=vs.peek();vs.pop();
				int v1=vs.peek();vs.pop();
				int res=getValue(v1,v2,ch);
				vs.push(res);
				
				String in2=in.peek();in.pop();
				String in1=in.peek();in.pop();
				in.push(in1+ch+in2);
				
				String pre2=pre.peek();pre.pop();
				String pre1=pre.peek();pre.pop();
				pre.push(ch+pre1+pre2);
			}
		}
		System.out.println("inorder : "+in.peek());
		System.out.println("preorder : "+pre.peek());
		System.out.println(vs.peek());
	}

	// exp tree

	public static class Node {
		char data;
		Node left;
		Node right;
		public Node(char data, Node left, Node right) {
			this.data=data;
			this.left=left;
			this.right=right;
		}
	}
	public static void display(Node root) {
		if(root == null) {
			return;
		}
		String str="";
		str+=root.left!=null?root.left.data+" -> ":".->";
		str+=root.data;
		str+=root.right!=null?" <- "+root.right.data:" <-. ";
		System.out.println(str);
		display(root.left);
		display(root.right);
	}
	public static void expressionTree(String exp) {
		Stack<Node> vs= new Stack<>();
		Stack<Node> os= new Stack<>();
		
		for(int i=0;i<exp.length();i++) {
			char ch=exp.charAt(i);
			if(ch=='('|| ch==')') {
				continue;
			}else if(ch>='0' && ch<='9') {
				vs.push(new Node(ch, null, null));
			}else {
				while(os.size()>0 && getPriority(os.peek().data)>=getPriority(ch)) {
					Node v2=vs.peek();vs.pop();
					Node v1=vs.peek();vs.pop();
					Node op=os.peek();os.pop();
					op.left=v1;
					op.right=v2;
					vs.push(op);
				}
				os.push(new Node(ch, null, null));
			}
		}
		while(os.size()>0) {
			Node v2=vs.peek();vs.pop();
			Node v1=vs.peek();vs.pop();
			Node op=os.peek();os.pop();
			op.left=v1;
			op.right=v2;
			vs.push(op);
		}
		display(vs.peek());
	}
	
	public static class MinStack{
		static int min;
		Stack<Integer> st;
		public MinStack() {
			min=Integer.MAX_VALUE;
			st= new Stack<Integer>();
		}
		void push(int val) {
			if(st.size()==0) {
				min=val;
				st.push(val);
			}else if(val>=min) {
				st.push(val);
			}else {
				st.push(val+(val-min));
				min=val;
			}
		}
		void pop() {
			if(st.size()==0) {
				System.out.println("underflow");
				return;
			}else if(st.peek()>=min) {
				st.pop();
			}else {
				min=(2*min)-st.peek();
				st.pop();
			}
		}
		int top() {
			if(st.size()==0) {
				return -1;
			}else if(st.peek()>=min) {
				return st.peek();
			}else {
				return min;
			}
		}
		int min() {
			return min;
		}
	}
	
	public static class MinStack2{
		Stack<Integer> vs;
		Stack<Integer> ms;
		public MinStack2() {
			ms=new Stack<Integer>();
			vs= new Stack<Integer>();
		}
		void push(int val) {
			vs.push(val);
			if(ms.size()==0 || val<=ms.peek()) {
				ms.push(val);
			}
		}
		void pop() {
			if(vs.peek()==ms.peek()) {
				ms.pop();
			}
			vs.pop();
		}
		int top() {
			return vs.peek();			
		}
		int min() {
			return ms.peek();
		}
	}
	
	public static class DLL{
		int data;
		DLL prev;
		DLL next;
		public DLL(int data,DLL prev,DLL next) {
			this.data=data;
			this.prev=prev;
			this.next=next;
		}
	}
	
	public static class MyStack{
		DLL head;
		DLL mid;
		int c;
		public MyStack() {
			head=null;
			mid=null;
			c=0;
		}
		void push(int val) {
			DLL nn = new DLL(val, null, head);
			c++;
			if(c==1) {
				mid=nn;
			}else {
				head.prev=nn;
				if(c%2!=0) {
					mid=mid.prev;
				}
			}
			head=nn;
		}
		void pop() {
			if(c==0) {
				System.out.println("underflow");
				return ;
			}
		
			head=head.next;
			if(head!=null){				
				head.prev=null;
			}
			c--;
			
			if(c%2==0) {
				mid = mid.next;
			}
		}
		int getMid() {
			if(c==0) {
				return -1;
			}
			return mid.data;
		}
	}
	
	public static class StackPQ{
		class PairPQ implements Comparable<PairPQ>{
			int data;
			int time;
			public PairPQ(int data,int time) {
				this.data=data;
				this.time=time;
			}
			@Override
			public int compareTo(PairPQ o) {
				return o.time-this.time;
			}
		}
		PriorityQueue<PairPQ> pq;
		int c;
		public StackPQ() {
			pq= new PriorityQueue<PairPQ>();
			c=1;
		}
		void push(int val) {
			pq.add(new PairPQ(val, c));
			c++;
		}
		void pop() {
			if(pq.size()==0) {
				System.out.println("underflow");
				return;
			}
			pq.remove();
		}
		int top() {
			if(pq.size()==0) {
				return -1;
			}
			return pq.peek().data;
		}
	}
	
	public static void maxInMinInEverySW(int[] arr) {
		int[] left=new int[arr.length];
		int[] right= new int[arr.length];
		
		Stack<Integer> st=new Stack<Integer>();
		
		for(int i=0;i<arr.length;i++) {
			while(st.size()>0 && arr[st.peek()]>arr[i]) {
				right[st.peek()]=i;
				st.pop();
			}
			st.push(i);
		}
		while(st.size()>0) {
			right[st.peek()]=arr.length;
			st.pop();
		}
		
		for(int i=arr.length-1;i>=0;i--) {
			while(st.size()>0 && arr[st.peek()]>arr[i]) {
				left[st.peek()]=i;
				st.pop();
			}
			st.push(i);
		}
		while(st.size()>0) {
			left[st.peek()]=-1;
			st.pop();
		}
		int[] res= new int[arr.length];
		for(int i=0;i<arr.length;i++) {
			int len=right[i]-left[i]-1;
			res[len-1]=Math.max(res[len-1],arr[i]);
		}
		int max=Integer.MIN_VALUE;
		for(int i=res.length-1;i>=0;i--) {
			max=Math.max(max, res[i]);
			res[i]=max;
		}
		
		for(int i:res) {
			System.out.print(i+" ");
		}
	}
	public static int longestValSS(String exp) {
		int res=0;
		Stack<Integer> st= new Stack<Integer>();
		st.push(-1);
		for(int i=0;i<exp.length();i++) {
			if(exp.charAt(i)=='(') {
				st.push(i);
			}else {
				st.pop();
				if(st.size()>0) {
					res=Math.max(res, i-st.peek());
				}else {
					st.push(i);
				}
			}
		}
		return res;
	}
	public static void sortInsert(Stack<Integer> st, int x) {
		if(st.size()==0||x>st.peek()) {
			st.push(x);
			return;
		}
		int temp=st.pop();
		sortInsert(st, x);
		st.push(temp);
	}
	public static void sortStack(Stack<Integer> st) {
		if(st.size()!=0) {
			int x=st.pop();
			sortStack(st);
			sortInsert(st,x);
		}
	}
	
	public static class PPpair{
		int oil;
		int dis;
		public PPpair(int oil ,int dis) {
			this.dis=dis;
			this.oil=oil;
		}
	}
	public static int findTour(PPpair[] arr) {
		int i=0;
		int j=0;
		int spare=0;
		
		while(true) {
			if(spare+arr[j].oil>=arr[j].dis) {
				spare=spare+arr[j].oil-arr[j].dis;
				j=(j+1)%arr.length;
				if(i==j) {
					return j;
				}
			}else {
				if(j<i) {
					return -1;
				}else {
					spare=0;
					i=(j+1)%arr.length;
					j=i;
				}
			}
		}
	}

	public static void firstNonRepeatingQ(String exp){
		LinkedList<Character> q= new LinkedList<>();
		int[] charArray= new int[26];
		for(int i=0;i<exp.length();i++){
			q.addLast(exp.charAt(i));
			charArray[exp.charAt(i)-'a']++;
			while(q.size()>0){
				if(charArray[q.getFirst()-'a']>1){
					q.removeFirst();
				}else{
					System.out.print(q.removeFirst()+" ");
					break;
				}
				if(q.size()==0){
					System.out.print("-1 ");
				}
			}
		}
	}

	public static int countPerOfGreaterThanN(int n){
		int res=0;
		for(int i=1;i<=9;i++){
			Stack<Integer> st= new Stack<>();
			st.push(i);
			if(i<=n){
				res++; 
			}
			while(st.size()>0){
				int rem=st.pop();
				for(int j=rem%10;j<=9;j++){
					int ans=rem*10+j;
					if(ans<=n){
						st.push(ans);
						res++;
					}
				}
			}
		}
		return res;
	}
	
	//permutations -inp(pop)-stack(push),if-st(top)==out(top),both(pop)

	public static int sumOfMinMax(int[] arr,int k){
		int[] nge=new int[arr.length];
		int[] nse=new int[arr.length];
		Stack<Integer>st= new Stack<>();
		for(int i=0;i<arr.length;i++){
			while(st.size()>0 && arr[st.peek()]<arr[i]){
				nge[st.peek()]=i;
				st.pop();
			}
			st.push(i);
		} 
		while(st.size()>0){
			nge[st.peek()]=arr.length;
			st.pop();
		}
		for(int i=0;i<arr.length;i++){
			while(st.size()>0 && arr[st.peek()]>arr[i]){
				nse[st.peek()]=i;
				st.pop();
			}
			st.push(i);
		} 
		while(st.size()>0){
			nse[st.peek()]=arr.length;
			st.pop();
		}
		int res=0;
		int i=0;
		while(i<arr.length-k+1){
			int j=i;
			int l=i;
			while(nge[j]<i+k){
				j=nge[j];
			}
			while(nse[l]<i+k){
				l=nse[l];
			}
			res+=arr[j]+arr[l];
			i++;
		}
		return res;
	}

	public static class LRU{
		static Deque<Integer> dq;
		static HashMap<Integer,Integer> hm; 
		static int size=0;
		public LRU(int N) {
			dq=new LinkedList<>();
			hm=new HashMap<>();
			size=N;
		}
		public static int get(int x) {
			if(!hm.containsKey(x)){
				return -1;
			}else{
			   dq.remove(x);
			   dq.addFirst(x);
			   return hm.get(x);
			}
		}
		public static void set(int x, int y) {
			if(!hm.containsKey(x)){
				if(dq.size()>=size){
					int peek=dq.peekLast();
					dq.removeLast();
					hm.remove(peek);
				}
				dq.addFirst(x);
				hm.put(x,y);
			}else{
			   dq.remove(x);
			   dq.addFirst(x);
			  hm.put(x,y);
			}
		}
	}

	public static class Kstacks{
		static int[]data;
		static int[]next;
		static int[]top;
		static int free;
		public Kstacks(int n, int k){
			data= new int[n];
			next= new int[n];
			top= new int[k];
			free=0;
			for(int i=0;i<k;i++){
				top[i]=-1;
			}
			for(int i=0;i<n-1;i++){
				next[i]=i+1;
			}
			next[n-1]=-1;
		}
		void push(int val,int s){
			if(free==-1){
				return;
			}
			int currInd=free;
			free=next[currInd];
			next[currInd]=top[s];
			top[s]=currInd;
			data[currInd]=val;
		}
		int pop(int s){
			if(top[s]==-1){
				return -1;
			}
			int currInd=top[s];
			int val= data[currInd];
			top[s]= next[currInd];
			next[currInd]=free;
			free=currInd;
			return val;
		}
	}
	
	public static void main(String[] args) {		
//		System.out.println(validParenthesis("()())))()()(()"));
// 		nextGreaterEle();
// 		maxInSW();
// 		largestAreaHistogram();
// 		mergeOverLapInt();
//		dididi("iddiddii");
//		dididi("ddidddid");
		
//		preorder : +8^3/4-32
//		postorder : 83432-/^+
//		89

//		infix123("8+3^(4/(3-2))");
//		prefix123("+8^3/4-32");
//		postfix123("83432-/^+");

//		expressionTree("8+4-2^(3+9/3)");
		
//		MinStack ms= new MinStack();
//		ms.push(10);
//        ms.push(2);
//        ms.push(18);
//        ms.push(1);
//        ms.push(56);
//        while (ms.st.size() > 0) {
//            int minval= ms.min();
//            int val = ms.top();ms.pop();
//            System.out.println(val + " " + minval);
//        }
//		MyStack ms= new MyStack();
//		ms.push(11);
//		ms.push(22);
//		ms.push(33);
//		ms.push(44);
//		ms.push(55);
//		ms.push(66);
//		ms.push(77);
//		System.out.println(ms.getMid());
//		ms.pop();
//		ms.pop();
//		System.out.println(ms.getMid());
	
//		StackPQ spq= new StackPQ();
//		spq.push(10);
//		spq.push(2);
//		spq.push(18);
//		spq.push(1);
//		spq.push(56);
//		while(spq.pq.size()!=0) {
//			System.out.println(spq.top());
//			spq.pop();
//		}
//		int[] arr= {10,20,30,50,10,70,30};
//		maxInMinInEverySW(arr);
		
//		System.out.println(longestValSS("()(())))))"));
		
//		Stack<Integer> s= new Stack<Integer>();
//		s.push(30); 
//        s.push(-5); 
//        s.push(18); 
//        s.push(14); 
//        s.push(-3); 
//       sortStack(s);
//       System.out.println(s);

		// PPpair[] arr={new PPpair(6, 5),
		// new PPpair(4, 5),new PPpair(8, 2),
		// new PPpair(2, 4),new PPpair(3, 4),
		// new PPpair(3, 6),new PPpair(1, 2),
		// new PPpair(7, 7),new PPpair(1, 1)};

		// PPpair[] arr={new PPpair(6,4),new PPpair(3,6),new PPpair(7,3)};
		// System.out.println(findTour(arr));

		// firstNonRepeatingQ("aabc");
		// System.out.println(countPerOfGreaterThanN(100));

		// int[] arr={2,5,-1,7,-3,-1,-2};
		// System.out.println(sumOfMinMax(arr,4));
			
		// LRU cache= new LRU(2); 
        // cache.set(1, 10);  
        // cache.set(2, 20);  
        // System.out.println(cache.get(1));
        // cache.set(3, 30);  
        // System.out.println(cache.get(2)); 
        // cache.set(4, 40);  
        // System.out.println(cache.get(1));
        // System.out.println(cache.get(3)); 
		// System.out.println(cache.get(4)); 
		

			// Kstacks ks= new Kstacks(10,3);
			// ks.push(15, 2); 
			// ks.push(45, 2); 
	  		// ks.push(17, 1); 
			// ks.push(49, 1); 
			// ks.push(39, 1); 
			// ks.push(11, 0); 
			// ks.push(9, 0); 
			// ks.push(7, 0); 
			// System.out.println("Popped element from stack 2 is " + ks.pop(2)); 
			// System.out.println("Popped element from stack 1 is " + ks.pop(1)); 
			// System.out.println("Popped element from stack 0 is " + ks.pop(0)); 
	}
}
