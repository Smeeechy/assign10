package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMinHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {
    private Object[] array;
    private int size;
    private Comparator<? super E> cmp;

    public BinaryMinHeap() {
        this.array = new Object[16];
        this.size = 0;
        this.cmp = null;
    }

    public BinaryMinHeap(Comparator<? super E> cmp) {
        this.array = new Object[16];
        this.size = 0;
        this.cmp = cmp;
    }

    public BinaryMinHeap(List<? extends E> list) {
        this.array = new Object[list.size() * 2];
        this.size = list.size();
        this.cmp = null;
        buildHeap(list);
    }

    public BinaryMinHeap(List<? extends E> list, Comparator<? super E> cmp) {
        this.array = new Object[list.size() * 2];
        this.size = list.size();
        this.cmp = cmp;
        buildHeap(list);
    }

    @SuppressWarnings("unchecked")
    private E get(int index) {
        if (index > size) {
            return null;
        }
        return (E) array[index];
    }

    private void buildHeap(List<? extends E> list) {
        for (int i = 1; i <= list.size(); i++) array[i] = list.get(i - 1);
        heapify();
    }
    private void heapify() {
        for (int i = size; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateUp(int index) {
        if (size < 2) return;
        E current = get(index);
        E parent = get(parent(index));
        if (parent != null && innerCompare(current, parent) < 0) {
            swap(index, parent(index));
        }

    }

    private void percolateDown(int index) {
        // find the smaller child
        E current = get(index);
        E left = get(left(index));
        E right = get(right(index));
        if (right != null && left != null && innerCompare(current, left) > 0 && innerCompare(current, right) > 0) {
            // swap with smaller child
            if (innerCompare(left, right) < 0) {
                swap(index, left(index));
                percolateDown(left(index));
            } else {
                swap(index, right(index));
                percolateDown(right(index));
            }
        } else if (left != null && innerCompare(current, left) > 0) {
            swap(index, left(index));
            percolateDown(left(index));
        } else if (right != null && innerCompare(current, right) > 0) {
            swap(index, right(index));
            percolateDown(right(index));
        }

    }

    private int innerCompare(E first, E second) {
        if (this.cmp != null) return cmp.compare(first, second);
        else return first.compareTo(second);
    }

    private int parent(int index) {
        return index / 2;
    }

    private int left(int index) {
        return index * 2;
    }

    private int right(int index) {
        return index * 2 + 1;
    }

    private void swap(int i, int j) {
        E temp = get(i);
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Adds the given element to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param element - element to be added to this priority queue
     */
    @Override
    public void add(E element) {
        array[++size] = element;
        percolateUp(size);
    }

    /**
     * Gets the highest-priority element this priority queue.
     * O(1)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (this.size == 0) throw new NoSuchElementException("heap is empty");
        return get(1);
    }

    /**
     * Gets and removes the highest-priority element this priority queue.
     * O(log N)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extract() throws NoSuchElementException {
        if (this.size == 0) throw new NoSuchElementException("heap is empty");
        E min = get(1);
        array[1] = array[size];
        array[size--] = null;
        percolateDown(1);
        return min;
    }

    /**
     * Gets the number of elements in this priority queue.
     * O(1)
     *
     * @return number of elements in this priority queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Determines whether this priority queue is empty.
     * O(1)
     *
     * @return true if this priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Empties this priority queue of elements.
     * O(1)
     */
    @Override
    public void clear() {
        this.array = new Object[16];
        this.size = 0;
    }

    /**
     * Generates an array of the elements in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is used for grading. The root element
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 1; i <= size; i++) result[i - 1] = array[i];
        return result;
    }
}
