package com.YvonneHong.dataStructures.BTree;

public class BTreeNode {
    int[] keys; 
    BTreeNode[] children;
    int numKeys; 
    boolean isLeaf; 

    public BTreeNode(int t, boolean isLeaf) {
        this.isLeaf = isLeaf; 
        this.keys = new int[(2 * t) - 1]; //maximum number of keys 
        this.children = new BTreeNode[2 * t]; //maximum number of children 
        this.numKeys = 0; 
    }
    
}
