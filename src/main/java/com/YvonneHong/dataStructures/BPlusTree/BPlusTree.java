package com.YvonneHong.dataStructures.BPlusTree;

public class BPlusTree {
    private BPTinternalNode root; 
    private int degree; //maximum number of children for internal nodes 

    //constructor 
    public BPlusTree(int degree) {
        this.degree = degree; 
        this.root = new BPTinternalNode(degree);
    }

    //insert a key-value pair into the tree 
    public void insert(int key, String value){
        //if the root node is full, split it 
        if(root.isFull()){
            BPTinternalNode newRoot = new BPTinternalNode(degree); 
            newRoot.addChild(root); 
            newRoot.splitChild(0); 
            root = newRoot; 
        }
        root.insert(key, value);
    }

    //search for a value associated with a key 
    public String search(int key){
        return root.search(key);
    }

    //delete a key from the tree
    public void delete(int key) {
        root.delete(key); 
    }

    //print the tree for debugging purposes 
    public void printTree() {
        root.printNode();
    }

   

}
