package com.YvonneHong.dataStructures.BPlusTree;
import java.util.ArrayList; 
import java.util.List; 

    // Define the base Node class (no need for abstract methods)
    public class BPlusTreeNode {
        protected List<Integer> keys; 
        protected int degree; 

        public BPlusTreeNode(int degree){
            this.degree = degree; 
            this.keys = new ArrayList<>(); 
        }

        //method to check if the node is full 
        public boolean isFull(){
            return keys.size() >= degree; 
        }

        //method to add a key 
        public void addKey(int key){
            keys.add(key); 
        }

        //abstract methods to be implemented by subclasses
        //we need to have abstract methods because we're dealing with both internal nodes 
        //and leaf nodes, which both require different implementations of the below methods/operations
        public void insert(int key, String value){}
        public String search(int key){return null;}

    }
