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
        return BTreeHelperMethods.searchRecursive(root, key);
    }
    
    //insert operation
    public void insert(int key){
        BTreeNode root = this.root; 
        if(root.numKeys == (2 * t - 1)){
            BTreeNode newNode = new BTreeNode(t, false); 
            newNode.children[0] = this.root; 
            BTreeHelperMethods.split(newNode, 0);
            root = newNode; 
            this.root = root; 
        }
        BTreeHelperMethods.insertNonFull(root, key);
    }

    // Delete operation
    public void delete(int key) {
        BTreeHelperMethods.deleteRecursive(root, key);
        if (root.numKeys == 0 && !root.isLeaf) {
            root = root.children[0];
        }
    }

    // Display method (in-order traversal)
    public void display() {
        BTreeHelperMethods.displayRecursive(root, "", true);
    }


}
