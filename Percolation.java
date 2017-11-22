// Import all the classes here from algs4
// e.g. import edu.princeton.cs.algs4.StdRandom;


// Corner cases.  By convention, the row and column indices are integers between 1 and n,
// where (1, 1) is the upper-left site:
// Throw a java.lang.IllegalArgumentException if any argument to open(), isOpen(), or isFull()
// is outside its prescribed range.
// The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int n;
    private WeightedQuickUnionUF wquf;

    public Percolation(int n) {

        this.wquf = new WeightedQuickUnionUF(n * n + 2); // n x n grid and 2 virtual nodes at the top and bottom

        this.n = n;

        if (n <= 0) throw new java.lang.IllegalArgumentException();

        this.grid = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.grid[i][j] = false;
            }
        }

    } // create n-by-n grid, with all sites blocked


    public void open(int row, int col) {

        if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();

        if (!isOpen(row, col)) {
            this.grid[row - 1][col - 1] = true;
            connectOpen(row, col); // Connect with open neighbours
        }


    } // open site (row, col) if it is not open already

    public void connectOpen(int row, int col) {

        row--;
        col--;

        int rowStart = Math.max(row - 1, 0);
        int rowFinish = Math.min(row + 1, this.n - 1);
        int colStart = Math.max(col - 1, 0);
        int colFinish = Math.min(col + 1, this.n - 1);

        int currentCell = this.n * row + col + 1;
        int neighbourCell;

        if (currentCell <= this.n) wquf.union(currentCell, 0); // Connect to virtual top

        if (currentCell > this.n * this.n - this.n)
            wquf.union(currentCell, this.n * this.n + 1); //connect to virtual bottom

        for (int curRow = rowStart; curRow <= rowFinish; curRow++) {
            for (int curCol = colStart; curCol <= colFinish; curCol++) {
                if (curCol == col || curRow == row) {
                    if (!(curCol == col && curRow == row)) {
                        if (isOpen(curRow + 1, curCol + 1)) {
                            neighbourCell = this.n * curRow + curCol + 1;
                            if (!wquf.connected(currentCell, neighbourCell)) {
                                wquf.union(currentCell, neighbourCell);
                            }
                        }
                    }

                }
            }
        }

    } //row, col are coordinates of the site that I am checking; 1 based indexing as parameters

    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();

        return this.grid[row - 1][col - 1];

    } // is site (row, col) open?; 1 based indexing as parameters

    public boolean isFull(int row, int col) {

        if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();


        row--;
        col--;

        int currentCell = this.n * row + col + 1;

        if (wquf.find(currentCell) == wquf.find(0)){
            return true;
        }
        else {
            return false;
        }
    } // is site (row, col) full?; 1 based indexing as parameters

    public int numberOfOpenSites() {

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.grid[i][j]) result += 1;
            }
        }

        return result;
    } // number of open sites

    public boolean percolates() {

        // Check bottom root id of top and bottom virtual id
        if (wquf.find(this.n * this.n + 1) == wquf.find(0)){
            return true;
        }
        else {
            return false;
        }

    } // does the system percolate?

    public static void main(String[] args) {

        Percolation obj = new Percolation(5);

//        System.out.println("Number of components at the beginning: " + obj.wquf.count());

        obj.open(1, 1);
        obj.open(2, 1);
        obj.open(3, 1);
        obj.open(4, 1);
        obj.open(5, 1);

        System.out.println("Percolates? " + obj.percolates());
//        System.out.println("Number of components at the end: " + obj.wquf.count());

    } // test client (optional)


}
