package src.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    public static void main(String[] args) {

        Point[] points = {
                new Point(1000, 1000),
                new Point(2000, 2000),
                new Point(3000, 3000),
                new Point(4000, 4000),

                new Point(1000, 2000),
                new Point(2000, 3000)
        };

        FastCollinearPoints collinear =
                new FastCollinearPoints(points);

        System.out.println(
                "Segments: " + collinear.numberOfSegments()
        );

        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment);
        }
    }

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        validate(points);

        Point[] copy = points.clone();
        Arrays.sort(copy);

        List<LineSegment> found = new ArrayList<>();

        int n = copy.length;

        for (int i = 0; i < n; i++) {

            Point origin = copy[i];

            Point[] sorted = copy.clone();

            Arrays.sort(sorted, origin.slopeOrder());

            int start = 1;

            while (start < n) {

                double slope = origin.slopeTo(sorted[start]);

                int end = start + 1;

                while (end < n
                        && Double.compare(
                        origin.slopeTo(sorted[end]),
                        slope
                ) == 0) {

                    end++;
                }

                if (end - start >= 3) {

                    Point min = origin;
                    Point max = origin;

                    for (int j = start; j < end; j++) {

                        if (sorted[j].compareTo(min) < 0) {
                            min = sorted[j];
                        }

                        if (sorted[j].compareTo(max) > 0) {
                            max = sorted[j];
                        }
                    }

                    if (origin.compareTo(min) == 0) {
                        found.add(
                                new LineSegment(min, max)
                        );
                    }
                }

                start = end;
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
