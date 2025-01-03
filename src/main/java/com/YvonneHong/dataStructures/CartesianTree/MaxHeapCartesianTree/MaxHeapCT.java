package com.YvonneHong.dataStructures.CartesianTree.MaxHeapCartesianTree;

import com.YvonneHong.dataStructures.CartesianTree.CartesianTreeNode;

public class MaxHeapCT {
    private CartesianTreeNode root;

    // Constructor
    public MaxHeapCT() {
        this.root = null;
    }

    // Insert a new value into the MaxHeap Cartesian Tree
    public void insert(int value) {
        root = MaxHeapCTOps.insertRecursive(root, value);
    }

    // Pop the max value from the heap
    public int popMax() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }
        int max = root.value;
        root = MaxHeapCTOps.removeMax(root);
        return max;
    }

    // Peek at the root value (max value)
    public int peek() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }
        return root.value;
    }

    // Get the size of the heap
    public int size() {
        return MaxHeapCTOps.getSize(root);
    }

    // Delete a specific value from the heap
    public void delete(int value) {
        root = MaxHeapCTOps.deleteRecursive(root, value);
    }


    // Print the tree for debugging
    public void printTree() {
        MaxHeapCTOps.recursivelyPrintTree(root, "", true);
    }

    //search for a node. Check if a specific node/value exists??

    //search for a value (boolean??? or return an int)

}
