
import java.util.*;

public class July1 {
    public static void main(String[] args) {
        // sortCustomString();
        // sortCustomNum();
        // binaryStringRep();
        // fibLOGN();
        // rangeAdd();
        // closestPallindrom();
    }
    
    public static void sortCustomString(){
        String one="dceagbfzyx";
        String two="jgbcdcafleiadkb";
    
        TreeMap<Character,Integer> map= new TreeMap<>();
        for(char c:two.toCharArray()){
            map.put(c, map.containsKey(c)?map.get(c)+1:1);
        }
    
        for(char c : one.toCharArray()){
            if(map.containsKey(c)){
                for(int i=0;i<map.get(c);i++){
                    System.out.print(c);
                }
                map.remove(c);
            }
        }
        for(char c:map.keySet()){
            System.out.print(c);
        }
    }

    public static class Mypair implements Comparable<Mypair>{
        double ele;
        double org;
        Mypair(double ele, double org){
            this.ele=ele;
            this.org=org;
        }

        @Override
        public int compareTo(Mypair o){
            return (int)(this.ele-o.ele);
        }
    }
    public static void sortCustomNum(){
        int[] arr={3,5,1,9,2,4};
        double[] darr={3.214,1.42,9.35,9.53,1.24,5.21};
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i], i);
        }
        Mypair[] marr=new Mypair[darr.length];
        for(int i=0;i<darr.length;i++){
            String obj=Double.toString(darr[i]);
            String ans="";
            for(char c:obj.toCharArray()){
                if(c=='.'){
                    ans+=c;
                }else{
                    ans+=map.get(c-'0');
                }
            }
            marr[i]=new Mypair(Double.parseDouble(ans), darr[i]);
        }
        Arrays.sort(marr);
        for(Mypair o:marr){
            System.out.println(o.ele+" "+o.org);
        }
    }

    public static void binaryStringRep(){
        String str="10100011100";
        // String str="1";
        int n=8;

        HashSet<Integer> set= new HashSet<>();
        for(int i=n/2+1;i<=n;i++){
            set.add(i);
        }
        int s=0;
        int e=0;
        while(e<str.length()){
            if(set.size()==0){
                break;
            }
            if(Integer.parseInt(str.substring(s,e+1),2)>n){
                // Invalid release
                s++;
                while(s<str.length() && str.charAt(s)!='1'){//till becomes valid
                    s++;
                }
            }else{
                //valid --check in set, remove from set
                if(set.contains(Integer.parseInt(str.substring(s,e+1),2))){
                    set.remove(Integer.parseInt(str.substring(s,e+1),2));
                }
                //chance of -- acquire <= n
                e++;
            }
        }
        if(set.size()!=0){
            System.out.println("Not possible");
        }else{
            System.out.println("Possible");
        }
    }
    
    public static void fibLOGN(){
        int n=10;
        int[][] m={{1,1},{1,0}};
        int[][] ans=powerOp(m, n);
        System.out.println(ans[0][1]+" "+ans[1][0]);
    }
    public static int[][] powerOp(int[][] mat,int n){
        if(n==1){
            return mat;
        }
        int[][] nb2=powerOp(mat, n/2);        
        if(n%2==0){
            return matmul(nb2, nb2);
        }
        else{
            return matmul(mat,matmul(nb2, nb2));
        }
    }
    public static int[][] matmul(int[][] mat1,int[][] mat2){
        int[][] ans=new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                for (int k = 0; k < mat2.length; k++) {
                    ans[i][j]+=mat1[i][k]*mat2[k][j];
                }
            }
        }
        return ans;
    }

    public static void rangeAdd(){
        int[] st={2,4,0};
        int[] en={5,7,8};
        int[] del={3,-2,1};
        int[] ans=new int[10];
        for(int i=0;i<del.length;i++){
            ans[st[i]]+=del[i];
            ans[en[i]]-=del[i];
        }
        for (int i = 1; i < ans.length; i++) {
            ans[i]+=ans[i-1];
        }
        for(int i:ans){
            System.out.print(i+" ");
        }
    }
    public static String reverse(String str){
        String ans="";
        for (int i = str.length()-1; i >=0 ; i--) {
            ans+=str.charAt(i);
        }
        return ans;
    }
    public static void closestPallindrom(){
        int n=178771;
        String str=n+"";
        String fH=str.substring(0,str.length()/2);
        String rev=reverse(fH);
        if((fH+rev).equals(str)){
            System.out.println("already pallindrom");
            return;
        }else{
            //orginal
            int np=Integer.parseInt(fH+rev);
            
            //orginal-1
            String npm1f= Integer.toString(Integer.parseInt(fH)-1);
            String npm1s=reverse(npm1f);
            int npm1=Integer.parseInt(npm1f+npm1s);

            //orignal+1
            String npp1f= Integer.toString(Integer.parseInt(fH)+1);
            String npp1s=reverse(npp1f);
            int npp1=Integer.parseInt(npp1f+npp1s);

            int min=Integer.MAX_VALUE;
            min=Math.min(Math.abs(n-np), Math.min(Math.abs(n-npm1), Math.abs(n-npp1)));
            if(n+min==npm1 || n+min==npp1 || n+min==np){
                System.out.println(n+min);
            }else{
                System.out.println(n-min);
            }
        }
    }
}