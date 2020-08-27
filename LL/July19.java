public class July19 {

	public static class Node {
		int data;
		Node next;
		public Node(int data, Node next) {
			this.data=data;
			this.next=next;
		}
	}
	
	static Node head;
	static Node tail;
	static int size;
	public static void setSize() {
		size=0;
		Node temp=head;
		while(temp!=null) {
			size++;
			temp=temp.next;
		}
	}
	
	public static void display(Node head) {
		for(Node i=head;i!=null;i=i.next) {
			System.out.print(i.data+" -> ");
		}
	}
	
	public static void addLast(int data) {
		Node nn= new  Node(data,null);
		if(head==null) {
			head=tail=nn;
		}else {
			tail.next=nn;
			tail=nn;
		}
	}
	
	
	public static void addFirst(int data) {
		Node nn= new  Node(data,null);
		if(head==null) {
			head=tail=nn;
		}else {
			nn.next=head;
			head=nn;
		}
	}
	
	public static Node getFirst(Node head,Node tail) {
		return head;
	}
	

	public static Node getLast(Node head,Node tail) {
		return tail;
	}
	
	public static Node getAt(Node head, Node tail, int index) {
		int c=0;
		for(Node i=head;i!=null;i=i.next) {
			if(index==c) {
				return i;
			}
			c++;
		}
		return null;
	}
	
	public static Node removeFirst() {
		if(head==null) {
			return null;
		}else {
			Node temp=head;
			head=head.next;
			temp.next=null;
			return temp;
		}
	}

	public static Node removeLast() {
		if(head==null) {
			return null;
		}else {
			Node i=head;
			while(i.next!=tail) {
				i=i.next;
			}
			Node temp=tail;
			tail=i;
			tail.next=null;
			temp.next=null;
			return temp;
		}
	}
	
	public static void dispRev(Node head) {
		if(head==null) {
			return;
		}
		dispRev(head.next);
		System.out.println(head.data);
	}
	
	
	public static void revListRec(Node head) {
		if(head==tail) {
			return;
		}
		revListRec(head.next);
		head.next.next=head;
	}
	
	public static void revListIterSwap(){
		int i=0;
		int j=size-1;
		while(i<j) {
			Node f=getAt(head, tail, i);
			Node s=getAt(head, tail, j);
			i++;
			j--;
			int temp=f.data;
			f.data= s.data;
			s.data=temp;
		}
	}
	
	
	public static void revListIter() {
		Node prev=null;
		Node curr=head;
		while(curr!=null) {
			Node next=curr.next;
			curr.next=prev;
			
			prev=curr;
			curr=next;
		}
		Node temp=head;
		head=tail;
		tail=temp;
	}
	public static void revListRecSwap(Node right, int last) {
		if(right==null) {
			return;
		}
		revListRecSwap(right.next, last+1);
		if(last>=size/2) {
			int temp=right.data;
			right.data=head.data;
			head.data=temp;
			head=head.next;
		}
	}
	
	public static boolean isPal(Node right) {
		if(right==null) {
			return true;
		}
		boolean ans= isPal(right.next);
		ans=ans && head.data==right.data;
		head= head.next;
		return ans;
	}
	
	public static Node midNode() {
		Node slow=head;
		Node fast=head;
		while(fast.next!=null && fast.next.next!=null) {
			slow=slow.next;
			fast=fast.next.next;
		}
		return slow;
	}
	
	public static Node kfrmLast(int k) {
		Node slow=head;
		Node fast=head;
		while(k-->0) {
			fast=fast.next;
		}
		while(fast!=null) {
			slow=slow.next;
			fast= fast.next;
		}
		return slow;
	}
	
	public static void Fold(Node right, int last){
		if(right== null) {
			return;
		}
		Fold(right.next, last+1);
		if(last>size/2) {
			right.next=head.next;
			head.next=right;
			head=head.next.next;
		}
		if(last==size/2) {
			tail=right;
			tail.next=null;
		}
	}
	
	
	public static Node phead;
	public static Node ptail;
	public static Node chead;
	public static Node ctail;
	
	public static void addFirst2(int data) {
		Node nn= new Node(data, null);
		if(chead==null) {
			chead=ctail=nn;
		}else {
			nn.next=chead;
			chead=nn;
		}
	}
	public static void kreverse(int k) {
		while(head!=null && size>=k) {
			for(int i=0;i<k;i++) {
				size--;
				int temp=removeFirst().data;
				addFirst2(temp);
			}
			if(phead==null) {
				phead=chead;
				ptail=ctail;
			}else {
				ptail.next=chead;
				ptail=ctail;
			}
			chead=null;
			ctail=null;
		}
		if(size<k) {
				ptail.next=head;
		}
		display(phead);
	}
	
	
	public static Node oh;
	public static Node ot;
	public static Node eh;
	public static Node et;
	
	public static void addLast3(int data) {
		Node nn= new Node(data, null);
		if(phead==null) {
			phead=ptail=nn;
		}else {
			ptail.next=nn;
			ptail=nn;
		}
	}
	public static void addLast4(int data) {
		Node nn= new Node(data, null);
		if(eh==null) {
			eh=et=nn;
		}else {
			et.next=nn;
			et=nn;
		}
	}
	public static void addLast5(int data) {
		Node nn= new Node(data, null);
		if(oh==null) {
			oh=ot=nn;
		}else {
			ot.next=nn;
			ot=nn;
		}
	}
	public static void segOddEven() {
		while(head!=null) {
			int temp=removeFirst().data;
			if(phead==null || ptail.data!=temp) {
				addLast3(temp);
			}
			if(temp%2==0) {
				addLast4(temp);
			}else {
				addLast5(temp);
			}
		}
		
		if(eh!=null && oh!=null) {
			et.next=oh;
			et=ot;
			display(eh);
		}//either empty list
		System.out.println();
		display(phead);
	}
	
	
	public static void floydCycle() {
		if(head==null) {
			return ;
		}
		Node s=head;
		Node f=head;
		
		while(f.next!=null && f.next.next!=null) {
			s=s.next;
			f=f.next.next;
			
			if(s==f) {
				System.out.println("cycle");
				if(s==head) {
					s.next=null;
				}else {
					f=head;
					while(s.next!=f.next) {
						s=s.next;
						f=f.next;
					}
					s.next=null;
					display(head);
					break;					
				}
			}
		}
	}
	
	
		public static void main(String[] args) {
		// addLast(3);
		// addLast(1);
		// addLast(2);
		// addLast(1);
		// addLast(6);
		// addLast(5);
		// addLast(4);
//		setSize();
		
//		System.out.println(size);
//		addFirst(10);
//		addFirst(20);
//		addFirst(30);
//		addFirst(40);
//		addFirst(50);
		
//		System.out.println(getFirst(head, tail).data);
//		System.out.println(getLast(head, tail).data);
		
//		Node ele=getAt(head, tail, 3);
//		System.out.println(ele!=null?ele.data:"not in range");
		
//		Node rf= removeFirst();
//		Node rf2= removeFirst();

//		System.out.println(rf.data+","+rf2.data);
		
//		Node rl=removeLast();
//		Node rl2=removeLast();
//		System.out.println(rl.data+","+rl2.data);
		
//		display(head);
//		dispRev(head);
		
//		revListRec(head);
//		Node temp=head;
//		head=tail;
//		tail=temp;
//		tail.next=null;
		
//		display(head);
//		revListRecSwap(head, 0);
//		System.out.println();
//		display(head);
	
//		display(head);
//		revListIterSwap();
//		System.out.println();
//		display(head);
		
//		revListIter();
//		display(head);
	
//		Node newHead=head;
//		revListRecSwap(head, 0);
//		display(newHead);
		
//		System.out.println(midNode().data);
		
//		System.out.println(kfrmLast(2).data);
		
//		System.out.println(isPal(head));
	
//		Node nn=head;
//		Fold(head, 0);
//		display(nn);
		
//		kreverse(3);
//		segOddEven();
		
	}
}
