import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntervalTest {

    @Test
    void intervalConstructor() {
        Interval interval = new Interval(0, 1);
        assertThat(interval.getLow()).isEqualTo(0);
        assertThat(interval.getHigh()).isEqualTo(1);
    }

    @Test
    void intervalInvalid1() {
        assertThatThrownBy(() -> new Interval(0, 0))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessage("The low bound of the interval can not be greater or equal to the " +
                        "high bound of the interval.");
    }

    @Test
    void intervalInvalid2() {
        assertThatThrownBy(() -> new Interval(0, -5))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessage("The low bound of the interval can not be greater or equal to the " +
                        "high bound of the interval.");
    }

    @Nested
    class IntervalCompareTo {
        @Test
        void intervalCompareTo1() {
            Interval interval1 = new Interval(0, 1);
            Interval interval2 = new Interval(2, 4);
            assertThat(interval1.compareTo(interval2)).isLessThan(0);
        }

        @Test
        void intervalCompareTo2() {
            Interval interval1 = new Interval(0, 1);
            Interval interval2 = new Interval(2, 4);
            assertThat(interval2.compareTo(interval1)).isGreaterThan(0);
        }

        @Test
        void intervalCompareTo3() {
            Interval interval1 = new Interval(0, 1);
            Interval interval2 = new Interval(0, 1);
            assertThat(interval1.compareTo(interval2)).isEqualTo(0);
        }

        @Test
        void intervalCompareTo4() {
            Interval interval1 = new Interval(0, 1);
            Interval interval2 = new Interval(0, 1);
            assertThat(interval1.compareTo(interval2)).isEqualTo(0);
        }
    }

    @Nested
    class ToString {
        @Test
        void toString1() {
            Interval interval = new Interval(0, 1);
            assertThat(interval.toString()).isEqualTo("[0, 1]");
        }

        @Test
        void toString2() {
            Interval interval = new Interval(-1, 1);
            assertThat(interval.toString()).isEqualTo("[-1, 1]");
        }
    }
}