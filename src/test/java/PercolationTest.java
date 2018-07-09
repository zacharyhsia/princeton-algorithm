import week1.Percolation;
import week1.PercolationVisualizer;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PercolationTest {
    private Percolation percolationFromFile(String filePath) {
//        File file = new File("src/test/resources/algs4-data/tinyUF.txt");
        File file = new File(filePath);
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setIn(fs);
        int gridSize = StdIn.readInt();
        try {
            assert fs != null;
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Percolation(gridSize);
    }

    @Test
    public void input1To8() {
        // loop from input1.txt to input8.txt
        for (int i = 1; i < 9; i++) {
            String path = "src/test/resources/percolation/input" + i + ".txt";
            StdOut.println("Test: input" + i);
            Percolation percolation = percolationFromFile(path);
            while (!StdIn.isEmpty()) {
                int row = StdIn.readInt();
                int col = StdIn.readInt();

                percolation.open(row, col);
            }
            StdOut.println("percolated? " + percolation.percolates());
        }
    }

    @Test
    public void input2() {
        String path = "src/test/resources/percolation/input50.txt";
//        StdOut.println("Test: input" + 10);
        Percolation percolation = percolationFromFile(path);
        int i = 1;
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();

            percolation.open(row, col);
            System.out.println("" + (i++) + "open: " + row + " " + col);
        }
        StdOut.println("percolated? " + percolation.percolates());
    }

    @Test
    public void randomPercTest() {
        // n-by-n percolation system (read from command-line, default = 10)
        int n = 10;

        // repeatedly open site specified my mouse click and draw resulting system
        StdDraw.enableDoubleBuffering();
        Percolation perc = new Percolation(n);
        PercolationVisualizer.draw(perc, n);
        StdDraw.show();

        while (!perc.percolates()) {

            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);

            perc.open(row, col);
            // draw n-by-n percolation system
            PercolationVisualizer.draw(perc, n);
            StdDraw.show();

            StdDraw.pause(20);
        }
    }
}