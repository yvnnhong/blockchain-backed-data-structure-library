package com.YvonneHong.dataStructures.BPlusTree;
import java.util.ArrayList;
import java.util.List;

    // InternalNode class (non-leaf node)
    public class BPTinternalNode extends BPlusTreeNode {
        private List<BPlusTreeNode> children; 

        public BPTinternalNode(int degree) {
            super(degree); //calls constructor in parent class 
            children = new ArrayList<>();
        }

        //split the child node at the given index
        public void splitChild(int index) {
            BPlusTreeNode child = children.get(index); 
            BPlusTreeNode newChild; 

            if(child instanceof BPTinternalNode) {
                newChild = ((BPTinternalNode) child).split(); 
            } else {
                newChild = ((BPTLeafNode) child).split(); 
            }

            keys.add(index, newChild.keys.get(0));
            children.add(index + 1, newChild);
        }

        //insert a key into this internal node
        @Override
        public void insert(int key, String value) {
            //find the child to insert into 
            int index = findChildIndex(key); 
            BPlusTreeNode child = children.get(index); 

            //if the child node is full, split it first 
            if(child.isFull()) {
                splitChild(index); 
                if(key > keys.get(index)) {
                    index++; 
                }
            }

            //insert the key in the child node 
            child.insert(key, value); 

        }

        //search for a key in this internal node 
        @Override
        public String search(int key) {
            int index = findChildIndex(key); 
            return children.get(index).search(key);
        }

        //find the correct child index for the key
        private int findChildIndex(int key) {
            int index = 0; 
            while((index < keys.size()) && (key >= keys.get(index))) {
                index++; 
            }
            return index;
        }

        //print the internal node for debugging
        @Override
        public void printNode(){
            System.out.print("Internal (non-leaf) Node Keys: "); 
            for(int key: keys){
                System.out.print(key + " "); 
            }
            System.out.println(); 
            for(BPlusTreeNode child: children) {
                child.printNode(); 
            }
        }

      
    }
