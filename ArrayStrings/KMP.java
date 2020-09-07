
public class KMP {
    public static int[] getLPS(String pattern) {
        int j = 0;
        int i = 1;
        int[] lps = new int[pattern.length()];
        lps[0] = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j + 1;
                i++;
                j++;
            } else {
                if (j == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
        return lps;
    }

    public static void kmp(String text, String pattern) {
        int[] lps = getLPS(pattern);
        int i = 0;
        int j = 0;
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    System.out.println("pattern found at " + (i - pattern.length()));
                    j = lps[j - 1];
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "nkasdhflkjdshfiluweaifusbdkjfahidufehiudskfjweiufhiuefkjsdbfiuefiuerhfjkeriugfrefie";
        String pattern = "usbdkjf";
        kmp(text, pattern);
    }
}
