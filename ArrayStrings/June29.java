
import java.util.*;

public class June29 {
    public static void main(String[] args) {
        // bestSightSeeing();
        // integerToRoman();
        // romanToInteger();
        // pourWater();
        // tripletSumClosest();
        // ambigousCoordinates();
        // loopingInArr();
        // System.out.println(largestTime(new int[]{1,7,2,9}));
    }


    public static void bestSightSeeing(){//sa+sb-(b-a)
        int[] arr={8,5,7,3,2,5,3,9};
        int max=0;
        boolean[] vis = new boolean[arr.length];
        int i=0;
        int j=1;
        while(j<arr.length){
            int k=i;
            while(k<j){
                if(!vis[k]){//check true
                    max=Math.max(max, (arr[j]+arr[k]-(j-k)));
                    if((arr[j]+arr[k]-(j-k))>(arr[j]+arr[i]-(j-i))){//original loose
                        i=k;//change new starting
                    }
                    else if((arr[j]+arr[k]-(j-k))<(arr[j]+arr[i]-(j-i))){//other candidate loose 
                        //mark k false
                        vis[k]=true;
                    }
                }
                k++;
            }
            j++;
        }
        System.out.println(max);
    }
    
    public static void pourWater(){
        // int[] arr={Integer.MAX_VALUE,5,3,4,1,5,3,4,2,1,6,4,Integer.MAX_VALUE};
        // int drops=20;
        // int index=5;
    }

    public static class MyPair{
        int i;
        String code;

        MyPair(int i,String code){
            this.i=i;
            this.code=code;
        }
    }
    public static void integerToRoman(){
        MyPair[] arr={new MyPair(1, "I"),new MyPair(4, "IV"),new MyPair(5, "V"),
        new MyPair(9, "IX"),new MyPair(10, "X"),new MyPair(40, "XL"),
        new MyPair(50, "L"),new MyPair(90, "XC"),new MyPair(100, "C"),
        new MyPair(400, "CD"),new MyPair(500, "D"),new MyPair(900, "CM"),
        new MyPair(1000, "M")};
        int n=3998;
        String romanStr="";
        int itr=arr.length-1;
        while(n>0){
            if(n>=arr[itr].i){
                n-=arr[itr].i;
                romanStr+=arr[itr].code;
            }else{
                itr--;
            }
        }
        System.out.println(romanStr);
    }
    public static void romanToInteger(){
        HashMap<String,Integer> map= new HashMap<>();
        map.put("I", 1);map.put("IV", 4);map.put("V", 5);
        map.put("IX", 9);map.put("X", 10);map.put("XL", 40);
        map.put("L", 50);map.put("XC", 90);map.put("C", 100);
        map.put("CD", 400);map.put("D", 500);map.put("CM", 900);
        map.put("M", 1000);
        // String romanStr="MMMCMXCVIII";
        String romanStr="MMCMXCIX";
        int n=0;
        for(int i=0;i<romanStr.length();i++){
            if(i<romanStr.length()-1 && map.containsKey(romanStr.substring(i,i+2))){
                n+=map.get(romanStr.substring(i,i+2));
                i++;
            }else{
                n+=map.get(romanStr.substring(i,i+1));
            }
        }
        System.out.println(n);
    }
    public static void tripletSumClosest(){
    }
    public static void ambigousCoordinates(){
    }
    
    public static void loopingInArr(){
        int[] arr={3,3,-2,2,3,4,-3,3,-2,2,2,-3,4,-1,-1,-4,8};
        boolean[] oavis=new boolean[arr.length];
        for(int a=0;a<oavis.length;a++){
            oavis[a]=false;
        }
        int i=0;
        while(i<arr.length){
            if(!oavis[i]){
                oavis[i]=true;
                int j=i;
                boolean[] temvis= new boolean[arr.length];
                for(int a=0;a<temvis.length;a++){
                    temvis[a]=false;
                }
                boolean flag=true;
                while(flag){
                    if(temvis[j]){
                        System.out.println("loop on " +j);
                        break;
                    }
                    temvis[j]=true;
                    int steps=arr[j];
                    if(j+steps>arr.length){
                        j=((j+steps)%arr.length);
                    }else{
                        j+=steps;
                    }
                    if(arr[i]>0 && arr[j]<0){
                        flag=!flag;
                    }else if(arr[i]<0 && arr[j]>0){
                        flag=!flag;
                    }
                }
            }
            i++;
        }
    }
    
    public static String largestTime(int[] arr){
        //2digit all permutations store in pq less than 59
        String ans="";
        HashMap<Integer,Integer>fmap= new HashMap<>();
        for(int i=0;i<arr.length;i++){
            fmap.put(arr[i],fmap.containsKey(arr[i])?fmap.get(arr[i])+1:1);
        }
        ArrayList<String> list= new ArrayList<>();
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                String n1=""+arr[i]+arr[j];
                String n2=""+arr[j]+arr[i];
                if(Integer.parseInt(n1)<=59){
                    list.add(n1);
                }
                if(Integer.parseInt(n2)<=59){
                    list.add(n2);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        // System.out.println(list);
        int i=0;
        while(i<list.size()){
            if(Integer.parseInt(list.get(i))<=23){
                int j=0;
                while(j<list.size()){
                    if(j!=i){
                        String temp=list.get(i)+list.get(j);
                        if(isValid(temp,fmap)){
                            ans=list.get(i)+":"+list.get(j);
                            return ans;
                        } 
                    }
                    j++;
                }
            }
            i++;
        }
        return ans;
    }
    public static boolean isValid(String str,HashMap<Integer,Integer> fmap){
        boolean ans=true;
        HashMap<Integer,Integer>lmap= new HashMap<>();
        for(char c:str.toCharArray()){
            lmap.put(c-'0',lmap.containsKey(c-'0')?lmap.get(c-'0')+1:1);
        }
        for(int i:lmap.keySet()){
            if(!fmap.containsKey(i)){
                return false;
            }else{
                if(fmap.get(i)!=lmap.get(i)){
                    return false;
                }
            }
        }
        return ans;
    }
}