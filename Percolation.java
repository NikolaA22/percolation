// Import all the classes here from algs4
// e.g. import edu.princeton.cs.algs4.StdRandom;


// Corner cases.  By convention, the row and column indices are integers between 1 and n,
// where (1, 1) is the upper-left site:
// Throw a java.lang.IllegalArgumentException if any argument to open(), isOpen(), or isFull()
// is outside its prescribed range.
// The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.

public class Percolation {

    public Percolation(int n) {
    } // create n-by-n grid, with all sites blocked

    public static void main(String[] args) {
    } // test client (optional)

    public void open(int row, int col) {
    } // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        return true;
    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        return true;
    } // is site (row, col) full?

    public int numberOfOpenSites() {
        return 0;
    } // number of open sites

    public boolean percolates() {
        return true;
    } // does the system percolate?


}
