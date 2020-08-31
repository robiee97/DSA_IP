import java.util.*;
public class LL {
    public static class Node { 
        int data; 
		Node next; 
        
		public Node(int data) 
		{ 
            this.data = data; 
		} 
	} 
    
    Node head = null;  
    
    Node sortedMerge(Node a, Node b) 
	{ 
		Node result = null;
		if (a == null) 
			return b; 
		if (b == null) 
			return a; 
        if (a.data <= b.data) { 
			result = a; 
			result.next = sortedMerge(a.next, b); 
		} 
		else { 
			result = b; 
			result.next = sortedMerge(a, b.next); 
		} 
		return result; 
	} 

	Node mergeSort(Node h) 
	{ 
		if (h == null || h.next == null) { 
			return h; 
		} 
		Node middle = getMiddle(h); 
		Node nextofmiddle = middle.next; 
        middle.next = null; 
        Node left = mergeSort(h); 
        Node right = mergeSort(nextofmiddle); 
        Node sortedlist = sortedMerge(left, right); 
		return sortedlist; 
	} 
    Node getMiddle(Node h) 
	{ 
		if (h == null) 
			return h; 
		Node fastptr = h.next; 
		Node slowptr = h; 

		while (fastptr.next != null && fastptr.next.next!=null) { 
            slowptr = slowptr.next; 
            fastptr = fastptr.next.next;
		} 
		return slowptr; 
	} 

	void push(int new_data) 
	{ 
		Node new_node = new Node(new_data); 
		new_node.next = head; 
		head = new_node; 
	} 
    void printList(Node h) 
	{ 
		while (h != null) { 
			System.out.print(h.data + " -> "); 
			h = h.next; 
		} 
	} 

	public static void main(String[] args) 
	{ 
		LL ll = new LL(); 
		ll.push(15); 
		ll.push(10); 
		ll.push(5); 
		ll.push(20); 
		ll.push(3); 
		ll.push(2); 
		// ll.printList(ll.head); 

		ll.head = ll.mergeSort(ll.head); 
		ll.printList(ll.head); 
	} 
} 
