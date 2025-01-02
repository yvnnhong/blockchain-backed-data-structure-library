package com.YvonneHong.dataStructures.BPlusTree;
import java.util.ArrayList;
import java.util.List; 

// LeafNode class (stores data)
public class BPTLeafNode extends BPlusTreeNode {
    private List<String> values; 
    private BPTLeafNode next; 
    private BPTLeafNode prev; 

    public BPTLeafNode(int degree) {
        super(degree); 
        values = new ArrayList<>(); 
        next = null; 
        prev = null; 
    }

    //split the leaf node when it is full 
    protected BPTLeafNode split() {
        int midIndex = keys.size() / 2;
        BPTLeafNode newLeaf = new BPTLeafNode(degree); 

        //move half of the keys and values to the new leaf node 
        for(int i = midIndex; i < keys.size(); i++) {
            newLeaf.addKey(keys.get(i)); 
            newLeaf.addValue(values.get(i)); 
        }

        //remove the moved keys and values from the current leaf 
        for(int i = midIndex; i < keys.size(); i++) {
            keys.remove(i); 
            values.remove(i); 
        }

        //link the old and new leaf nodes 
        newLeaf.prev = this; 
        newLeaf.next = this.next; 
        if(this.next != null) {
            this.next.prev = newLeaf; //??
        }
        this.next = newLeaf; 

        return newLeaf; 
    }

    //add a key-value pair to this leaf node 
    protected void addKey(int key){
        keys.add(key); 
    }

    //add a value to this leaf node 
    protected void addValue(String value) {
        values.add(value); 
    }

    //insert a key-value pair into this leaf node 
    @Override 
    protected void insert(int key, String value){
        int index = 0; 
        while((index < keys.size()) && (keys.get(index) < key)){
            index++; 
        }
        keys.add(index, key);
        values.add(index, value); 
    }

    //search for a value by key in this leaf node 
    @Override
    protected String search(int key) {
        int index = keys.indexOf(key); 
        if(index >= 0) {
            return values.get(index); 
        }
        return null; 
    }

    //print the leaf node for debugging 
    @Override 
    protected void printNode() { //change name to printNodeKeys instead ? 
        System.out.print("Leaf Node Keys: ");
        for(int key : keys) {
            System.out.print(key + " "); 
        }
        System.out.println();
    }
    

  
}
