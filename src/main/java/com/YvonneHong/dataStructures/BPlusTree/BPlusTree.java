package com.YvonneHong.dataStructures.BPlusTree;

public class BPlusTree {
    private BPTinternalNode root; 
    private int degree; //maximum number of children for internal nodes 

    //constructor 
    public BPlusTree(int degree) {
        this.degree = degree; 
        this.root = new BPTinternalNode(degree);
    }

   

}
