public class PriorityQueue {
    private int[] queue = new int[5];
    int size;

    public void insert(int number) {
        if (queue.length == size) throw new IllegalStateException();
        int i;

        for (i = size++; i > 0; i--) {
            if (queue[i - 1] < number) break;
            queue[i] = queue[i - 1];
        }
        
        queue[i] = number;
    }

}
