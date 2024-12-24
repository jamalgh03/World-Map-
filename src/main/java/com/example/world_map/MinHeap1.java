package com.example.world_map;
import java.util.Comparator;
import java.util.LinkedList;

public class MinHeap1<T> {
    private LinkedList<T> heap;
    private Comparator<T> comparator;

    public MinHeap1(Comparator<T> comparator) {
        this.heap = new LinkedList<>();
        this.comparator = comparator;
    }

    public void insert(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T min = heap.getFirst();
        heap.set(0, heap.getLast());
        heap.removeLast();
        heapifyDown(0);

        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallestIndex = index;

        if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = rightChildIndex;
        }

        if (smallestIndex != index) {
            swap(index, smallestIndex);
            heapifyDown(smallestIndex);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

