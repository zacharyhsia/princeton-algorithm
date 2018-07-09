import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A faster solution based on sorting.
 */
public class FastCollinearPoints {
    private static final double EPSILON = 0.0000001;
    private static final int MIN_POINTS = 3;
    private final List<LineSegment> lineSegments = new ArrayList<>();
    private final List<LSInPointSlope> lsInPointSlopes = new ArrayList<>();

    /**
     * Constructor takes an array of points.
     * @param points points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        Point[] copyPoints = Arrays.copyOf(points, points.length);

        Arrays.sort(copyPoints);

        Point lastPoint = null;

        for (int i = 0; i < copyPoints.length; i++) {
            if (copyPoints[i] == null) {
                throw new IllegalArgumentException("points cannot be null");
            }

            if (lastPoint != null) {
                if (copyPoints[i].compareTo(lastPoint) == 0) {  // find duplicated point
                    throw new IllegalArgumentException("cannot contain a repeated point");
                }
            }

            lastPoint = copyPoints[i];

            List<Point> otherPoints = new ArrayList<>();

            // add all other points to a list
            for (int j = 0; j < copyPoints.length; j++) {
                if (j == i) {
                    continue;
                }
                otherPoints.add(copyPoints[j]);
            }

            // sort the list by slope w.r.t the base point
            otherPoints.sort(copyPoints[i].slopeOrder());
            findLineSegmentsForPoint(copyPoints[i], otherPoints);
        }
    }

    /**
     * Get the number of line segments.
     * @return number of line segment
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }

    /**
     * Return the array of line segments.
     * @return line segments
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    /**
     * Represent a LineSegment in format of minPoint-slope.
     */
    private class LSInPointSlope {
        private final Point minPoint;
        private final double slope;

        LSInPointSlope(Point minPoint, double slope) {
            this.minPoint = minPoint;
            this.slope = slope;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() != LSInPointSlope.class) {
                return false;
            }

            LSInPointSlope that = (LSInPointSlope) obj;
            return this.minPoint.compareTo(that.minPoint) == 0
                    && that.slope == that.slope;
        }

        @Override
        public int hashCode() {
            return ((Double) slope).hashCode();
        }
    }

    /**
     *  Find LineSegments for point base.
     * @param base the base point
     * @param others other points sorted by the slope w.r.t the base point
     */
    private void findLineSegmentsForPoint(Point base, List<Point> others) {
        if (others.isEmpty()) {
            return;
        }

        double lastSlope = base.slopeTo(others.get(0));
        int count = 1;

        for (int i = 1; i < others.size(); i++) {
            double curSlope = base.slopeTo(others.get(i));

            if (slopeEquals(curSlope, lastSlope)) {
                count++;
            }

            if (i == others.size() - 1) {
                if (count >= MIN_POINTS) {
                    convert2PointSlope(base, others.subList(i - count + 1, i + 1));
                }
                count = 1;
            }

            if (curSlope != lastSlope) {
                if (count >= MIN_POINTS) {
                    convert2PointSlope(base, others.subList(i - count, i));
                }
                count = 1;
            }

            lastSlope = curSlope;
        }
    }

    /**
     * Find the min point of the given points that on the same line.
     * @param base base point
     * @param points other points
     */
    private void convert2PointSlope(Point base, List<Point> points) {
        List<Point> pointsOnLine = new ArrayList<>(points);
        pointsOnLine.add(base);
        Collections.sort(pointsOnLine);

        Point minPoint = pointsOnLine.get(0);
        Point maxPoint = pointsOnLine.get(pointsOnLine.size() - 1);
        double slope = minPoint.slopeTo(pointsOnLine.get(1));
        LSInPointSlope ls = new LSInPointSlope(minPoint, slope);

        boolean found = false;
        for (LSInPointSlope existingLS : lsInPointSlopes) {
            if (existingLS.equals(ls)) {
                found = true;
            }
        }

        if (!found) {
            lsInPointSlopes.add(ls);
            lineSegments.add(new LineSegment(minPoint, maxPoint));
        }
    }

    private boolean slopeEquals(double slope1, double slope2) {
        if (slope1 == Double.POSITIVE_INFINITY) {
            return slope2 == Double.POSITIVE_INFINITY;
        }

        if (slope2 == Double.POSITIVE_INFINITY) {
            return false;
        }
        return Math.abs(slope1 - slope2) < EPSILON;
    }
}
