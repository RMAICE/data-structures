public class Searching {
    public static int linearSearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++)
            if (numbers[i] == target)
                return i;

        return -1;
    }

    public static int binarySearch(int[] numbers, int target) {
        return binarySearch(0, numbers.length - 1, numbers, target);
    }
    
    private static int binarySearch(int leftBoundary, int rightBoundary, int[] n, int target) {
        if (rightBoundary < leftBoundary)
            return -1;

        var middle = (leftBoundary + rightBoundary) / 2;
        
        if (target == n[middle])
            return middle;
        
        if (target > n[middle])
            return binarySearch(middle + 1, rightBoundary, n, target);
        
        return binarySearch(leftBoundary, middle - 1, n, target);
    }

    public static int binarySearchIterative(int[] numbers, int target) {
        var leftB = 0;
        var rightB = numbers.length - 1;
        
        while (leftB <= rightB) {
            var m = (leftB + rightB) / 2;
            
            if (target == numbers[m])
                return m;

            if (target < numbers[m])
                rightB = m - 1;
            else
                leftB = m + 1;
        }
        
        return -1;
    }

    public static int ternarySearch(int[] numbers, int target) {
        return ternarySearch(numbers, target, 0, numbers.length - 1);
    }

    private static int ternarySearch(int[] n, int target, int left, int right) {
        if (left > right)
            return -1;
        
        var partitionSize = (right - left) / 3;
        var mid1 = left + partitionSize;
        var mid2 = right - partitionSize;

        if (n[mid1] == target)
            return mid1;
        
        if (n[mid2] == target)
            return mid2;
        
        if (target < n[mid1])
            return ternarySearch(n, target, left, mid1 - 1);
            
        if (target > n[mid2])
            return ternarySearch(n, target, mid2 + 1, right);
        
        // when in right partition
        return ternarySearch(n, target, mid1 + 1, mid2 - 1);
    }

    public static int jumpSearch(int[] n, int target) {
        int blockSize = (int) Math.sqrt(n.length);
        int start = 0;
        int next = blockSize;

        while (start < n.length && n[next - 1] < target) {
            start = next;
            next += blockSize;

            if (next > n.length)
                next = n.length;
        };


        for (int i = start; i < next; i++)
            if (n[i] == target)
                return i;

        return -1;
    }

    public static int exponentialSearch(int[] numbers, int target) {
        var bound = 1;

        while (bound < numbers.length && target > numbers[bound])
            bound *= 2;
        
        var left = bound / 2;
        var right = Math.min(bound, numbers.length - 1);

        return binarySearch(left, right, numbers, target);
    }
}
