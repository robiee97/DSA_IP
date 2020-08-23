public class July7{
    public static void main(String[] args) {
        // KS path,LIS path,ED path
        // personGoesToParty();
        // countBinaryString();
        // countStringOfType_an_bn_cn();
    }
    public static void personGoesToParty(){
        
    }
    public static void countBinaryString(){//count strings with non consecutive 0's
        int n=10;
        int c0=1;
        int c1=1;

        for (int i = 0; i <=n; i++) {
            int nc0=c1;
            int nc1=c0+c1;

            c0=nc0;
            c1=nc1;
        }
        System.out.println(c0+c1);
    }
    public static void countStringOfType_an_bn_cn(){
        String str="abcabc";
        int a=0;
        int b=0;
        int c=0;

        for (int i = 0; i < str.length(); i++) {
            char ch= str.charAt(i);
            if(ch=='a'){
                a=1+2*a;
            }else if (ch=='b'){
                b=a+2*b;
            }else{
                c=b+2*c;
            }
        }
        System.out.println(c);
    }
}