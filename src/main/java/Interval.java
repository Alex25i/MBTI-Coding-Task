import java.security.InvalidParameterException;

/**
 * Class which represents an interval.
 *
 * Assumption: Limits of the Interval is in the {@link Integer} value range.
 */
public class Interval implements Comparable<Interval> {
    private Integer low;
    private Integer high;

    /**
     * Creates a new Interval with given bounds.
     * The lower bound must be smaller than the higher one.
     *
     * @param low  Lower bound of the interval
     * @param high Higher bound of the interval
     */
    public Interval(int low, int high) throws InvalidParameterException{
        if (low >= high){
            throw new InvalidParameterException("The low bound of the interval can not be greater or equal to the " +
                    "high bound of the interval.");
        }

        this.low = low;
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public Integer getHigh() {
        return high;
    }

    /**
     * Compares this current object with another object of the same type and returns an integer that indicates whether
     * the current instance precedes, follows, or occurs in the same position in the sort order as the other object.
     * <p>
     * The order follows the natural order of the low value.
     *
     * @param r The object to be compared.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     */
    @Override
    public int compareTo(Interval r) {
        return this.getLow().compareTo(r.getLow());
    }

    /**
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("[%d, %d]", this.getLow(), this.getHigh());
    }

}
