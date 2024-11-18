package assign10;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryMinHeapTest {
    PriorityQueue<Integer> heap;

    @Test
    void add() {
        heap = new BinaryMinHeap<>();
        heap.add(5);
        heap.add(2);
        heap.add(3);
        assertEquals(2, heap.peek());
    }

    @Test
    void peekEmptyHeap() {
        heap = new BinaryMinHeap<>();
        assertThrows(NoSuchElementException.class, () -> heap.peek());
    }

    @Test
    void extract() {
        heap = new BinaryMinHeap<>();
        heap.add(5);
        heap.add(10);
        heap.add(2);
        heap.add(7);
        assertEquals(2, heap.extract());
        assertEquals(3, heap.size());
    }

    @Test
    void extractEmptyHeap() {
        heap = new BinaryMinHeap<>();
        assertThrows(NoSuchElementException.class, () -> heap.extract());
    }

    @Test
    void size() {
        heap = new BinaryMinHeap<>();
        assertEquals(0, heap.size());
        heap.add(10);
        assertEquals(1, heap.size());
    }

    @Test
    void isEmpty() {
        heap = new BinaryMinHeap<>();
        assertTrue(heap.isEmpty());
        heap.add(10);
        assertFalse(heap.isEmpty());
    }

    @Test
    void clear() {
        heap = new BinaryMinHeap<>();
        heap.add(10);
        heap.add(5);
        heap.add(15);
        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void toArray() {
        heap = new BinaryMinHeap<>();
        heap.add(10);
        assertEquals(1, heap.toArray().length);
    }
}