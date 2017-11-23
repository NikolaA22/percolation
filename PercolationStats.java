/**
 * Created by nikola on 11/22/17.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// FIXME Keep p in a double[][]
public class PercolationStats {

    private Percolation percolation;
    private int n;
    private int trials;
    private double [] results; //keep [trial][p]

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();

        this.results = new double[trials];

        this.n = n;

        this.trials = trials;




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

            this.results[t] = p;



        }


    }

    public double mean() {

        return StdStats.mean(this.results);

    }

    public double stdev() {
        return StdStats.stddev(this.results);

    }

    public double confidenceLo() {

        double tmp = mean() - (1.96*stdev()*(1/Math.sqrt(this.trials)));

        return tmp;

    }

    public double confidenceHi() {

        double tmp = mean() + (1.96*stdev()*(1/Math.sqrt(this.trials)));

        return tmp;

    }

    public static void main(String[] args) {

        String n = args[0];

        String trials = args[1];


        PercolationStats obj = new PercolationStats(Integer.parseInt(n), Integer.parseInt(trials));


        System.out.println("mean                    = " + obj.mean());
        System.out.println("stdev                   = " + obj.stdev());
        System.out.println("95% confidence interval = [" + obj.confidenceLo() + " " + obj.confidenceHi()+"]");
    }
}
