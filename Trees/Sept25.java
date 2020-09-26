import java.util.*;
public class Sept25 {
    // fenwick tree or Binary Index Tree
    public static int getParent(int index) {
        return index - (index & -index);
    }

    public static int sum(int[] tree, int index) {
        index += 1;
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index = getParent(index);
        }
        return sum;
    }

    public static int getNext(int index) {
        return index + (index & -index);
    }

    public static void updateTree(int[] tree, int val, int index) {
        while (index < tree.length) {
            tree[index] += val;
            index = getNext(index);
        }
    }

    public static void buildTree(int[] tree, int[] arr) {
        for (int i = 1; i <= arr.length; i++) {
            updateTree(tree, arr[i - 1], i);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3,8,2,6,5,7,4 };
        int[] tree = new int[arr.length + 1];
        buildTree(tree, arr);
        System.out.println(sum(tree, 4));
    }
}
