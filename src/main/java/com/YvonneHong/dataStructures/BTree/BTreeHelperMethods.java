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
        if (index != 0 && node.children[index - 1].numKeys >= t) {
            borrowFromPrev(node, index);
        } else if (index != node.numKeys && node.children[index + 1].numKeys >= t) {
            borrowFromNext(node, index);
        } else {
            if (index != node.numKeys) {
                merge(node, index);
            } else {
                merge(node, index - 1);
            }
        }
    }

    private void borrowFromPrev(Node node, int index) {
        Node child = node.children[index];
        Node sibling = node.children[index - 1];

        for (int i = child.numKeys - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }
        if (!child.isLeaf) {
            for (int i = child.numKeys; i >= 0; i--) {
                child.children[i + 1] = child.children[i];
            }
        }
        child.keys[0] = node.keys[index - 1];
        if (!node.isLeaf) {
            child.children[0] = sibling.children[sibling.numKeys];
        }
        node.keys[index - 1] = sibling.keys[sibling.numKeys - 1];

        sibling.numKeys--;
        child.numKeys++;
    }

    private void borrowFromNext(Node node, int index) {
        Node child = node.children[index];
        Node sibling = node.children[index + 1];

        child.keys[child.numKeys] = node.keys[index];
        if (!child.isLeaf) {
            child.children[child.numKeys + 1] = sibling.children[0];
        }
        node.keys[index] = sibling.keys[0];

        for (int i = 1; i < sibling.numKeys; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
        }
        if (!sibling.isLeaf) {
            for (int i = 1; i <= sibling.numKeys; i++) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }

        sibling.numKeys--;
        child.numKeys++;
    }

    private void merge(Node node, int index) {
        Node child = node.children[index];
        Node sibling = node.children[index + 1];

        child.keys[t - 1] = node.keys[index];
        for (int i = 0; i < sibling.numKeys; i++) {
            child.keys[i + t] = sibling.keys[i];
        }
        if (!child.isLeaf) {
            for (int i = 0; i <= sibling.numKeys; i++) {
                child.children[i + t] = sibling.children[i];
            }
        }

        for (int i = index + 1; i < node.numKeys; i++) {
            node.keys[i - 1] = node.keys[i];
        }
        for (int i = index + 2; i <= node.numKeys; i++) {
            node.children[i - 1] = node.children[i];
        }

        node.numKeys--;
        child.numKeys += sibling.numKeys + 1;
    }

    private void display(BTreeNode node, String indent, boolean last) {
        System.out.println(indent + "+- " + (last ? "-" : "|") + " " + node);
        indent += last ? "   " : "|  ";
        for (int i = 0; i < node.numKeys; i++) {
            if (!node.isLeaf) {
                display(node.children[i], indent, i == node.numKeys - 1);
            }
        }
    }
    
}
