import java.util.Scanner;

public class addTwoN{
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int a=s.nextInt();
        int b=s.nextInt();
        System.out.println(addNumbers(a,b)); 
    }
    public static int addNumbers(int x,int y){
        while(y!=0){
            int carry=x & y;
            x=x ^ y;
            y=carry << 1;
        }
        return x;
    }
}