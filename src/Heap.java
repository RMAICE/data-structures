public class Heap {
    private int[] items = new int[10];
    private int size = 0;
    
    public Heap() {}
    
    public Heap(int[] n) {
        items = n;
        size = items.length - 1;

        for (int i = 0; i < items.length; i++) {
            bubbleDown(i);
        }
    }

    public void insert(int value) {
        if (isFull())
            throw new IllegalStateException();

        items[size++] = value;

        bubbleUp();
    }

    public boolean isFull() {
        return size == items.length;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void bubbleUp() {
        var itemPos = size - 1;

        while (itemPos > 0 && items[itemPos] > items[parent(itemPos)]) {
            swap(parent(itemPos), itemPos);

            itemPos = parent(itemPos);
        }
    }

    private void swap(int first, int second) {
        var temp = items[first];

        items[first] = items[second];
        items[second] = temp;
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();

        var root = items[0];

        items[0] = items[--size];

        bubbleDown(0);

        return root;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleDown(int index) {
        var itemPos = index;
        
        while (itemPos <= size && !isValidParent(itemPos)) {
            var nextPos = getLargerChildPos(itemPos);
            
            swap(itemPos, nextPos);

            itemPos = nextPos;
        }
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) return true;

        var isValid = items[index] >= leftChild(index);

        if (hasRightChild(index))
            isValid &= items[index] >= rightChild(index);

        return isValid;
    }

    private int getLargerChildPos(int index) {
        if (!hasLeftChild(index))
            return index;

        if (!hasRightChild(index))
            return left(index);

        return leftChild(index) > rightChild(index)
            ? left(index)
            : right(index);
    }

    private boolean hasLeftChild(int index) {
        return left(index) <= size;
    }

    private boolean hasRightChild(int index) {
        return right(index) <= size;
    }

    private int leftChild(int parent) {
        return items[left(parent)];
    }

    private int rightChild(int parent) {
        return items[right(parent)];
    }

    private int left(int parent) {
        return parent * 2 + 1;
    }

    private int right(int parent) {
        return parent * 2 + 2;
    }

    public int max() {
        if (isEmpty())
            throw new IllegalStateException();

        return items[0];
    }
}
