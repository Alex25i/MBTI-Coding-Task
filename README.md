# Coding Challenge

Welcome to my solution to the coding challenge.
(Overall handling time: ~4h)

## Installation

To build and run this project, use Java Version 13, and execute the following command in the project directory.

``` bash
./mvnw clean install
```

## Assumptions

I made a few assumptions for this challenge:

1. Adjacent intervals are NOT considered overlapping! Therefore, e.g. the following two intervals will not get
   merged: [1,3] [3,4]
2. The value range of the java Integer is sufficient for the bounds of the interval.
3. It is ok to sort the input: The ordering of the input list of the MERGE-function can be altered and there is no
   requirement preventing that.
4. It is not ok to alter the input elements: The intervals of the input should not be modified.

## Intuition

1. For the output a new list gets created.
2. The input list is sorted by the lower bound of the intervals.
3. The input list is processed sequentially: Each element is compared with the last added element of the output list.
    1. If they overlap: Create a new interval which combines the two intervals and replace it with the element of the
       output list.
    2. If they do not overlap: Add the element to the output list (by reference).

## Complexity analysis

### Time complexity

The sorting has a time complexity of O(n log n).
The merge processing has a complexity of O(n).
Therefore, the overall time complexity is O(n log n).

### Space complexity

The sorting algorithm uses constant or sometimes O(n) extra space.
The merge algorithm uses (less than) O(n) extra space at maximum.
So, the overall space requirement is O(n).
