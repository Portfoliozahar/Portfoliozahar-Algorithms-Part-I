package src.A1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {

        // TODO: Configure Run Configuration to redirect input from coin.txt
        String champion = null;
        int count = 0;

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            count++;

            if (StdRandom.bernoulli(1.0 / count)) {
                champion = word;
            }
        }

        StdOut.println(champion);
    }
}