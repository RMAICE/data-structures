import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Sorting {
    public static int[] bubbleSort(int[] numbers) {
        int[] n = numbers.clone();
        boolean isSorted;

        for (int j = 0; j < n.length; j++) {
            isSorted = true;

            for (int i = 1; i < n.length - j; i++)
                if (n[i - 1] > n[i]) {
                    swap(n, i, i-1);
                    isSorted = false;
                }

            if (isSorted)
                return n;
        }

        return n;
    }

    private static void swap(int[] n, int index, int index2) {
        var temp = n[index];
        n[index] = n[index2];
        n[index2] = temp;
    }

    public static int[] selectionSort(int[] numbers) {
        var n = numbers.clone();
        for (int i = 0; i < n.length; i++) {
            swap(n, i, findMinIndex(n, i));
        }
        return n;
    }

    private static int findMinIndex(int[] n, int i) {
        int minNdx = i;
        for (int j = i + 1; j < n.length; j++)
            minNdx = n[j] < n[minNdx] ? j : minNdx;
        return minNdx;
    }

    public static int[] insertionSort(int[] numbers) {
        var n = numbers.clone();

        // 7, 3, 1, 4, 6, 2, 3

        for (int i = 1; i < n.length; i++) {
            var current = n[i];
            var j = i - 1;

            while (j >= 0 && n[j] > current) {
                n[j + 1] = n[j];
                j--;
            }

            n[j + 1] = current;
        }

        return n;
    }

    public static int[] mergeSort(int[] numbers) {
        return mergeSortInternal(numbers.clone());
    }

    private static int[] mergeSortInternal(int[] numbers) {
        if (numbers.length <= 1)
            return numbers;
        
        int middle = numbers.length / 2;

        int[] left = new int[middle];
        for (int i = 0; i < left.length; i++)
            left[i] = numbers[i];

        int[] right = new int[numbers.length - middle];
        for (int i = middle; i < numbers.length; i++)
            right[i - middle] = numbers[i];

        mergeSortInternal(left);
        mergeSortInternal(right);

        return mergeSorting(left, right, numbers);
    }

    private static int[] mergeSorting(int[] left, int[] right, int[] result) {
        int i = 0, lp = 0, rp = 0;

        while (lp < left.length && rp < right.length) {
            if (left[lp] <= right[rp])
                result[i++] = left[lp++];
            else
                result[i++] = right[rp++];
        }

        while (lp < left.length)
            result[i++] = left[lp++];
        
        while (rp < right.length)
            result[i++] = right[rp++];

        return result;
    }

    public static int[] quickSort(int[] numbers) {
        int[] n = numbers.clone();
        qSort(n, 0, n.length - 1);
        return n;
    }

    private static void qSort(int[] n, int start, int end) {
        if (start >= end)
            return;

        int boundary = partition(n, start, end);

        qSort(n, start, boundary - 1);
        qSort(n, boundary + 1, end);
    }

    private static int partition(int[] n, int start, int end) {
        var pivot = n[end];
        var boundary = start - 1;

        for (var i = start; i <= end; i++) {
            var current = n[i];

            if (current <= pivot)
                swap(n, ++boundary, i);
        }

        return boundary;
    }

    // pros: fast
    // cons:
    // - uses more space
    // - values must be positive int
    // - most of values are present
    public static int[] countSort(int[] numbers) {
        int[] n = numbers.clone();
        int[] countsArr = new int[findMax(n) + 1];

        for (int c : n)
            countsArr[c]++;

        var j = 0;
        for (var i = 0; i < countsArr.length; i++)
            for (var k = 0; k < countsArr[i]; k++)
                n[j++] = i;

        return n;
    }

    private static int findMax(int[] n) {
        int max = 0;
        for (int current : n)
            if (current > max) max = current;
        return max;
    }

    public static int[] bucketSort(int[] numbers, int numberOfBuckets) {
        var n = numbers.clone();

        int j = 0;
        for (var bucket : createBuckets(n, numberOfBuckets)) {
            Collections.sort(bucket);
            for (var value : bucket) {
                n[j++] = value;
            }
        }

        return n;
    }

    private static List<List<Integer>> createBuckets(int[] n, int numberOfBuckets) {
        List<List<Integer>> buckets = new LinkedList<>();

        for (int i = 0; i < numberOfBuckets; i++)
            buckets.add(new ArrayList<>());

        for (var item : n)
            buckets.get(Math.min(numberOfBuckets, item / numberOfBuckets)).add(item);

        return buckets;
    }
}
