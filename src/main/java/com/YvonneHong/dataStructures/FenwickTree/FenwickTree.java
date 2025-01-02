package com.YvonneHong.dataStructures.FenwickTree;

public class FenwickTree {
    private int[] tree; 
    private int size; 

    //constructor to initialize Fenwick tree
    public FenwickTree(int size){
        this.size = size; 
        this.tree = new int[size + 1]; // fenwick tree is 1-indexed
    }

    //method to build the Fenwick tree from an array (bulk load)
    public void build(int[] data) {
        for(int i = 0; i < data.length; i++){
            this.update(i + 1, data[i]); // update the tree starting from index 1
        }
    }

    //method to update the Fenwick tree with a value at index 'idx'
    public void update(int idx, int delta) {
        while(idx <= size) {
            tree[idx] += delta; 
            idx += idx & -idx; //move to parent index 
        }
    }

    //method to query the prefix sum from index 1 to idx 
    public int query(int idx) {
        int sum = 0; 
        while(idx > 0) {
            sum += tree[idx]; 
            idx -= idx & -idx; //move to parent index 
        }
        return sum; 
    }

    //method to perform a range query (query from range [left, right])
    public int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    //method to insert a value at index idx (this is the same as 'update' method)
    public void insert(int idx, int value) {
        update(idx, value); 
    }

    // Method to delete (subtract) a value at index idx
    public void delete(int idx, int value) {
        update(idx, -value);
    }

    // A utility method to print the Fenwick Tree (for debugging)
    public void printTree() {
        for (int i = 1; i <= size; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }
    
}
