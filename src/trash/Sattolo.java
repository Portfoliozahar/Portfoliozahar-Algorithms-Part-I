package src.trash;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Sattolo {

    // this class should not be instantiated
    private Sattolo() {
    }

    public static void cycle(Object[] a) {
        int n = a.length;
        for (int i = n; i > 1; i--) {
            // choose index uniformly in [0, i-1)
            int r = (int) (Math.random() * (i - 1));
            Object swap = a[r];
            a[r] = a[i - 1];
            a[i - 1] = swap;
        }
    }


    public static void main(String[] args) {

        // read in the data
        String[] a = StdIn.readAllStrings();

        // shuffle the array
        Sattolo.cycle(a);

        // print results.
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
}