import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation using union find.
 */
public class Percolation {
    private static final int DIRECTION_NUM = 4;
    private int numOfOpenSites;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final boolean[][] grid;
    private final int virtualHead;
    private final int virtualTail;

    /**
     * create n-by-n grid, with all sites blocked
     * @param n grid size
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size cannot be smaller than 1.");
        }
        this.n = n;
        numOfOpenSites = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
        virtualHead = 0;
        virtualTail = n * n + 1;
        grid = new boolean[n][n];
    }

    private int getSiteId(int row, int col) {
        return col + (row - 1) * this.n;
    }

    /**
     * Open site (row, col) if it is not open already
     * @param row row
     * @param col column
     */
    public void open(int row, int col) {
        if (!inBound(row, col)) {
            throw new IllegalArgumentException();
        }

        if (isOpen(row, col)) {
            return;
        }
        
        // change the site state to open
        grid[row - 1][col - 1] = true;
        
        // if it is in the first row, union it with the virtual head & Tail
        if (row == 1) {
            uf.union(getSiteId(row, col), virtualHead);
        }

        if (row == n) {
            uf.union(getSiteId(row, col), virtualTail);
        }
        
        int[] directionX = {-1, 1, 0, 0};
        int[] directionY = {0, 0, -1, 1};

        for (int i = 0; i < DIRECTION_NUM; i++) {
            int neighborX = row + directionX[i];
            int neighborY = col + directionY[i];

            if (inBound(neighborX, neighborY) && isOpen(neighborX, neighborY)) {
                uf.union(getSiteId(row, col), getSiteId(neighborX, neighborY));
            }
        }

        numOfOpenSites++;
    }

    private boolean inBound(int x, int y) {
        return x > 0 && x <= n && y > 0 && y <= n;
    }

    /**
     * Check if the site is open
     * @param row row
     * @param col column
     * @return true for open, false for not
     */
    public boolean isOpen(int row, int col) {
        if (!inBound(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1];
    }

    /**
     * Check if the site is full (connected to virtual head)
     * @param row row
     * @param col column
     * @return true fo full
     */
    public boolean isFull(int row, int col) {
        if (!inBound(row, col)) {
            throw new IllegalArgumentException();
        }
        return uf.connected(getSiteId(row, col), virtualHead);
    }

    /**
     * Get the number of open sites
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    /**
     * Check if the grid is percolated
     * @return true if percolated
     */
    public boolean percolates() {
        return uf.connected(virtualHead, virtualTail);
    }
}
