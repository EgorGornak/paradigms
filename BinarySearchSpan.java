package search;

public class BinarySearchSpan {
    // Pre:  a.length > 0 a[i] >= a[i + 1], for i in [0..a.length - 2]
    // 	  || a.length == 1
    // Post: a' == a &&
    //    (R = 0 && x >= a[0] 
    // 	  || R = a.length  && a[a.length - 1] > x 
    //    || R = i, i in [1..a.length - 1] &&  a[i - 1] > x >= a[i])
    public static int iterativeBinarySearch(int x, int[] a) {
        int l = 0, r = a.length;
        // INV: l <= R <= r
        while (l < r) {
            // INV && l < r
            int m = (l + r) / 2;
            // l <= m < r
            if (a[m] > x) {
                // INV && a[m] > x
                l = m + 1;
                // INV && l <= r
            } else  {
                // INV && a[m] <= x
                r = m;
                // INV && r >= l
            }
            // INV && r >= l
        }
        // INV && r >= l && l >= r
        // INV && l == r
        // l <= R <= r
        // R = r
        return r;
    }

    // Pre: a[a.length] = -INF  && (x >= a[r])
    //    (a.length > 0 a[i] >= a[i + 1], for i in [0..a.length - 2]
    // 	  || a.length == 1)
    //    l,r in [0..a.length], a[a.length] = -INF, a[i] = INF,  && x >= a[r] && x < a[l - 1]
    // Post: a' == a &&
    //    (R = 0 && x > a[0]
    // 	  || R = a.length  && x < a[a.length -1]
    //    || R = i, i in [1..a.length - 1] &&  a[i - 1] >= x > a[i])    
    public static int recursiveBinarySearch(int x, int[] a, int l, int r) { 
        // INV: l <= R <= r
        if (l == r) {
            // INV && l == r
            // R = r
            return r - 1;
        }
        int m = (l + r) / 2;
        // l <= m < r
        if (a[m] >= x) {
            // INV && a[m] >= x
            // Pre && m + 1 <= R && m + 1 <= r
            return recursiveBinarySearch(x, a, m + 1, r);
        } else {
            // INT && a[m] < x
            // Pre && m >= R && l <= m
            return recursiveBinarySearch(x, a, l, m);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage search.BinarySearch x a[0] a[1] ...");
            return;
        }

        int x = 0;
        try {
       		x = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
        	System.err.println(e);
        	return;
        }

        int[] a = new int[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
	        try {
	       		a[i] = Integer.parseInt(args[i + 1]);
	        } catch (NumberFormatException e) {
	        	System.err.println(e);
	        	return;
	        }
        }

        int ansIterative = iterativeBinarySearch(x, a);
        int length = recursiveBinarySearch(x, a, 0, a.length) - ansIterative + 1;
        System.out.println(ansIterative + " " + length);
    }
}
