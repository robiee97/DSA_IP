
import java.util.*;
public class July12 {
    public static void cnwToPartinKset(){
        int n=7;
        int k=4;
        int[][] strg= new int[n+1][k+1];
        for (int i = 0; i < strg.length; i++) {
            for (int j = 0; j < strg[0].length; j++) {
                if(i==0){
                    strg[i][j]=1;
                }else{
                    strg[i][j]=(strg[i][j-1]*j)+strg[i-1][j-1];
                }    
            }
        }
    }
    public static void wordBreak(){
        String str="ilikemango";
        HashSet<String> set=new HashSet<>();
        set.add("i");
        set.add("go");
        set.add("man");
        set.add("like");
        int e=0;
        int s=0;

        boolean flag=false;
        while(e<str.length()){
            if(set.contains(str.substring(s,e))){
                flag=true;
                s=e;
            }else{
                e++;
            }
        }   
        System.out.println(flag);
    }
    public static void keyBoard(){
        HashMap<Integer,Integer[]> map= new HashMap<>();
        map.put(0, new Integer[]{0,8});
        map.put(1, new Integer[]{1,2,4});
        map.put(2, new Integer[]{1,2,3,5});
        map.put(3, new Integer[]{2,6,3});
        map.put(4, new Integer[]{1,4,5,7});
        map.put(5, new Integer[]{2,4,5,6,8});
        map.put(6, new Integer[]{9,5,6,3});
        map.put(7, new Integer[]{8,7,4});
        map.put(8, new Integer[]{7,8,9,5});
        map.put(9, new Integer[]{8,9,6});
        int n=3;
        int[][] strg= new int[n+1][10];
        for(int i=1;i<strg.length;i++){
            for (int j = 0; j < strg[0].length; j++) {
                if(i==1){
                    strg[i][j]=1;
                }else{
                    for(Integer e:map.get(j)){
                      strg[i][j]+=strg[i-1][e];  
                    }
                }
            }
        }
        int sum=0;
        for (int i : strg[n]) {
            sum+=i;
        }
        System.out.println(sum);
    }
    public static void noSolPossible(){
        // String exp="2x+2y+3z=4";
        int[] arr={2,2,3};
        int tar=4;
        int[] strg= new int[tar+1];
        strg[0]=1;
        for (int i = 0; i < arr.length; i++) {//combination
            for (int j = 1; j < strg.length; j++) {
                if(j>=arr[i]){
                    strg[j]+=strg[j-arr[i]];
                }
            }
        }
        System.out.println(strg[strg.length-1]);
    }
    public static void main(String[] args) {
        // friends();
        // minIns();
        // minDele();
        // lcsOf3();
        // cnwToPartinKset();
        // wordBreak();
        // keyBoard();
        // noSolPossible();
    }
}