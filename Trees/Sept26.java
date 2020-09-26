import java.util.*;

public class Sept26 {
    // segment tree Sept-26
    public static void buildTree(int[] tree, int[] arr, int index, int s, int e) {
        //null node
        if (s > e) {
            return;
        }
        //base-case
        if (s == e) {
            tree[index] = arr[s];
            return;
        }
        int mid = (s + e) / 2;
        buildTree(tree, arr, 2 * index + 1, s, mid);
        buildTree(tree, arr, 2 * index + 2, mid + 1, e);
        tree[index] = Math.min(tree[2 * index + 1], tree[2 * index] + 2);
    }

    // Range MIN Query
    public static int query(int[] tree, int[] lazy, int index, int s, int e, int qs, int qe) {
        if (lazy[index] != 0) {
            int pending = lazy[index];
            lazy[index] = 0;
            tree[index] += pending * (e - s + 1);
            if (s != e) {
                lazy[2 * index + 1] += pending;
                lazy[2 * index + 2] += pending;
            }
        }

        // no overlap
        if (qs > e || qe < s) {
            return Integer.MAX_VALUE;
        }
        // completely overlap
        if (s >= qs && e <= qe) {
            return tree[index];
        }
        // partial overlap
        int mid = (s + e) / 2;
        int lans = query(tree, lazy, 2 * index + 1, s, mid, qs, qe);
        int rans = query(tree, lazy, 2 * index + 2, mid + 1, e, qs, qe);
        return Math.min(lans, rans);
    }

    // update element
    public static void update(int[] tree, int index, int s, int e, int val, int i) {
        if (i < s || i > e) {
            return;
        }
        if (s == e) {
            tree[index] = val;
            return;
        }
        int mid = (s + e) / 2;
        update(tree, 2 * index + 1, s, mid, val, i);
        update(tree, 2 * index + 2, mid + 1, e, val, i);
        tree[index] = Math.min(tree[2 * index + 1], tree[2 * index + 2]);
    }

    // Range update by element
    public static void rangeUpdate(int[] tree, int[] lazy, int index, int s, int e, int val, int rs, int re) {
        if (lazy[index] != 0) {
            int pending = lazy[index];
            lazy[index] = 0;
            tree[index] += pending * (e - s + 1);
            if (s != e) {
                lazy[2 * index + 1] += pending;
                lazy[2 * index + 2] += pending;
            }
        }

        if (re < s || rs > e) {
            return;
        }
        if (s >= rs && e <= re) {
            int pending = (e - s + 1) * val;
            tree[index] += pending;
            if (s != e) {
                lazy[2 * index + 1] += val;
                lazy[2 * index + 2] += val;
            }
            return;
        }
        int mid = (s + e) / 2;
        rangeUpdate(tree, lazy, 2 * index + 1, s, mid, val, rs, re);
        rangeUpdate(tree, lazy, 2 * index + 2, mid + 1, e, val, rs, re);
        tree[index] = Math.min(tree[2 * index + 1], tree[2 * index + 2]);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, -2, 4, 5 };
        int[] tree = new int[(4 * arr.length) + 1];
        // lazy tree
        int[] lazy = new int[(4 * arr.length) + 1];
        int s = 0;
        int e = arr.length - 1;
        int index = 0;
        // buildTree(tree, arr, index, s, e);
        // range query

        // update(tree, index, s, e, -3, 2);
        // rangeUpdate(tree, lazy, index, s, e, 5, 3, 5);
        
        // System.out.println(query(tree, lazy, index, s, e, 1, 3));
    }
}
