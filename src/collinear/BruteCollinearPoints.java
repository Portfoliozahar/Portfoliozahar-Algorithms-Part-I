package src.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        validate(points);

        Point[] copy = points.clone();
        Arrays.sort(copy);

        List<LineSegment> found = new ArrayList<>();

        int n = copy.length;

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {

                        double slope1 = copy[i].slopeTo(copy[j]);
                        double slope2 = copy[i].slopeTo(copy[k]);
                        double slope3 = copy[i].slopeTo(copy[l]);

                        if (Double.compare(slope1, slope2) == 0
                                && Double.compare(slope1, slope3) == 0) {

                            found.add(
                                    new LineSegment(copy[i], copy[l])
                            );
                        }
                    }
                }
            }
        }

        segments = found.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    private static void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        Point[] copy = points.clone();

        for (Point point : copy) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        Arrays.sort(copy);

        for (int i = 1; i < copy.length; i++) {
            if (copy[i].compareTo(copy[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }
}