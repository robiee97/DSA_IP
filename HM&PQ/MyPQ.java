import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
public class MyPQ{

    ArrayList<Integer> list;
    boolean minormax;

    public MyPQ(boolean minormax,ArrayList<Integer> list){
        this.minormax=minormax;
        this.list=list;
        for(int i=list.size()/2-1;i>=0;i--){
            downHeapify(i);
        }
    }

    public void downHeapify(int idx){
        int lci=2*idx+1;
        int rci=lci+1;
        int hpi=idx;
        if(lci<list.size() && isHighPriority(lci, hpi)){
            hpi=lci;
        }
        if(rci<list.size() && isHighPriority(rci, hpi)){
            hpi=rci;
        }
        if(hpi!=idx){
            Collections.swap(list,idx,hpi);
            downHeapify(hpi);
        }
    }
    public void upHeapify(int idx){
        if(idx==0){
            return;
        }
        int pi=(idx-1)/2;
        if(isHighPriority(idx, pi)){
            Collections.swap(list,idx,pi);    
            upHeapify(pi);        
        } 
    }
    public boolean isHighPriority(int i,int j){
        if(minormax){
            return list.get(i)<list.get(j);
        }else{
            return list.get(i)>list.get(j);
        }
    }

    public void push(int val){
        list.add(val);
        upHeapify(list.size()-1);
    }
    public void pop(){
        Collections.swap(list,0,list.size()-1);
        list.remove(list.size()-1);
        downHeapify(0);
    }
    public int top(){
        return list.get(0);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(10);list.add(50);list.add(40);list.add(90);list.add(60);
        list.add(30);list.add(20);list.add(80);
        // true-minHeap, false-maxHeap
        MyPQ mpq= new MyPQ(true, list); 
        while(mpq.size()>0){
            System.out.println(mpq.top());
            mpq.pop();
        }
    }
}