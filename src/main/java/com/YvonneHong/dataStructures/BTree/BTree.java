package com.YvonneHong.dataStructures.BTree;

public class BTree {
    public BTreeNode root;
    private int t; //minimum degree (defines the range for number of keys)

    public BTree(int t) {
        this.t = t; 
        root = new BTreeNode(t, true);
    }

    //search operation
    public BTreeNode search(int key) {
        return search(root, key);
    }
    
    //insert operation
    public void insert(int key){
        BTreeNode root = this.root; 
        if(root.numKeys == (2 * t - 1)){
            BTreeNode newNode = new BTreeNode(t, false); 
            newNode.children[0] = this.root; 
            split(newNode, 0);
            root = newNode; 
            this.root = root; 
        }
        insertNonFull(root, key);
    }
}
