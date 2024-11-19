package assign10;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryMinHeapTest {
    PriorityQueue<Integer> heap;

    // testing BinaryMinHeap()
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
    // testing BinaryMinHeap(Comparator<? super E> cmp)
    @Test
    void cmpAdd() {
        heap = new BinaryMinHeap<>((a,b) -> b - a);
        heap.add(5);
        heap.add(2);
        heap.add(3);
        assertEquals(5, heap.peek());
    }

    @Test
    void cmpExtract() {
        heap = new BinaryMinHeap<>((a,b) -> b - a);
        heap.add(5);
        heap.add(10);
        heap.add(2);
        heap.add(7);
        assertEquals(10, heap.extract());
        assertEquals(3, heap.size());
        assertEquals(7, heap.extract());
        assertEquals(2, heap.size());
    }
    // testing BinaryMinHeap(List<? extends E> list)
    @Test
    void listAdd() {
        heap = new BinaryMinHeap<>(List.of(1,2,65,10,2,32));
        heap.add(5);
        heap.add(2);
        heap.add(3);
        assertEquals(1, heap.peek());
    }

    @Test
    void listExtract() {
        heap = new BinaryMinHeap<>(List.of(1,2,65,10,2,32));
        heap.add(5);
        heap.add(10);
        heap.add(2);
        heap.add(7);
        assertEquals(1, heap.extract());
        assertEquals(9, heap.size());
        assertEquals(2, heap.extract());
        assertEquals(8, heap.size());
        assertEquals(2, heap.extract());
        assertEquals(7, heap.size());
        assertEquals(2, heap.extract());
        assertEquals(6, heap.size());
    }

    // testing BinaryMinHeap(List<? extends E> list, Comparator<? super E> cmp)
    @Test
    void cmpListAdd() {
        heap = new BinaryMinHeap<>(List.of(1,2,65,10,2,32),(a,b) -> b - a);
        heap.add(5);
        heap.add(2);
        heap.add(3);
        assertEquals(65, heap.peek());
    }

    @Test
    void cmpListExtract() {
        heap = new BinaryMinHeap<>(List.of(1,2,65,10,32,2),(a,b) -> b - a);
        heap.add(5);
        heap.add(10);
        heap.add(2);
        heap.add(7);
        assertEquals(65, heap.extract());
        assertEquals(9, heap.size());
        assertEquals(32, heap.extract());
        assertEquals(8, heap.size());
        assertEquals(10, heap.extract());
        assertEquals(7, heap.size());
        assertEquals(10, heap.extract());
        assertEquals(6, heap.size());
    }


}