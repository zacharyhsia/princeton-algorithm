import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Simulate percolation to get the threshold.
 */
public class PercolationStats {
    private static final double FACTOR = 1.96;
    private final double[] simulationResults;
    private final int trails;

    /**
     * Perform trials independent experiments on an n-by-n grid.
     * @param n grid length
     * @param trials number of trials
     */
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        this.trails = trials;
        simulationResults = new double[trials];
        for (int i = 0; i < trials; i++) {
            simulationResults[i] = calculatePercolationThreshold(n);
        }
    }

    /**
     * Sample mean of percolation threshold.
     * @return mean value of trials
     */
    public double mean() {
        return StdStats.mean(simulationResults);
    }

    /**
     * Sample standard deviation of percolation threshold.
     * @return standard deviation
     */
    public double stddev() {
        return StdStats.stddev(simulationResults);
    }

    /**
     * Low endpoint of 95% confidence interval.
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (FACTOR * Math.sqrt(stddev())) / (Math.sqrt(trails));
    }

    /**
     * High endpoint of 95% confidence interval.
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (FACTOR * Math.sqrt(stddev())) / (Math.sqrt(trails));
    }

    /**
     * Simulate the percolation threshold for grid of size n.
     * @param n grid size
     * @return threshold of this simulation
     */
    private double calculatePercolationThreshold(int n) {
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            percolation.open(row, col);
        }

        return (double) percolation.numberOfOpenSites() / (n * n);
    }

    /**
     * Test client
     * @param args Usage: <Grid length> <Trails>
     */
    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(gridSize, trials);
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats
                .confidenceHi() + "]");
    }
}
