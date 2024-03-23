import java.util.LinkedList;

public class InsertionSort {
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(LinkedList<Double> a) {
        int n = a.size();
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a.get(j), a.get(j - 1)); j--) {
                exch(a, j, j-1);
            }
        }
    }

    //
      private static boolean less(Double v, Double w)
      { return v.compareTo(w) < 0; }

    //  Swap item in array a[] at index i with the one at index j
      private static void exch(LinkedList<Double> a, int i, int j)
      {
          Double swap = a.get(i);
          a.set(i, a.get(j));
          a.set(j, swap);
      }

}
