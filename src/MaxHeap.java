public class MaxHeap {
    public static void heapify(int[] numbers) {
        // for (int i = 0; i < numbers.length; i++)
        var lastParentIndex = (numbers.length / 2) - 1;
        for (int i = lastParentIndex; i >= 0; i--) // optimized
            heapify(numbers, i);
    }

    private static void heapify(int[] numbers, int index) {
        var target = index;
        var left = index * 2 + 1;
        var right = index * 2 + 2;

        if (left < numbers.length && numbers[target] < numbers[left])
            target = left;

        if (right < numbers.length && numbers[target] < numbers[right])
            target = right;

        if (target == index) return;

        swap(numbers, target, index);
        heapify(numbers, target);
    }

    private static void swap(int[] array, int first, int second) {
        var temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static int findKthLargest(int[] numbers, int k) {
        var heap = new Heap();

        for (int i : numbers) {
            heap.insert(i);
        }

        for (int i = 0; i < k - 1; i++)
            heap.remove();

        return heap.max();
    }
}
