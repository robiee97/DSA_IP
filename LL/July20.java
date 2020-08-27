
import java.util.LinkedList;
import java.util.PriorityQueue;

public class July20 {
	public static class Node implements Comparable<Node>{
		int data;
		Node next;

		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		@Override
		public int compareTo(Node other) {
			return this.data-other.data;
		}
	}

	static Node head;
	static Node tail;
	static int size;
	static Node head2;
	static Node tail2;
	static int size2;

	public static void setSize() {
		size = 0;
		Node temp = head;
		while (temp != null) {
			size++;
			temp = temp.next;
		}
	}

	public static void display(Node head) {
		for (Node i = head; i != null; i = i.next) {
			System.out.print(i.data + " -> ");
		}
		System.out.println();
	}

	public static void addLast(int data) {
		Node nn = new Node(data, null);
		if (head == null) {
			head = tail = nn;
		} else {
			tail.next = nn;
			tail = nn;
		}
	}
	public static void addFirst(int data) {
		Node nn = new Node(data, null);
		if (head == null) {
			head = tail = nn;
		} else {
            nn.next=head;
			head = nn;
		}
	}

	public static void removeLast() {
		if (head == null) {
			return;
		} else {
			Node i = head;
			while (i.next != tail) {
				i = i.next;
			}
			Node temp = tail;
			tail = i;
			tail.next = null;
			temp.next = null;
		}
	}

	// *******************************************//
	public static void deleteUsingOnePointer(int data) {
		for (Node i = head; i != null; i = i.next) {
			if (i.data == data) {
				Node temp = i.next;
				i.data = temp.data;
				i.next = temp.next;
				temp.next = null;
			}
		}
	}

	// aabbbbccddddefggggg - e, aac - c
	public static void firstNonRepeatingChar() {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			System.out.println(head.data + " ");
			return;
		}
		Node i = head;
		Node j = head.next;
		int counter = 1;
		while (j != null) {
			if (i.data == j.data) {
				counter++;
				j = j.next;
			} else {
				if (counter == 1) {
					System.out.println(i.data + " ");
					return;
				} else {
					counter = 1;
					i = j;
					j = j.next;
				}
			}
		}
	}

	public static void intersectionOf2List() {
		int diff;
		if (size > size2) {
			diff = size - size2;
			for (int i = 0; i < diff; i++) {
				head = head.next;
			}
			while (head != null && head2 != null) {
				if (head.data == head2.data) {
					System.out.println("Intersection");
					return;
				}
				head = head.next;
				head2 = head2.next;
			}
		} else {
			diff = size2 - size;
			for (int i = 0; i < diff; i++) {
				head2 = head2.next;
			}
			while (head != null && head2 != null) {
				if (head.data == head2.data) {
					System.out.println("Intersection");
					return;
				}
				head = head.next;
				head2 = head2.next;
			}
		}
	}

	// remove duplicates from unsorted list- T(nlogn)S(1) => mergesort, remove dup
	// T(n) S(n) => Hashing

	public static void revList(Node head) {
		if (head == tail) {
			return;
		}
		revList(head.next);
		head.next.next = head;
	}

	public static void delLesserNodes() {
		revList(head);
		Node temp = head;
		head = tail;
		tail = temp;
		tail.next = null;

		int max = head.data;
		Node i = head;
		while (i.next != null) {
			if (i.data < max) {
				// delete
				Node nn = i.next;
				i.data = nn.data;
				i.next = nn.next;
				nn.next = null;
			}
			max = Math.max(i.data, max);
			i = i.next;
		}
		if (i.data < max) {
			removeLast();
		}

		revList(head);
		Node temp2 = head;
		head = tail;
		tail = temp2;
		tail.next = null;
	}

	public static Node sumListh;
	public static Node sumListt;
	public static void addLast2(int data) {
		Node nn = new Node(data, null);
		if (sumListh == null) {
			sumListh = sumListt = nn;
		} else {
			nn.next =sumListh;
			sumListh = nn;
		}
	}
	static int carry = 0;
	public static void addTwoList(Node h1, Node h2) {
		if(h1==null) {
			return;
		}
		addTwoList(h1.next, h2.next);
		int sum = h1.data + h2.data + carry;
		carry = sum / 10;
		sum %= 10;
		addLast2(sum);
	}
	
	//if unequal length use 6->5->7 to 657 and 1->1->1 to 111 and add to get 768 and convert to 7->6->8

	//triple tar sum pair - loop through 1st sort 2nd and 3rd
	
	public static void flatternLL(LinkedList<Node> list) {
		PriorityQueue<Node> pq= new PriorityQueue<Node>();
		for (Node l : list) {
			pq.add(l);
		}
		while(pq.size()>0) {
			Node rem=pq.remove();
			System.out.println(rem.data+" ");
			if(rem.next!=null) {
				pq.add(rem.next);				
			}
		}
	}

	
	public static class NodeF{
		int data;
		NodeF next;
		NodeF child;
	}
	
	static Node nh;
	static Node nt;
	static Node hqh;
	static Node hqt;
	
	public static void addLastHold(int data) {
		
	}
	public static void addLastFinal(int data) {
		
	}
	public static NodeF removeFirstHold() {
		return null;
	}
	
	public static void flatternMLL(NodeF head) {
		addLastHold(head.data);
		while(size>0) {
			NodeF rem=removeFirstHold();
			addLastFinal(rem.data);
			NodeF temp=rem;
			while(temp!=null) {
				if(temp.next!=null) {
					addLastFinal(temp.data);
				}
				if(temp.child!=null){
					addLastHold(temp.data);	
				}
				temp=temp.next;
			}
		}
		
	}

    public static void sortListOnABS(Node head){
        Node temp=head;
        while(temp!=null){
            if(temp.data<=0){
                if(temp.next==null){
                    int od=temp.data;
                    removeLast();
                    addFirst(od);
                    temp=null;
                }else{
                    int od=temp.data;
                    temp.data=temp.next.data;
                    temp.next=temp.next.next;
                    addFirst(od);
                }
            }else{
                temp=temp.next;
            }
        }
    }

    public static void sortBiotonic(Node head){
        Node f=head;
        Node s=head;
        while(s.data<s.next.data){
            s=s.next;
        }
        mergesort(f,s);
    }

	public static void main(String[] args) {
//		addLast(12);
//		addLast(15);
//		addLast(10);
//		addLast(11);
//		addLast(2);
//		addLast(3);
//		addLast(5);
//		addLast(6);
//		
//		setSize();
//		deleteUsingOnePointer(40);
//		display(head);
//		firstNonRepeatingChar();
//		delLesserNodes();
//		display(head);
//		addTwoList(head, head);
//		if(carry!=0) {
//			addLast2(carry);
//		}
//		display(sumListh);
		
//		LinkedList<Node> list= new LinkedList<Node>();
//		for(int i=0;i<4;i++) {
//			list.add(head);
//		}
//		flatternLL(list);

		// addLast(1);
		// addLast(-2);
		// addLast(5);
		// addLast(-6);
		// addLast(-7);
		// addLast(-8);
        // addLast(9);
        // addLast(10);
        // addLast(-11);

        // sortListOnABS(head);

        // display(head);
	}
}
