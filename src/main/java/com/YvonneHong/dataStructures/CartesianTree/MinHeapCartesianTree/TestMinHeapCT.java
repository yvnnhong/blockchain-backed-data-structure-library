package com.YvonneHong.dataStructures.CartesianTree.MinHeapCartesianTree;

public class TestMinHeapCT {

    public static void main(String[] args) {
        // Create a MinHeapCT instance
        MinHeapCT heap = new MinHeapCT();

        // Test insertions
        System.out.println("Inserting values: 20, 15, 30, 10, 25...");
        heap.insert(20);
        heap.insert(15);
        heap.insert(30);
        heap.insert(10);
        heap.insert(25);

        // Print the tree after insertion
        System.out.println("Heap after insertion:");
        heap.printTree();

        // Test peek (should return the smallest value, which is 10)
        System.out.println("Peek (min value): " + heap.peek());

        // Test popMin (should return and remove the smallest value, which is 10)
        System.out.println("Pop Min: " + heap.popMin());

        // Print the tree after popping the min value
        System.out.println("Heap after popping min value:");
        heap.printTree();

        // Test delete method
        System.out.println("Deleting value 15...");
        heap.delete(15);

        // Print the tree after deletion
        System.out.println("Heap after deleting value 15:");
        heap.printTree();

        // Test size method
        System.out.println("Size of heap: " + heap.size());

        // Test popping all elements to empty the heap
        System.out.println("Popping all elements:");
        while (heap.size() > 0) {
            System.out.println("Pop Min: " + heap.popMin());
            heap.printTree();
        }
    }
}
