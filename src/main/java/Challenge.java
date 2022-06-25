import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class which contains the merge function of the challenge.
 */
public class Challenge {

    /**
     * Takes a list of {@link Interval} and merges overlapping ones.
     * Adjacent intervals are NOT considered overlapping.
     *
     * @param intervals A list of intervals
     * @return A new list with the intervals from the input where overlapping intervals are merged.
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null) {
            throw new InvalidParameterException("Parameter 'intervals' must not be null.");
        }

        // If input is empty: Return interval
        if (intervals.isEmpty()) {
            return intervals;
        }

        Collections.sort(intervals); // input gets changed here! -> Assumption: It is ok to sort the input.
        LinkedList<Interval> outputIntervals = new LinkedList<>();

        // Go through the list sequentially.
        // Take two successive elements of the list and decide weather they should be merged.
        for (Interval interval : intervals) {
            // if the output is still empty or the current interval doesn't overlap with the last one
            // append the current one to output
            // Assumption: Adjacent intervals are NOT considered overlapping!
            if (outputIntervals.isEmpty() || interval.getLow() >= outputIntervals.getLast().getHigh()) {
                outputIntervals.add(interval); // reference to object from input gets added here.
            } else {
                // the last interval does overlap with the current interval
                Interval iLast = outputIntervals.getLast();
                int high = Integer.max(interval.getHigh(), iLast.getHigh());
                Interval iMerged = new Interval(iLast.getLow(), high);

                outputIntervals.removeLast();
                outputIntervals.add(iMerged);
            }
        }
        return outputIntervals;
    }
}

