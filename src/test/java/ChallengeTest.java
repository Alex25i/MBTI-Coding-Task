import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChallengeTest {

    @Test
    void mergeEmpty() {
        List<Interval> intervals = new LinkedList<>();
        List<Interval> result = Challenge.merge(intervals);

        assertThat(result)
                .isInstanceOf(List.class)
                .isEmpty();
    }

    @Test
    void mergeNull() {
        assertThatThrownBy(() -> Challenge.merge(null))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessage("Parameter 'intervals' must not be null.");
    }

    @Test
    void mergeSingeInterval() {
        Interval interval1 = new Interval(25, 30);
        List<Interval> intervals = new LinkedList<>(List.of(interval1));
        List<Interval> result = Challenge.merge(intervals);

        assertThat(result)
                .isInstanceOf(List.class)
                .hasSize(1)
                .hasOnlyElementsOfType(Interval.class);
    }


    @Test
    void mergeSingeIntervalInputCheck() {
        Interval interval1 = new Interval(25, 30);
        List<Interval> intervals = new LinkedList<>(List.of(interval1));
        List<Interval> result = Challenge.merge(intervals);

        // no elements from input should get removed
        assertThat(intervals.size()).isEqualTo(1);
    }


    @Nested
    class HappyCase {
        @Test
        void mergeHappyCaseInputCheck() {
            // [25,30] [2,19] [14,23] [4,8]
            Interval interval1 = new Interval(25, 30);
            Interval interval2 = new Interval(2, 19);
            Interval interval3 = new Interval(14, 23);
            Interval interval4 = new Interval(4, 8);
            List<Interval> intervals = new LinkedList<>(List.of(interval1, interval2, interval3, interval4));
            List<Interval> result = Challenge.merge(intervals);

            // no elements from input should get removed
            assertThat(intervals.size()).isEqualTo(4);
        }

        @Test
        void mergeHappyCase1() {
            // [25,30] [2,19] [14,23] [4,8]
            Interval interval1 = new Interval(25, 30);
            Interval interval2 = new Interval(2, 19);
            Interval interval3 = new Interval(14, 23);
            Interval interval4 = new Interval(4, 8);
            List<Interval> intervals = new LinkedList<>(List.of(interval1, interval2, interval3, interval4));
            List<Interval> result = Challenge.merge(intervals);

            // check for output: [2,23] [25,30]
            assertThat(result)
                    .isInstanceOf(List.class)
                    .hasSize(2)
                    .hasOnlyElementsOfType(Interval.class);

            // first interval
            assertThat(result.get(0).getLow())
                    .isEqualTo(2);
            assertThat(result.get(0).getHigh())
                    .isEqualTo(23);

            // second interval
            assertThat(result.get(1).getLow())
                    .isEqualTo(25);
            assertThat(result.get(1).getHigh())
                    .isEqualTo(30);
        }

        @Test
        void mergeHappyCase2() {
            // [25,30] [2,19] [14,23] [4,8]
            Interval interval1 = new Interval(25, 30);
            Interval interval2 = new Interval(-10, 19);
            Interval interval3 = new Interval(14, 23);
            Interval interval4 = new Interval(4, 8);
            List<Interval> intervals = new LinkedList<>(List.of(interval1, interval2, interval3, interval4));
            List<Interval> result = Challenge.merge(intervals);

            // check for output: [2,23] [25,30]
            assertThat(result)
                    .isInstanceOf(List.class)
                    .hasSize(2)
                    .hasOnlyElementsOfType(Interval.class);

            // first interval
            assertThat(result.get(0).getLow())
                    .isEqualTo(-10);
            assertThat(result.get(0).getHigh())
                    .isEqualTo(23);

            // second interval
            assertThat(result.get(1).getLow())
                    .isEqualTo(25);
            assertThat(result.get(1).getHigh())
                    .isEqualTo(30);
        }


    }


}