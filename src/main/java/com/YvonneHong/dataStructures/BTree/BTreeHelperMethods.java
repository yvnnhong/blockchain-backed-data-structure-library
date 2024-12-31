package com.YvonneHong.dataStructures.BTree;

public class BTreeHelperMethods {

    private int t; //minimum degree (defines the range for number of keys)

    private BTreeNode search(BTreeNode node, int key) {
        int i = 0; 
        while((i < node.numKeys) && (key > node.keys[i])) {
            i++;
        }
        if((i < node.numKeys) && (key == node.keys[i])) {
            return node; //we have found the key 
        }
        if(node.isLeaf) {
            return null; //key is not found in the leaf node 
        }
        return search(node.children[i], key); //recurse on the child 
    }

    private void insertNonFull(BTreeNode node, int key) {
        int i = node.numKeys - 1;
        if(node.isLeaf) {
            while((i >= 0) && (key < node.keys[i])) {
                node.keys[i + 1] = node.keys[i]; 
                i--;
            }
            node.keys[i + 1] = key; 
            node.numKeys++;
        } else {
            while((i>=0) && (key < node.keys[i])) {
                i--; 
            }
            i++;
            if(node.children[i].numKeys == ((2 * t) - 1)) {
                split(node, i); 
                if(key > node.keys[i]) {
                    i++; 
                }
            }
            insertNonFull(node.children[i], key);
        }
    }

    //split operation to split a full child node 
    private void split(BTreeNode parent, int i) {
        BTreeNode fullChild = parent.children[i];
        BTreeNode newChild = new BTreeNode(t, fullChild.isLeaf); 

        parent.children[i + 1] = newChild; 
        parent.keys[i] = fullChild.keys[t - 1]; 
        parent.numKeys++; 

        for(int j = 0; j < t - 1; j++) {
            newChild.keys[j] = fullChild.keys[j + t]; 
        }

        if(!fullChild.isLeaf) {
            for(int j = 0; j < t; j++) {
                newChild.children[j] = fullChild.children[j + t]; 
            }
        }

        fullChild.numKeys = t - 1; 
    }
    
}
