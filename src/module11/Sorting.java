package Module11;

import java.util.*;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     * <p>
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     * <p>
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     * <p>
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     * <p>
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (arr.length == 1) {
            return;
        }

        // split array
        int length = arr.length;
        int midIndex = length / 2;
        T[] leftArray = getSubArray(arr, 0, midIndex - 1);
        T[] rightArray = getSubArray(arr, midIndex, length - 1);

        // sort the two sub arrays
        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        // sort the array
        int i = 0; int j = 0;
        int leftLength = leftArray.length; int rightLength = rightArray.length;

        while (i < leftLength && j < rightLength) {
            if (comparator.compare(leftArray[i],rightArray[j]) <= 0) {
                arr[i+j] = leftArray[i];
                i++;
            } else {
                arr[i+j] = rightArray[j];
                j++;
            }
        }

        while (i < leftLength) {
            arr[i+j] = leftArray[i];
            i++;
        }
        while (j < rightLength) {
            arr[i+j] = rightArray[j];
            j++;
        }
    }

    private static <T> T[] getSubArray(T[] arr , int startIndex, int endIndex) {
        T[] newArray = (T[]) new Object[endIndex - startIndex + 1];

        int currIndex = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            newArray[currIndex] = arr[i];
            currIndex++;
        }

        return newArray;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     * <p>
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     * <p>
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     * <p>
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     * <p>
     * Do NOT use anything from the Math class except Math.abs().
     * <p>
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>(19);
        int maxDigits = getMaxDigits(arr);

        for (int iteration = 0; iteration < maxDigits; iteration++) {
            for (int number: arr) {
                int digit = getNthDigits(number, iteration + 1);
                buckets.get(Math.abs(digit + 9)).addLast(number); // the arraylist elt is a linkedlist
            }
        }

        int index = 0;
        for (LinkedList<Integer> bucket : buckets) {
            while (!bucket.isEmpty()) {
                arr[index] = bucket.removeFirst();
                index++;
            }
        }
    }

    /**
     * Get the nth digit in an int number.
     * Digits are sorted right to left and are 1-indexed.
     * if given number = 143 and numberOfDigit = 2
     * it will return 4
     */
    private static int getNthDigits(int number, int numberOfDigit) {
        int nthDigit = 0;
        for (int i = 0; i < numberOfDigit; i++) {
            nthDigit = number % 10;
            number = number / 10;
        }
        return nthDigit;
    }

    /**
     * Find the number of digits in the longest number
     * @param arr the array we'll look in
     * @return number of digits in the longest number
     */
    private static int getMaxDigits(int[] arr) {
        int maxDigits = 0;

        for (int number : arr) {
            int digitsNumber = getDigitsNumber(number);
            if (maxDigits < digitsNumber) {
                maxDigits = digitsNumber;
            }
        }

        return maxDigits;
    }

    private static int getDigitsNumber(int number) {
        int digitsNumber = 1;
        while (number > 9) {
            number = number / 10;
            digitsNumber++;
        }
        return digitsNumber;
    }
}
