public class July19{
    public static class Node{
        int data;
        Node next;
        public Node(int data, Node next){
            this.data=data;
            this.next=next;
        }
    }
    static Node head;
    static Node tail;

    public static void addLast(int data){
        Node nn= new Node(data,null);
        if(head==null){
            head=nn;
            tail=nn;
        }else{
            tail.next=nn;
            tail=nn;
        }
    }   
    public static void display(Node head){
        for(Node i=head;i!=null;i=i.next){
            System.out.print(i.data+" -> ");
        }        
    }

    public static void main(String[] args) {
        addLast(10);
        addLast(20);
        addLast(30);
        addLast(40);
        addLast(50);
        display(head);
    }
}