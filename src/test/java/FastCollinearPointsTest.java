import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FastCollinearPointsTest {
    Point[] points = new Point[] {
            new Point(1, 1),
            new Point(2, 2),
            new Point(-1, -1),
            new Point(100, 100),
            new Point(200, 200)
    };

    Point[] points2 = new Point[] {
            new Point(1, 1),
            new Point(2, 2),
            new Point(-1, -1),
            new Point(100, 100),
            new Point(200, 200),
            new Point(0, 2),
            new Point(2, 0),
            new Point(3, -1),
    };

    Point[] duplicatedPoints = new Point[] {
            new Point(1, 1),
            new Point(2, 2),
            new Point(-1, -1),
            new Point(100, 100),
            new Point(200, 200),
            new Point(0, 2),
            new Point(2, 0),
            new Point(1, 1),
    };

    @Test (expected = IllegalArgumentException.class)
    public void exceptionsTest() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void dupulicatedPointsTest() {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(duplicatedPoints);
    }

    @Test
    public void numberOfSegments() throws Exception {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(fastCollinearPoints.numberOfSegments(), 1);

        fastCollinearPoints = new FastCollinearPoints(points2);
        assertEquals(fastCollinearPoints.numberOfSegments(), 2);
    }

    @Test
    public void segments() throws Exception {
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(fastCollinearPoints.segments()[0].toString(),"(-1, -1) -> (200, 200)");
    }

}