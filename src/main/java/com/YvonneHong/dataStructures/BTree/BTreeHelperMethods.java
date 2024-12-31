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

    private void delete(BTreeNode node, int key) {
        int index = findKey(node, key); 
        if((index < node.numKeys) && (node.keys[index] == key)) {
            if(node.isLeaf) {
                removeFromLeaf(node, index); 
            } else {
                removeFromNonLeaf(node, index); 
            }
        } else {
            if(node.isLeaf) {
                return; 
            }
            boolean flag = (index == node.numKeys) ? true : false; 
            if(node.children[index].numKeys < t) {
                fill(node, index);
            }
            if(flag && index > node.numKeys) {
                delete(node.children[index - 1], key); 
            } else {
                delete(node.children[index], key); 
            }
        }
    }

    private int findKey(BTreeNode node, int key) {
        int index = 0; 
        while((index < node.numKeys) && (node.keys[index] < key)) {
            index++;
        }
        return index;
    }

    private void removeFromLeaf(BTreeNode node, int index) {
        for(int i = index + 1; i < node.numKeys; i++) {
            node.keys[i - 1] = node.keys[i]; 
        }
        node.numKeys--; 
    }

    private void removeFromNonLeaf(BTreeNode node, int index) {
        int key = node.keys[index]; 
        if(node.children[index].numKeys >= t) {
            int pred = getPred(node, index); 
            node.keys[index] = pred; 
            delete(node.children[index], pred);
        } else if(node.children[index + 1].numKeys >= t) {
            int succ = getSucc(node, index);
            node.keys[index] = succ;
            delete(node.children[index + 1], succ); 
        } else {
            merge(node, index); 
            delete(node.children[index], key); 
        }
    }

    private int getPred(BTreeNode node, int index) {
        BTreeNode current = node.children[index]; 
        while(!current.isLeaf) {
            current = current.children[current.numKeys]; 
        }
        return current.keys[current.numKeys - 1]; 
    }

    private int getSucc(BTreeNode node, int index) {
        BTreeNode current = node.children[index + 1]; 
        while(!current.isLeaf) {
            current = current.children[0];
        }
        return current.keys[0]; 
    }

    private void fill(BTreeNode node, int index) {
        
    }
    
}
