/**
 * Created by nikola on 11/22/17.
 */

import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats {

    private Percolation percolation;
    private int n;

    public PercolationStats(int n, int trials) {

        this.n = n;


        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();

        for (int t = 0; t < trials; t++) {

            this.percolation = new Percolation(n);

            int randRow;
            int randCol;



            while (!this.percolation.percolates()) {

                randRow = StdRandom.uniform(1, n+1);
                randCol = StdRandom.uniform(1, n+1);

                this.percolation.open(randRow, randCol);

//                System.out.println(this.percolation.numberOfOpenSites());
            }



        }


    }

    public double mean() {
        return 0;
    }

    public double stdev() {
        return 0;

    }

    public double confidenceLo() {
        return 0;

    }

    public double confidenceHi() {
        return 0;

    }

    public static void main(String[] args) {

        PercolationStats obj = new PercolationStats(1000, 1);

        double p;

        double openSites = obj.percolation.numberOfOpenSites();

        p = openSites / (obj.n * obj.n);

        System.out.println("Probability p is: " + p);
    }
}
