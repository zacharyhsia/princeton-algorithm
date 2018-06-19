import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class PermutationTest {
    @Test
    public void main() throws Exception {
        File file = new File("src/test/resources/queues/tale.txt");
        FileInputStream fs = null;

        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setIn(fs);
        int k = 4;
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        for (String str : StdIn.readAllStrings()) {
            randomizedQueue.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }

}