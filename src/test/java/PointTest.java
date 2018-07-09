import org.junit.Test;

public class PointTest {
    Point[] points = new Point[] {
            new Point(1, 1),
            new Point(2, 2),
            new Point(-1, -1),
            new Point(200, 200)
    };

    @Test
    public void slopeTo() throws Exception {
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] ls = bruteCollinearPoints.segments();
        for (LineSegment line : ls) {
            System.out.println(line);
        }

        System.out.println(bruteCollinearPoints.numberOfSegments());
    }
}