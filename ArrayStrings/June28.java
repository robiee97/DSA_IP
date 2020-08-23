import java.util.*;

public class June28 {
    public static void main(String[] args) {
        // kdistinctEleOfGap();
        // mostWaterContainer();
        // buyAndSellStock();
        // countSubArr0s1sAll0sTogether();
        // nestingArr();
        // nestingArr2();
        // spiltArrEqijkSum();
    }

    public static void kdistinctEleOfGap(){
        int n=12;
        int k=4;
        for(int i=1;i<n-k;i++){
            System.out.print(i+" ");
        }
        int s=n-k;
        int e=n;
        if((e-s+1)%2!=0){
            while(s<e){
                System.out.print(s+" ");
                s++;
                System.out.print(e+" ");
                e--;
            }
            System.out.print(e+" ");
        }else{
            while(s<e){
                System.out.print(s+" ");
                s++;
                System.out.print(e+" ");
                e--;
            }
        }
    }
    public static void mostWaterContainer(){
        int[] arr={3,7,2,8,4,5,6};
        int maxWater=0;
        int s=0;
        int e=arr.length-1;
        while(s<e){
            int width=e-s;
            int min=Math.min(arr[s],arr[e]);
            maxWater=Math.max(maxWater,(width*min));
            if(arr[e]>arr[s]){
                s++;
            }else{
                e--;
            }
        }
        System.out.println(maxWater);
    }
    
    public static void buyAndSellStock(){
        int[] arr={10,20,40,30,50,60,55,70,80,49,58};
        int amt=0;
        int total=0;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i+1]>arr[i]){
                amt-=arr[i];
            }
            if(arr[i+1]<arr[i]){
                total+=amt+arr[i];
                amt=0;
            }
            if(i+1==arr.length-1){
                total+=amt+arr[i+1];
            }
        }
        System.out.println(total);
    }
    public static void countSubArr0s1sAll0sTogether(){
        int[] arr={0,0,1,1,1,0,0,0,0,1,1,0,0};
        // int[] arr={1,0,1,0,0,1,1,0,0,0,1,1};
        // int[] arr={1,1,0,0,0,0,1,1,1,0,0};
        int ps=-1;
        int pe=-1;
        int cs=0;
        int ce=0;
        int ctr=0;
        while(ce<arr.length-1){
            if(arr[ce+1]!=arr[ce]){
                //switch
                ps=cs;
                pe=ce;
                cs=ce+1;
                ce=ce+1;
                ctr++;
            }else if(arr[ce+1]==arr[ce]){
                ce++;
                if((pe-ps)>=(ce-cs)){//valid length
                    ctr++;
                }
            }   
        }
        System.out.println(ctr);
    }
    public static void nestingArr(){
        int[] arr={4,5,6,1,2,3,0,7};
        ArrayList<ArrayList<Integer>>lis= new ArrayList<>();

        boolean[] isv=new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if(!isv[i]){
                lis.add(nestedArr(i,arr,isv));
            }
        }
        System.out.println(lis);
    }
    public static ArrayList<Integer> nestedArr(int i,int[] arr,boolean[] isv){
        ArrayList<Integer> ans=new ArrayList<>();
        while(true){
            if(isv[i]){
                break;
            }
            ans.add(i);
            isv[i]=true;
            i=arr[i];
        }
        return ans;
    }
    public static void nestingArr2(){
        int[] arr={2,0,1,4,6,7,3,2,4,8,7,10};
        boolean[] isv=new boolean[arr.length];
        HashMap<Integer,Integer> ivs= new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(!isv[i]){
                HashMap<Integer,Integer> ivd= new HashMap<>();
                Stack<Integer> st= new Stack<>();
                while(true){
                    st.add(i);
                    if(isv[i]){
                        break;
                    }
                    ivd.put(i, st.size());
                    isv[i]=true;
                    i=arr[i];
                }
                int rem=st.peek();
                st.pop();
                int len=st.size();
                if(ivs.containsKey(rem)){
                    int itr=1;
                    while(st.size()!=0){
                        int peek=st.peek();
                        st.pop();
                        ivs.put(peek, ivs.get(rem)+itr);
                        itr++;
                    }
                }else{
                    while(st.size()!=0){
                        int peek=st.peek();
                        st.pop();
                        ivs.put(peek, len);
                    }
                }               
            }
        }
        int maxValueIndex=0;
        int max=0;
        for(int i:ivs.keySet()){
            if(ivs.get(i)>max){
                maxValueIndex=i;
                max=ivs.get(i);
            }
        }
        boolean[] check= new boolean[arr.length];
        while(true){
            if(check[maxValueIndex]){
                break;
            }
            check[maxValueIndex]=true;
            System.out.print(arr[maxValueIndex]+" ");
            maxValueIndex=arr[maxValueIndex];
        }
    }
    public static void spiltArrEqijkSum(){

    } 
}