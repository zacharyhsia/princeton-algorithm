import edu.princeton.cs.algs4.Quick;

import java.util.ArrayList;
import java.util.List;

/**
 * A brute force method that examines 4 points at a time
 * and checks whether they all lie on the same line segment,
 * returning all such line segments.
 */
public class BruteCollinearPoints {
    private static final double EPSILON = 0.0000001;

    private final List<LineSegment> lineSegmentList = new ArrayList<>();

    /**
     * Constructor takes an array of points.
     * @param points points
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        Quick.sort(points);

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int m = j + 1; m < points.length; m++) {
                    for (int n = m + 1; n < points.length; n++) {

                        if (points[i] == null ||
                                points[j] == null ||
                                points[m] == null ||
                                points[n] == null) {
                            throw new IllegalArgumentException("points cannot be null");
                        }
                        double slopeIJ = points[i].slopeTo(points[j]);
                        double slopeIK = points[i].slopeTo(points[m]);
                        double slopeIL = points[i].slopeTo(points[n]);

                        if (slopeIJ == Double.NEGATIVE_INFINITY ||
                                slopeIK == Double.NEGATIVE_INFINITY ||
                                slopeIL == Double.NEGATIVE_INFINITY) {
                            throw new IllegalArgumentException("cannot contain a repeated point");
                        }

                        if (slopeEquals(slopeIJ, slopeIK) && slopeEquals(slopeIJ, slopeIL)) {
                            lineSegmentList.add(new LineSegment(points[i], points[n]));
                        }
                    }
                }
            }
        }
    }

    /**
     * Get the number of line segments.
     * @return number of line segment
     */
    public int numberOfSegments() {
        return lineSegmentList.size();
    }

    /**
     * Return the array of line segments.
     * @return line segments
     */
    public LineSegment[] segments() {
        return lineSegmentList.toArray(new LineSegment[lineSegmentList.size()]);
    }

    private boolean slopeEquals(double slope1, double slope2) {
        return Math.abs(slope1 - slope2) < EPSILON;
    }
}
