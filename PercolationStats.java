/**
 * Created by nikola on 11/22/17.
 */

import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats {

    private Percolation percolation;
    private int n;
    private double sumThreshold;
    private int trials;

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();


        this.n = n;

        this.trials = trials;


        this.sumThreshold = 0;

        for (int t = 0; t < trials; t++) {

            double p;

            this.percolation = new Percolation(n);

            int randRow;
            int randCol;



            while (!this.percolation.percolates()) {

                randRow = StdRandom.uniform(1, n+1);
                randCol = StdRandom.uniform(1, n+1);

                this.percolation.open(randRow, randCol);

            }


            double openSites = this.percolation.numberOfOpenSites();

            p = openSites / (this.n * this.n);

            this.sumThreshold += p;


        }


    }

    public double mean() {

        return this.sumThreshold/this.trials;

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

        PercolationStats obj = new PercolationStats(100, 100);


        System.out.println("Probability p is: " + obj.mean());
    }
}
