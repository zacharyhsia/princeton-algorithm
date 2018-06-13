import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class UFQuickFindTest {
    int N;

    @Before
    public void setup() {
        File file = new File("src/test/resources/algs4-data/tinyUF.txt");
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setIn(fs);
        N = StdIn.readInt();
    }
    @Test
    public void tinyTest() throws Exception {
        UF uf = new UFQuickFind(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q)) {
                continue;
            } else {
                uf.union(p, q);
                StdOut.println("union: " + p + " " + q);
            }
        }
        StdOut.println(uf.count() + " components.");
        assertEquals(uf.count() , 2);
    }

    @Test
    public void quickUnionTinyTest() {
        UF uf = new UFQuickUnionNaive(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (uf.connected(p, q)) {
                continue;
            } else {
                uf.union(p, q);
                StdOut.println("union: " + p + " " + q);
            }
        }
        StdOut.println(uf.count() + " components.");
        assertEquals(uf.count() , 2);
    }
}